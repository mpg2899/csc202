package assignment2;

public class PlayMaze {

	// Variables needed
	// previous moves
	// solution list
	public static final String START = "*";
	public static final String EXIT = "E";
	public static final String WALL = "0";
	public static final String PATH = "1";
	
	public static final int MAZE_WIDTH = 10;
	public static final int MAZE_HEIGHT = 10;
	
	public static boolean moves_left = true;
	public static boolean not_solved = true;
	
	public static String separator = ",";
	public static String tester = "Hi, hi2";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Read input from file and create 2D array
		String[] test1 = tester.split(separator);
		System.out.println(test1[1]);
		// Determine Starting position.
		moves_left = true;
		not_solved = true;
		// if no starting position
		/*
		 * moves_left = false;
		 *
		 */
		
		// Main loop to look through maze.
		while (moves_left && not_solved) {
			
		}
		
		 // No valid moves left
		 if (!moves_left) {
			 
		 }
		
		
		 if (!not_solved) {
			 
		 }
	}

}
