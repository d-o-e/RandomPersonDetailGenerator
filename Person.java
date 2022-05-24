public class Person {
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