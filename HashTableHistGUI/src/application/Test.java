package application;

import java.time.Duration;
import java.time.Instant;

public class Test {
	public static void main(String[] args) {
		Instant start = Instant.now();
		SimpleList list = HashTableTest.generateSimpleList();
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end));
		System.out.println(list);
	}
}
