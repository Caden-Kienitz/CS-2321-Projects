package cs2321;
import java.util.Iterator;
import net.datastructures.Position;
import net.datastructures.PositionalList;

/*
 * @author:Caden Kienitz
 * CS2321 Program 2
 * Description: This class implements a positional list as a doubly linked list
 */
public class DoublyLinkedList<E> implements PositionalList<E> {


	/* 
	 * Node class which should contain element and pointers to previous and next nodes
	 */
	public static class Node<E> implements Position<E> {
		private Node<E> next;     //The next node
		private Node<E> prev;     // The previous node
		private E element;         //element to be assigned to that node

		/*
		 * Constructor for new node
		 */
		public Node(Node<E> p, Node<E> n, E e) {
			prev = p;
			next = n;
			element = e;
		}
		/*
		 * Methods to get different data
		 */
		@Override
		public E getElement() {
			return element;
		}

		public Node<E> getPrev() {
			return prev;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setElement(E e) {
			element = e;
		}
		public void setPrev(Node<E> p) {
			prev = p;
		}
		public void setNext(Node<E> n) {
			next = n;
		}
		// --------End of nested class ---------

	}

	/*
	 *Element iterator will return one element at a time  
	 */
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posIterator =	new	PositionIterator( );  //Use a position iterator 

		public	boolean	hasNext( )	{
			return	posIterator.hasNext( );	
		}

		public	E	next( )	{	
			return	posIterator.next( ).getElement( );	
		}

	} //--------- end of nested class ------

	/*
	 * Position iterator will return one Position at a time  
	 */
	private class PositionIterator implements Iterator<Position<E>> {
		private Position<E> cursor = first();
		private Position<E> last = null;

		@Override
		public boolean hasNext() {
			return (cursor != null);
		}

		@Override
		public Position<E> next() {
			last = cursor;
			cursor = after(cursor);
			return last;
		}

	} // -------- End of nested class ----------

	/*
	 * Position iterator will return one Position at a time  
	 */
	private class PositionIterable implements Iterable<Position<E>> {

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}

	}
	private Node<E> head;
	private Node<E> tail;
	private int size = 0;

	/*
	 * Create sentinal nodes to be the head and tail but will always be null
	 */
	public DoublyLinkedList() {
		head = new Node<>(null, null, null);      
		tail = new Node<>(head, null, null);
		head.setNext(tail);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (head.next == tail) {
			return true;
		}
		return false;
	}

	/*
	 * Validates the position
	 * @param:The position to be validated
	 * @return: returns the position as a node
	 */
	private Node<E> validate(Position<E> p) throws IllegalArgumentException{
		if (!(p instanceof Node)){
			throw new IllegalArgumentException("Invalide position");			
		}

		Node<E> node = (Node<E>) p;
		if(node.getNext() == null) {
			throw new IllegalArgumentException("p has been removed from the list");
		}
		return node;
	}

	/*
	 * Checks if the parameter is one of the sentinel nodes
	 * User should not have access to these nodes
	 */
	private Position<E> position(Node<E> node){
		if (node == head || node == tail) {
			return null;
		}
		return node;
	}

	/*
	 * The first node in the list
	 */
	@Override
	public Position<E> first() {
		return position(head.getNext());
	}

	/*
	 * The last node in the list
	 */
	@Override
	public Position<E> last() {
		return position(tail.getPrev());
	}

	/*
	 * returns the node before the parameter in the list
	 */
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	/*
	 * returns the node after the parameter in the list
	 */
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}

	/*
	 * Helper method to add a node between two nodes
	 * Used for methods like addFirst, addLast
	 * @param1: The node that will be previous to the new node
	 * @param2: The node that will be after to the new node
	 * @param3: The element to be assigned to the new node
	 */
	private Position<E> addBetween(Node<E> pos1, Node<E> pos2, E e){
		Node<E> node = new Node(pos1, pos2, e);
		pos1.setNext(node);
		pos2.setPrev(node);
		size++;
		return node;
	}

	@Override
	public Position<E> addFirst(E e) {
		return addBetween(head, head.getNext(), e);
	}

	@Override
	public Position<E> addLast(E e) {
		return addBetween(tail.getPrev(), tail, e);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(node.getPrev(), node, e);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(node, node.getNext(), e);
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E lastElement = node.getElement();
		node.setElement(e);
		return lastElement;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> next = node.getNext();
		Node<E> prev = node.getPrev();
		prev.setNext(next);             //change the reference to next and previous
		next.setPrev(prev);             //for the nodes before and after the position
		size --;
		
		//Set all data of the node being removed to null so it can not be accessed
		E temp = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);	
		return temp;
	}


	public E removeFirst() throws IllegalArgumentException{
		return remove(head.getNext());
	}

	public E removeLast() throws IllegalArgumentException {
		return remove(tail.getPrev());
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	/*
	 * returns the list as an array of objects
	 */
	@Override
	public Object [] toArray() {
		E[] array = (E[]) new Object[this.size()];    //Initialize new array
		Iterator<E> iterator = iterator();
		for (int i = 0; i < array.length; i++) {     //iterate through list and transfer to array
			array[i] = iterator.next();
		}
		return array;
	}
}
