/*Michael Gugino
 * CSC 202
 * Assignment 2
 * 03/18/2014
 */
package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Exception;
public class PlayMaze {

// Define some constant values
	public static final String START = "*";
	public static final String EXIT = "E";
	public static final String WALL = "0";
	public static final String PATH = "1";
	
	public static final int MAZE_WIDTH = 10;
	public static final int MAZE_HEIGHT = 10;
	
// Some boolean values to implement program logic.
// These don't need to be public
	public static boolean moves_left = true;
	public static boolean not_solved = true;
	public static boolean solved = false;
	public static boolean valid_path_found = false;
	public static boolean circle_path;
	
	//  How we will parse our lines in the file.
	public static String separator = ",";

	//  Path to file
	public static String filepath = "map.txt";
	
	// Our map variable, holds our Maze data recorded from file.
	public static String[][] myMap = new String[MAZE_HEIGHT][MAZE_WIDTH];
	
	// Read in map file, or use our debugging map if we cannot open the file.
	public static void readMap() {
		
		try {
		 BufferedReader br = new BufferedReader(new FileReader(filepath));
		 String line;
		 int count = 0;
		   while ((line = br.readLine()) != null && count < MAZE_HEIGHT) {  
		  
		    String[] xline = line.split(separator);  
		    myMap[count] = xline;
		    count++;
		   }
		}
		catch (Exception e) {
			System.out.println("Cannot open file name.  Using default map.");
			createMap();
		}
	}
	
	// Useful debugging function to create a simple map.
	public static void createMap() {
		for (int i = 0; i < MAZE_HEIGHT; i++) {
			for (int z = 0; z < MAZE_WIDTH; z++) {
				myMap[i][z] = "0";
			}
		}
		myMap[4][5] = "1";
		myMap[4][6] = "1";
		myMap[5][5] = "*";
		myMap[5][6] = "1";
		myMap[6][6] = "1";
		myMap[6][5] = "1";
		myMap[6][4] = "E";
		System.out.println(myMap[1][5]);
	}
	
	// Find our starting position in the maze.
	public static MazePath findStart() {
		for (int i = 0; i < MAZE_HEIGHT; i++) {
			for (int z = 0; z < MAZE_WIDTH; z++) {
				if (myMap[i][z].equals(START)) {
					return new MazePath(null, i, z);
				}
			}
		}
		return null;
	}
	
	// Loop through the found paths to determine if the path
	// is already in the list to prevent circles.
	public static boolean pathExists(MazePath p, MazePath t) {
		boolean exists = false;
		while (p.previous != null) {
			if (p.previous.x == t.x && p.previous.y == t.y) {
				exists = true;
				break;
			}
			p = p.previous;
		}
		return exists;
	}
	
	// Make sure our selected direction is not a wall, and is inside our array dimensions.
	public static boolean validatePath(int direction, MazePath p, String[][] map) {
		// direction: 0 = up, 1 = left, 2 = down, 3 = right
		boolean isValid = false;
		if (direction == 0) {
			if (p.x - 1 >= 0)
				if (!map[p.x-1][p.y].equals(WALL)) 
					isValid = true;
			
		}
		if (direction == 1) {
			if (p.y - 1 >= 0)
				if (!map[p.x][p.y-1].equals(WALL)) 
					isValid = true;
			
		}
		if (direction == 2) {
			if (p.x + 1 < MAZE_HEIGHT)
				if (!map[p.x+1][p.y].equals(WALL))
					isValid = true;
			
		}
		if (direction == 3) {
			if (p.y + 1 < MAZE_WIDTH)
				if (!map[p.x][p.y+1].equals(WALL))
					isValid = true;
			
		}
		return isValid;
	}
	
	// Test to see if the currently selected path is an exit.
	// Return true if answer found.
	public static boolean checkForAnswer(String[][] map, int x, int y) {
		boolean answerFound;
		
		if (map[x][y].equals(EXIT)) {
			answerFound = true;
		}
		else answerFound = false;
		return answerFound;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Read input from file and create 2D array
		readMap();
		
		
		// Debugging method for creating simple map:
		//createMap();
		
		// Determine Starting position.
		MazePath myPath = findStart();
		
		// Determine if there was a valid starting point.
		if (myPath == null) {
			moves_left = false;
		}
		else moves_left = true;
		
		// Initialize some Temp variables for path tiles
		MazePath tempPath = null;
		int nextx = 0, nexty = 0;
		
		
		not_solved = true;
		
		// Main loop to look through maze.
		while (moves_left && !solved) {
	
			// Try a direction
			valid_path_found = false;
			
			while (!valid_path_found && myPath.r == false) {
				if (myPath.u == false) {
					myPath.u = true;
					valid_path_found = validatePath(0, myPath, myMap);
					nextx = myPath.x - 1;
					nexty = myPath.y;
				}
				else if (myPath.d == false) {
					myPath.d = true;
					valid_path_found = validatePath(2, myPath, myMap);
					nextx = myPath.x + 1;
					nexty = myPath.y;
				}
				else if (myPath.l == false) {
					myPath.l = true;
					valid_path_found = validatePath(1, myPath, myMap);
					nextx = myPath.x;
					nexty = myPath.y - 1;
				}
				else if (myPath.r == false) {
					myPath.r = true;
					valid_path_found = validatePath(3, myPath, myMap);
					nextx = myPath.x;
					nexty = myPath.y + 1;
				}
			}
			
			if (valid_path_found) {
				tempPath = new MazePath(myPath, nextx, nexty);
				// Check for existing path
				circle_path = pathExists(myPath, tempPath);
				if (!circle_path) {
					myPath = tempPath;
				// Check for answer
					solved = checkForAnswer(myMap, myPath.x, myPath.y);
				}
			}
			else if (myPath.previous == null) {
				moves_left = false;
			}
			else {
				myPath = myPath.previous;
			}
			
		}
		
		 // No valid moves left
		 if (!moves_left) {
			 System.out.println("There doesn't seem to be a solution");
		 }
		
		 
		 if (solved) {
			 System.out.println("Solution: ");
			 System.out.println("-----");
			 System.out.println("EXIT");
			 System.out.println("-----");
			 while (myPath.previous != null) {
				System.out.println(myPath.x + ", " + myPath.y);
				myPath = myPath.previous;
			 }
			 System.out.println(myPath.x + ", " + myPath.y);
			 System.out.println("-----");
			 System.out.println("Start");
			 System.out.println("-----");
		 }
	}

}
