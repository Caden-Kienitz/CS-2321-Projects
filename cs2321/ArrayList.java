package cs2321;

import java.util.Iterator;

import net.datastructures.List;
/*
 * @author:Caden Kienitz
 * CS2321 Program 2
 * Description: This class implements a list as an arrayList
 */
public class ArrayList<E> implements List<E> {
	public static int cap = 16;
	private E[ ] data;
	private int size = 0;



	private class ArrayListIterator implements Iterator<E> {
		int i = 0;

		@Override
		public boolean hasNext() {
			return i < size;
		}

		@Override
		public E next() {
			return data[i++];
		}

	}  //------End of nested class -------

	/*
	 * Constructor of arraylist with default capacity of 16
	 */
	public ArrayList() {
		data = (E[]) new Object[16];
	}

	/*
	 * Constructor of arraylist with given capacity
	 * @param: Capacity of arraylist
	 */
	public ArrayList(int capacity) {
		data = (E[]) new Object[capacity];

	}

	@Override
	public int size() {
		return size;
	}

	// Return the current capacity of array, not the number of elements.
	// Notes: The initial capacity is 16. When the array is full, the array should be doubled. 
	public int capacity() {
		return cap;
	}


	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i >= size + 1) {              //Check that the index is in bounds
			throw new IndexOutOfBoundsException(); // this if statement is used in many methods
		}
		return data[i];

	}

	/*
	 * Sets the element at a certain index
	 */
	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		if (i < 0 || i >= size + 1) {
			throw new IndexOutOfBoundsException();
		}
		E temp = data[i];
		data[i] = e;
		return temp;
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		if (i < 0 || i >= size+1) {
			throw new IndexOutOfBoundsException();
		}
		if(size == data.length) {     //check if the array is at capacity
			incSize(2*data.length);   //If so, creates new array of double capacity with helper method
		}
		for(int k = size - 1; k >= i; k--) {
			data[k+1] = data[k];
		}
		data[i] = e;
		size++;

	}
	/*
	 * Helper method to create a new ArrayList of increased capacity
	 */
	protected void incSize(int capacity) {
		E[] temp = (E[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = data[i];
		}
		data = temp;
		cap = cap * 2;
	}

	/*
	 * Removes an element at a given index
	 */
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}
		E temp = data[i];
		for (int k=i; k < size-1; k++)
			data[k] = data[k+1];
		data[size - 1] = null;
		size--; 
		return temp;
	}


	/*
	 * These methods all use the add method to complete their task
	 */
	public void addFirst(E e)  {
		this.add(0,e);
	}

	public void addLast(E e)  {
		this.add(size,e);
	}

	public E removeFirst() throws IndexOutOfBoundsException {
		return this.remove(0);
	}

	public E removeLast() throws IndexOutOfBoundsException {
		return this.remove(size-1);
	}


	/*
	 * Create a new arraylist iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}



}
