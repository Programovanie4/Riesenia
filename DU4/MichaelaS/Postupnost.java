
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

abstract class Postupnost implements Iterovatelne{
	protected long prvy;
	protected long aktualny;

	public long prvy() {
		aktualny = prvy;
		return aktualny;
	}

	public abstract void reset();
	public abstract long dalsi();
	public abstract long dalsiParny();

	public void printPostupnost(int n) {
		System.out.print(prvy());
		for(int i = 0; i < n; i++){
			System.out.print(", " + dalsi());
		}
		System.out.println();
	}

	public static void main(String[] args) {
//		Postupnost ap = new AritmetickaPostupnost(10, 3);
//		Postupnost fp = new FibonaccihoPostupnost(0, 1);
////
//		BinaryTree t = new BinaryTree();
//		t.insert(44l).insert(33l).insert(11l).insert(22l).insert(66l).insert(55l);
//
//		System.out.println(t);      // vypíše (((., 11, (., 22, .)), 33, .), 44, ((., 55, .), 66, .))
//		for(long n = t.dalsi(); n != Long.MAX_VALUE; n = t.dalsi()){
//			System.out.println("--------------->" + n);  // vypíše 11, 22, 33, 44, 55, 66
//		}
//		System.out.println("====================================");
//		t.reset();      // nevracia nič, iba resetuje na začiatok
//		System.out.println("--------------->" + t.dalsi());      // vráti 11
//		System.out.println("--------------->" + t.dalsi());      // vráti 22
//		t.insert(15l);  // vkladáme za pozíciu iterátora
//		System.out.println("--------------->" + t.dalsi());      // vráti 33
//		System.out.println("--------------->" + t.dalsi());      // vráti 44
//		t.insert(45l);  // vkladáme pred pozíciu iterátora
//		t.insert(46l);  // vkladáme pred pozíciu iterátora
//		t.insert(47l);  // vkladáme pred pozíciu iterátora
//		System.out.println("--------------->" + t.dalsi());      // vráti 45
//		System.out.println("--------------->" + t.dalsi());      // vráti 46
//		System.out.println("--------------->" + t.dalsi());      // vráti 47
//		System.out.println("--------------->" + t.dalsi());      // vráti 55
//		t.insert(57l).insert(77l).insert(87l).insert(97l); // nejaké nepárne čísla
//		System.out.println("--------------->" + t.dalsiParny()); // vráti 66
//		System.out.println("--------------->" + t.dalsiParny()); // vráti 9223372036854775807 == Long.MAX_VALUE
//
//		BinaryTree t2 = new BinaryTree();  // .insert(50l).insert(51l).insert(52l).insert(53l).insert(92l);
//		t2.insert(32l).insert(20l).insert(28l).insert(29l).insert(38l).insert(50l).insert(51l).insert(92l).insert(53l).insert(52l);
//		System.out.println(t2);      // vypíše ((., 20, (., 28, (., 29, .))), 32, (., 38, (., 50, (., 51, (((., 52, .), 53, .), 92, .)))))
//		System.out.println("--------------->" + t2.dalsi());
//		System.out.println("--------------->" + t2.dalsi());
//		t2.insert(-31l);
//		t2.insert(101l);
//		System.out.println("--------------->" + t2.dalsi());
//		t2.insert(-12l);
//		System.out.println("--------------->" + t2.dalsi());
//		System.out.println("--------------->" + t2.dalsi());
//		System.out.println("--------------->" + t2.dalsi());
//		System.out.println("--------------->" + t2.dalsi());
//		System.out.println("--------------->" + t2.dalsi());
//		System.out.println("--------------->" + t2.dalsi());
//		System.out.println("--------------->" + t2.dalsi());
//		System.out.println("--------------->" + t2.dalsi());
//		System.out.println("--------------->" + t2.dalsi());

		TreeSet<Long> set = new TreeSet<Long>();
		Random rand = new Random();
		BinaryTree tree = new BinaryTree();
		for(int i = 0; i < 100; i++) {
			long n = rand.nextInt(100);
			tree.insert(n);
			set.add(n);
		}
		int j = 0;
		for(Iterator<Long> it = set.iterator(); it.hasNext();) {
			long n = it.next();
			System.out.println(n + " == " + tree.dalsi());
			j++;
			if(j % 10 == 0) {
				long newNum = rand.nextInt((int) n);
				tree.insert(newNum);
				System.out.println("current: "+n+" new: "+newNum);
			}
		}


//		for(long n = t2.dalsi(); n != Long.MAX_VALUE; n = t2.dalsi()){
//			System.out.println("--------------->" + n);
//		}

	}
}
