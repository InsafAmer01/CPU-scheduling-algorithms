package OS_Project;

import java.util.Arrays;

//implementation of Circular Queue using Generics
class CircularQueue<T> {

	private int currentSize; // Current Circular Queue Size
	private T[] circularQueueElements;
	private int maxSize; // Circular Queue maximum size

	private int rear;// rear position of Circular queue(new element enqueued at rear).
	private int front; // front position of Circular queue(element will be dequeued from front).

	@SuppressWarnings("unchecked")
	public CircularQueue(int maxSize) {
		this.maxSize = maxSize;
		circularQueueElements = (T[]) new Object[this.maxSize];
		currentSize = 0;
		front = -1;
		rear = -1;
	}

	/**
	 * Enqueue elements to rear.
	 */
	public void enqueue(T item) {
		if (isFull()) {
			System.out.println("Circular Queue is full. Element cannot be added");

		} else {
			rear = (rear + 1) % circularQueueElements.length;
			circularQueueElements[rear] = item;
			currentSize++;

			if (front == -1) {
				front = rear;
			}
		}
	}

	/**
	 * Dequeue element from Front.
	 */
	public T dequeue() {
		T deQueuedElement;
		if (isEmpty()) {
			System.out.println("Circular Queue is empty. Element cannot be retrieved"); 
			return null;
		} else {
			deQueuedElement = circularQueueElements[front];
			circularQueueElements[front] = null;
			front = (front + 1) % circularQueueElements.length;
			currentSize--;
		}
		return deQueuedElement;
	}

	/**
	 * Check if queue is full.
	 */
	public boolean isFull() {
		return (currentSize == circularQueueElements.length);
	}
	
	public int length() {
		return maxSize;
	}

	/**
	 * Check if Queue is empty.
	 */
	public boolean isEmpty() {
		return (currentSize == 0);
	}

	
	public T getFront () {
		return circularQueueElements[front] ;
	}
	
	public int getRear() {
		return rear;
	}

	public void setRear(int rear) {
		this.rear = rear;
	}

	@Override
	public String toString() {
		return Arrays.toString(circularQueueElements) ;
	}
	
	

}