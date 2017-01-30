import java.util.ArrayList;
import java.lang.StringBuffer;

class RecursionProblems {
	// solutions to CC150 chapter 9: Recursion and Dynamic Programming

	public static class RunUpStaircase {
		// A child is running up a staircase with n steps, and can hop either 1
		// step, 2 steps, or 3 steps at a time. Implement a method to count how many
		// possible ways the child can run up the stairs.

        // this is a modified version of Fibonacci numbers problem

		private int height;
        private int[] outcomes;  // for memorization

        public RunUpStaircase(int n) {
        	if (n <= 0) {
        		throw new IndexOutOfBoundsException();
        	}

            this.height = n;
            if (n < 3) {
                outcomes = new int[3];
                outcomes[0] = 1; // run up to staircase 1
                outcomes[1] = 2; // run up to staircase 2
                outcomes[2] = 4; // run up tp staircase 3
            } else {
                outcomes = new int[n];
                outcomes[0] = 1; // run up to staircase 1
                outcomes[1] = 2; // run up to staircase 2
                outcomes[2] = 4; // run up tp staircase 3
            }
        }

        public int step(int n) {
        	// return number of possible ways to run up n steps
        	if (n > this.height) {
        		throw new IndexOutOfBoundsException();
        	}

            if (outcomes[n - 1] == 0) {
        	    outcomes[n - 1] = step(n - 1) + step(n - 2) + step(n - 3);
            }
            return outcomes[n - 1];            
        }

        public int step() {
            return step(this.height);
        }
	}

    public static int magicIndex(int[] array, int startIndex, int endIndex) {
        // magic index: array[magicIndex] == magicIndex
        // a recursive solution

        if (startIndex > endIndex) {
            // magic index does not exist in current array
            return -1;
        }

        int midIndex = (startIndex + endIndex) / 2;
        int midValue = array[midIndex];

        if (midValue == midIndex) {
            return midIndex;
        } 
        int leftIndex = Math.min(midValue, midIndex - 1);
        int leftMagicIndex =  magicIndex(array, startIndex, leftIndex);
        
        if (leftMagicIndex > -1) {
            return leftMagicIndex;
        }

        int rightIndex = Math.max(midValue, midIndex + 1);
        int rightMagicIndex = magicIndex(array, rightIndex, endIndex);       
        
        if (rightMagicIndex > -1) {
            return rightMagicIndex;
        } 

        return -1;
    }

    public static int magicIndex(int[] array) {
        return magicIndex(array, 0, array.length - 1);
    } 

    public static ArrayList<StringBuffer> getPermutations(String str, ArrayList<StringBuffer> oldPerms, int index) {
        // return all permutations of a given string

        // @Zijiao: IMPORTANT, spent quite a lot time on this method

        ArrayList<StringBuffer> newPerms = new ArrayList<StringBuffer>();
        
        // put a new letter in each existing permutation
        for (StringBuffer perm: oldPerms) {
            for (int i = 0; i <= perm.length(); i ++) {
                StringBuffer newPerm = new StringBuffer(perm.length() + 1); 
                // length + 1 since we need to insert one letter here
                newPerm.append(perm);
                newPerms.add(newPerm.insert(i, str.charAt(index)));
            }
        }
        
        if (index < str.length() - 1) {
            index ++;
            return getPermutations(str, newPerms, index); // this is a recursive solution
        }

        return newPerms;
    }

    public static ArrayList<StringBuffer> getPermutations(String str) {
        // return all permutations of a given string

        if (str == null) {
            return null;
        }

        ArrayList<StringBuffer> perms = new ArrayList<StringBuffer>();
        perms.add(new StringBuffer());

        return getPermutations(str, perms, 0);
    }

    public static void printParen(int l, int r, String[] str, int count) {
        // print all valid combinations of n-pairs of pareatheses
        // original input l == r: number of pairs of parentheses; count should be 0

        if (l == 0 && r == 0) {
            for (int i = 0; i < str.length; i ++) {
                System.out.print(str[i]);
            }            
            System.out.print(", ");
        } else {
            // if r > l, we put in a ")", otherwise put in a "("
            if (l > 0) {
                str[count] = "(";
                printParen(l - 1, r, str, count + 1);    
            }
            if (r > l) {
                str[count] = ")";
                printParen(l, r - 1, str, count + 1);
            }
        }
        return;
    }
    
    public static int makeChange1(int sum, int c, int n, int count) {
        //Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents) and pennies (1 cent), 
        // write code to calculate the number of ways of representing n cents.

        // sum == n means succesfully find a way; count is number of ways; c is last added cent
        // first call of nCents should input sum as 0, c as 25 and count as 0

        // this method is from http://www.hawstein.com/posts/8.7.html
        // this method is more like method of exhaustion than recursive solution 
        // notice that this method count make(sum == 0) as 0, not 1

        // @Zijiao: IMPORTANT problem and code, REVIEW it, analysis the recursion structure

        if (sum <= n) {

            if (sum == n) {
                return 1; // this return value will be added to count
            }
            
            if (c >= 25) {
                count += makeChange1(sum + 25, 25, n, 0);
            }
            if (c >= 10) {
                count += makeChange1(sum + 10, 10, n, 0);
            }
            if (c >= 5) {
                count += makeChange1(sum + 5, 5, n, 0);
            }
            if (c >= 1) {
                count += makeChange1(sum + 1, 1, n, 0);
            }
        }

        return count;
    }

    public static int makeChange1(int n) {
        //Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents) and pennies (1 cent), 
        // write code to calculate the number of ways of representing n cents.

        // this method http://www.hawstein.com/posts/8.7.html
        // A bottom-up revcursion

        return makeChange1(0, 25, n, 0);
    }

    public static int makeChange2(int n, int denom) {
        // solution on CTCI

        // A top-down recursion

        int nextDenom = 0;
        switch(denom) {
            case 25:
                nextDenom = 10;
                break;
            case 10:
                nextDenom = 5;
                break;
            case 5:
                nextDenom = 1;
                break;
            case 1:
                return 1; 
        }

        int count  = 0;
        for (int i = 0; i * denom <= n; i ++) {
            // here makeChange2() replace a small amount recursion with iteration, compared to make3Change3()
            count += makeChange2(n - i * denom, nextDenom);
        }

        return count;
    }

    public static int makeChange2(int n) {
        return makeChange2(n, 25);
    }

    public static int makeChange3(int n, int denom) {
        // method from SCIP Chapter 1 (page 26 of Chinese version)
        // similar to makeChange2(), but more "recursive" and "beautiful"

        // A top-down recursion

        if (n == 0) {
            return 1;
        } else if (n < 0) {
            return 0;
        }

        if (denom == 0) {
            return 0;
        }

        int nextDenom = 0;
        switch(denom) {
            case 25:
                nextDenom = 10;
                break;
            case 10:
                nextDenom = 5;
                break;
            case 5:
                nextDenom = 1;
                break;
            case 1:
                nextDenom = 0; 
        }

        return makeChange3(n, nextDenom) + makeChange3(n - denom, denom); // (not using current denom) + (using current denom)
    }

    public static int makeChange3(int n) {
        return makeChange3(n, 25);
    }

    public static void placeQueens(int row, Integer[] columns, ArrayList<Integer[]> results) {
        // Solution to the classic 8-Queens Problem using a bottom-up recursion 
        // use checkValid(); used in eightQueens()

        // columns[row] means place a queen at Row = row and Column = columns[row]
        // store the results in an arraylist of int[]

        // row should start with 0 in the first call of this method

        if (row == 8) {
            // found a solution, add it to results
            results.add(columns.clone());
        } else {
            for (int col = 0; col < 8; col ++) {
                if (checkValid(columns, row, col)) {
                    columns[row] = col; // place a queen
                    placeQueens(row + 1, columns, results); // then place a queen on next row
                }
            }
        }
    }

    public static int eightQueens() {
        // return amount of solutions of 8 Queen Prolem
        ArrayList<Integer[]> results = new ArrayList<Integer[]>(100);
        Integer[] columns = new Integer[8];
        placeQueens(0, columns, results);
        return results.size();
    }

    public static boolean checkValid(Integer[] columns, int row1, int col1) {
        // used in placeQueens()

        for (int row2 = 0; row2 < row1; row2 ++) {
            // row2 iterate through rows less than row1 (so no need to check for rows)
            int col2 = columns[row2];
            
            // check column
            if (col1 == col2) {
                return false;
            }

            // check diagnals
            int colDistance = Math.abs(col2 - col1);
            int rowDistance = row1 - row2;
            if (colDistance == rowDistance) {
                return false;
            }
        }

        return true;
    }


	public static void main(String[] args) {
        

	}
}























