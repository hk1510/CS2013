package hw09;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HashTableTester extends Application {

	HashTable hashTable = new HashTable();
	SimpleList simpleList = new SimpleList();


	@Override
	public void start(Stage primaryStage) {

		Instant start = Instant.now();
		generateHashTable();
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end).toMillis() + "ms");
		
		Instant begin = Instant.now();
		generateSimpleList();
		Instant finish = Instant.now();
		System.out.println(Duration.between(begin, finish).toMillis() + "ms");


	}

	public static void main(String[] args) {

		Application.launch(args);

	}

	public static File files() {

		FileChooser fileChooser = new FileChooser();

		File inFile = fileChooser.showOpenDialog(new Stage());

		return inFile;

	}

	public void generateSimpleList() {

		Entry entry;
		try {

			Scanner in = new Scanner(files());
			while (in.hasNext()) {

				entry = new Entry(in.next());
				
				boolean b = false;

				for(int i = 0; i < simpleList.size();i++) {
					
					if(simpleList.getEntry(i).getWord().equalsIgnoreCase(entry.getWord())) {
						
					simpleList.getEntry(i).incrementCount();
					
					b = true;
					
					break;
					}
				}
				
				if(b != true) {
					simpleList.add(entry);
				}

				
				//if they have already been added increase their count in the entry by 1

			}

			in.close();

		} catch (FileNotFoundException ex) {

			ex.printStackTrace();

		}

	}
	
	public void generateHashTable() {
		
		Entry entry;
		try {

			Scanner in = new Scanner(files());
			while (in.hasNext()) {

				entry = new Entry(in.next());
				
				hashTable.add(entry);
				
			}

			in.close();

		} catch (FileNotFoundException ex) {

			ex.printStackTrace();

		}
	}





}
