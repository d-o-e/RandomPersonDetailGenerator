public class Main {

	public static void main(String[] args) {
		Generator randomGenerator= new Generator("Census.csv");

		for (int i = 0; i < 1000; i++) {
			System.out.println(randomGenerator.nextPersonDetails());
		}

	}
}