package cs2020;

import java.util.ArrayList;

public class Queue<T> implements IQueue<T> {
	
	ArrayList <T> al;
	
	public Queue() {
		al = new ArrayList<T>();
	}
	
	public void Enqueue(T item) {
		al.add(item);
	}

	@Override
	public T Dequeue() {
		if(this.IsEmpty())
			return null;
		T r = al.get(0);
		al.remove(0);
		return r;
	}

	@Override
	public boolean IsEmpty() {
		return al.isEmpty();
	}

	@Override
	public int GetLength() {
		return al.size();
	}

}
