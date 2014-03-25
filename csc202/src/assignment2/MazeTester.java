package assignment2;

public class MazeTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MazePath tempPath = new MazePath( 1, 0);
		LinkedStack answerList = new LinkedStack<LLNode<MazePath>>();
		answerList.push(tempPath);
		MazePath t2 = (MazePath) answerList.top();
		System.out.println(t2.x);
	}

}
