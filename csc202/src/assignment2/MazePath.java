package assignment2;
// This is a simple Java object that will store basic maze information for each new path discovered.
public class MazePath {

	public int x,y; 
	public boolean l,r,u,d;

	
	MazePath (int x, int y) {
		this.x = x;
		this.y = y;
		l = false;
		r = false;
		u = false;
		d = false;
	}
}
