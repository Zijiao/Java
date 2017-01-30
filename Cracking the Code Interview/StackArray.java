import java.lang.StringBuffer;

class StackArray<Item> {
	// implement stack using an array
	// has some extra features describe in CC150 chap 3

    private Item[] stack;
    private int size; // number of elements in the stack
    private int capacity; // capacity of the stack, default is 10
    private int tailPointer; // point to tail, used for pop and peak 
    private int[] maxTracker; // maxTrack[n] is the maximum of stack[0]~stack[n]

    public StackArray(int capacity) {
    	this.capacity = capacity;
    	this.stack = (Item[])new Object[capacity];
    	this.maxTracker = new int[capacity];
    	this.tailPointer = -1;
    }

    public StackArray() {
    	this(10); // default capacity is 10
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public void push(Item data) {
    	if (data == null) {
    		throw new IllegalArgumentException("No null values please.");
    	}

    	if (this.size == this.capacity) {
    		throw new IndexOutOfBoundsException("The stack is full.");
    	}
        this.tailPointer ++;
    	stack[tailPointer] = data;
    	this.size ++;

    	if (this.size < 2) {
    		this.maxTracker[tailPointer] = (Integer)data; // tailPointer should be 0
    	} else {
    		if ((Integer)this.maxTracker[tailPointer - 1] < (Integer)data) {
    			maxTracker[tailPointer] = (Integer)data;
    		} else {
                maxTracker[tailPointer] = maxTracker[tailPointer - 1];
    		}
    	}
    }

    public Item peak() {
    	if (this.size == 0) {
    		return null;
    	}

    	return stack[tailPointer];
    }
    
    public Item pop() {
    	if (this.size == 0) {
    		return null;
    	}
        
        tailPointer --;
        this.size --;
    	return stack[tailPointer + 1];
    }

    public int max() {
    	// return the maximum value in the stack
    	return this.maxTracker[tailPointer];
    } 

    public String toString() {
        StringBuffer string = new StringBuffer(this.size); 
        int scanner = this.tailPointer;
        while (scanner > -1) {
        	string.append(this.stack[scanner]);
        	string.append(" ");
        	scanner --;
        }
        return string.toString();
    }

    public static void main(String[] args) {
        StackArray<Integer> stack = new StackArray<Integer>();
        stack.push(0);
        System.out.println(stack.max());
        stack.push(1);
        stack.push(2);
        System.out.println(stack);
        System.out.println(stack.max());
        System.out.println(stack.pop());
    }
}












