/*
 * author PB
 */

public class Morse {

	public static String decode(String code) {
		String[] words = code.split("   ");
		String res = "";
		for(String word:words) {
			String[] letters = word.split(" ");
			for(String letter:letters) {
				switch(letter) {
				case ".-":		res += "A"; break;
				case "-...":	res += "B"  ; break;
				case "-.-.":	res += "C"  ; break;
				case "-..":		res += "D"  ; break;
				case ".":		res += "E"  ; break;
				case "..-.":	res += "F"  ; break;
				case "--.":		res += "G"  ; break;
				case "....":	res += "H"  ; break;
				case "..":		res += "I"  ; break;
				case ".---":	res += "J"  ; break;
				case "-.-":		res += "K"  ; break;
				case ".-..":	res += "L"  ; break;
				case "--":		res += "M"  ; break;
				case "-.":		res += "N"  ; break;
				case "---":		res += "O"  ; break;
				case ".--.":	res += "P"  ; break;
				case "--.-":	res += "Q"  ; break;
				case ".-.":		res += "R"  ; break;
				case "...":		res += "S"  ; break;
				case "-":		res += "T"  ; break;
				case "..-":		res += "U"  ; break;
				case "...-":	res += "V"  ; break;
				case ".--":		res += "W"  ; break;
				case "-..-":	res += "X"  ; break;
				case "-.--":	res += "Y"  ; break;
				case "--..":	res += "Z" ; break;
				}
			}
			res += " ";
		}
		return res;
	}

}
