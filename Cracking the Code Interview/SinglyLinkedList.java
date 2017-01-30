import java.util.ArrayList;
import java.lang.StringBuffer;
import java.util.HashSet;
import java.util.Stack;


public class SinglyLinkedList<Item> {
	// A singly linked list, ie 1 -> 2 -> 3 (not 1 <->2 <-> 3)
	// contains solution to CC150 problems about linkedlist

	private class Node<Item> {
		private Item data;
		private Node next;

		private Node(Item item) {
			this.data = item;
			this.next = null;
		}

		private Node(Item item, Node next) {
			// may not be used
			this.data = item;
			this.next = next;
		}

	}

	private Node<Item> head;
	private int length;

	public SinglyLinkedList() {
		// constructor
		this.head = null;
		this.length = 0;
	}

	public int size() {
		return this.length;
	}

	public boolean isEmpty() {
		return (this.length == 0);
	}

	public void add(Item data) {
		// add to the tail of current linked list
		if (data == null) {
			throw new IllegalArgumentException("No null values please.");
		}

		if (head == null) {
			head = new Node(data);
			this.length ++;
		} else {
			Node temp = head;
			while (temp.next != null) {
				temp = temp.next;
			} 
			temp.next = new Node(data);
			this.length ++;
		}
	}

	public void add(Item data, int k) {
		// add data to kth position, where k < size
		//more like "insert" 

		if (data == null) {
			throw new IllegalArgumentException("No null values please.");
		}

        if (k > this.length) {
        	throw new IndexOutOfBoundsException("0 <= k < size please."); 
        }
        
        Node temp = this.head;
        while (k > 1) { 
            temp = temp.next;
            k --;
        }

        // start the insertion
        Node oldNext = temp.next;
        temp.next = new Node(data, oldNext);
        this.length ++;
	}

	public boolean contains(Item data) {
		// return true if the instance contains data, otherwise return false

		if (data == null) {
			throw new IllegalArgumentException("No null values please.");
		}

		if (head == null) {
			return false;
		} else {
			Node temp = head;
			if (temp.data == data) {
				// head 
				return true;
			} else {
				while (temp.next != null) {
					if (temp.data == data) {
						return true;
					} else {
						temp = temp.next;
					}
				}
			}
		}

		return false;
	}

	public boolean remove(Item data) {
		// remove the first Node with data

		if (data == null) {
			throw new IllegalArgumentException("No null values please.");
		}
        
        // head
        if (head.data == data) {
        	head = head.next;
        	this.length --;
        	return true;
        }

        // not head
        Node temp = this.head;
        while (temp.next != null && temp.next.data != data) {
        	temp = temp.next;
        }

        if (temp.next == null) {
        	// data not found
        	return false;
        } else {
        	// data found, delete it
        	temp.next = temp.next.next;
        	this.length --;
        	return true;
        }
	}

	public void removeDuplicateFast() {
        //scan the list once, uses a HashMap, time complexity is O(n) 
        if (this.length < 2) {
        	return;
        }

        HashSet trackSet = new HashSet();
        trackSet.add(this.head.data);
        Node temp = head;
        while (temp.next != null) {
        	if (!trackSet.contains(temp.next.data)) {
        		trackSet.add(temp.next.data);
                temp = temp.next;
        	} else {
        		// delete it
        		temp.next = temp.next.next;
        		this.length --;
        	}
        }       
	}

	public void removeDuplicateSlow() {
		// use no extra space or data structures, time complexity is O(n^2)
        if (this.length < 2) {
        	return;
        }

        Node scanPos = head; 
        

        while (scanPos.next != null) {
        	Item scanData = (Item)scanPos.data;
            Node tempNode = scanPos;

            while (tempNode.next != null) {
                if (tempNode.next.data == scanData) {
            	    tempNode.next = tempNode.next.next;           	    
            	    this.length --;
                }
                tempNode = tempNode.next;
            }
            scanPos = scanPos.next;
        }
	}

	public void kthToLastPrint(Node node, int k) {
		// Implement an algorithm to print the nth to last element of a singly linked list.
	    // recerusive call
	    if (node == null) {
	    		return;
	    	}	

	    if (k <= 0) {
	    	System.out.print(node.data + " ");
	    }
	    k --;
	    kthToLastPrint(node.next, k);
	}

    public void kthToLastPrint(int k) {
    	// kthToLastPrint, an API easier to use
    	if (k < 0 || k >= this.length) {
    		throw new IllegalArgumentException("k is not a valid index.");
    	}

    	kthToLastPrint(this.head, k);
    	System.out.println();
    }

    public ArrayList<Item> kthToLastArray(int k) {
    	// similar to kthToLastPrint, but return an arraylist instead
    	// iterative
        if (k < 0 || k >= this.length) {
        	throw new IllegalArgumentException("k is not a valid index.");
        }
       
        Node temp = head;
        while (k > 0) {
            temp = temp.next;
            k --;
        }

        ArrayList<Item> array = new ArrayList<Item>(this.length - k - 1);
        while (temp.next != null) {
        	array.add((Item)temp.data);
        	temp = temp.next;
        }
        array.add((Item)temp.data); // last node
        return array;
    }

    public boolean removeNode(Node node) {
	    //Implement an algorithm to delete a node in the middle of a single linked list
    	//given only access to that node. CC150
    	// cannot delete the last node

        System.out.println("Removing node: " + node.data);
		if (node == null) {
			return false;
		}

		if (node.next == null) {
			// does not perform well, current node's predecessor's next point remains the same
			// can be fixed by setting it to some special values; for instance, "" when Item type is char 
			node = node.next;
			this.length --;
			return true;
		} else {
			node.data = (Item)node.next.data;
			node.next = node.next.next;
			this.length --;
			return true;
		}
	}

    public void partition(int x) {
    	// partition ths linkedlist around a value x, such that nodes less than x 
    	// come before all nodes greater than x. If there are more than one x values,
    	// partition around the first x
    	// create 2 extra linkedlist
        // x has to be int

    	if ((Object)x == null) {
    		throw new IllegalArgumentException();
    	}

        SinglyLinkedList smallList = new SinglyLinkedList();
        SinglyLinkedList largeList = new SinglyLinkedList();
        
        Node scanner = this.head;
        while (scanner.next != null) {
            if ((Integer) scanner.data < x) {
                smallList.add((Item)scanner.data);
            } else if ((Integer) scanner.data >= x) {
                largeList.add((Item)scanner.data);
            }
            scanner = scanner.next;
        }
        if ((Integer)scanner.data < x) {
            smallList.add((Item)scanner.data);
        } else if ((Integer) scanner.data >= x) {
            largeList.add((Item) scanner.data);
        }
        
        // combine two lists
        this.head = smallList.head;
        scanner = this.head;
        while (scanner.next != null) {
            scanner = scanner.next;
        }
        scanner.next = largeList.head;
    }

    public SinglyLinkedList digitAddition(SinglyLinkedList that) {
        //You have two numbers represented by a linked list, where each node contains a single digit. 
        //The digits are stored in reverse order, such that the 1’s digit is at the head of the list.
        // Write a function that adds the two numbers and returns the sum as a linked list.
        //EXAMPLE
        //Input: (3 -> 1 -> 5), (5 -> 9 -> 2): 513 + 295
        //Output: (8 -> 0 -> 8): 808
        // Item type needs to be Integer
        // carafully deal with addition carry-over

        // add this to that and return the result as a linked list
        if (this.length == 0) {
            return that;
        } 
        if (that.length == 0) {
            return this;
        }

        SinglyLinkedList result = new SinglyLinkedList();
        Node thisScanner = this.head;
        Node thatScanner = that.head;
        int sum;
        int carryOver = 0; // 0 or 1

        while (thisScanner.next != null && thatScanner.next != null) {
            sum = (Integer)thisScanner.data + (Integer)thatScanner.data + carryOver;
            if (sum / 10 == 0) {
                result.add(sum);
                carryOver = 0;
            } else {
                result.add(sum % 10);
                carryOver = 1;
            }
            thisScanner = thisScanner.next;
            thatScanner = thatScanner.next;
        }
        sum = (Integer)thisScanner.data + (Integer)thatScanner.data + carryOver;
        if (sum / 10 == 0) {
                result.add(sum);
                carryOver = 0;
            } else {
                result.add(sum % 10);
                carryOver = 1;
            }
        
        // when one list is short than the other
        if (thisScanner.next == null && thatScanner.next != null) {
            thatScanner = thatScanner.next;
            while (thatScanner.next != null) {
                sum = (Integer)thatScanner.data + carryOver;
                if (sum / 10 == 0) {
                result.add(sum);
                carryOver = 0;
                } else {
                result.add(sum % 10);
                carryOver = 1;
                }
                thatScanner = thatScanner.next;
            } 
            sum = (Integer)thatScanner.data + carryOver;
            if (sum / 10 == 0) {
            result.add(sum);
            carryOver = 0;
            } else {
            result.add(sum % 10);
            carryOver = 1;
            }  

        } else if (thatScanner.next == null && thisScanner.next != null) {
            thisScanner = thisScanner.next;
            while (thisScanner.next != null) {
                sum = (Integer)thisScanner.data + carryOver;
                if (sum / 10 == 0) {
                result.add(sum);
                carryOver = 0;
                } else {
                result.add(sum % 10);
                carryOver = 1;
                }
                thisScanner = thisScanner.next;
            }
            sum = (Integer)thisScanner.data + carryOver;
            if (sum / 10 == 0) {
            result.add(sum);
            carryOver = 0;
            } else {
            result.add(sum % 10);
            carryOver = 1;
            }// last one
        }
        if (carryOver == 1) {
            result.add(carryOver);
        }
        return result;
    }

    public boolean palindromeCheck() {
        // check whether the linkedlist is a palindrome
        if (this.head == null) {
            return false;
        }

        if (this.length == 1) {
            return true;
        }

        Stack<Item> stack = new Stack<Item>();

        Node scanner = this.head;
        while (scanner.next != null) {
            stack.push((Item)scanner.data);
            scanner = scanner.next;
        }
        stack.push((Item)scanner.data);
        
        scanner = this.head;
        while (scanner.next != null) {
            if ((Item)stack.pop() != (Item)scanner.data) {
                return false;
            }
            scanner = scanner.next;
        }
        if ((Item)stack.pop() != (Item)scanner.data) {
            return false;
        }

        return true;
    }


    @Override
	public String toString() {
		// uses a StringBuffer
        
        Node temp = head;
        if (temp == null) {
        	return "";
        } else {
        	StringBuffer returnValue = new StringBuffer();
        	returnValue.append("");
        	while (temp.next != null) {
                returnValue.append(temp.data);
                returnValue.append(" ");
                temp = temp.next;
        	}
        	returnValue.append(temp.data); // last node
        	return returnValue.toString();
        }
    }

    public static void main(String[] args) {
        // used for test
        // some methods may need type Item to be int only

        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(2);
        list1.add(0);
        
        System.out.println(list1.palindromeCheck());
    }
}







