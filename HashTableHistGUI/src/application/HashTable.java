package application;

public class HashTable { 
	
	private static final double MAX_LOAD_FACTOR = 0.5;
	
	private int capacity = 7;
	private int size;
	private int count;
	private Entry[] hashTable;
	
	public HashTable() {
		hashTable = new Entry[capacity];
		this.size = 0;
	}
	
	public void add(Entry key) {
		int hashCode = key.hashCode();
		int hash = hashCode % capacity;
		int secondHash = secondaryHash(hashCode); 
		boolean duplicate = false;
		int hashLoc = hash;
		int j = 0;
		while (hashTable[hashLoc] != null) {
			if (hashTable[hashLoc].getWord().contentEquals(key.getWord())) {
				hashTable[hashLoc].incrementCount();
				duplicate = true;
				break;
			}
			j++;
			hashLoc = (hash + (j*secondHash)) % capacity;	
		}
		if (!duplicate) {
			hashTable[hashLoc] = key;
			size++;
		}
		count++;
		if ((size / (double)capacity) > MAX_LOAD_FACTOR) {
			this.resize();
		}
		
	}
	private int secondaryHash(int key) {
		return 7 - (key % 7);
	}
	private void resize() {
		capacity = findPrime();
		this.size = 0;
		int newCount = this.count;
		Entry[] oldTable = this.hashTable;
		this.hashTable = new Entry[capacity];
		for (int i = 0; i < oldTable.length; i++) {
			if (oldTable[i] != null) {
				this.add(oldTable[i]);
			}
		}
		this.count = newCount;
	}
	private int findPrime() {
		int candidate = capacity * 2;
		boolean prime = false;
		while (!prime) {
			candidate++;
			prime = true;
			for (int i = 2; i < candidate; i++) {
				if (candidate % i == 0) {
					prime = false;
				}
			}
		}
		return candidate;
	}
	public Entry[] sort() {
		Entry[] sortedList = new Entry[10];
		return sortedList;
	}
	public String toString() {
        String formatter = "%-20s%-1d";
		String output = "";
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				output += String.format(formatter, hashTable[i].getWord(), hashTable[i].getCount()) + "\n";
			}
		}
		return output;
	}
}
