package application;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application {
	
	static final double HEIGHT = 1080;
	static final double WIDTH  = 1920;
	
	private TextArea maze = new TextArea("Loaded maze will be shown here once selected.");
	private TextArea solvedMaze = new TextArea("Solved maze will be shown here once solve button is clicked.");
	private Maze mazeobj;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Button chooseMaze  = new Button("Choose Maze");
			Button solveMaze = new Button("Solve Maze");
			
			maze.setEditable(false);
			solvedMaze.setEditable(false);
			
			maze.setPrefHeight(0.8 * HEIGHT);
			maze.setPrefWidth(0.45 * WIDTH);
			solvedMaze.setPrefHeight(0.8 * HEIGHT);
			solvedMaze.setPrefWidth(0.45 * WIDTH);
			maze.setStyle("-fx-font-family: 'monospaced'; -fx-font-weight: bold");
			solvedMaze.setStyle("-fx-font-family: 'monospaced'; -fx-font-weight: bold");
			
			
			chooseMaze.setOnAction(e -> readMaze(primaryStage));
			solveMaze.setOnAction(e -> solve());
			
			root.add(chooseMaze, 0, 0);
			root.add(solveMaze, 1, 0);
			root.add(maze, 0, 1);
			root.add(solvedMaze, 1, 1);
			
			root.setAlignment(Pos.CENTER);
			root.setHgap(20);
			root.setVgap(10);
			
			for (int i = 0; i < root.getChildren().size(); i++) {
				root.setHalignment(root.getChildren().get(i), HPos.CENTER);
			}
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setHeight(HEIGHT);
			primaryStage.setWidth(WIDTH);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Maze Solver");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void readMaze(Stage stage) {
		mazeobj = null;
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(stage);
		if (!file.getName().substring(file.getName().length() - 4).equals(".txt")) {
			maze.setText("File not .txt, please choose a .txt maze file.");
		}
		else {
			try {
				mazeobj = new Maze(file);
			}
			catch (IOException e){
				maze.setText("Error reading file. Most likely invalid maze file.");
			}
			catch (Exception f) {
				maze.setText("Invalid maze file.");
			}
			maze.setText(mazeobj.toString());
		}
	}
	
	private void solve() {
		try {
			if (mazeobj.solve()) {
				solvedMaze.setText(mazeobj.getSol());
			}
			else {
				solvedMaze.setText("Maze cannot be solved.");
			}
		}
		catch(NullPointerException e) {
			solvedMaze.setText("No maze selected or invalid maze file selected.");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
