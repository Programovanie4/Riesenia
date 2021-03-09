public class RegDate {
    static String RegExpDate(){
        return "" +
                "^((((([13579][26])|([02468][048]))00)|([0-9]{2}(0[48]|[2468][048]|[13579][26])))-02-29)$"+ //priestupe roky februar
                "|^(([0-9]{4})-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))$"+ //31 dni mesiace
                "|^(([0-9]{4})-02-(0[1-9]|1[0-9]|2[0-8]))$"+//februar
                "|^(([0-9]{4})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";//30 dni mesiace
    }

    public static void main(String[] args) {
        System.out.println(RegExpDate());
    }
}