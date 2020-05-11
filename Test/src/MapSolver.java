import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

public class MapSolver {
	private char[][] map;
	private char[][] testMap;
	private int startRow;
	private int startCol;
	
	public MapSolver(File mapFile) {
		fileReader(mapFile);
	}
	
	public void fileReader(File mapFile) {
		//Determine whether or not file is a .txt file
		try {
			if(!mapFile.getName().substring(mapFile.getName().length() - 4).equals(".txt")) {
				throw new FileNotFoundException("The file you entered was not a .txt file. Try Again!");
			}
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
		
		//Load File and get core information out of it
		try {
			//Get dimensions
			FileInputStream inputFile = new FileInputStream(mapFile);
			String dimensions = "";
			char temp;
			while((temp = ((char) inputFile.read())) != '\n') {
				dimensions += temp;
			}
			
			//Get coordinates of start position
			String startCoords = "";
			char temp2;
			while((temp2 = ((char) inputFile.read())) != '\n') {
				startCoords += temp2;
			}
			
			//Make 2D array out of the dimensions in the file
			String[] coords = dimensions.split(" ");
			int rows = Integer.parseInt(coords[0]);
			int cols = Integer.parseInt(coords[1].trim());
			map = new char[rows][cols];
			testMap = new char[rows][cols];
			
			//Defining the x and y coordinates of the start of the path
			String[] splitStartCoords = startCoords.split(" ");
			this.startRow = Integer.parseInt(splitStartCoords[0]);
			this.startCol = Integer.parseInt(splitStartCoords[1].trim());
			
			//Putting Map in the 2D array
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map[i].length; j++) {
					map[i][j] = testMap[i][j] = (char) inputFile.read();
				}
				inputFile.skip(2);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public Stack<int[]> pathMaker() {
		Stack<int[]> path = new Stack<int[]>();
		
		int count = 0;
		
		int currRow = this.startRow;
		int currCol = this.startCol;
		
		while(testMap[currRow][currCol] != 'E') {
			count++;
			if(currRow - 1 >= 0) {
				if((testMap[currRow - 1][currCol] == '1' || testMap[currRow - 1][currCol] == 'E')) {
					int[] newEl = {currRow - 1, currCol};
					path.push(newEl);
					testMap[currRow][currCol] = 'X';
					currRow = currRow - 1;
					continue;
				} 
			}
			if(currRow + 1 <= testMap.length) {
				if((testMap[currRow + 1][currCol] == '1' || testMap[currRow + 1][currCol] == 'E')) {
					int[] newEl = {currRow + 1, currCol};
					path.push(newEl);
					testMap[currRow][currCol] = 'X';
					currRow = currRow + 1;
					continue;
				} 
			} 
			if(currCol - 1 >= 0) {
				if((testMap[currRow][currCol - 1] == '1' || testMap[currRow][currCol - 1] == 'E')) {
					int[] newEl = {currRow, currCol - 1};
					path.push(newEl);
					testMap[currRow][currCol] = 'X';
					currCol = currCol - 1;
					continue;
				}
			} 
			if(currCol + 1 <= testMap[0].length) {
				if((testMap[currRow][currCol + 1] == '1' || testMap[currRow][currCol + 1] == 'E')) {
					int[] newEl = {currRow, currCol + 1};
					path.push(newEl);
					testMap[currRow][currCol] = 'X';
					currCol = currCol + 1;
					continue;
				}
			}
			testMap[currRow][currCol] = 'X';
			
			if(count > 0 && path.size() == 0) {
				System.out.println("Sorry! There is no solution to this map!");
				System.exit(1);
			}
			
			path.pop();
			currRow = path.peek()[0];
			currCol = path.peek()[1];
		}
		
		return path;
	}
	public void pathPrinter() {
		Stack<int[]> pathStack = pathMaker();
		
		while(pathStack.size() != 0) {
			map[pathStack.peek()[0]][pathStack.peek()[1]] = 'X';
			pathStack.pop();
		}
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}
	}
}