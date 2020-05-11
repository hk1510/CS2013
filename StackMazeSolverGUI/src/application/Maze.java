package application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

public class Maze {
	
	private char[][] maze;
	private char[][] mazecopy;
	private int[] start = new int[2];
	private Stack<int[]> solution = new Stack<int[]>();
	
	public Maze(File file) throws IOException, NumberFormatException, NullPointerException{
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		char bytechar = 0;
		
		String str = "";
		while ((bytechar = (char)in.read()) != '\n') {
			str += bytechar;
		}
		String[] sizes = str.split(" ");

		maze = new char[Integer.parseInt(sizes[0].trim())][Integer.parseInt(sizes[1].trim())];
		mazecopy = new char[Integer.parseInt(sizes[0].trim())][Integer.parseInt(sizes[1].trim())];
		
		str = "";
		while ((bytechar = (char)in.read()) != '\n') {
			str += bytechar;
		}
		String[] startstr = str.split(" ");
		start[0] = Integer.parseInt(startstr[0].trim());
		start[1] = Integer.parseInt(startstr[1].trim());
		
		str = "";
		while((bytechar = (char)in.read()) != (char) -1) {
			str += bytechar;
		}
		String[] rows = str.split("\n");
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				maze[i][j] = rows[i].charAt(j);
				mazecopy[i][j] = rows[i].charAt(j);
			}
		}
	}
	
	public boolean solve() {

		int[] current = start;
		int[] firstmove = {current[0], current[1]};
		solution.push(firstmove);
		
		while (true) {
			
			//Maze is solved when E is reached
			if(maze[current[0]][current[1]] == 'E') {
				return true;
			}
			//marks current position as visited
			maze[current[0]][current[1]] = '@';
			//check up
			if (validate(current, 0)) {
				current[0] -= 1;
				int[] move = {current[0], current[1]};
				solution.push(move);
			}
			//check right
			else if (validate(current, 1)) {
				current[1] += 1;
				int[] move = {current[0], current[1]};
				solution.push(move);
			}
			//check down
			else if (validate(current, 2)) {
				current[0] += 1;
				int[] move = {current[0], current[1]};
				solution.push(move);
			}
			//check left
			else if (validate(current, 3)) {
				current[1] -= 1;
				int[] move = {current[0], current[1]};
				solution.push(move);
			}
			//if all checks fail, then remove the most recent addition to path and check previous addition
			else {
				int[] popped = (int[]) solution.pop();
				//marks current position as visited and popped (used when debugging)
				maze[popped[0]][popped[1]] = '#';
				if(solution.isEmpty()) {
					return false;
				}
				current[0] = ((int[]) solution.peek())[0];
				current[1] = ((int[]) solution.peek())[1];
			}
			
		}
	}
	
	//check if a position is valid to move to from the current position
	// 0 - up, 1 - right, 2 - down, 3 - left
	//@ - visited, # - popped
	private boolean validate(int[] current, int pos) {
		switch(pos) {
			case 0:
				if (current[0] == 0) {
					return false;
				}
				else if (maze[current[0] - 1][current[1]] == '1') {
					return true;
				}
				else if (maze[current[0] - 1][current[1]] == 'E') {
					return true;
				}
				else {
					return false;
				}
			case 1:
				if (current[1] + 1 >= maze[current[0]].length) {
					return false;
				}
				else if (maze[current[0]][current[1] + 1] == '1') {
					return true;
				}
				else if (maze[current[0]][current[1] + 1] == 'E') {
					return true;
				}
				else {
					return false;
				}
			case 2:
				if (current[0] + 1 >= maze.length) {
					return false;
				}
				else if (maze[current[0] + 1][current[1]] == '1') {
					return true;
				}
				else if (maze[current[0] + 1][current[1]] == 'E') {
					return true;
				}
				else {
					return false;
				}
			case 3:
				if (current[1] == 0) {
					return false;
				}
				else if (maze[current[0]][current[1] - 1] == '1') {
					return true;
				}
				else if (maze[current[0]][current[1] - 1] == 'E') {
					return true;
				}
				else {
					return false;
				}
			default:
				return false;
		}
	}
	public String getSol() {
		for (int i = 1; i < solution.size() - 1; i++) {
			mazecopy[((int[])solution.get(i))[0]][((int[])solution.get(i))[1]] = '.';
		}
		StringBuilder string = new StringBuilder("");
		for (int i = 0; i < mazecopy.length; i++) {
		
			for (int j = 0; j < maze[i].length; j++) {
				if (j == maze[i].length - 1) {
					string.append(mazecopy[i][j] + "\n");
				}
				else {
					string.append(mazecopy[i][j] + " ");
				}
			}
			
		}
		return string.toString();
	}
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (j == maze[i].length - 1) {
					string.append(maze[i][j] + "\n");
				}
				else {
					string.append(maze[i][j] + " ");
				}
			}
		}
		return string.toString();
	}
	
}
