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
	
// Some map variables that can be changed with CLI arguments.
	public static String WALL = "0";
	public static String PATH = "1";
	public static int MAZE_WIDTH = 10;
	public static int MAZE_HEIGHT = 10;
	
	//  How we will parse our lines in the file.
	// Uncomment for CSV file
	//public static String separator = ",";
	// For non-CSV file
	public static String separator = "(?!^)";

	//  Path to file
	public static String filepath;
	
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
					return new MazePath(i, z);
				}
			}
		}
		return null;
	}
	
	// Loop through the found paths to determine if the path
	// is already in the list to prevent circles.

	
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
	
//-----------------------------------------
// MAIN
//-----------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Read map filename from CLI.
		if (args.length > 0 ) {
		filepath = args[0];
		}
		else {
			System.out.println("Map filename must be provided");
			System.exit(1);
		}
		
		// Reverse role of 1/0's.
		if (args.length == 2) {
			if (args[1].equals("r")) {
			System.out.println("Wall/Path switch activated");
			WALL = "1";
			PATH = "0";
			}
		}
		
		// Change Dimensions from standard 10x10
		if (args.length == 4) {
			System.out.println("Setting New Dimensions:");
			try {
				MAZE_WIDTH = Integer.parseInt(args[2]);
				MAZE_HEIGHT = Integer.parseInt(args[3]);
				if (MAZE_WIDTH <= 2 || MAZE_HEIGHT <= 2) {
					throw null;
				}
			}
			catch (Exception e) {
				System.out.print("Arguments 3 and 4 must be integers greater than 2");
			}
		}
		if (args.length ==  3) {
			System.out.println("Setting New Dimensions:");
			try {
				MAZE_WIDTH = Integer.parseInt(args[1]);
				MAZE_HEIGHT = Integer.parseInt(args[2]);
				if (MAZE_WIDTH <= 2 || MAZE_HEIGHT <= 2) {
					throw null;
				}
			}
			catch (Exception e) {
				System.out.print("Arguments 2 and 3 must be integers greater than 2");
				System.exit(1);
			}
		}
		// Some boolean values to implement program logic.

			boolean moves_left = true;
			boolean solved = false;
			boolean valid_path_found = false;
			boolean circle_path;
		// Create our LinkedStack list.
		LinkedStack answerList = new LinkedStack<LLNode<MazePath>>();
		// Read input from file and create 2D array
		readMap();
		
		
		// Debugging method for creating simple map:
		//createMap();
		
		// Determine Starting position.
		MazePath myPath = findStart();
		answerList.push(myPath);
		// Determine if there was a valid starting point.
		if (myPath == null) {
			moves_left = false;
		}
		else moves_left = true;
		
		// Initialize some Temp variables for path tiles
		MazePath tempPath = null;
		int nextx = 0, nexty = 0;
		
		
		// Main loop to look through maze.
		while (moves_left && !solved) {
			// Retrieve top element from our list
			MazePath topPath = (MazePath) answerList.top();
			
			
			// Try a direction
			valid_path_found = false;
			
			while (!valid_path_found && topPath.r == false) {
				if (topPath.u == false) {
					topPath.u = true;
					valid_path_found = validatePath(0, topPath, myMap);
					nextx = topPath.x - 1;
					nexty = topPath.y;
				}
				else if (topPath.d == false) {
					topPath.d = true;
					valid_path_found = validatePath(2, topPath, myMap);
					nextx = topPath.x + 1;
					nexty = topPath.y;
				}
				else if (topPath.l == false) {
					topPath.l = true;
					valid_path_found = validatePath(1, topPath, myMap);
					nextx = topPath.x;
					nexty = topPath.y - 1;
				}
				else if (topPath.r == false) {
					topPath.r = true;
					valid_path_found = validatePath(3, topPath, myMap);
					nextx = topPath.x;
					nexty = topPath.y + 1;
				}
			}
			
			if (valid_path_found) {
				circle_path = false;
				// Create a temporary path object using our new valid x and y.
				// This will be compared to all path objects in answerList
				// to determine if we're going in a circle, which isn't allowed.
				tempPath = new MazePath(nextx, nexty);
				
				// Create a new list to store our objects while popping the answerList.
				LinkedStack copyList = new LinkedStack<LLNode<MazePath>>();
				// Check for existing path
				MazePath p;
				while (!answerList.isEmpty()) {
					p = (MazePath) answerList.top();
					copyList.push(p);
					if (p.x == tempPath.x && p.y == tempPath.y) {
						circle_path = true;
					}
					answerList.pop();
				}
				
				// Let's put the elements back into answerList.
				while (!copyList.isEmpty()) {
					p = (MazePath) copyList.top();
					answerList.push(p);
					copyList.pop();
				}
				
				// The newly discovered path tile is valid
				// and does not exist in the answerList.
				// We add it to stack with push().
				if (!circle_path) {
					answerList.push(tempPath);
				// Check for EXIT
					solved = checkForAnswer(myMap, tempPath.x, tempPath.y);
				}
			}

			else {
				//myPath = myPath.previous;
				answerList.pop();
				if (answerList.isEmpty()) {
					moves_left = false;
				}
			}
			
		}
		
		 // No valid moves left
		 if (!moves_left) {
			 System.out.println("There doesn't seem to be a solution");
		 }
		
		 
		 if (solved) {
			 //Format the output
			 System.out.println("Solution: ");
			 System.out.println("-----");
			 System.out.println("Start");
			 System.out.println("-----");
			 
			 // We're going to copy the list into a reverse list for output.
			 LinkedStack copyList2 = new LinkedStack<LLNode<MazePath>>();
			 while (!answerList.isEmpty()) {
				MazePath answerPath = (MazePath) answerList.top();
				copyList2.push(answerPath);
				//System.out.println(answerPath.x + ", " + answerPath.y);
				answerList.pop();
			 }
			 // Pop copyList2 to get our output.
			 while (!copyList2.isEmpty()) {
				MazePath answerPath2 = (MazePath) copyList2.top();
				System.out.println(answerPath2.x + ", " + answerPath2.y);
				copyList2.pop();
			 }
			 System.out.println("-----");
			 System.out.println("END");
			 System.out.println("-----");
		 }
	}

}
