
public class Queue 
{
	public static void main(String[] args) 
	{
		implQueue q = new implQueue();
		q.enQueue(7);
		q.enQueue(8);
		q.enQueue(11);
		q.enQueue(20);
		q.enQueue(23);
		q.enQueue(43);
		q.deQueue();
		q.deQueue();
		System.out.println(q.getSize());
		q.print();	
		System.out.println(q.peek());
	}
}

class implQueue
{
	int capacity = 5;
	int[] queue = new int[capacity];
	int front;
	int rear;
	int size;
	
	// method to add element to queue
	public void enQueue(int data) 
	{
		if(!isFull()) 
		{
			queue[rear] = data;
			rear++;
			size++;
		}
		else // REMEMBER THIS PART
		{
			int length =  getSize();
			int[] newQueue = new int[capacity * 2];
			System.arraycopy(queue, 0, newQueue, 0, length);
			queue = newQueue;
			capacity *= 2;

			queue[rear] = data;
			rear++;
			size++;
		}
	}
	
	// method to remove element from head (front) of queue
	public int deQueue() 
	{
		int data = queue[front];
		front++;
		size--;

		// System.out.println(size + " " + capacity);
		// System.out.println((double) size / (double) capacity);

		if ((double) size / (double) capacity <= 0.25)
		{
			int length =  getSize();
			int[] newQueue = new int[capacity / 2];
			System.arraycopy(queue, capacity - size, newQueue, 0, length); //FIX
			queue = newQueue;
			front = 0;
			capacity /= 2;
		}

		return data;
	}
	
	// method to print elements of queue
	public void print() 
	{
		System.out.print("elements of queue: ");

		for(int i = 0 ; i < size ; i++) 
		{
			System.out.print(queue[front + i]+"  ");
		}

		System.out.println();
	}
	
	// method to look at, but not remove, head (front)
	public int peek() 
	{
		return queue[front];
	}
	
	// method to print size of queue
	public int getSize() 
	{
		return size;
	}
	
	public boolean isEmpty() 
	{
		return getSize() == 0;
	}
	
	public boolean isFull() 
	{
		return getSize() == 5;
	}
}