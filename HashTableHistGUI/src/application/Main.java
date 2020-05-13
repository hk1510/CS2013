package application;



public class Main{
	public static void main(String[] args) {
//		HashTable table = new HashTable();
//		System.out.println(table);
//		table.add(new Entry("Hello"));
//		System.out.println(table);
//		table.add(new Entry("Hello"));
//		System.out.println(table);
//		table.add(new Entry("broo"));
//		System.out.println(table);
//		table.add(new Entry("bruh"));
//		System.out.println(table);
//		table.add(new Entry("woah"));
//		System.out.println(table);
		System.out.println(Math.abs(2147483647 + 1));
		String bruh = "bruh;alkjf;ask;alskdjf;asldkfjalj";
		System.out.println(bruh.hashCode());
		System.out.println(new Entry(bruh).hashCode());
	}
}
 