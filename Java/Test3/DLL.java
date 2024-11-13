public class DLL 
{
	int size;
	Link head;					//"head" is an object of the Link class
	Link tail;					// "tail" is object of Link class
	
	// constructor of DLL class
	DLL()
    {
		head = null;
		size = 0;
		tail = null;
	}

    public void makeCircular() // REMEMBER THIS
    {
        tail = head;

        while (tail.next != null) 
        {
			tail = tail.next;
		}

        if (tail.next == null)
        {
            tail.next = head;
        }

        while (head.previous != null)
        {
            head = head.previous;
        }

        if (head.previous == null)
        {
            head.previous = tail;
        }
    }
	
	// method to add to front of list
	public void addFront(int data) 
    {
		if (head == null)
        {
			head = new Link(null,data,null);
        }
		else
        {
			Link newLink = new Link(null,data,head);
			head.previous = newLink;
			head = newLink;
		}

		size++;
	}
	
	// method to add to rear of list
	public void addRear(int data) 
    {
		if(head == null)
        {
			head = new Link(null,data,null);
        }
		else 
        {
			Link current = head;
            
			while(current.next != null) 
            {
				current = current.next;
			}

			Link newNode = new Link(current,data,null);
			current.next = newNode;
		}

		size++;
	}
	
	// method to insert at a specific index
	public void insertAt(int data,int index)
    {
		if(head == null) 
        {
			return;
        }

		if(index < 0 || index > size)
        {
            return;
        }

		if(index == 0) 
        {
			addFront(data);
			return;
        }

		if(index == size) 
        {
			addRear(data);
			return;
        }
		
		Link current = head;
		int i = 0;

		while(i < index - 1) 
        {
			current = current.next;
			i++;
		}

		Link newLink = new Link(current,data,current.next);
		current.next = newLink;
		current.next.previous = newLink;
		size++;
	}
	
	// method to remove first index
	public void removeFront()
    {
		if(head == null)
        {
            return;
        }

		head = head.next;
		size--;
	}
	
	// method to remove last index
	public void removeRear() 
    {
		if(head == null)
        {
            return;
        }

		if(head.next == null) 
        {
			head = null;
			size--;
			return;
		}

		Link current = head;
        
		while(current.next.next != null)
        {
            current = current.next;
        }

		current.next = null;
		size--;
	}
	
	// method to remove a node at a specific index
	public void removeAt(int index) 
    {
		if(head == null)
        {
            return;
        }

		if(index < 0 || index > size)
        {
            return;
        }

		if(index == 0) 
        {
			removeFront();
			return;
		}

		if(index == size - 1) 
        {
			removeRear();
			return;
		}

		Link current = head;
		int i = 0;

		while(i < index - 1) 
        {
			current = current.next;
			i++;
		}

		current.next = current.next.next;
		size--;
	}
	
	// print method (forward direction)
	public void printF() 
    {
		Link current = head;

        System.out.print(current.data + "  ");
        current = current.next;

		while(current != head) 
        {
			System.out.print(current.data+"  ");
			current = current.next;
		}

		System.out.println();
	}
	
	// method to print(reverse direction)
	public void printR() 
    {
		Link current = head;

		while(current.next != null)
        {
            current = current.next;
        }

		while(current != null) 
        {
			System.out.print(current.data+"  ");
			current = current.previous;
		}

		System.out.println();
	}
	
	public static void main(String[] args) 
    {
		DLL dll = new DLL();
		dll.addFront(100);
		dll.addFront(200);
		dll.addRear(300);
		dll.addRear(400);
        dll.makeCircular();
        System.out.println(dll.tail.next.data);
        System.out.println(dll.head.previous.data);
		System.out.println("forward print:");
		dll.printF();
	}
}

class Link
{
	int data;
	Link next;
	Link previous;
	
	Link(int data)
    {
		this.data = data;
		next = previous = null;
	}
	
	Link(Link previous,int data, Link next)
    {
		this.previous = previous;
		this.data = data;
		this.next = next;
	}
}