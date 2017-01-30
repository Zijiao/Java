import java.util.Stack;

class StackSort {
	//Write a program to sort a stack in ascending order. You should not make any assumptions about 
	// how the stack is implemented. The following are the only functions that should be used 
	// to write this program: push | pop | peek | isEmpty.

	// sort a stack with stack-only operations

    //@ Zijiao: use a helper stack

    public static void stackSort(Stack<Integer> origStack) {
        // Use a helper stack, compare and swap top elements from the two stacks, 
        //  then the pop the bigger one and the original stack, the smaller one on
        //  the helper stack
        if (origStack.empty()) {
        	throw new IllegalArgumentException("The stack is empty.");
        } 
        
        int origTemp = origStack.pop(); // store popped element from origStack
        if (origStack.empty()) {
        	origStack.push(origTemp);
        	return;
        }

        Stack<Integer> helpStack = new Stack<Integer>();
        int helpTemp; // store popped element from helpStack
        helpStack.push(origTemp);

        	System.out.println("origStack: " + origStack);
            System.out.println("helpStack: " + helpStack);
            System.out.println();

        while (!origStack.empty()) {
            origTemp = origStack.pop();
            helpTemp = helpStack.pop();
            
            while (origTemp < helpTemp && !helpStack.empty()) {
            	origStack.push(helpTemp);
            	helpTemp = helpStack.pop();
                
                }

        // here we need to check which one is smaller since there is uncertainty in case helpStack.empty() is true
            if (origTemp > helpTemp) {
            	helpStack.push(helpTemp);
                helpStack.push(origTemp);                
            } else {               
                helpStack.push(origTemp);
                helpStack.push(helpTemp);
            }
        
        } 
        
        while (!helpStack.empty()) {
        	origStack.push(helpStack.pop());
        }
        return;
    }

    public static void main(String[] args) {
    	Stack stack = new Stack();
    	stack.push(1);
    	stack.push(3);
    	stack.push(2);
    	stack.push(4);
    	System.out.println(stack);
    	stackSort(stack);
    	System.out.println(stack);
    }
}