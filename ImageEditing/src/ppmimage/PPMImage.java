package ppmimage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.scene.paint.Color;

public class PPMImage {
	private String magicNumber = "";
	private int width;
	private int height;
	private int maxColorValue;
	private char[][][] raster;
	private char[][][] rastercopy;
	
	PPMImage(File image) {
		readImage(image);
	}
	public void writeImage(File file) {
		if(!file.getName().substring(file.getName().length() - 4).equals(".ppm")) {
			throw new FileNotPPMException("File not .ppm");
		}
		try {
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
			output.write(magicNumber.charAt(0));
			output.write(magicNumber.charAt(1));
			output.write('\n');
			String widthstr = Integer.toString(width);
			String heightstr = Integer.toString(height);
			for(int i = 0; i < widthstr.length(); i++) {
				output.write(widthstr.charAt(i));
			}
			output.write(' ');
			for(int i = 0; i < heightstr.length(); i++) {
				output.write(heightstr.charAt(i));
			}
			output.write('\n');
			String max = Integer.toString(maxColorValue);
			for(int i = 0; i < max.length(); i++) {
				output.write(max.charAt(i));
			}
			output.write('\n');
			for (int i = 0; i < raster.length; i++) {
				for (int j = 0; j < raster[i].length; j++) {
					for (int k = 0; k < raster[i][j].length; k++) {
						output.write(raster[i][j][k]);
					}
				}
			}
			output.close();
			System.out.println("Wrote Image!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	private void readImage(File image){
		if(!image.getName().substring(image.getName().length() - 4).equals(".ppm")) {
			throw new FileNotPPMException("File not .ppm");
		}
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(image));
			char bytechar = 0;
			String sizeString = "";
			try {
				while((bytechar = (char) input.read()) != '\n') {
					magicNumber += "" + bytechar;
				}
				while((bytechar = (char) input.read()) != '\n') {
					sizeString += "" + bytechar;
				}
				String[] sizes = sizeString.split(" ");
				width = Integer.parseInt(sizes[0]);
				height = Integer.parseInt(sizes[1]);
				String max = "";
				while((bytechar = (char) input.read()) != '\n') {
					max += "" + bytechar;
				}
				raster = new char[height][width][3];
				rastercopy = new char[height][width][3];
				maxColorValue = Integer.parseInt(max);
				char[] storeArr = new char[width*height*3];
				int count = 0;
				while((bytechar = (char) input.read()) != (char) -1) {
					storeArr[count] = bytechar;
					count++;
				}
				count = 0;
				for (int i = 0; i < raster.length; i++) {
					for (int j = 0; j < raster[i].length; j++) {
						for (int k = 0; k < raster[i][j].length; k++) {
							raster[i][j][k] = storeArr[count];
							rastercopy[i][j][k] = storeArr[count];
							count++;
						}
					}
				}
				System.out.println("Read Image!");
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}
	public void flipHorizontal() {
		int bottomrow = height - 1;
		for (int i = 0; i < bottomrow; i++) {
			for(int j = 0; j < width; j++) {
				for(int k = 0; k < 3; k++) {
					char temp = raster[i][j][k];
					raster[i][j][k] = raster[bottomrow][j][k];
					raster[bottomrow][j][k] = temp;
				}
			}
			bottomrow -= 1;
		}
		this.writeImage(new File("temp.ppm"));
	}
	public void flipVertical() {
		
		for (int i = 0; i < height; i++) {
			int rightcolumn = width - 1;
			for(int j = 0; j < rightcolumn; j++) {
				for(int k = 0; k < 3; k++) {
					char temp = raster[i][j][k];
					raster[i][j][k] = raster[i][rightcolumn][k];
					raster[i][rightcolumn][k] = temp;
				}
				rightcolumn -= 1;
			}
			
		}
		this.writeImage(new File("temp.ppm"));
	}
	public void grayscale() {
		for (int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				char r = raster[i][j][0];
				char g = raster[i][j][1];
				char b = raster[i][j][2];
				raster[i][j][0] = (char) ((r * .299) + (g * .587) + (b * .114));
				raster[i][j][1] = (char) ((r * .299) + (g * .587) + (b * .114));
				raster[i][j][2] = (char) ((r * .299) + (g * .587) + (b * .114));
			}
		}
		this.writeImage(new File("temp.ppm"));
	}
	public void sepia() {
		for (int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				char r = raster[i][j][0];
				char g = raster[i][j][1];
				char b = raster[i][j][2];
				if (((r * .393) + (g * .769) + (b * .189)) > 255) {
					raster[i][j][0] = 255;
				}
				else {
					raster[i][j][0] = (char) ((r * .393) + (g * .769) + (b * .189));
				}
				if (((r * .349) + (g * .686) + (b * .168)) > 255) {
					raster[i][j][1] = 255;
				}
				else {
					raster[i][j][1] = (char) ((r * .349) + (g * .686) + (b * .168));
				}
				if (((r * .272) + (g * .534) + (b * .131)) > 255) {
					raster[i][j][2] = 255;
				}
				else {
					raster[i][j][2] = (char) ((r * .272) + (g * .534) + (b * .131));
				}
			}
		}
		this.writeImage(new File("temp.ppm"));
	}
	public void negative() {
		for (int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				char r = raster[i][j][0];
				char g = raster[i][j][1];
				char b = raster[i][j][2];
				raster[i][j][0] = (char) (255 - r);
				raster[i][j][1] = (char) (255 - g);
				raster[i][j][2] = (char) (255 - b);
			}
		}
		this.writeImage(new File("temp.ppm"));
	}
	public void filterColors(double startDegree, double endDegree) {
		for (int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				char r = raster[i][j][0];
				char g = raster[i][j][1];
				char b = raster[i][j][2];
				if (!(Color.rgb(r,g,b).getHue() >= startDegree && Color.rgb(r,g,b).getHue() <= endDegree)) {
					raster[i][j][0] = (char) ((r * .299) + (g * .587) + (b * .114));
					raster[i][j][1] = (char) ((r * .299) + (g * .587) + (b * .114));
					raster[i][j][2] = (char) ((r * .299) + (g * .587) + (b * .114));
				}
			}
		}
		this.writeImage(new File("temp.ppm"));
	}
	public void filterRed() {
		for (int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				char r = raster[i][j][0];
				char g = raster[i][j][1];
				char b = raster[i][j][2];
				if (!(Color.rgb(r,g,b).getHue() >= 345 || Color.rgb(r,g,b).getHue() <= 30)) {
					raster[i][j][0] = (char) ((r * .299) + (g * .587) + (b * .114));
					raster[i][j][1] = (char) ((r * .299) + (g * .587) + (b * .114));
					raster[i][j][2] = (char) ((r * .299) + (g * .587) + (b * .114));
				}
			}
		}
		this.writeImage(new File("temp.ppm"));
	}
	public void filterBlue() {
		this.filterColors(177,260);
		this.writeImage(new File("temp.ppm"));
	}
	public void filterGreen() {
		this.filterColors(65,150);
		this.writeImage(new File("temp.ppm"));
	}
	public void reset() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				for (int k = 0; k < 3; k++) {
					raster[i][j][k] = rastercopy[i][j][k];
				}
			}
		}
		this.writeImage(new File("temp.ppm"));
	}
}
