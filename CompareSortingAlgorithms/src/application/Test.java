package application;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

//This class is meant to help out with the javafx line graph
//It provides arraylists of times for each algorithm
public class Test {
	//create ArrayLists to store the times for each sorting algorithm
	static ArrayList<Long> insertionS = new ArrayList<>();
	static ArrayList<Long> bubbleS = new ArrayList<>();
	static ArrayList<Long> selectionS = new ArrayList<>();
	static ArrayList<Long> mergeS = new ArrayList<>();
	static ArrayList<Long> quickS = new ArrayList<>();
	static ArrayList<Long> countingS = new ArrayList<>();
	static ArrayList<Long> radixS = new ArrayList<>();
	
	private Test() {}
	
	public static void testAlgs() {
		//array1 - 50000, array2 - 100000, array3 - 150000, array4 - 200000, array5 - 250000, array6 - 300000
		ArrayList<Integer> array1 = new ArrayList<>();
		ArrayList<Integer> array2 = new ArrayList<>();
		ArrayList<Integer> array3 = new ArrayList<>();
		ArrayList<Integer> array4 = new ArrayList<>();
		ArrayList<Integer> array5 = new ArrayList<>();
		ArrayList<Integer> array6 = new ArrayList<>();
		
		Random random = new Random();
		
		for (int i = 0; i < 50000; i++) {	
			array1.add(random.nextInt(1000));
		}
		for (int i = 0; i < 100000; i++) {	
			array2.add(random.nextInt(1000));
		}
		for (int i = 0; i < 150000; i++) {	
			array3.add(random.nextInt(1000));
		}
		for (int i = 0; i < 200000; i++) {	
			array4.add(random.nextInt(1000));
		}
		for (int i = 0; i < 250000; i++) {	
			array5.add(random.nextInt(1000));
		}
		for (int i = 0; i < 300000; i++) {	
			array6.add(random.nextInt(1000));
		}
		
		ArrayList<Integer> copy;
		Instant start;
		Instant end;
		
		//50000 element array
		System.out.println("\n50,000 ELEMENT ARRAY");	
			//insertion Sort
		copy = copyArray(array1);
		start = Instant.now();
		Sorting.insertionSort(copy);
		end = Instant.now();
		System.out.println("Insertion Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		insertionS.add(Duration.between(start, end).toMillis());
		
			//bubble Sort
		copy = copyArray(array1);
		start = Instant.now();
		Sorting.bubbleSort(copy);
		end = Instant.now();
		System.out.println("Bubble Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		bubbleS.add(Duration.between(start, end).toMillis());
		
			//selection Sort
		copy = copyArray(array1);
		start = Instant.now();
		Sorting.selectionSort(copy);
		end = Instant.now();
		System.out.println("Selection Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		selectionS.add(Duration.between(start, end).toMillis());
		
			//merge Sort
		copy = copyArray(array1);
		start = Instant.now();
		Sorting.mergeSort(copy);
		end = Instant.now();
		System.out.println("Merge Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		mergeS.add(Duration.between(start, end).toMillis());
		
			//quick Sort
		copy = copyArray(array1);
		start = Instant.now();
		Sorting.quickSort(copy);
		end = Instant.now();
		System.out.println("Quick Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		quickS.add(Duration.between(start, end).toMillis());
		
			//counting Sort
		copy = copyArray(array1);
		start = Instant.now();
		Sorting.countingSort(copy);
		end = Instant.now();
		System.out.println("Counting Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		countingS.add(Duration.between(start, end).toMillis());
		
			//radix Sort
		copy = copyArray(array1);
		start = Instant.now();
		Sorting.radixSort(copy);
		end = Instant.now();
		System.out.println("Radix Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		radixS.add(Duration.between(start, end).toMillis());
		
		
		//100000 element array
		System.out.println("\n100,000 ELEMENT ARRAY");			
			//insertion Sort
		copy = copyArray(array2);
		start = Instant.now();
		Sorting.insertionSort(copy);
		end = Instant.now();
		System.out.println("Insertion Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		insertionS.add(Duration.between(start, end).toMillis());
		
			//bubble Sort
		copy = copyArray(array2);
		start = Instant.now();
		Sorting.bubbleSort(copy);
		end = Instant.now();
		System.out.println("Bubble Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		bubbleS.add(Duration.between(start, end).toMillis());
		
			//selection Sort
		copy = copyArray(array2);
		start = Instant.now();
		Sorting.selectionSort(copy);
		end = Instant.now();
		System.out.println("Selection Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		selectionS.add(Duration.between(start, end).toMillis());
		
			//merge Sort
		copy = copyArray(array2);
		start = Instant.now();
		Sorting.mergeSort(copy);
		end = Instant.now();
		System.out.println("Merge Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		mergeS.add(Duration.between(start, end).toMillis());
		
			//quick Sort
		copy = copyArray(array2);
		start = Instant.now();
		Sorting.quickSort(copy);
		end = Instant.now();
		System.out.println("Quick Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		quickS.add(Duration.between(start, end).toMillis());
		
			//counting Sort
		copy = copyArray(array2);
		start = Instant.now();
		Sorting.countingSort(copy);
		end = Instant.now();
		System.out.println("Counting Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		countingS.add(Duration.between(start, end).toMillis());
		
			//radix Sort
		copy = copyArray(array2);
		start = Instant.now();
		Sorting.radixSort(copy);
		end = Instant.now();
		System.out.println("Radix Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		radixS.add(Duration.between(start, end).toMillis());
		
		//150000 element array
		System.out.println("\n150,000 ELEMENT ARRAY");			
			//insertion Sort
		copy = copyArray(array3);
		start = Instant.now();
		Sorting.insertionSort(copy);
		end = Instant.now();
		System.out.println("Insertion Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		insertionS.add(Duration.between(start, end).toMillis());
		
			//bubble Sort
		copy = copyArray(array3);
		start = Instant.now();
		Sorting.bubbleSort(copy);
		end = Instant.now();
		System.out.println("Bubble Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		bubbleS.add(Duration.between(start, end).toMillis());
		
			//selection Sort
		copy = copyArray(array3);
		start = Instant.now();
		Sorting.selectionSort(copy);
		end = Instant.now();
		System.out.println("Selection Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		selectionS.add(Duration.between(start, end).toMillis());
		
			//merge Sort
		copy = copyArray(array3);
		start = Instant.now();
		Sorting.mergeSort(copy);
		end = Instant.now();
		System.out.println("Merge Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		mergeS.add(Duration.between(start, end).toMillis());
		
			//quick Sort
		copy = copyArray(array3);
		start = Instant.now();
		Sorting.quickSort(copy);
		end = Instant.now();
		System.out.println("Quick Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		quickS.add(Duration.between(start, end).toMillis());
		
			//counting Sort
		copy = copyArray(array3);
		start = Instant.now();
		Sorting.countingSort(copy);
		end = Instant.now();
		System.out.println("Counting Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		countingS.add(Duration.between(start, end).toMillis());
		
			//radix Sort
		copy = copyArray(array3);
		start = Instant.now();
		Sorting.radixSort(copy);
		end = Instant.now();
		System.out.println("Radix Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		radixS.add(Duration.between(start, end).toMillis());

		//200000 element array
		System.out.println("\n200,000 ELEMENT ARRAY");			
			//insertion Sort
		copy = copyArray(array4);
		start = Instant.now();
		Sorting.insertionSort(copy);
		end = Instant.now();
		System.out.println("Insertion Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		insertionS.add(Duration.between(start, end).toMillis());
		
			//bubble Sort
		copy = copyArray(array4);
		start = Instant.now();
		Sorting.bubbleSort(copy);
		end = Instant.now();
		System.out.println("Bubble Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		bubbleS.add(Duration.between(start, end).toMillis());
		
			//selection Sort
		copy = copyArray(array4);
		start = Instant.now();
		Sorting.selectionSort(copy);
		end = Instant.now();
		System.out.println("Selection Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		selectionS.add(Duration.between(start, end).toMillis());
		
			//merge Sort
		copy = copyArray(array4);
		start = Instant.now();
		Sorting.mergeSort(copy);
		end = Instant.now();
		System.out.println("Merge Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		mergeS.add(Duration.between(start, end).toMillis());
		
			//quick Sort
		copy = copyArray(array4);
		start = Instant.now();
		Sorting.quickSort(copy);
		end = Instant.now();
		System.out.println("Quick Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		quickS.add(Duration.between(start, end).toMillis());
		
			//counting Sort
		copy = copyArray(array4);
		start = Instant.now();
		Sorting.countingSort(copy);
		end = Instant.now();
		System.out.println("Counting Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		countingS.add(Duration.between(start, end).toMillis());
		
			//radix Sort
		copy = copyArray(array4);
		start = Instant.now();
		Sorting.radixSort(copy);
		end = Instant.now();
		System.out.println("Radix Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		radixS.add(Duration.between(start, end).toMillis());
	
	
		//250000 element array
		System.out.println("\n250,000 ELEMENT ARRAY");			
			//insertion Sort
		copy = copyArray(array5);
		start = Instant.now();
		Sorting.insertionSort(copy);
		end = Instant.now();
		System.out.println("Insertion Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		insertionS.add(Duration.between(start, end).toMillis());
		
			//bubble Sort
		copy = copyArray(array5);
		start = Instant.now();
		Sorting.bubbleSort(copy);
		end = Instant.now();
		System.out.println("Bubble Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		bubbleS.add(Duration.between(start, end).toMillis());
		
			//selection Sort
		copy = copyArray(array5);
		start = Instant.now();
		Sorting.selectionSort(copy);
		end = Instant.now();
		System.out.println("Selection Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		selectionS.add(Duration.between(start, end).toMillis());
		
			//merge Sort
		copy = copyArray(array5);
		start = Instant.now();
		Sorting.mergeSort(copy);
		end = Instant.now();
		System.out.println("Merge Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		mergeS.add(Duration.between(start, end).toMillis());
		
			//quick Sort
		copy = copyArray(array5);
		start = Instant.now();
		Sorting.quickSort(copy);
		end = Instant.now();
		System.out.println("Quick Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		quickS.add(Duration.between(start, end).toMillis());
		
			//counting Sort
		copy = copyArray(array5);
		start = Instant.now();
		Sorting.countingSort(copy);
		end = Instant.now();
		System.out.println("Counting Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		countingS.add(Duration.between(start, end).toMillis());
		
			//radix Sort
		copy = copyArray(array5);
		start = Instant.now();
		Sorting.radixSort(copy);
		end = Instant.now();
		System.out.println("Radix Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		radixS.add(Duration.between(start, end).toMillis());
		
		//300000 element array
		System.out.println("\n300,000 ELEMENT ARRAY");			
			//insertion Sort
		copy = copyArray(array6);
		start = Instant.now();
		Sorting.insertionSort(copy);
		end = Instant.now();
		System.out.println("Insertion Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		insertionS.add(Duration.between(start, end).toMillis());
		
			//bubble Sort
		copy = copyArray(array6);
		start = Instant.now();
		Sorting.bubbleSort(copy);
		end = Instant.now();
		System.out.println("Bubble Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		bubbleS.add(Duration.between(start, end).toMillis());
		
			//selection Sort
		copy = copyArray(array6);
		start = Instant.now();
		Sorting.selectionSort(copy);
		end = Instant.now();
		System.out.println("Selection Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		selectionS.add(Duration.between(start, end).toMillis());
		
			//merge Sort
		copy = copyArray(array6);
		start = Instant.now();
		Sorting.mergeSort(copy);
		end = Instant.now();
		System.out.println("Merge Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		mergeS.add(Duration.between(start, end).toMillis());
		
			//quick Sort
		copy = copyArray(array6);
		start = Instant.now();
		Sorting.quickSort(copy);
		end = Instant.now();
		System.out.println("Quick Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		quickS.add(Duration.between(start, end).toMillis());
		
			//counting Sort
		copy = copyArray(array6);
		start = Instant.now();
		Sorting.countingSort(copy);
		end = Instant.now();
		System.out.println("Counting Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		countingS.add(Duration.between(start, end).toMillis());
		
			//radix Sort
		copy = copyArray(array6);
		start = Instant.now();
		Sorting.radixSort(copy);
		end = Instant.now();
		System.out.println("Radix Sort:" + Duration.between(start, end).toMillis() + " milliseconds");
		radixS.add(Duration.between(start, end).toMillis());
		
		System.out.println("\nTimes for each of the algorithms (from 50,000 to 300,000)");
		System.out.println("Insertion Sort: " + insertionS);
		System.out.println("Bubble Sort: " + bubbleS);
		System.out.println("Selection Sort: " + selectionS);
		System.out.println("Merge Sort: " + mergeS);
		System.out.println("Quick Sort: " + quickS);
		System.out.println("Counting Sort: " + countingS);
		System.out.println("Radix Sort: " + radixS);
	}
	private static ArrayList<Integer> copyArray(ArrayList<Integer> array) {
		ArrayList<Integer> copy = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			copy.add(array.get(i));
		}
		return copy;
	}
}
