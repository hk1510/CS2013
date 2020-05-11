package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Instant start = Instant.now();
		System.out.println(HashTableTest.count());
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end).toMillis() + " ms");
		start = Instant.now();
		SimpleList list = HashTableTest.generateSimpleList();
		end = Instant.now();
		System.out.println(Duration.between(start, end).toMillis() + " ms");
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			count += list.getEntry(i).getCount();
		}
		System.out.println(count);
//		try {
//			BorderPane root = new BorderPane();
//			Button chooseFile = new Button("Choose File");
//			HBox menu = new HBox(50);
//			menu.setPadding(new Insets(20));
//			menu.setAlignment(Pos.CENTER);
//			menu.getChildren().add(chooseFile);
//			FileChooser fc = new FileChooser();
//			
//			chooseFile.setOnAction(e -> {
//				Instant start = Instant.now();
//				SimpleList list = HashTableTest.generateSimpleList();
//				Instant end = Instant.now();
//				System.out.println(Duration.between(start, end));
//				System.out.println(list);
//			});
//			
//			root.setTop(menu);
//			
//			Scene scene = new Scene(root,400,400);
//			primaryStage.setScene(scene);
			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
 