public class Test3 
{
    public static void main(String[] args) throws Exception 
    {
        DLL dll = new DLL();
        dll.addRear(20);
        dll.addRear(100);
        dll.addRear(2);
        dll.addRear(90);
        dll.makeCircular();
        System.out.println(dll.tail.next.data);

        implQueue q = new implQueue();
        q.enQueue(15);
        q.enQueue(20);
        q.enQueue(33);
        q.enQueue(90);
        q.enQueue(1);
        q.enQueue(1000);
        q.enQueue(10);
        q.enQueue(15);
        q.enQueue(20);
        q.enQueue(33);
        q.print();
        q.deQueue();
        q.deQueue();
        q.deQueue();
        q.deQueue();
        q.deQueue();
        q.deQueue();
        q.deQueue();
        q.deQueue();
        q.print();
    }
}