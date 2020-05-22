package hw09;

public class HashTable {
	private Entry[] entryArray = new Entry[15];
	private int size = 0;
	//make a load size that would be reasonable.
	
	//track the load factor for the words that are coming in
	
	public HashTable() {
		
		
	}
	
	public void getText() {
		
		//make a method that will allow for the scanner to read in the data from the text. 
	}
	
	public void add(Entry value) {

		int hash = value.hashCode() % this.entryArray.length;
		
		if(this.entryArray[hash] == null) {
			
			this.entryArray[hash] = value;
			this.size++;
			
		}
		else {
			
			boolean b = false;
			
			while(this.entryArray[hash] != null) {
				
				if(this.entryArray[hash].getWord().equalsIgnoreCase(value.getWord())) {
					this.entryArray[hash].incrementCount();
					b = true;
					break;
				}
				
				hash += 1 ; //collision resolution : linear probing
				hash = hash % this.entryArray.length;
				
			}
			
			if(b != true) {
				this.entryArray[hash] = value;
				this.size++;
			}
			
			

		}
		
		double loadFactor = (double)this.size / (double)this.entryArray.length;
		
		if(loadFactor > .77) {
			resize();
		}
	
	}

	public void resize() {
		
		Entry[] array = new Entry[this.entryArray.length*2];
		
		Entry[] oldArray = this.entryArray;
		
		this.entryArray = array;
		
		this.size = 0;
		
		for(int i = 0; i < oldArray.length; i++) {
			
			if(oldArray[i] != null) {
				add(oldArray[i]);
			}
			
		}
		
		
		
	}
	
	@Override
	public String toString() { 
		
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < this.entryArray.length; i++) {
			if(this.entryArray[i] != null) {
				result.append(this.entryArray[i].toString() + " ");
			}
		}
		return result.toString();
		
		
	}
	
 
}
