import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Generator {
	private static final int SIZE = 1000;
	Random random = new Random();
	private final Set<String> generatedNames = new HashSet<>(SIZE);
	private final Set<Integer> generatedSSNS = new HashSet<>(SIZE);
	private final Deque<Person> generatedPeople = new ArrayDeque<>(SIZE);
	private final String[] lastnames = new String[SIZE];
	private final ArrayList<String> maleNames = new ArrayList<>(100);
	private final ArrayList<String> femaleNames = new ArrayList<>(100);

	public Generator(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			scanner.nextLine(); // skipping headers
			int count = 0;
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] line = data.split(",");
				lastnames[count] = line[0];
				maleNames.add(line[1]);
				femaleNames.add(line[2]);
				count++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		generateLists();
		populatePeopleList();

	}

	private void populatePeopleList() {
		Iterator<String> names = generatedNames.iterator();
		Iterator<Integer> ssn = generatedSSNS.iterator();
		while (names.hasNext() && ssn.hasNext()) {
			generatedPeople.add(new Person(names.next(), ssn.next()));
		}
	}

	private void generateLists() {
		String randomName;
		int ssn;
		for (int i = 0; i < SIZE; i++) {
			randomName = generateFullnames();
			while (!generatedNames.add(randomName)) randomName = generateFullnames();
			ssn = generateSSN();
			while (!generatedSSNS.add(ssn)) ssn = generateSSN();
		}

	}

	private String generateFullnames() {
		String lastname = lastnames[random.nextInt(SIZE)];
		String firstname = random.nextInt(2) == 1 ?
			                   maleNames.get(random.nextInt(maleNames.size()))
			                   : femaleNames.get(random.nextInt(femaleNames.size()));
		return lastname + ", " + firstname;
	}

	public Person nextPersonDetails() {
		return generatedPeople.isEmpty() ? null : generatedPeople.remove();
	}

	private int generateSSN() {
		return (random.nextInt(900000000) + 100000000);
	}

	public static void main(String[] args) {
		Generator randomGenerator= new Generator("Census.csv");

		for (int i = 0; i < 1000; i++) {
			System.out.println(randomGenerator.nextPersonDetails());
		}

	}
}


class Person {
	String fullName;
	int ssn;

	public Person(String fullName, int ssn) {
		this.fullName = fullName;
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "Name : " + fullName + "  SSN :" + ssn;
	}
}