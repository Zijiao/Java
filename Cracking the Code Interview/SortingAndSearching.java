import java.util.Comparator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.BitSet;

class SortingAndSearching {
	// chapter 11 of CTCI, sorting and searching problems

	public static void merge(int[] a, int[] b, int lastA, int lastB) {
		// You are given two sorted arrays, A and B, and A has a large enough buffer at the end to hold B. 
		//Write a method to merge B into A in sorted order.

        // lastB should be length of array B - 1
        if (a.length < lastA + lastB + 2) {
        	throw new IndexOutOfBoundsException();
        }

        int lastNew = lastA + lastB + 1;

        while (lastA > -1 && lastB > -1) {
        	if (a[lastA] >= b[lastB]) {
                a[lastNew] = a[lastA];
                lastA --;
        	} else if (a[lastA] < b[lastB]) {
        		a[lastNew] = b[lastB];
        		lastB --;
        	}
        	lastNew --;
        } 

        while (lastA > -1) {
        	a[lastNew] = a[lastA];
        	lastA --;
        	lastNew --;
        }

        while (lastB > -1) {
        	a[lastNew] = b[lastB];
        	lastB --;
        	lastNew --;
        }

        return;
	}

	public static class AnagramComparator implements Comparator<String> {
        // Write a method to sort an array of strings so that all the anagrams are next to each other.

        // method 1: implementing a customized class Comparator

        public String sortChars(String s) {
        	char[] content = s.toCharArray();
        	Arrays.sort(content);
        	return content.toString();
        }
        
        @Override
        public int compare(String s1, String s2) {
        	return sortChars(s1).compareTo(sortChars(s2));
        }
	}

	public static void anagramSort(String[] array) {
        // Write a method to sort an array of strings so that all the anagrams are next to each other.

        // method 2: using a hashmap to group anagrams 

        if (array.length == 0 || array.length == 1) {
        	return;
        }
        
        HashMap<String, LinkedList<String>> hashmap = new HashMap<String, LinkedList<String>>();
        for (String s: array) {
        	char[] content = s.toCharArray();
        	Arrays.sort(content);
        	String key = Arrays.toString(content);

        	if (!hashmap.containsKey(key)) {
        		hashmap.put(key, new LinkedList<String>());
        	}

        	hashmap.get(key).add(s);
        }
        
        int index = 0;
        for (String key: hashmap.keySet()) {
        	System.out.println(key);
        	for (String s: hashmap.get(key)) {
        		array[index] = s;
        		index ++;
        	}
        }
	}

	public static int findRotated(int key, int[] array, int start, int end) {
		// Given a sorted array of n integers that has been rotated an unknown number of times, give an O(log n) algorithm 
		// that finds an element in the array. You may assume that the array was originally sorted in increasing order.
        //EXAMPLE:
        //Input: find 5 in array (15 16 19 20 25 1 3 4 5 7 10 14)
        //Output: 8 (the index of 5 in the array)
       
        // all elements must be distinct 

        // start and end are two indices pointing to start and end locations of the array that we are dealing with

        // key fact: at beginning, only 1 half of the array is in total correct order

        if (start > end) {
        	return -1; // not found
        }

        int mid = (start + end) / 2;
        if (array[mid] == key) {
        	return mid; // found the target element
        }
        
        // here we split the input array into two parts, both part are in correct order; first part > second part
        // int the EXAMPLE array, (15 16 19 20 25) is the first part, (1 3 4 5 7 10 14) is the second part
        if (array[mid] > array[start]) {
            // array[mid] is in first part
            if (key < array[mid] && key > array[end]) {
            	return findRotated(key, array, start, mid - 1);
            } else {
            	return findRotated(key, array, mid + 1, end);
            }
        } else if (array[mid] <= array[start]) {
            if (array[mid] < key && key < array[end]) {
            	return findRotated(key, array, mid + 1, end);
            } else {
            	return findRotated(key, array, start, mid - 1);
            }
        }

        return -1; // dummy return startment
	}

	public static int findRotated(int key, int[] array) {
         return findRotated(key, array, 0, array.length - 1);
	}

	public static boolean findMatrix(int key, int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd) {
		// Given a matrix in which each row and each column is sorted, write a method to find an element in it.

		// devide the matrix into 4 parts (A, B, C, D) around element matrxi[rowMid][colMid] so that
		//   A < matrix[rowMid][colMid], D > matrix[rowMid][colMid], B and C not sure
        
        /*
		for (int i = rowStart; i <= rowEnd; i ++) {
            	for (int j = colStart; j <= colEnd; j ++) {
            		System.out.print(matrix[i][j]+ ", ");
            		if (j == colEnd) {
            			System.out.println();
            		}
            	}
            }
            System.out.println("==============");
        */
         
        if (rowStart > rowEnd || colStart > colEnd) {
        	// reached when matrix is not a square matrix
        	return false;
        }
		
		if (rowStart == rowEnd && colStart == colEnd) {
			// reached when matrix is a squre matrix
			// need to check for equal since when both row and col reach equal, may cause infinite loop
           
			if (matrix[rowStart][colStart] == key) {
				System.out.println("key found at row: " + rowStart + " col: " + colStart);
				return true;
			} else {
				return false;
			}
		}
        
        int colMid = (colEnd + colStart) / 2;
        int rowMid = (rowEnd + rowStart) / 2;

        if (matrix[rowMid][colMid] == key) {
        	System.out.println("key found at row: " + rowMid + " col: " + colMid);
        	return true;

        } else if (matrix[rowMid][colMid] > key) {
        	// search in A, B, C
        	return findMatrix(key, matrix, rowStart, rowMid, colStart, colMid) 
        	      || findMatrix(key, matrix, rowStart, rowMid, colMid + 1, colEnd)
        	      || findMatrix(key, matrix, rowMid + 1, rowEnd, colStart, colMid);

        } else {
        	// search in B, C, D
            return findMatrix(key, matrix, rowStart, rowMid, colMid + 1, colEnd)
                  || findMatrix(key, matrix, rowMid + 1, rowEnd, colStart, colMid)
                  || findMatrix(key, matrix, rowMid + 1, rowEnd, colMid + 1, colEnd);
        }
	}

	public static boolean findMatrix(int key, int[][] matrix) {
		return findMatrix(key, matrix, 0, matrix.length - 1, 0, matrix[0].length - 1);
	}

    public static int[] masterMind(String guess, String answer) {
        // The Game of Master Mind is played as follows:
        //The computer has four slots containing balls that are red (R ), yellow (Y), green (G) or blue (B). For example, the computer might have 
        // RGGB (e.g., Slot #1 is red, Slots #2 and #3 are green, Slot #4 is blue).
        //You, the user, are trying to guess the solution. You might, for example, guess YRGB.When you guess the correct color for 
        // the correct slot, you get a “hit”. If you guess a color that exists but is in the wrong slot, you get a “pseudo-hit”. 
        //For example, the guess YRGB has 2 hits and one pseudo hit.
        //For each guess, you are told the number of hits and pseudo-hits. Write a method that, given a guess and a solution, returns the number of hits and pseudo hits.

        char[] guessChar = guess.toCharArray();
        char[] answerChar = answer.toCharArray();
        
        int[] result = new int[2]; // result[0] stores hits, result[1] stores psudo-hits
        for (int i = 0; i < guessChar.length; i ++) {
            if (guessChar[i] == answerChar[i]) {
                resultp[0] ++;
            }
        }


    }

	public static void main(String[] args) {
		
	}
}























