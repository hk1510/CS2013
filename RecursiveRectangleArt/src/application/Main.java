package application;
	
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	
	double height = 900;
	double width = 1600;
	
	BorderPane root = new BorderPane();
	Group mainRect = new Group();
	
	TextField subdivtf = new TextField();
	RadioButton r13 = new RadioButton("1/3");
	RadioButton r14 = new RadioButton("1/4");
	RadioButton r15 = new RadioButton("1/5");
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Label subdiv = new Label("Level of Subdivisions: ");
			
			ToggleGroup divs = new ToggleGroup();
			r13.setToggleGroup(divs);
			r13.fire();
			r14.setToggleGroup(divs);
			r15.setToggleGroup(divs);
			
			Button generate = new Button("Generate!");
			
			GridPane menu = new GridPane();
			menu.add(subdiv, 0, 0);
			menu.add(subdivtf, 1, 0);
			menu.add(r13, 2, 0);
			menu.add(r14, 3, 0);
			menu.add(r15, 4, 0);
			menu.add(generate, 5, 0);
			menu.setHgap(10);
			menu.setTranslateX(20);
			menu.setTranslateY(20);
			
			generate.setOnAction(e -> generateRects());
			
			root.setTop(menu);
			root.setCenter(mainRect);
			Scene scene = new Scene(root,400,400);
			primaryStage.setTitle("Recursive Art Generator");
			primaryStage.setHeight(height);
			primaryStage.setWidth(width);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void generateRects() {
		mainRect.getChildren().clear();
		try{
			RecursiveRectangles rectangles = new RecursiveRectangles(getLevels(), subdivs());
			mainRect.getChildren().addAll(rectangles.getRectangles());
			root.setCenter(mainRect);
		}
		catch(NumberFormatException e) {
			root.setCenter(new Text("Make sure level of subdivisions is a non-negative integer."));
		}
		
	}
	
	private int subdivs() {
		if(r13.isSelected()) {
			return 3;
		}
		else if (r14.isSelected()) {
			return 4;
		}
		else {
			return 5;
		}
	}
	
	private int getLevels() {
		if(Integer.parseInt(subdivtf.getText()) < 0) {
			throw new NumberFormatException();
		}
		return Integer.parseInt(subdivtf.getText());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
