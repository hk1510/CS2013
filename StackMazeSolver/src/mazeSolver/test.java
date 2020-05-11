package mazeSolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class test {
	public static void main(String[] args) {
		try {
			Maze maze1 = new Maze(new File("maze2.txt"));
			System.out.println(maze1);
			System.out.println(maze1.solve());
			System.out.println(maze1);
			maze1.printSol();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
