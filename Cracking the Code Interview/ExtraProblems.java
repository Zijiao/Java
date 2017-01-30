import java.util.HashSet;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;

class ExtraProblems {
        
        // chapter 17, 18 of CTCI

        // chapter 17

        public static int[] masterMind(String guess, String answer) {
        // The Game of Master Mind is played as follows:
        //The computer has four slots containing balls that are red (R ), yellow (Y), green (G) or blue (B). For example, the computer might have 
        // RGGB (e.g., Slot #1 is red, Slots #2 and #3 are green, Slot #4 is blue).
        //You, the user, are trying to guess the solution. You might, for example, guess YRGB.When you guess the correct color for 
        // the correct slot, you get a “hit”. If you guess a color that exists but is in the wrong slot, you get a “pseudo-hit”. 
        //For example, the guess YRGB has 2 hits and one pseudo hit.
        //For each guess, you are told the number of hits and pseudo-hits. Write a method that, given a guess and a solution, returns the number of hits and pseudo hits.

        // no validity check

        char[] guessChar = guess.toCharArray();
        char[] answerChar = answer.toCharArray();
        BitSet set1 = new BitSet(4);
        BitSet set2 = new BitSet(4);

        
        int[] result = new int[2]; // result[0] stores hits, result[1] stores psudo-hits
        for (int i = 0; i < 4; i ++) {

            if (guessChar[i] == answerChar[i]) {
                result[0] ++;
                set1.set(i);
                set2.set(i);
            }
        }

        for (int i = 0; i < 4; i ++) {
        	for (int j = 0; j < 4; j ++) {
        		if (set1.get(i) == false && set2.get(j) == false && guessChar[i] == answerChar[j]) {
                    result[1] ++;
                    set1.set(i);
                    set2.set(j);
                    break;
        		}
        	}
        }

        return result;
    }

    public static void findUnsortedSequence(int[] array) {
    	// Given an array of integers, write a method of find indices m and n such that if you sort
    	//  elements m through n, the entire array would be sorted. Minimized n - m (that is, find
    	//  the smallest such sequence)

    	// test case: int[] array = {1, 3, 5, 6, 7, 10, 11, 7, 12, 4, 7, 16, 18, 19};

        // no validity check
        
        if (array.length < 4) {
        	System.out.println("Please input an array with length larger than 3, otherwise meaningless for this problem.");
        	return;
        }
        
        // first locate midStart
        int midStart;
        for (midStart = 1; midStart < array.length; midStart ++) {
        	if (array[midStart] < array[midStart - 1]) {
        		break;
        	}
        }
        
        int lastMin = -1;
        while (midStart > 1 && array[midStart - 1] >= findMin(array, midStart, array.length - 1, lastMin)) {
        	midStart --;
        } // at this moment we have located midStart

        // now we start to locate midEnd
        int midEnd;
        for (midEnd = array.length - 2; midEnd >= midStart; midEnd --) {
        	if (array[midEnd] > array[midEnd + 1]) {
        		break;
        	}
        }

        int lastMax = -1;
        while (midEnd < array.length - 1 && array[midEnd + 1] <= findMax(array, midStart, midEnd, lastMax)) {
        	midEnd ++;
        } // at this moment we have located midEnd

        System.out.println("midStart: " + midStart + " midEnd: " + midEnd);
        System.out.println("The minimum unsorted part of the input array is: ");
        for (int i = midStart; i <= midEnd; i ++) {
        	System.out.print(array[i] + ", ");
        }
        System.out.println();
        return;
    }

    public static int findMin(int[] array, int start, int end, int lastMin) {
        // used in findUnsortedSequence()
        if (start > end) {
        	throw new IndexOutOfBoundsException();
        }

        if (lastMin == -1) {
        	// scan the whole array from start to end
        	int min = array[end];
        	for (int i = end; i >= start; i -- ) {
        		if (array[i] < min) {
        			min = array[i];
        		}
        	}
        	return min;
        } else {
        	int min = lastMin;
        	if (array[start] < lastMin) {
        		min = array[start];
        	}
        	return min;
        }
    }

    public static int findMax(int[] array, int start, int end, int lastMax) {
        // used in findUnsortedSequence()
        if (start > end) {
        	throw new IndexOutOfBoundsException();
        }

        if (lastMax == -1) {
        	// scan the whole array from start to end
        	int max = array[start];
        	for (int i = end; i >= start; i -- ) {
        		if (array[i] > max) {
        			max = array[i];
        		}
        	}
        	return max;
        } else {
        	int max = lastMax;
        	if (array[start] > lastMax) {
        		max = array[end];
        	}
        	return max;
        }
    }

    public static void printNumber(int number) {
    	// Given any integer, print an English phrase that decribes the integer 
    	//  (e.g., "One Thousand, Two Hundred Thirty Four").

    	int millions = number / 1000000;

    	if (millions > 0) {
            printThreeDigits(millions);
            System.out.print(" Millions, ");
    	}

    	int thousands = number / 1000 % 1000;

    	if (thousands > 0) {
    		printThreeDigits(thousands);
    		System.out.print(" Thousand, ");
    	}

    	int basics = number % 1000;
    	if (basics > 0) {
    		printThreeDigits(basics);
    	}

    	System.out.println();
    }

    public static void printThreeDigits(int threeDigits) {
    	// used in printNumber()

        String[] digits = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    	String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Sevnteen", "Eighteen", "Nineteen"};
    	String[] tens = {"Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        int firstDigit = threeDigits / 100;
        int secondDigit = threeDigits % 100 / 10;
        int thirdDigit = threeDigits % 10 ;

        if (firstDigit > 0) {
        	System.out.print(digits[firstDigit - 1] + " Hundred ");
        }
        if (secondDigit == 1) {
            System.out.print(teens[thirdDigit]);
        } else if (secondDigit > 1) {
        	System.out.print(tens[secondDigit - 2] + " ");
        	if (thirdDigit > 0) {
        		System.out.print(digits[thirdDigit - 1]);
        	}
        }     
        return;
    }

    public static void findComplement1(int[] array, int sum) {
    	// Design an algorithm to find all pairs of integers within an array which sum to a specified value.
        // For example, given input array: -2, -1, 0, 3, 5, 6, 7, 9, 13, 14, and sum 9, print (0, 9), (3, 6) 

        // method 1: hashset, O(n)
        
        HashSet set = new HashSet(array.length);
        for (int i = 0; i < array.length; i ++) {
            set.add(array[i]);
        }
        
        boolean found = false;
        HashSet visited = new HashSet(array.length);
        for (int i = 0; i < array.length; i ++) {
        	if (!visited.contains(array[i]) && set.contains(sum - array[i])) {
        		System.out.print("(" + array[i] + ", " + (sum - array[i]) + ") ");
        		visited.add(sum - array[i]);
        		found = true;
        	}
        }
        if (!found) {
        	System.out.print("No pairs found with sum equals to " + sum);
        }
        System.out.println();
    }

    public static void findComplement2(int[] array, int sum) {
        // Design an algorithm to find all pairs of integers within an array which sum to a specified value.
        // For example, given input array: -2, -1, 0, 3, 5, 6, 7, 9, 13, 14, and sum 9, print (0, 9), (3, 6) 

        // method 2: sort & iterate, O(n^2) 	
        if (array.length < 2) {
        	System.out.println("array length should be larger than 1 for this problem");
        	return;
        }

        Arrays.sort(array);

        if (array[array.length - 1] + array[array.length - 2] < sum || array[0] + array[1] > sum) {
        	System.out.println("No pairs found with sum equals to " + sum);
        	return;
        }

        int head = 0;
        int tail = array.length - 1;
        boolean found = false;

        while (head < tail) {
        	while (array[head] + array[tail] > sum) {
        		tail --;
        	}
        	if ((array[head] + array[tail]) == sum) {
        		System.out.print("(" + array[head] + ", " + array[tail] + ") ");
        		found = true;
        	}
        	head ++;
        }
        if (!found) {
            System.out.print("No pairs found with sum equals to " + sum);	
        }
        System.out.println();
        return;
    }

    public static int findLargestSum(int[] array) {
        // You are given an array of integers (both positive and negative). Find the contiguous sequence
        //   with the largest sum. Return the sum. (return 0 if all elements are negative)

        // example: 2, 3, -8, -1, 2, 4, -2, 3; should return 7 = sum(2, 4, -2 ,3).

        // method 1: using a globalMax and a partialMax (dynamic programming), from http://www.hawstein.com/posts/19.7.html

        boolean positiveFound = false;
        int globalMax = 0;
        int partialMax = 0;
        
        for (int i = 0; i < array.length; i ++) {
        	if (partialMax <= 0) {
        		partialMax = array[i];
        	} else {
        		partialMax += array[i];
        	}

        	if (partialMax >= globalMax) {
        		globalMax = partialMax;
        	}
        }

        return globalMax; 
    }



    //  chapter 18

    public static int[] shuffleCards(int amount, int[] deck) {
        // Write a method to shuffle a deck of cards. It must be a perfect suffle -- in other words, each of the 52! permutations
        //  of the deck has to be equally likely. Assume that you are given a random number gererator which is perfect.

        // @Zijiao: recursive method; use intgers 0 ~ amount - 1 to represent the cards

        // amount is the total number of cards which, in a standard deck, should be 52 (without jokers). 
        

        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        if (amount == 1) {
            deck[0] = 0; // actually a redundant operation; for the sake of readability 
        }

        if (amount > 1) {
            shuffleCards(amount - 1, deck);

            // key step: add a new card (amout - 1) then swap amout - 1 and a random card before it
            int swapIndex = (int)(Math.random() * amount);

            if (swapIndex != amount - 1) {
                int temp = deck[swapIndex];
                deck[swapIndex] = amount - 1;
                deck[amount - 1] = temp;
            } else {
                deck[amount - 1] = amount - 1;
            }
        }

        return deck;
    }

    public static int[] shuffleCards(int amount) {
        return shuffleCards(amount, new int[amount]);
    }

    public static int[] shuffleCards() {
        // shuffle a standard 52 cards deck
        return shuffleCards(52, new int[52]);
    }

    public static void smallestNumbers(int[] array, int k, int start, int end, int[] answer, int answerStart) {
        // Describe an algorithm to find the smallest one million numbers in one billion numbers. Assume that the
        //  computer memory hold all one billion numbers.

        // @Zijiao: use partition() same as one in quick sort with randomization
        // index means find the k smallest numbers
        // test case - array: 10 9 8 7 6 5 4 3 2 1, k = 4, the method should return 1 2 3 4 5 (index starting at 0)

        // @ Zijiao: IMPORTANT learned a lesson about making it clear about boundary conditions
        
        // set a randomized pivot, and swap it to the tail of the array
        int pivotIndex = (int)(Math.random() * (end - start + 1)) + start;
        swap(array, pivotIndex, end);
        int pivot = array[end];

        // start partitioning
        int splitIndex = start; // splitIndex split elemments smaller and larger than pivot 
        for (int i = start; i <= end; i ++) {
            if (array[i] < pivot) {
                // swap array[splitIndex] and array[i], then splitIndex ++
                swap(array, i, splitIndex);
                splitIndex ++;
            }
        }

        // then swap pivot (curently array[end]) to where it should be
        swap(array, end, splitIndex);

        if (splitIndex - start == k)  {
            // copy elements to answer and return it
            for (int i = start; i <= splitIndex; i ++) {
                answer[answerStart ++] = array[i];
            }
        } else if (splitIndex - start > k) {
            smallestNumbers(array, k, start, splitIndex, answer, answerStart);
            
        } else if (splitIndex - start < k) { 
            for (int i = start; i <= splitIndex; i ++) {
                answer[answerStart ++] = array[i];
            }
            smallestNumbers(array, k - splitIndex + start - 1, splitIndex + 1, end, answer, answerStart);
        }

        return;
    }

    public static int[] smallestNumbers(int[] array, int k) {
        if (k >= array.length) {
            throw new IllegalArgumentException("k is out of bound.");
        }

        int[] answer = new int[k + 1]; 

        smallestNumbers(array, k, 0, array.length - 1, answer, 0);

        return answer;
    }

    public static final void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        return;
    }

    public static class CompareByLength implements Comparator<String> {
        // not used, for practice
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }

    public static boolean transformWord(String origin, String goal, String lastOrigin, String[] dict) {
        // Given two words of equal length that are in a dictionary, wrtie a method to transform one word into another
        //  word by changing only one letter at a time. The new word you get in each step must be in the dictionary.
        // Example:
        // Input: DAMP, LIKE
        // Output: DAMP -> LAMP -> LIMP -> LIME -> LIKE
        
        for (String word: dict) {
            if (word != lastOrigin && wordDifference(word, origin) == 1) {
                lastOrigin = origin;
                origin = word;
                if (origin == goal) {
                    return true;
                } else {
                    transformWord(origin, goal, lastOrigin, dict);
                }
            }
        }
        return false;
    }

    public static int wordDifference(String word1, String word2) {
        // used in transformWord()
        if (word1.length() != word2.length()) {
            return -1;
        }

        int difference = 0;
        for (int i = 0; i < word1.length(); i ++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                difference ++;
            }
        }
        return difference;
    }

    public static void main(String[] args) {
        String[] dict = {"DAM" , "DAMP" , "LAMP" , "LIMP" , "LIME" , "LIKE"};   
        String origin = "DAMP";
        String goal = "LIKE";    
        System.out.println(transformWord(origin, goal, origin, dict)); 


    }
}






