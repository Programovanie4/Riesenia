import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.time.temporal.ChronoUnit.SECONDS;

public class Bezec {
    private final String idBezca;
    public Node start;
    public Map<String, LocalTime> casy;
    //Posledne navstevene stanoviste
    public String last = null;

    public Bezec(String idBezca){
        casy = new HashMap<>();
        this.idBezca = idBezca;
    }
    public String getId(){
        return idBezca;
    }

    public void append(Node next){
        if(start == null) {
            start = next;
            return;
        }
        var ptr = start;
        while(ptr.getNext() != null){
            ptr = ptr.getNext();
        }
        ptr.setNext(next);
    }

    public void vypocet(List<String> stanovista){
        casy = new HashMap<>();
        Node ptr = start;
        last = null;
        int index = 0;
        while(ptr != null){
            if(ptr.getStan().equals(stanovista.get(index))){
                casy.put(ptr.getStan(), ptr.getCas().toLocalTime());
                last = ptr.getStan();
                index++;
            }
            ptr = ptr.getNext();
        }
        premapovatCasy(stanovista);
    }


    private void premapovatCasy(List<String> stanovista){
        String start = stanovista.get(0);
        LocalTime casStartu = casy.get(start);
        if(casStartu == null) return;
        for(String s: stanovista){
            if(casy.get(s) == null) return;
            casy.put(s, LocalTime.ofSecondOfDay(casStartu.until(casy.get(s), SECONDS)));
        }
    }
}


class Node{
    private final String stanoviste;
    private final LocalDateTime cas;
    private Node next = null;

    public Node(String stanoviste, LocalDateTime cas){
        this.stanoviste = stanoviste;
        this.cas = cas;
    }
    public String getStan(){
        return stanoviste;
    }
    public LocalDateTime getCas(){
        return cas;
    }
    public Node getNext(){return next;}
    public void setNext(Node next){this.next=next;}
}
