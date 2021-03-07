import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.io.*;
import java.util.*;

public class Model implements Serializable {
    private Character[][] gameMatrix;
    private Stack<Character[][]> states;
    private int playgroundWidth;
    private int playgroundHeight;
    private int time;
    private int lvl;

    private final String PATH_TO_LEVELS = "src/mase";
    private final int WIN = 1;
    private final int LOOSE = 2;
    private final int PLAY = 3;

    //pripomenutie serializacie aka zdroj na https://www.tutorialspoint.com/java/java_serialization.htm
    public boolean save() {
        try {
            FileOutputStream outFile = new FileOutputStream("log.ser");
            ObjectOutputStream out = new ObjectOutputStream(outFile);
            out.writeObject(this);
            out.close();
            outFile.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static Model load() {
        Model loaded = null;
        try {
            FileInputStream fileIn = new FileInputStream("log.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loaded = (Model) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("IO exc");
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("You didnt save anything");
            return null;
        }
        return loaded;
    }

    public Character[][] getGameMatrix() {
        return gameMatrix;
    }

    public int getPlaygroundHeight() {
        return playgroundHeight;
    }

    public int getPlaygroundWidth() {
        return playgroundWidth;
    }

    public Model(int _time, Label timeLabel) {
        lvl = 1;
        gameMatrix = loadLevel();
        time = _time;
        states = new Stack<>();
    }

    private Character[][] transformToArray(List<List<Character>> list) {
        Character[][] array = new Character[list.size()][list.size()];
        Iterator<List<Character>> rows = list.iterator();
        int i = 0;
        while(rows.hasNext()) {
            Iterator<Character> columnsOfSingleRow = rows.next().iterator();
            int j = 0;
            while(columnsOfSingleRow.hasNext()) {
                array[i][j] = columnsOfSingleRow.next();
                j++;
            }
            i++;
        }
        return array;
    }

    public void lvlup() {
        lvl++;
    }

    public void restartLVL() {
        lvl = 1;
    }

    public Character[][] loadLevel() {
        File file = new File(PATH_TO_LEVELS + lvl + ".txt");
        List<List<Character>> newMatrix = new LinkedList<>();
        playgroundWidth = 0;
        Character matrix[][] = null;
        try {
            Scanner scanner = new Scanner(file);
            playgroundHeight = scanner.nextInt();
            playgroundWidth = scanner.nextInt();
            scanner.nextLine();
            matrix = new Character[playgroundHeight][playgroundWidth];
            System.out.println(playgroundHeight + ", " + playgroundWidth);
            for(int i=0;i<playgroundHeight;i++) {
                String line = scanner.nextLine();
                for(int j=0;j<playgroundWidth;j++) {
                    matrix[i][j] = line.charAt(j);
                }
            }

        } catch (Exception e) {
            System.out.println("You passed by all levels, try it again");
            restartLVL();
            return loadLevel();
        }
        return matrix;
    }
    int time0 = 0;

    public void manageTime(int timeLimit, Label timeLabel, View c) {
        Timer timer = new Timer();
        final int[] time = {timeLimit};
        time0 = timeLimit;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (time0 == 0) { //ak cas uplynie
                    System.out.println("Koniec hry");
                    c.setOnMouseClicked(e -> {
                        System.out.println("Game over");
                    });
                    timer.cancel();
                    timer.purge();
                    return;
                } else {
                    time0 -= 1;
                    Platform.runLater(() -> timeLabel.setText("     Zostavajuci cas: " + String.valueOf(time0)));
                }
            }
        },1000,1000);
    }

    private boolean isInGame(int i, int j) {
        if(i<0 || j<0 || i>=playgroundHeight || j>=playgroundWidth) {
            return false;
        }
        return true;
    }

    private int deleteNeighbours(int row, int column, Character x) {
        if(isInGame(row, column) && x == gameMatrix[row][column]) {
            gameMatrix[row][column] = '0';
            return deleteNeighbours(row-1,column,x) +
                    deleteNeighbours(row+1,column,x) +
                    deleteNeighbours(row,column+1,x) +
                    deleteNeighbours(row,column-1,x) + 1;
        }
        return 0;
    }

    private Position findZero() {
        for(int i=0;i<playgroundHeight;i++) {
            for(int j=0;j<playgroundWidth;j++) {
                if(gameMatrix[i][j] == '0') {
                    return new Position(i,j);
                }
            }
        }
        return null;
    }

    private int countZeroesUp(int row, int column) {
        int count = 0;
        int i = row;
        while(i>=0 && gameMatrix[i][column] == '0') {
            count++;
            i--;
        }
        return count;
    }

    private void fall(int row, int column) {
        int moves = countZeroesUp(row, column);
        int i = row;
        while(i-moves >=0) {
            gameMatrix[i][column] = gameMatrix[i-moves][column];
            i--;
        }
        while(i>=0) {
            gameMatrix[i][column] = '1';
            i--;
        }
    }

    private int findZeroColumn() {
        boolean zeroes = true;
        for(int i=0;i<playgroundWidth;i++) {
            for(int j=0;j<playgroundHeight;j++) {
                if(gameMatrix[j][i] != '0' && gameMatrix[j][i] != '1') {
                    zeroes = false;
                    break;
                }
            }
            if(zeroes) {return i;}
            zeroes = true;
        }
        return -1;
    }

    private void shiftLeft(int column) {
        column++;
        while(column < playgroundWidth) {
            for(int i=0;i<playgroundHeight;i++) {
                gameMatrix[i][column-1] = gameMatrix[i][column];
            }
            column++;
        }
        column--;
        for(int i=0;i<playgroundHeight;i++) {
            gameMatrix[i][column] = '2';
        }
    }

    private void fixGameMatrix() {
        Position zero = findZero();
        while(zero != null) {
            fall(zero.getRow(), zero.getColumn());
            int emptyColumn = findZeroColumn();
            if(emptyColumn > -1) {
                shiftLeft(emptyColumn);
            }
            zero = findZero();
        }
    }

    public boolean clicked(int i, int j) throws WinException, LooseException {
        Character current = gameMatrix[i][j];
        states.push(getNewMatrix());
        int deleted = deleteNeighbours(i,j,current);
        if(deleted == 1) {
            deleted--;
            gameMatrix[i][j] = current;
            if(!isMovePossible()) {
                restartLVL();
                gameMatrix = loadLevel();
                throw new LooseException();
            }
        }
        else {
            fixGameMatrix();
            int state = checkState();
            if(state == WIN) {
                lvlup();
                gameMatrix = loadLevel();
                throw new WinException();
            }
            else if(state == LOOSE) {
                restartLVL();
                gameMatrix = loadLevel();
                throw new LooseException();
            }
        }
        System.out.println(deleted);
        return true;
    }

    public void undo() {
        if(!states.empty()) {
            gameMatrix = states.pop();
        }
        //potom este treba v canvas zavolat paint. N
    }

    private Character[][] getNewMatrix() {
    Character[][] newMatrix = new Character[playgroundHeight][playgroundWidth];
        for(int i=0;i<playgroundHeight;i++) {
            for(int j=0;j<playgroundWidth;j++) {
                newMatrix[i][j] = gameMatrix[i][j];
            }
        }
    return newMatrix;
    }

    private int checkState() {
        if(isAllSolved()) {
            return WIN;
        }
        if(isMovePossible()) {
            return PLAY;
        }
        return LOOSE;
    }

    private boolean isAllSolved() {
        for(int i=0;i<playgroundHeight;i++) {
            for(int j=0;j<playgroundWidth;j++) {
                Character check = gameMatrix[i][j];
                if(check == 'A' || check == 'B' || check == 'C' || check == 'D') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isMovePossible() {

        for(int i=0;i<playgroundHeight;i++) {
            for(int j=0;j<playgroundWidth;j++) {
                Character check = gameMatrix[i][j];
                if(check == '0' || check == '1' || check == '2') {
                    continue;
                }
                if(hasSameNeighbours(i,j,check)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasSameNeighbours(int row, int column, Character x) {
        if(isInGame(row-1,column) && gameMatrix[row-1][column] == x) {return true;}
        if(isInGame(row+1,column) && gameMatrix[row+1][column] == x) {return true;}
        if(isInGame(row,column+1) && gameMatrix[row][column+1] == x) {return true;}
        if(isInGame(row,column-1) && gameMatrix[row][column-1] == x) {return true;}
        return false;
    }
}
