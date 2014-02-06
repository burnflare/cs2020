package cs2020;

/**
 * The interface for implementing a Queue ADT.
 * 
 * @author Joel Low <joel.low@nus.edu.sg>
 * @param T The type of the elements in this queue.
 */
public interface IQueue<T> {
	/**
	 * Enqueues an item into the back of the queue.
	 * 
	 * The length of the queue must increase by one after this function returns
	 * successfully.
	 * 
	 * @param item The item to enqueue.
	 */
	public void Enqueue(T item);
	
	/**
	 * Dequeues an item from the front of the queue.
	 * 
	 * The length of the queue must decrease by one after this function returns
	 * successfully. If the queue is already empty, then the length of the queue
	 * does not change.
	 * 
	 * @return The element at the front of the queue. Return null if the queue
	 *         is already empty.
	 */
	public T Dequeue();
	
	/**
	 * Checks whether the queue is currently empty.
	 * 
	 * @return True if the queue is empty.
	 */
	public boolean IsEmpty();
	
	/**
	 * Get the number of elements currently within this queue.
	 * 
	 * @return The number of elements currently within the queue.
	 */
	public int GetLength();
}
