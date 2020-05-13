package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.Instant;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HashTableTest extends Application{
	private static File file;
	@Override
	public void start(Stage primaryStage) throws Exception {
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(primaryStage);
		Instant start = Instant.now();
		HashTable table = HashTableTest.generateHashTable();
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end).toMillis() + " ms");
		start = Instant.now();
		SimpleList list = HashTableTest.generateSimpleList();
		end = Instant.now();
		System.out.println(Duration.between(start, end).toMillis() + " ms");
		writeToFile(list, table);
		primaryStage.close();
		Platform.exit();
	}
	
	public static SimpleList generateSimpleList() {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			throw new NullPointerException();
		}
		return table;
	}
	
	public static int count() {
		int count = 0;
		if (file != null) {
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line;
				while ((line = input.readLine()) != null) {
					String[] words = line.split(" ");
					for (int i = 0; i < words.length; i++) {
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
	private static void writeToFile(SimpleList list, HashTable table) {
		File outFile1 = new File("C:\\Users\\Harshil Kotamreddy\\Desktop\\out1.txt");
		File outFile2 = new File("C:\\Users\\Harshil Kotamreddy\\Desktop\\out2.txt");
		try {
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile1)));
			output.write(list.toString());
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile2)));
			output.write(table.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Application.launch();
	}
}
