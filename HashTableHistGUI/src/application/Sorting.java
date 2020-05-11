package application;

import java.util.ArrayList;
import java.util.LinkedList;

public class Sorting {
	
	//prevent class from being instantiated by making the constructor private
	private Sorting() {}
	
	public static <E extends Comparable<E>> void insertionSort(ArrayList<E> array) {
		for (int i = 1; i < array.size(); i++) {
			E temp = array.get(i);
			int j = i;
			while (j > 0 && temp.compareTo(array.get(j - 1)) == -1) {
				array.set(j, array.get(j - 1));
				j--;
			}
			array.set(j, temp);
		}
	}
	public static <E extends Comparable<E>> void bubbleSort(ArrayList<E> array) {
		for (int i = 1; i < array.size(); i++) {
			boolean swapped = false;
			for (int j = 0; j < array.size() - 1; j++) {
				if (array.get(j).compareTo(array.get(j+1)) == 1) {
					E temp = array.get(j);
					array.set(j, array.get(j + 1));
					array.set(j + 1, temp);
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
	}
	public static <E extends Comparable<E>> void selectionSort(ArrayList<E> array) {
		for (int i = 0; i < array.size(); i++) {
			int min = i;
			for (int j = i + 1; j < array.size(); j++) {
				if (array.get(j).compareTo(array.get(min)) == -1) {
					min = j;
				}
			}
			E temp = array.get(i);
			array.set(i, array.get(min));
			array.set(min, temp);
		}
	}
	public static <E extends Comparable<E>> void mergeSort(ArrayList<E> array) {
		if (array.size() > 1) {
			int mid = (array.size() - 1) / 2;
			
			ArrayList<E> left = new ArrayList<>();
			for (int i = 0; i <= mid; i++) {
				left.add(array.get(i));
			}
			mergeSort(left);
			
			ArrayList<E> right = new ArrayList<>();
			for (int i = mid + 1; i < array.size(); i++) {
				right.add(array.get(i));
			}
			mergeSort(right);
			
			merge(left, right, array);
		}
	}
	private static <E extends Comparable<E>> void merge(ArrayList<E> array1, ArrayList<E> array2, ArrayList<E> result) {
		int i = 0;
		int j = 0; 
		int k = 0;
		while (i < array1.size() && j < array2.size()) {
			if (array1.get(i).compareTo(array2.get(j)) == -1) {
				result.set(k, array1.get(i));
				i++;
			}
			else {
				result.set(k,  array2.get(j));
				j++;
			}
			k++;
		}
		while (i < array1.size()) {
			result.set(k, array1.get(i));
			i++;
			k++;
		}
		while (j < array2.size()) {
			result.set(k, array2.get(j));
			j++;
			k++;
		}
	}
	public static <E extends Comparable<E>> void quickSort(ArrayList<E> array) {
		quickSort(array, 0, array.size() - 1);
	}
	private static <E extends Comparable<E>> void quickSort(ArrayList<E> array, int low, int high) {
		if (low < high) {
			int p = partition(array, low, high);
			quickSort(array, low, p - 1);
			quickSort(array, p + 1, high);
		}
	}
	private static <E extends Comparable<E>> int partition(ArrayList<E> array, int low, int high) {
		E pivot = array.get(high);
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (array.get(j).compareTo(pivot) <= 0) {
				i = i + 1;
				E temp = array.get(i);
				array.set(i, array.get(j));
				array.set(j, temp);
			}
		}
		E temp = array.get(i + 1);
		array.set(i + 1, array.get(high));
		array.set(high, temp);
		return i + 1;
	}
	public static void countingSort(ArrayList<Integer> array) {
		int max = array.get(0);
		for (int i = 1; i < array.size(); i++) {
			if (array.get(i).compareTo(max) == 1) {
				max = array.get(i);
			}
		}
		ArrayList<Integer> sorted = countingSort(array, max + 1);
		for (int i = 0; i < array.size(); i++) {
			array.set(i, sorted.get(i));
		}
	}
	private static ArrayList<Integer> countingSort(ArrayList<Integer> array, int k) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			result.add(0);
		}
		ArrayList<Integer> counts = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			counts.add(0);
		}
		for (int i = 0; i < array.size(); i++) {
			counts.set(array.get(i), counts.get(array.get(i)) + 1);
		}
		for (int i = 1; i < k; i++) {
			counts.set(i, counts.get(i) + counts.get(i - 1));
		}
		for (int i = array.size() - 1; i >= 0; i--) {
			result.set(counts.get(array.get(i)) - 1, array.get(i));
			counts.set(array.get(i), counts.get(array.get(i)) - 1);
		}
		return result;
	}
	public static void radixSort(ArrayList<Integer> array) {
		ArrayList<LinkedList<Integer>> buckets = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			buckets.add(new LinkedList<Integer>());
		}
		int max = array.get(0);
		for (int i = 1; i < array.size(); i++) {
			if (array.get(i).compareTo(max) == 1) {
				max = array.get(i);
			}
		}
		int order = 0;
		while (max > 0) {
			max /= 10;
			order++;
		}
		for (int i = 1; i <= order; i++) {
			for (int j = 0; j < array.size(); j++) {
				buckets.get(((array.get(j) % (int)(Math.pow(10, i))) / (int)Math.pow(10, i-1))).add(array.get(j));
			}
			array.clear();
			for (int k = 0; k < 10; k++) {
				while(!buckets.get(k).isEmpty()) {
					array.add(buckets.get(k).getFirst());
					buckets.get(k).removeFirst();
				}
			}
		}
	}
	
}
