package assignment2;

public class MazePath {

	int x,y; 
	boolean l,r,u,d;
	MazePath previous;
	
	MazePath (MazePath previous, int x, int y) {
		this.x = x;
		this.y = y;
	}
}
