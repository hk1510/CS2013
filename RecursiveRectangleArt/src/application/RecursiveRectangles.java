package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RecursiveRectangles {
	
	ArrayList<Rectangle> rectangles = new ArrayList<>();
	
	public RecursiveRectangles(int levels, int subdivisions) {
		rectangles.addAll(createRectangles(levels, subdivisions, 1200, 700, new ArrayList<Rectangle>(), 0 , 0));
	}
	
	private ArrayList<Rectangle> createRectangles(int level, int subdivision, double width, double height, ArrayList<Rectangle> rects, double x, double y) {
		Random random = new Random();
		if (level == 0) {
			ArrayList<Rectangle> baseList = new ArrayList<>();
			Rectangle r1 = new Rectangle(x - (width/2), y - (height/2), width, height);
			r1.setFill(Color.hsb((r1.getWidth()*r1.getHeight()) % 361, 1, 1));
			r1.setStroke(Color.rgb(0, 0, 0));
			r1.setStrokeWidth(1);
			baseList.add(r1);
			return baseList;
		}
		else if (height > width) {
			ArrayList<Rectangle> newList = new ArrayList<>();
			int chooseLine = random.nextInt(subdivision - 1) + 1;
			newList.addAll(createRectangles(level - 1, subdivision, width, (subdivision - chooseLine)*(height/subdivision), newList, x, y - (chooseLine*height)/(subdivision*2)));
			newList.addAll(createRectangles(level - 1, subdivision, width, chooseLine*(height/subdivision), newList, x, y + ((subdivision - chooseLine)*height)/(subdivision*2)));
			return newList;
		}
		else if (width > height) {
			ArrayList<Rectangle> newList = new ArrayList<>();
			int chooseLine = random.nextInt(subdivision - 1) + 1;
			newList.addAll(createRectangles(level - 1, subdivision, (subdivision - chooseLine)*(width/subdivision), height, newList, x - (chooseLine*width)/(subdivision*2), y));
			newList.addAll(createRectangles(level - 1, subdivision, chooseLine*(width/subdivision), height, newList, x + ((subdivision - chooseLine)*width)/(subdivision*2), y));
			return newList;
		}
		else {
			if(random.nextBoolean()) {
				ArrayList<Rectangle> newList = new ArrayList<>();
				int chooseLine = random.nextInt(subdivision - 1) + 1;
				newList.addAll(createRectangles(level - 1, subdivision, width, (subdivision - chooseLine)*(height/subdivision), newList, x, y - (chooseLine*height)/(subdivision*2)));
				newList.addAll(createRectangles(level - 1, subdivision, width, chooseLine*(height/subdivision), newList, x, y + ((subdivision - chooseLine)*height)/(subdivision*2)));
				return newList;
			}
			else {
				ArrayList<Rectangle> newList = new ArrayList<>();
				int chooseLine = random.nextInt(subdivision - 1) + 1;
				newList.addAll(createRectangles(level - 1, subdivision, (subdivision - chooseLine)*(width/subdivision), height, newList, x - (chooseLine*width)/(subdivision*2), y));
				newList.addAll(createRectangles(level - 1, subdivision, chooseLine*(width/subdivision), height, newList, x + ((subdivision - chooseLine)*width)/(subdivision*2), y));
				return newList;
			}
		}
	}
	
	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}
}
