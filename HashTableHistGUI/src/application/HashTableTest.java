package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.stage.FileChooser;

public class HashTableTest {
	
	public static SimpleList generateSimpleList() {
		SimpleList list = new SimpleList();
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(null);
		if (file != null) {
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line;
				while ((line = input.readLine()) != null) {
					String[] words = line.split(" ");
					for (String word : words) {
						word = word.toLowerCase();
						boolean found = false;
						for (int i = 0; i < list.size(); i++) {
							if (list.getEntry(i).getWord().equals(word)) {
								list.getEntry(i).incrementCount();
								found = true;
								break;
							}
						}
						if (!found) {
							list.add(new Entry(word));
						}
					}
				}
				input.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		else {
			throw new NullPointerException();
		}
		return list;
	}
	
	public static HashTable generateHashTable() {
		return new HashTable();
	}
	
	public static int count() {
		int count = 0;
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(null);
		if (file != null) {
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line;
				while ((line = input.readLine()) != null) {
					String[] words = line.split(" ");
					for (String word : words) {
						count++;
					}
				}
				input.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		else {
			throw new NullPointerException();
		}
		return count;
	}
}
