package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashTableTest{
	
	public static SimpleList generateSimpleList(File file) {
		SimpleList list = new SimpleList();
		if (file != null) {
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line;
				while ((line = input.readLine()) != null) {
					String[] words = line.split(" ");
					for (int i = 0; i < words.length; i++) {
						String word = words[i].toLowerCase().trim();
						if(!word.trim().equals("")) {
							boolean found = false;
							for (int j = 0; j < list.size(); j++) {
								if (list.getEntry(j).getWord().equals(word)) {
									list.getEntry(j).incrementCount();
									found = true;
									break;
								}
							}
							if (!found) {
								list.add(new Entry(word));
							}
						}
					}
				}
				input.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
		}
		else {
			throw new NullPointerException();
		}
		return list;
	}
	
	public static HashTable generateHashTable(File file) {
		HashTable table = new HashTable();
		if (file != null) {
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line;
				while ((line = input.readLine()) != null) {
					String[] words = line.split(" ");
					for (int i = 0; i < words.length; i++) {
						String word = words[i].toLowerCase().trim();
						if(!word.trim().equals("")) {
							table.add(new Entry(word));
						}
					}
				}
				input.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			throw new NullPointerException();
		}
		return table;
	}
}
