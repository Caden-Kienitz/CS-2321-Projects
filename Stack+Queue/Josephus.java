package cs2321;

/*
 * @author: Caden Kienitz
 * Date: Sept. 21, 2022
 * CS2321 Program1
 * Description: This class implements a queue to play Josephs game
 */
public class Josephus{
	/**
	 * All persons sit in a circle. When we go around the circle, initially starting
	 * from the first person, then the second person, then the third... 
	 * we count 1,2,3,.., k-1. The next person, that is the k-th person is out. 
	 * Then we restart the counting from the next person, go around, the k-th person 
	 * is out. Keep going the same way, when there is only one person left, she/he 
	 * is the winner. 
	 *  
	 * @parameter persons  an array of string which contains all player names.
	 * @parameter k  an integer specifying the k-th person will be kicked out of the game
	 * @return return a array in the order when the players were out of the game. 
	 *         The last one in the array is the winner.  
	 */
	public static String[] order(String[] persons, int k ) {
		String[] endOrder = new String[persons.length];
		int c = -1;         //counter to increment endQueue when adding kth person
		CircularArrayQueue<String> queue = new CircularArrayQueue<String>(persons.length);
		for(int i = 0; i < persons.length; i++) {
			queue.enqueue(persons[i]);        //load the persons array into the queue
			
		}
		while(queue.size() > 1) {
			for( int i = 0; i< k-1; i++) {
				String e = queue.dequeue();     
				queue.enqueue(e);
				
			}
			String out = queue.dequeue();    //set temporary variable to the person out of the game
			c++;                 //increment counter 
			endOrder[c] = out;     //add the out person to end array
		}
		endOrder[c+1] = queue.dequeue();        //add the final person
		return endOrder;
	}
}
