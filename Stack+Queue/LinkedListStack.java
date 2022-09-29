package cs2321;

import net.datastructures.Stack;

public class LinkedListStack<E> implements Stack<E> {
	Node<E> top = null;
	int size = 0;

public static class Node<E>{
	E val;
	Node<E> next;
	
	public Node(E e){
		val = e;			
	}
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
		else{return false;}
	}

	@Override
	public void push(E e) {
		Node<E> n = new Node<E>(e);
		n.next = top;
		top = n;
		size ++;		
	}

	@Override
	public E top() {
		if(size() == 0) {
			return null;
		}
		return top.val;
	}

	@Override
	public E pop() {
		E temp;
		if(size() == 0) {
			return null;
		}
		else {
			temp = top.val;
			top = top.next;
			size --;
			return temp;
		}
		
	}	

}
