package ppmimage;

import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application{
	double height = 1080;
	double width = 1920;
	double imageScale = 0.8;
	
	BorderPane root = new BorderPane();
	GridPane menu = new GridPane();
	
	Button chooseimg = new Button("Choose Image");
	Button saveimg = new Button("Save Image");
	Button resetimg = new Button("Reset Image");
	Button fliph = new Button("Flip Horizontal");
	Button flipv = new Button("Flip Vertical");
	Button gscale = new Button("Grayscale");
	Button sepia = new Button("Sepia");
	Button negative = new Button("Negative");
	Button redf = new Button("Red Filter");
	Button greenf = new Button("Green Filter");
	Button bluef = new Button("Blue Filter");
	Label start = new Label("Start Hue: ");
	TextField starttf = new TextField();
	Label end = new Label("End Hue: ");
	TextField endtf = new TextField();
	Button userf = new Button("Filter");
	
	PPMImage image = null;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			File file = new File("temp.ppm");
			file.delete();
			menu.add(chooseimg, 0, 0);
			menu.add(saveimg, 1, 0);
			menu.add(resetimg, 2, 0);
			menu.add(fliph, 3, 0);
			menu.add(flipv, 4, 0);
			menu.add(gscale, 5, 0);
			menu.add(sepia, 6, 0);
			menu.add(negative, 7, 0);
			menu.add(redf, 8, 0);
			menu.add(greenf, 9, 0);
			menu.add(bluef, 10, 0);
			menu.add(start, 11, 0);
			menu.add(starttf, 12, 0);
			menu.add(end, 13, 0);
			menu.add(endtf, 14, 0);
			menu.add(userf, 15, 0);
			menu.setAlignment(Pos.CENTER);
			menu.setHgap(10);
			
			root.setTop(menu);
			root.setCenter(new Text("Please choose an image to start editing."));
			
			
			chooseimg.setOnAction(e -> chooseImg(primaryStage));
			saveimg.setOnAction(e -> saveImg(primaryStage));
			resetimg.setOnAction(e -> reset());
			fliph.setOnAction(e -> fliph());
			flipv.setOnAction(e -> flipv());
			gscale.setOnAction(e -> gscale());
			sepia.setOnAction(e -> sepia());
			negative.setOnAction(e -> negative());
			redf.setOnAction(e -> redFilter());
			greenf.setOnAction(e -> greenFilter());
			bluef.setOnAction(e -> blueFilter());
			userf.setOnAction(e -> userFilter());
			
			Scene scene = new Scene(root);
			primaryStage.setTitle("PPM Editor");
			primaryStage.setHeight(height);
			primaryStage.setWidth(width);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private void chooseImg(Stage stage) {
		try {
			FileChooser choosefile = new FileChooser();
			File file = choosefile.showOpenDialog(stage);
			image = new PPMImage(file);
			image.writeImage(new File("temp.ppm"));
			showImg();
		} 
		catch (NullPointerException e) {
			showPrompt("File selection cancelled.");
		}
		catch (FileNotPPMException e) {
			showPrompt("File not of type .ppm");
		}     
	}
	private void saveImg(Stage stage) {
		FileChooser choosefile = new FileChooser();
		File file = choosefile.showSaveDialog(stage);
		try {
			image.writeImage(file);
			showPrompt("Image saved at " + file.getAbsolutePath());
		}
		catch (FileNotPPMException e) {
			showPrompt("File not of type .ppm");
		}
		catch (NullPointerException e) {
			showPrompt("No image to save or save cancelled.");
		}
		
	}
	private void reset() {
		if(image == null) {
			root.setCenter(new Text("No image is currently loaded."));
		}
		else {
			image.reset();
			showImg();
		}
		
	}
	private void fliph() {
		try {
			image.flipHorizontal();
			showImg();
		}
		catch(NullPointerException e) {
			root.setCenter(new Text("No image is currently loaded."));
		}
	}
	private void flipv() {
		try {
			image.flipVertical();
			showImg();
		}
		catch(NullPointerException e) {
			root.setCenter(new Text("No image is currently loaded."));
		}
	}
	private void gscale() {
		try {
			image.grayscale();
			showImg();
		}
		catch(NullPointerException e) {
			root.setCenter(new Text("No image is currently loaded."));
		}
	}
	private void sepia() {
		try {
			image.sepia();
			showImg();
		}
		catch(NullPointerException e) {
			root.setCenter(new Text("No image is currently loaded."));
		}	
	}
	private void negative() {
		try {
			image.negative();
			showImg();
		}
		catch(NullPointerException e) {
			root.setCenter(new Text("No image is currently loaded."));
		}
	}
	private void redFilter() {
		try {
			image.filterRed();
			showImg();
		}
		catch(NullPointerException e) {
			root.setCenter(new Text("No image is currently loaded."));
		}
	}
	private void blueFilter() {
		try {
			image.filterBlue();
			showImg();
		}
		catch(NullPointerException e) {
			root.setCenter(new Text("No image is currently loaded."));
		}
	}
	private void greenFilter() {
		try {
			image.filterGreen();
			showImg();
		}
		catch(NullPointerException e) {
			root.setCenter(new Text("No image is currently loaded."));
		}
	}
	private void userFilter() {
		try {
			double start = Double.parseDouble(starttf.getText());
			double end = Double.parseDouble(endtf.getText());
			if (start < 0 || end > 360) {
				showPrompt("Filter range needs to be between 0 and 360");
			}
			else if (start > end) {
				showPrompt("Make sure start hue is less than end hue.");
			}
			else {
				image.filterColors(start, end);
				showImg();
			}
			
		}
		catch(NumberFormatException e) {
			root.setCenter(new Text("Make sure to enter numbers for the filter range."));
		}
		catch(NullPointerException e) {
			root.setCenter(new Text("No image is currently loaded."));
		}
		
	}
	private void showImg() {
		try {
            ImageView iv = new ImageView(SwingFXUtils.toFXImage(ImageIO.read(new File("temp.ppm")), null));
            iv.preserveRatioProperty().setValue(true);
            iv.setFitWidth(root.getWidth() * this.imageScale);
            iv.setFitHeight(root.getHeight() * this.imageScale);
            root.setCenter(iv);
        } catch (IIOException f) {
        	Text blank = new Text("Please choose an image to start editing.");
        	root.setCenter(blank);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	private void showPrompt(String s) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(10);
		pane.add(new Text(s), 0, 0);
		Button back = new Button("Go back to editing");
		back.setOnAction(f -> showImg());
		pane.add(back, 0, 1);
		GridPane.setHalignment(pane.getChildren().get(1), HPos.CENTER);
		root.setCenter(pane);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
