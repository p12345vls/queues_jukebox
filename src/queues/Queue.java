package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Objects of type Queue manage items in a singly linked list where
 * they enqueue() from the front and dequeue() items from the end of the queue.
 *
 * @param <T> the generic type
 */
public class Queue<T> implements Iterable<T> {
	
	/** The size of the queue. */
	private int size;
	
	/** The head of the queue. */
	private Node head;
	
	/** The tail of the queue. */
	private Node tail;
	
	/** The genre name. */
	private String genreName;

	/**
	 * The Class Node.
	 */
	private class Node {
		
		/** The data of the node. */
		private T data;
		
		/** The next node. */
		private Node next;

		/**
		 * Instantiates a new node.
		 *
		 * @param data the song
		 */
		public Node(T data) {
			this.data = data;

		}

	}

	/**
	 * Instantiates a new queue.
	 */
	public Queue() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Instantiates a new queue.
	 *
	 * @param genreName the genre name
	 */
	public Queue(String genreName) {
		this.genreName = genreName;
	}

	/**
	 * Checks if the queue empty.
	 *
	 * @return true, if it is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * the size of the queue
	 *
	 * @return the size
	 */
	public int size() {
		return size;
	}


	/** 
	 * @return the song least recently added to this queue
	 */
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		return head.data;
	}

	/**
	 * adding songs to the queue.
	 *
	 * @param data the songs
	 */
	public void enqueue(T data) {
		Node currTail = tail;
		tail = new Node(data);
		tail.next = null;
		if (isEmpty())
			head = tail;
		else
			currTail.next = tail;
		size++;
	}

	/**
	 * Removes and returns the item on this queue that was least recently added.
	 * 
	 * @return the item on this queue that was least recently added
	 * @throws NoSuchElementException if this queue is empty
	 *             
	 */
	public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T data = head.data;
		head = head.next;
		size--;
		if (isEmpty())
			tail = null;
		return data;
	}

	/**
	 * Returns a string representation of this queue.
	 * 
	 * @return the sequence of items
	 */
	@Override
	public String toString() {

		if (isEmpty()) {
			return "[ ]";
		}

		Node position = this.head;
		String result = "-- ";
		int size = size();
		while (position != null) {
			String hyphen = size == 1 ? "" : "-- ";
			size--;
			result += position.data + hyphen;
			position = position.next;
		}

		return result;
	}

	/**
	 * @return the name of the songs if a particular genrelist is not empty
	 * else returns the name of the genre
	 */
	public T getName() {
		if (isEmpty()) {
			return (T) genreName;

		}
		return this.head.data;
	}

	/**
	 * returns a new iterator
	 */
	public ListIterator iterator() {
		return new ListIterator();
	}

	/**
	 * The Class ListIterator.
	 */
	private class ListIterator implements Iterator<T> {
		
		/** The current node of the list. */
		private Node current = head;

		/**
		 * returns true if the next item of the list is not null 
		 * 
		 */
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * returns the next item of the list 
		 */
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			T data = current.data;
			current = current.next;
			return data;
		}
	}

}
