import java.lang.StringBuffer;

class SetOfStacks {

	/* Imagine a (literal) stack of plates. If the stack gets too high, it might topple. 
	 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds
	 * some threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks 
	 * should be composed of several stacks, and should create a new stack once the previous 
	 * one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop() should behave identically to 
	 * a single stack (that is, pop() should return the same values as it would if there were 
	 * just a single stack).
	 */

    // @Zijiao Implement as "LinkedList of stacks", that is, doubly linkedlist of "nodes", 
    // where a "node" is a stack. 
    //Add a new node when last stack is full, delete an old node when the stack it is empty

    // SOME RULES: only the last node can have an empty stack
    //   inner nodes must have stack with at least 1 element

    private class Node {
    	// local class
    	// each node contrains a stack

    	int[] stack;
    	int stackPointer; // point to the last element of current stack
    	Node prev;
    	Node next;

    	private Node(int capacity, Node prev, Node next) {
            this.stack = new int[capacity];
            this.prev = prev;
            this.next = next;
            this.stackPointer = -1;
    	}

    	private Node(int capacity, Node prev) {
    		this(capacity, prev, null);
    	}

    	private Node(int capacity) {
    		this(capacity, null, null);
    	}

    	private boolean isEmpty() {
    		// if an inner node is empty, it should be deleted immediately
    		return (this.stackPointer == -1);
    	}

    	private boolean isFull(int capacity) {
    		// if the (tail) node is full, a new node shuold be added
    		return (this.stackPointer == capacity - 1); 
    	}

    	private void push(int data) {
            this.stackPointer ++;
            this.stack[stackPointer] = data;
    	}

    	private int peak() {

    		return this.stack[stackPointer];
    	}

    	private int pop() {
    		this.stackPointer --;
    		return this.stack[stackPointer + 1];
    	}

    }

    // variables
    Node head;
    Node tail;
    int capacity;
    int length;
    
    public SetOfStacks(int capacity) {
        this.capacity = capacity;
        this.head = new Node(capacity);
        this.tail = this.head;
        this.length = 1; // length is number of nodes in the linked list,
    }

    public SetOfStacks() {
    	// default capacity is 5 in test case

    	this(5);
    }
    
    public void push(int data) {
    	// check, then add

    	if (this.tail.isFull(this.capacity)) {
            this.tail.next = new Node(capacity, this.tail);
            this.tail = this.tail.next;
            this.length ++;
    	}
        
    	this.tail.push(data);
     }

    public int peak() {
    	// take a look at tail
    	return this.tail.peak();
    }
    
    public int size() {
    	return this.length;
    }

    public int capacity() {
    	return this.capacity;
    }

    public int pop() {
    	if (this.tail == this.head && this.tail.isEmpty()) {
    		throw new IndexOutOfBoundsException("Stacks is empty.");
    	}

    	// first pop than check/delete

    	int popValue = this.tail.pop();
         
        // only the head node is allowed empty when there is only 1 node in the linkedlist

    	if (this.tail.isEmpty()) {
    		if (this.tail != this.head) {
            this.tail.prev.next = null;
            this.tail = this.tail.prev;
            this.length --;
            } 
    	}
    	return popValue;
    }
    
  
    public int popAt(int k) {
    	// pop from kth stack, where 0 <= k < length
    	if (k >= this.length) {
    		throw new IndexOutOfBoundsException("k must be less than the length of linkedlist " + this.length);
    	}
        
        Node tempNode = this.head;
    	while (k > 0) {
            tempNode = tempNode.next;
    	}

    	// tempNode is where we want to pop the stack
        int popValue = tempNode.pop();

        if (tempNode.isEmpty()) {
        	if (tempNode == this.head) {
        		if (this.length > 1) {
        		    this.head = this.head.next;
        		} /*else if (this.length == 1) {
        			this.head = new Node(capacity);
        			this.tail = this.head;
        		} */   // the commented part did the same thing as it deleted

        	} else {
        		tempNode.prev.next = tempNode.next;
        		tempNode.next.prev = tempNode.prev;
        	}
        	this.length --;
        }
        return popValue;
    }

     public String toString() {
        StringBuffer string = new StringBuffer();
        Node tempNode = this.head;
        int tempPointer;
        int nodeCount = 0;

        while (tempNode.next != null) {

            tempPointer = tempNode.stackPointer;
            string.append("stack " + nodeCount + ": ");
            nodeCount ++;

            while (tempPointer > -1) {
            	string.append(tempNode.stack[tempPointer]);
            	string.append(" ");
            	tempPointer --;
            }
            string.append("\n");
        	tempNode = tempNode.next;
        }

        // last node
        tempPointer = tempNode.stackPointer;
        string.append("stack " + nodeCount + ": ");
        while (tempPointer > -1) {
            string.append(tempNode.stack[tempPointer]);
            string.append(" ");
            tempPointer --;
        }

        return string.toString();        
    }

     public static void main(String[] args) {
     	SetOfStacks stacks = new SetOfStacks();
        for (int i = 0; i < 11; i ++) {
        	stacks.push(i);
        }
        System.out.println(stacks);
        for (int i = 0; i < 11; i ++) {
        	stacks.popAt(0);
        	System.out.println(stacks);
        	System.out.println();
        }
        System.out.println("Now pushing some values");
        for (int i = 0; i < 6; i ++) {
        	stacks.push(i);
        }
        System.out.println(stacks);
     }


    
}










