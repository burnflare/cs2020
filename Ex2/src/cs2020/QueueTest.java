package cs2020;

import org.junit.*;
import org.junit.Assert;

/**
 * Tests the Queue implementation.
 * 
 * To use this, ensure that your Queue class has a default constructor which
 * takes no parameters and returns an empty queue.
 * 
 * @author Joel Low <joel.low@nus.edu.sg>
 */
public class QueueTest {
	private IQueue<Integer> queue;
	private int[] Fixture = new int[] { 3, 2, 6, 4, 7, 1 };
	
	private void AddSomeItems() {
		int added = 0;
		for (int i: Fixture) {
			queue.Enqueue(i);
			++added;
			
			Assert.assertEquals("Enqueueing an item into the queue must " +
					"increase the length of the queue.", queue.GetLength(),
					added);
		}
	}
	
	@Before
	public void SetUp() {
		queue = new Queue<Integer>();
	}
	
	@Test
	public void TestInit() {
		Assert.assertEquals("The queue must start out empty.", 0, queue.GetLength());
	}
	
	@Test
	public void TestEnqueue() {
		AddSomeItems();
		
		Assert.assertFalse("After adding items into the queue,  it should not " +
				"report that it is empty.", queue.IsEmpty());
		Assert.assertEquals(String.format("After adding %d items to the queue, " +
				"it must report that length.", Fixture.length), Fixture.length,
				queue.GetLength());
	}
	
	@Test
	public void TestLengthAndEmpty() {
		Assert.assertTrue("A queue of length zero must be empty.", queue.IsEmpty());
		
		AddSomeItems();
		
		Assert.assertFalse("A queue of nonzero length must not be empty.",
				queue.IsEmpty());
	}
	
	@Test
	public void TestDequeue() {
		AddSomeItems();
		
		//Put all the items in the queue into an array to compare.
		int[] items = new int[Fixture.length];
		for (int i = 0; i < items.length; ++i) {
			items[i] = queue.Dequeue();
			
			Assert.assertEquals("Dequeueing an item from the queue must " +
					"decrease the length of the queue.", queue.GetLength(),
					items.length - i - 1);
		}
		
		//Make sure the items are in the correct order.
		Assert.assertArrayEquals("The order of the queue should be first-in, " +
				"first-out.", Fixture, items);
		
		//Make sure that the queue returns null when we try to dequeue when it
		//is empty.
		Assert.assertEquals("When dequeueing an empty queue, return null.", null,
				queue.Dequeue());
	}


	@Test
	public void testBigQueue() {
		SetUp();
		// Put a lot of things in the queue
		int size = 1599999;
		for (int i=0; i<size; i++){
			queue.Enqueue(i);
		}
		for (int i=0; i<size/10000; i++){
			int out = queue.Dequeue();
			Assert.assertEquals(i, out);
		}
    }
 
}
