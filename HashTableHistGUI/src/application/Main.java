package application;
	
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application {
	private File file;
	private TextField htTimetf = new TextField("ms");
	private TextField slTimetf = new TextField("ms");
	private Text infotf = new Text();
	private HashTable hashTable;
	private SimpleList simpleList;
	private FileChooser fc = new FileChooser();
	@Override
	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		Button chooseFile = new Button("Choose File");
		Button outFile1 = new Button("Choose output file for HashTable");
		Button outFile2 = new Button("Choose output file for SimpleList");
		Button runHT = new Button("Run HashTable");
		Button runSL = new Button("Run SimpleList");
		HBox bottomMenu = new HBox(50);
		bottomMenu.getChildren().addAll(outFile1, outFile2);
		bottomMenu.setAlignment(Pos.CENTER);
		GridPane secondaryPane = new GridPane();
		secondaryPane.setAlignment(Pos.CENTER);
		secondaryPane.setHgap(200);
		secondaryPane.setVgap(20);
		Label hashTableTime = new Label("HashTable time in ms: ");
		Label simpleListTime = new Label("SimpleList time in ms: ");
		htTimetf.setEditable(false);
		slTimetf.setEditable(false);
		secondaryPane.add(runHT, 0, 0);
		secondaryPane.add(runSL, 1, 0);
		secondaryPane.add(hashTableTime, 0, 1);
		secondaryPane.add(htTimetf, 0, 2);
		secondaryPane.add(simpleListTime, 1, 1);
		secondaryPane.add(slTimetf, 1, 2);
		secondaryPane.add(infotf, 0, 3, 2, 1);
		GridPane.setHalignment(runHT, HPos.CENTER);
		GridPane.setHalignment(runSL, HPos.CENTER);
		this.fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"));
		chooseFile.setOnAction(e -> {
			try {
				this.file = fc.showOpenDialog(primaryStage);
				this.infotf.setText("File read from: " + file.getAbsolutePath());			
			} catch (NullPointerException n) {
				this.infotf.setText("File Selection Cancelled.");
			}
		});
		runHT.setOnAction(e -> {
			if (file == null) {
				this.infotf.setText("Please choose a file to read from.");
			}
			else {
				System.out.println("HashTable took around 2 seconds to run on my computer.");
				Instant start = Instant.now();
				this.hashTable = HashTableTest.generateHashTable(file);
				Instant end = Instant.now();
				this.htTimetf.setText(Duration.between(start, end).toMillis() + " ms");
				this.infotf.setText("HashTable has completed.");
			}
		});
		runSL.setOnAction(e -> {
			if (file == null) {
				this.infotf.setText("Please choose a file to read from.");
			}
			else {
				System.out.println("SimpleList took around 5 minutes to run on my computer.");
				Instant start = Instant.now();
				this.simpleList = HashTableTest.generateSimpleList(file);
				Instant end = Instant.now();
				this.slTimetf.setText(Duration.between(start, end).toMillis() + " ms");
				this.infotf.setText("SimpleList has finished running.");
			}
		});
		outFile1.setOnAction(e -> {
			if (this.hashTable == null) {
				this.infotf.setText("Please run HashTable first.");
			}
			else {
				File outFile = fc.showSaveDialog(primaryStage);
				try {
					BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
					System.out.println("Sorting and writing to the output file takes around 5-6 seconds.");
					ArrayList<Entry> hashList = hashTable.list();
					Collections.sort(hashList);
					String str = "";
					String formatter = "%-20s%-1d";
			        for (int i = 0 ; i < hashList.size() ; i++) {
			            Entry entry = hashList.get(i);
			            str += String.format(formatter, entry.getWord(), entry.getCount()) + "\n";
			        }
					output.write(str);
					output.close();
					this.infotf.setText("HashTable output file written to: " + outFile.getAbsolutePath());
				} catch (NullPointerException n) {
					this.infotf.setText("File Selection Cancelled.");
				} catch (FileNotFoundException f) {
					f.printStackTrace();
				} catch (IOException f) {
					f.printStackTrace();
				}

			}
		});
		outFile2.setOnAction(e -> {
			if (this.simpleList == null) {
				this.infotf.setText("Please run SimpleList first.");
			}
			else {
				File outFile = fc.showSaveDialog(primaryStage);
				try {
					BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
					System.out.println("Sorting and writing to the output file takes around 5-6 seconds.");
					ArrayList<Entry> slList = new ArrayList<>();
					for (int i = 0; i < simpleList.size(); i++) {
						slList.add(simpleList.getEntry(i));
					}
		 			Collections.sort(slList);
					String str = "";
					String formatter = "%-20s%-1d";
			        for (int i = 0 ; i < slList.size() ; i++) {
			            Entry entry = slList.get(i);
			            str += String.format(formatter, entry.getWord(), entry.getCount()) + "\n";
			        }
					output.write(str);
					output.close();
					this.infotf.setText("SimpleList output file written to: " + outFile.getAbsolutePath());
				} catch (NullPointerException n) {
					this.infotf.setText("File Selection Cancelled.");
				} catch (FileNotFoundException f) {
					f.printStackTrace();
				} catch (IOException f) {
					f.printStackTrace();
				}
			}
		});
		pane.setTop(chooseFile);
		pane.setBottom(bottomMenu);
		pane.setCenter(secondaryPane);
		pane.setPadding(new Insets(100));
		BorderPane.setAlignment(chooseFile, Pos.CENTER);
		Scene scene = new Scene(pane, 1280, 720);
		primaryStage.setTitle("HashTable Tester");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
