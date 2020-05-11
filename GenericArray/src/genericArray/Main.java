package genericArray;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		
		//Test with Integer Array
		System.out.println("Testing with Integer array: [1, 2, 3, 4, 5]");
		System.out.println();
		//Initialize array with 1, 2, 3, 4, 5
		System.out.println("Initializing array with [1, 2, 3, 4, 5]");
		MyArray<Integer> intArray = new MyArray<>(1,2,3,4,5);
		//Print out array to show toString() functionality
		System.out.println("Current state of array: " + intArray);
		System.out.println();
		
		
		//Demonstration of get()
		for(int i = 0; i < intArray.size(); i++) {
			System.out.println("get(" + i + "): " + intArray.get(i));
		}
		System.out.print("get(1,3): ");
		System.out.println(intArray.get(1, 3));
		
		//Demonstration of thrown exceptions for get()
		try {
			intArray.get(-1);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException thrown and successfully caught!");
		}
		try {
			intArray.get(1, 5);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException thrown and successfully caught!");
		}
		System.out.println();
		
		
		//Demonstration of put()
		intArray.put(4, 2);
		System.out.println("put(4, 2): " + intArray);
		intArray.put(1, 6);
		System.out.println("put(1, 6): " + intArray);
		intArray.put(0, 7);
		System.out.println("put(0, 7): " + intArray);
		
		//Use put to reinput old values into array manually
		intArray.put(0, 4, 1, 2, 3, 4, 5);
		System.out.println("put(0, 4, 1, 2, 3, 4, 5): " + intArray);
		
		//Demonstration of thrown exceptions for put()
		try {
			intArray.put(9, 1);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException thrown and successfully caught!");
		}
		try {
			intArray.put(0, 1, 1, 2, 3, 4, 5);
		}
		catch(TooManyValuesException e) {
			System.out.println("TooManyValuesException thrown and successfully caught!");
		}
		try {
			intArray.put(0, 4, 1, 2);
		}
		catch(TooFewValuesException e) {
			System.out.println("TooFewValuesException thrown and successfully caught!");
		}
		System.out.println();
		
		//Print array again to see its current state (Demonstrating toString())
		System.out.println("Current state of array: " + intArray);
		System.out.println();
		
		//Demonstration of equals()
		System.out.println("Compare current array with a new MyArray with the same objects: ");
		System.out.println(intArray.equals(new MyArray(1,2,3,4,5)));
		System.out.println("Compare current array with a new MyArray with different objects: ");
		System.out.println(intArray.equals(new MyArray(1,5,3,2,1)));
		System.out.println();
		
		//Demonstration of max() and min()
		System.out.println("Max: " + intArray.max());
		System.out.println("Min: " + intArray.min());
		System.out.println();
		
		//Demonstrate reverse()
		intArray.reverse();
		System.out.println("reverse: " + intArray);
		intArray.reverse();
		System.out.println("reverse again:" + intArray);
		System.out.println();
		
		//Demonstrate shuffle()
		System.out.print("Array shuffled: ");
		intArray.shuffle();
		System.out.println(intArray);
		
		System.out.print("Array shuffled again: ");
		intArray.shuffle();
		System.out.println(intArray);
		System.out.println();
		
		//Demonstrate rightShift() and leftShift()
		System.out.print("Array shifted to the right by 4: ");
		intArray.rightShift(4);
		System.out.println(intArray);
		
		System.out.print("Array shifted to the left by 4: ");
		intArray.leftShift(4);
		System.out.println(intArray);
		
		System.out.print("Array shifted to the left by 3: ");
		intArray.leftShift(3);
		System.out.println(intArray);
		
		System.out.print("Array shifted to the right by 3: ");
		intArray.rightShift(3);
		System.out.println(intArray);
		
		//Demonstration of thrown exceptions with leftshift() and rightShift()
		try {
			intArray.leftShift(-10);
		}
		catch(ShiftDistanceCannotBeNegativeException e) {
			System.out.println("ShiftDistanceCannotBeNegativeException thrown and successfully caught!");
		}
		try {
			intArray.rightShift(-10);
		}
		catch(ShiftDistanceCannotBeNegativeException e) {
			System.out.println("ShiftDistanceCannotBeNegativeException thrown and successfully caught!");
		}
		System.out.println();
		
		//Demonstration of size()
		System.out.println("Size: " + intArray.size());
		System.out.println();
		
		//Demonstrate sort()
		System.out.print("Array sorted: ");
		intArray.sort();
		System.out.println(intArray);
		System.out.println();
		
//-----------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------
		
		//Test with String Array
		System.out.println("Testing with String array: ['Hello,' , 'there!', 'General', 'Kenobi!', 'You', 'are', 'a', 'bold', 'one!']");
		System.out.println();
		//Initialize array with 1, 2, 3, 4, 5
		System.out.println("Initializing array with ['Hello,' , 'there!', 'General', 'Kenobi!', 'You', 'are', 'a', 'bold', 'one!']");
		MyArray<String> strArray = new MyArray<>("Hello," , "there!", "General", "Kenobi!", "You", "are", "a", "bold", "one!");
		//Print out array to show toString() functionality
		System.out.println("Current state of array: " + strArray);
		System.out.println();
		
		
		//Demonstration of get()
		for(int i = 0; i < strArray.size(); i++) {
			System.out.println("get(" + i + "): " + strArray.get(i));
		}
		System.out.print("get(1,3): ");
		System.out.println(strArray.get(1, 3));
		
		//Demonstration of thrown exceptions for get()
		try {
			strArray.get(-1);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException thrown and successfully caught!");
		}
		try {
			strArray.get(1, 5);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException thrown and successfully caught!");
		}
		System.out.println();
		
		
		//Demonstration of put()
		strArray.put(4, "I");
		System.out.println("put(4, 2): " + strArray);
		strArray.put(1, "...");
		System.out.println("put(1, 6): " + strArray);
		strArray.put(7, "wimpy");
		System.out.println("put(0, 7): " + strArray);
		
		//Use put to reinput old values into array manually
		strArray.put(0, 8, "Hello," , "there!", "General", "Kenobi!", "You", "are", "a", "bold", "one!");
		System.out.println("put(0, 9, 'Hello,' , 'there!', 'General', 'Kenobi!', 'You', 'are', 'a', 'bold', 'one!'): " + strArray);
		
		//Demonstration of thrown exceptions for put()
		try {
			strArray.put(12, 1);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException thrown and successfully caught!");
		}
		try {
			strArray.put(0, 1, "java", "c++", "c--", "c#", "cflat");
		}
		catch(TooManyValuesException e) {
			System.out.println("TooManyValuesException thrown and successfully caught!");
		}
		try {
			strArray.put(0, 4, "j");
		}
		catch(TooFewValuesException e) {
			System.out.println("TooFewValuesException thrown and successfully caught!");
		}
		System.out.println();
		
		//Print array again to see its current state (Demonstrating toString())
		System.out.println("Current state of array: " + strArray);
		System.out.println();
		
		//Demonstration of equals()
		System.out.println("Compare current array with a new MyArray with the same objects: ");
		System.out.println(strArray.equals(new MyArray("Hello," , "there!", "General", "Kenobi!", "You", "are", "a", "bold", "one!")));
		System.out.println("Compare current array with a new MyArray with different objects: ");
		System.out.println(strArray.equals(new MyArray("Java", "C++")));
		System.out.println("Compare current array with a new MyArray with different types of objects: ");
		System.out.println(strArray.equals(new MyArray(1,2,3,4,5)));
		System.out.println();
		
		//Demonstration of max() and min()
		System.out.println("Max: " + strArray.max());
		System.out.println("Min: " + strArray.min());
		System.out.println();
		
		//Demonstrate reverse()
		strArray.reverse();
		System.out.println("Reverse: " + strArray);
		strArray.reverse();
		System.out.println("Reverse again:" + strArray);
		System.out.println();
		
		//Demonstrate shuffle()
		System.out.print("Array shuffled: ");
		strArray.shuffle();
		System.out.println(strArray);
		
		System.out.print("Array shuffled again: ");
		strArray.shuffle();
		System.out.println(strArray);
		System.out.println();
		
		//Demonstrate rightShift() and leftShift()
		System.out.print("Array shifted to the right by 4: ");
		strArray.rightShift(4);
		System.out.println(strArray);
		
		System.out.print("Array shifted to the left by 4: ");
		strArray.leftShift(4);
		System.out.println(strArray);
		
		System.out.print("Array shifted to the left by 3: ");
		strArray.leftShift(3);
		System.out.println(strArray);
		
		System.out.print("Array shifted to the right by 3: ");
		strArray.rightShift(3);
		System.out.println(strArray);
		
		//Demonstration of thrown exceptions with leftshift() and rightShift()
		try {
			strArray.leftShift(-10);
		}
		catch(ShiftDistanceCannotBeNegativeException e) {
			System.out.println("ShiftDistanceCannotBeNegativeException thrown and successfully caught!");
		}
		try {
			strArray.rightShift(-10);
		}
		catch(ShiftDistanceCannotBeNegativeException e) {
			System.out.println("ShiftDistanceCannotBeNegativeException thrown and successfully caught!");
		}
		System.out.println();
		
		//Demonstration of size()
		System.out.println("Size: " + strArray.size());
		System.out.println();
		
		//Demonstrate sort()
		System.out.print("Array sorted: ");
		strArray.sort();
		System.out.println(strArray);
		System.out.println();
		
	}
}
