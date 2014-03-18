package assignment2;

public class MazePath {

	public int x,y; 
	public boolean l,r,u,d;
	public MazePath previous;
	
	MazePath (MazePath previous, int x, int y) {
		this.previous = previous;
		this.x = x;
		this.y = y;
		l = false;
		r = false;
		u = false;
		d = false;
	}
}
