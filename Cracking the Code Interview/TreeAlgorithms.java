import java.lang.StringBuffer;
import java.util.LinkedList; // used as a queue
import java.util.ArrayList;

class TreeAlgorithms {
	// contains problems of CC150 chapter 4 except problem 4.2
	// all related to binary trees

	public static class BSTree {
		// implement a binary search tree
		// left-child <= parent < right-child 

		public class Node {
			// each node contains and int key and three pointers: parent, left-child and right-child

			int key;
			Node parent;
			Node leftChild;
			Node rightChild;

			public Node(int key, Node parent, Node leftChild, Node rightChild) {
                this.key = key;
                this.parent = parent;
                this.leftChild = leftChild;
                this.rightChild = rightChild;
			} 

			public Node(int key) {
				// given only the key value

				this(key, null, null, null);
			}

			public Node(int key, Node parent) {
				// given the key value and its parent node

				this(key, parent, null, null);
			}

			public Node(int key, Node leftChild, Node rightChild) {
                // given the key value and its two children

				this(key, null, leftChild, rightChild);
			}
		}

	    Node root; // the only pointer pointing to the tree is the root

	    public BSTree() {
	    	this.root = null;
	    } 

	    public BSTree(int[] array) {
            this.root = null;

            this.createMinHeight(array, 0, array.length - 1);
   	    }	

	    public void createMinHeight(int[] array, int p, int q) {
	    // Given a sorted (increasing order) array, write an algorithm to create a binary tree with minimal height.

            if (array.length == 0) {
                return;
            }
            
            int r = (int)Math.floor((float)(q - p)/2) + p; // index of median of the array
            this.insert(array[r]);

            if (r > p) {
                createMinHeight(array, p, r - 1);
            }
            if (r < q) {
                createMinHeight(array, r + 1, q);
            } 
            return;
	    }

	    public Node insert(Node node, int key) {

	    	// IMPORTANT 

	    	// add a key into the tree

	    	// @Zijiao: Learned the lesson that Java does not "pass by reference" but 
	    	//  "pass by the value of reference"  

	    	if (node.leftChild == null && key <= node.key) {
	    		node.leftChild = new Node(key, node);
	    		return node;
	    	}
	    	if (node.rightChild == null && key > node.key) {
	    		node.rightChild = new Node(key, node);
	    		return node;

	    	} else {
	    		if (key <= node.key) {
                	node.leftChild = insert(node.leftChild, key); // insert into left-subtree
                } else if (key > node.key){
                	node.rightChild = insert(node.rightChild, key); // insert into right-subtree
                }
                return node;
	    	}
	    }

	    public void insert(int key) {
	    	if (this.root == null) {
	    		this.root = new Node(key);
	    	} else {
	            this.root = insert(this.root, key);
	        }		    	   	
	    }

	    public String toString() {
	    	// Breadth-first search the tree
	    	if (this.root == null) {
	    		return "";
	    	}
            
            LinkedList<Node> queue = new LinkedList<Node>(); // works as a queue
            StringBuffer string = new StringBuffer();
            Node node;
            queue.addLast(this.root);

            while (!queue.isEmpty()) {
                 node = queue.pollFirst();
                 string.append(node.key);
                 string.append(" ");
                 if (node.leftChild != null) {
                     queue.addLast(node.leftChild);
                 }
                 if (node.rightChild != null) {
                     queue.addLast(node.rightChild);
                 }
            }
            return string.toString();
	    }

	    public boolean isBalanced() {
	    // Implement a function to check if binary tree is balanced. 
		// For the purpose of this quesion, a balanced tree is defined to be a tree such
		//  that the height of the two subtrees of any node never differ by more than one.

		// @Zijiao : a bottom-up algorithm

            if (this.root == null) {
            	// empty tree is treated as balanced
        	    return true;
            }

            return getHeight(this.root) > -1;
	    }

	    public int getHeight(Node node) {

	    	// null-node counts as height 0
	    	if (node == null) {
                return 0;
	    	}

            // if any child has height -1, pass it to its parent recursively until root
	    	if ((getHeight(node.leftChild) == -1) || (getHeight(node.rightChild) == -1)) {
	    		return -1;
	    	}
            
            // if unbalanced, return -1
	    	if (Math.abs(getHeight(node.leftChild) - getHeight(node.rightChild)) > 1) {
	    		return -1;
	    	} 
	    	
	    	// height increase when children are balanced
	    	return Math.max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
	    	
	    }

	    public ArrayList<LinkedList<Integer>> createLinkedList() {
	    	// Given a binary search tree, design an algorithm which creates a linked list of all the nodes at each depth
	    	//  (i.e., if you have a tree with depth D, you’ll have D linked lists).


	    	// develop from DFS, using a LinkedList working as a queue

	    	ArrayList<LinkedList<Integer>> array = new ArrayList<LinkedList<Integer>>();

	    	if (this.root == null) {
	    		return array;
	    	}
	    	
	    	LinkedList<Node> queue = new LinkedList<Node>();

	    	int count = 1; // couting the number of remaining nodes in each level, increases as: count = count * 2 
	    	int countCopy = count;
	    	Node node;
	    	int level = 0;

            queue.addLast(this.root);
	    	array.add(new LinkedList<Integer>());

	    	while (!queue.isEmpty()) {

	    		if (countCopy == 0) {
                	level ++;
	    			count = count * 2; // count = 2 ^ level, can be replaced
	    			countCopy = count;
	    			array.add(new LinkedList<Integer>());
	    		}
	    		
                node = queue.pollFirst();
                
                array.get(level).addLast((Integer)node.key);
                
                if (node.leftChild != null) {
                	queue.addLast(node.leftChild);
                }
                if (node.rightChild != null) {
                	queue.addLast(node.rightChild);
                }
                countCopy --;   
	    	}
	    	return array;
	    }

	    public boolean isBSTree(Node node) {
	    	// check whether a binary tree is a binary search tree
	    	// binary search satisfies: node.leftChild <= node < rightChild

            boolean leftIsBST = true;
            boolean rightIsBST = true;

            if (node.leftChild != null) {
            	leftIsBST = ((node.leftChild.key <= node.key) && isBSTree(node.leftChild));
            } 
            
            if (node.rightChild != null) {
            	rightIsBST = ((node.rightChild.key > node.key) && isBSTree(node.rightChild));
            }

            return leftIsBST && rightIsBST; 
	    }

	    public boolean isBSTree() {
	    	if (this.root == null) {
	    		return true; // can throw an exception instead
	    	}
	    	return isBSTree(this.root);
	    }

	    public void messUpForTest() {
	    	// mess up the binary search tree to test method isBSTree()

            this.root.rightChild.rightChild.rightChild = new Node(10);

	    }

	    public int successor(Node node) {
	    	// return a node's successor (the largest value among all values that smaller than node.key)
            
            // @Zijiao: a node's successor is the smallest value in its right subtree when it has one
            // othereise its rightest parent, otherwise it has now subtrees
            //calls method min() 

            // This method does not check for validity of the input node (whether is in the tree)
            if (node == null) {
            	throw new IllegalArgumentException("No null values please.");
            }
            
            if (node.rightChild == null && node.parent != null) {
                while (node.parent != null && node.parent.key >= node.key) {
                	node = node.parent; // rightest parent
                }
                if (node.parent == null) {
                	return -1; // the node is the left-most node (the smallest one)
                } else {
                	return node.key;
                }

            } else if (node.rightChild != null) {
            	return min(node.rightChild); // smallest value in its right subtree
            } else {
            	return -1; // return -1 means this node has no successor
            }


	    }

	    public int min(Node node) {
	    	// return the maximum value in a subtree rooted at node

	    	while (node.leftChild != null) {
	    		node = node.leftChild;
	    	}

	    	return node.key;
	    }
	}	

	public static void main(String[] args) {
        
        int[] array = {0, 1, 2, 3, 4, 5, 6};
        BSTree tree = new BSTree(array);
        System.out.println(tree.createLinkedList());
        System.out.println(tree.root.rightChild.leftChild.key);
        System.out.println(tree.successor(tree.root.rightChild.leftChild));
    }
} 




