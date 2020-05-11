package genericArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MyArray<E extends Comparable<E>> {
    //No other data fields necessary.
    public E[] data;

    public MyArray(int size) {
        this.data = (E[])(new Comparable[size]);
    }

    //This constructor can accept an array or a comma-separated list of values.
    public MyArray(E ... elements) {
        this.data = (E[])(new Comparable[elements.length]);
        //Make a deep copy to prevent shared references.
        System.arraycopy(elements, 0, this.data, 0, elements.length);
    }
    public E get(int index){
    	if (index < 0 || index > data.length) {
    		throw new IndexOutOfBoundsException();
    	}
    	else {
    		return data[index];
    	}
    }
    public MyArray<E> get(int start, int end){
    	MyArray<E> array = new MyArray<>(end-start + 1);
    	if (!((end < start) || (start < 0)||(start > data.length - 1) || (end < 0) || (end > data.length - 1))) {
    		for(int i = 0; i < end-start + 1; i++) {
    			array.put(i, data[start+i]);
    		}
    		return array;
    	}
    	else {
    		throw new IndexOutOfBoundsException();
    	}
    }
    public void put(int index, E value) {
    	if (!(index < 0 || index > data.length)) {
    		data[index] = value;
    	}
    	else {
    		throw new IndexOutOfBoundsException();
    	}
    }
    public void put(int start, int end, E ... values) {
    	if((end < start) || (start < 0)||(start > data.length - 1) || (end < 0) || (end > data.length - 1)) {
    		throw new IndexOutOfBoundsException();
    	}
    	else if(end - start + 1< values.length) {
    		throw new TooManyValuesException("Number of values is more than index range!");
    	}
    	else if(end - start + 1> values.length) {
    		throw new TooFewValuesException("Number of values is less than index range!");
    	}
    	else {
    		System.arraycopy(values, 0, this.data, start, values.length);
    	}
    }
    @Override
    public boolean equals(Object obj) {
    	if (obj == this) {
    		return true;
    	}
    	if(!(obj instanceof MyArray)) {
    		return false;
    	}
    	MyArray<E> array = (MyArray<E>) obj;
    	for(int i = 0; i < data.length; i++) {
    		if(!(array.get(i).equals(data[i]))) {
    			return false;
    		}
    	}
    	return true;
    }
    public E max() {
    	E max = data[0];
    	for(int i = 1; i < data.length; i++) {
    		if(data[i].compareTo(max) > 0) {
    			max = data[i];
    		}
    	}
    	return max;
    }
    public E min() {
    	E min = data[0];
    	for(int i = 1; i < data.length; i++) {
    		if(data[i].compareTo(min) < 0) {
    			min = data[i];
    		}
    	}
    	return min;
    }
    public void reverse() {
    	for(int i = 0, j = data.length - 1; i < j; i++, j--) {
    		E temp = data[i];
    		data[i] = data[j];
    		data[j] = temp;
    	}
    }
    public void shuffle() {
    	Random random = new Random();
    	for(int i = 0; i < data.length; i++) {
    		int j = random.nextInt(data.length);
    		E temp = data[i];
    		data[i] = data[j];
    		data[j] = temp;
    	}
    }
    public void rightShift(int shiftDistance) {
    	ArrayList<E> copy = new ArrayList<>();
    	for(int i = 0; i < data.length; i++) {
    		copy.add(data[i]);
    	}
    	if (shiftDistance < 0) {
    		throw new ShiftDistanceCannotBeNegativeException("Shift distance must be greater than 0!");
    	}
    	for(int i = 0; i < data.length; i++) {
    		data[(i + shiftDistance) % data.length] = copy.get(i);
    	}
    }
    public void leftShift(int shiftDistance) {
    	ArrayList<E> copy = new ArrayList<>();
    	for(int i = 0; i < data.length; i++) {
    		copy.add(data[i]);
    	}
    	if (shiftDistance < 0) {
    		throw new ShiftDistanceCannotBeNegativeException("Shift distance must be greater than 0!");
    	}
    	for(int i = 0; i < data.length; i++) {
    		data[i] = copy.get((i + shiftDistance) % data.length);
    	}
    }
    public int size() {
    	return data.length;
    }
    @Override
    public String toString() {
    	String string = "";
    	for(int i = 0; i < data.length; i++) {
    		if(i == 0) {
    			string += "[" + data[i] + ", ";
    		}
    		else if(i == data.length - 1) {
    			string += data[i] + "]";
    		}
    		else {
    			string += data[i] + ", ";
    		}
    	}
    	return string;
    }
    public void sort() {
    	boolean sorted = false;
    	while(!sorted) {
    		sorted = true;
    		for(int i = 0; i < data.length - 1; i++) {
    			if(data[i].compareTo(data[i+1]) > 0) {
    				E temp = data[i];
    				data[i] = data[i+1];
    				data[i+1] = temp;
    				sorted = false;
    			}
    		}
     	}
    }
}
