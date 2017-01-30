import java.util.Stack;

class MyQueue {
	// implement a queue with two stacks
	
    Stack<Integer> pushStack; // push into this stack first
    Stack<Integer> popStack;  // pop from this stack
	
	public MyQueue() {
		pushStack = new Stack<Integer>();
		popStack = new Stack<Integer>();
	}

	public void push(int data) {

		if (pushStack.empty()) {
			pushStack.push(data);
		} else {
			popStack.push(data);
		}

		return;
	}

	public int pop() {
        if (popStack.empty() && pushStack.empty()) {
        	throw new IndexOutOfBoundsException("The queue is empty.");
        }
        
        int temp;
		if (popStack.empty()) {
			while (!pushStack.empty()) {
                temp = (Integer)pushStack.pop();
                popStack.push(temp);
            }
		}

		return popStack.pop();
	}

	public String toString() {
		return pushStack.toString() + popStack.toString();
	}

	public static void main(String[] args) {
		MyQueue queue = new MyQueue();
		
		int temp;
		for (int i = 0; i < 10; i ++) {
			queue.push(i);
		}
		for (int i = 0; i < 5; i ++) {
			temp = (Integer)queue.pop();
			System.out.println(temp);
		}

        System.out.println();

		for (int i = 0; i < 5; i ++) {
			queue.push(i + 10);
		}

		System.out.println();
        
        for (int i = 0; i < 10; i ++) {
        	temp = (Integer)queue.pop();
        	System.out.println(temp);
        }
	}
}








