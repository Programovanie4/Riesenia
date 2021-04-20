import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ziak {
	private String name;
	private int age;

	public Ziak() {
		super();
	}

	public Ziak(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static int compareByNameThenAge(final Ziak lhs, final Ziak rhs) {
		if (lhs.name.equals(rhs.name)) {
			return lhs.age - rhs.age;
		} else {
			return lhs.name.compareTo(rhs.name);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Ziak other = (Ziak) obj;
		if (age != other.age) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Ziak [name=").append(name).append(", age=").append(age).append("]");
		return builder.toString();
	}

	public static void main(String[] args) {
		{  // priklad triedenie zoznamu ziakov 
			List<Ziak> trieda = Arrays.asList(new Ziak[] { 
					new Ziak("Marienka", 10), new Ziak("Janko", 12)});
			Collections.sort(trieda, new Comparator<Ziak>() {
				@Override
				public int compare(Ziak h1, Ziak h2) {
					return h1.getName().compareTo(h2.getName());
				}
			});
			if (trieda.get(0).equals(new Ziak("Janko", 12))) {
				System.out.println("1) je to Janko");
			} else {
				System.out.println("1) nie je to Janko");
			}
		}

		{ // priklad triedenie zoznamu ziakov, kratsi zapis
			List<Ziak> trieda = Arrays.asList(new Ziak[] { 
					new Ziak("Marienka", 10), new Ziak("Janko", 12)});
			trieda.sort((Ziak h1, Ziak h2) -> h1.getName().compareTo(h2.getName()));
			if (trieda.get(0).equals(new Ziak("Janko", 12))) {
				System.out.println("2) je to Janko");
			} else {
				System.out.println("2) nie je to Janko");
			}
		}

		{ // priklad triedenie zoznamu ziakov, este kratsi zapis
			List<Ziak> trieda = Arrays.asList(new Ziak[] { 
					new Ziak("Marienka", 10), new Ziak("Janko", 12)});
			trieda.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));
			if (trieda.get(0).equals(new Ziak("Janko", 12))) {
				System.out.println("3) je to Janko");
			} else {
				System.out.println("3) nie je to Janko");
			}
		}

		{ // priklad triedenie zoznamu ziakov podla definovaneho usporiadania
			List<Ziak> trieda = Arrays.asList(new Ziak[] { 
					new Ziak("Marienka", 10), new Ziak("Janko", 12)});
			trieda.sort(Ziak::compareByNameThenAge);
			if (trieda.get(0).equals(new Ziak("Janko", 12))) {
				System.out.println("4) je to Janko");
			} else {
				System.out.println("4) nie je to Janko");
			}
		}
		{ // priklad triedenie zoznamu ziakov podla mena
			List<Ziak> trieda = Arrays.asList(new Ziak[] { 
					new Ziak("Marienka", 10), new Ziak("Janko", 12)});
			Collections.sort(trieda, Comparator.comparing(Ziak::getName));

			if (trieda.get(0).equals(new Ziak("Janko", 12))) {
				System.out.println("5) je to Janko");
			} else {
				System.out.println("5) nie je to Janko");
			}
		}
		{ // priklad triedenie zoznamu ziakov podla mena, ale opacne
			List<Ziak> trieda = Arrays.asList(new Ziak[] { 
					new Ziak("Marienka", 10), new Ziak("Janko", 12)});
			Comparator<Ziak> comparator = (h1, h2) -> h1.getName().compareTo(h2.getName());
			trieda.sort(comparator.reversed());

			if (trieda.get(0).equals(new Ziak("Marienka", 10))) {
				System.out.println("6) je to Marienka");
			} else {
				System.out.println("6) nie je to Marienka");
			}
		}

		{ // priklad triedenie zoznamu ziakov podla mena, ale opacne
			List<Ziak> trieda = Arrays.asList(new Ziak[] { 
					new Ziak("Marienka", 10), new Ziak("Janko", 12)});
			trieda.sort((lhs, rhs) -> {
				if (lhs.getName().equals(rhs.getName())) {
					return lhs.getAge() - rhs.getAge();
				} else {
					return lhs.getName().compareTo(rhs.getName());
				}
			});

			if (trieda.get(0).equals(new Ziak("Marienka", 10))) {
				System.out.println("7) je to Marienka");
			} else {
				System.out.println("7) nie je to Marienka");
			}
		}

		{ // priklad triedenie zoznamu ziakov podla mena, potom podla veku
			List<Ziak> trieda = Arrays.asList(new Ziak[] { 
					new Ziak("Marienka", 10), new Ziak("Janko", 12)});
			trieda.sort(Comparator.comparing(Ziak::getName).thenComparing(Ziak::getAge));

			if (trieda.get(0).equals(new Ziak("Marienka", 10))) {
				System.out.println("8) je to Marienka");
			} else {
				System.out.println("8) nie je to Marienka");
			}
		}
		{ // priklad triedenie zoznamu ziakov podla veku, potom podla mena
			List<Ziak> trieda = Arrays.asList(new Ziak[] { 
					new Ziak("Marienka", 10), new Ziak("Janko", 12)});
			trieda.sort(Comparator.comparing(Ziak::getAge).thenComparing(Ziak::getName));

			if (trieda.get(0).equals(new Ziak("Marienka", 10))) {
				System.out.println("9) je to Marienka");
			} else {
				System.out.println("9) nie je to Marienka");
			}
		}
	}
}