/**
 * 
 */
package cs2321;

import java.lang.reflect.Array;

import net.datastructures.Queue;

/**
 * @author Caden Kienitz
 * @param <E>
 *CS2321 Program1
 *Description: This class creates a queue using a circular array
 */

public class CircularArrayQueue<E> implements Queue<E> {
	public int front, end, size;
	public E[] queue;
	public int capacity;
	     
	/*
	 * Constructor to create the queue
	 * @param: the size of the queue
	 */
	public CircularArrayQueue(int queueSize) {
		queue = (E[]) new Object[queueSize];
		this.size = 0;
		this.front = 0;
		this.end = 0;
		this.capacity = queueSize;
	}
	
	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}


	@Override
	public E first() {
		if(queue[front] == null) {    //Check if the first element in the queue is null
			return null;
		}
		else {
			return queue[front];
		}
		
	}

	@Override
	public E dequeue() {
		E temp;        //temporary variable to store the element being dequeued
		if(size() == 0) {
			return null;
		}
		temp = queue[front];    //set equal to the element being dequeued
		front++;
		if(front == capacity) {
			front = 0;
		}
		size --;
		return temp;
	}

	/* Throw the exception IllegalStateException when the queue is full */
	@Override
	public void enqueue(E e) throws IllegalStateException {
		if(size()==queue.length) {                   //If the amount of elements is equal to the capacity
			throw new IllegalStateException();     
		}
		else {
			queue[end] = e;
			end++;
			size++;
			if(end == capacity) {     //if the end of the queue is at the capacity,
				end = 0;              // wrap it around to 0
			}
			
		}
	}   
}
