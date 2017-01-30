class MatrixRotation {
	/*Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, 
	write a method to (anti-clockwise) rotate the image by 90 degrees.
	*/

	// here are give 2 methods, both in-place.

	public static void matrixRotation1(int[][] matrix, int n) {
        // a 2-step algorithm, O(n^2)

        // step 1, swap elements symmetric with respect to the main diagnal
        int swapTemp;
        for (int i = 0; i < n; i ++) {
        	for (int j = i; j < n; j ++) {
        		if (i != j) {
        			swapTemp = matrix[i][j];
        			matrix[i][j] = matrix[j][i];
        			matrix[j][i] = swapTemp;
        		}
        	}
        }

        // step 2, swap row i and row n - i - 1
        for (int i = 0; i < n - 1 - i; i ++) {
        	for (int j = 0; j < n; j ++) {
        		swapTemp = matrix[i][j];
        		matrix[i][j] = matrix[n - 1 - i][j];
        		matrix[n - 1 - i][j] = swapTemp; 
        	}
        }

	}

	public static void matrixRotation2(int[][] matrix, int n) {
		// a more intuitive method from CC150, O(n^2)
		// rotate 4 edges by layer by layer
        // @Zijiao: short and clear but easy to make mistakes

		int swapTemp;
		for (int i = 0; i < n - i - 1; i ++) {
            // i represents layer
            for (int j = i; j < n - 1 - i ; j ++) {
            	// running on each "inner" matrix
            	System.out.println(i + " " + j);

                // copy of top
            	swapTemp = matrix[i][j];
                
                // top = right
                matrix[i][j] = matrix[j][n - i - 1];

                // right = bottom
                matrix[j][n - i - 1] = matrix[n - i - 1][n - j - 1];

                // bottom = left
            	matrix[n - i - 1][n - j - 1] = matrix[n - j - 1][i];

            	// left = copy of top
            	matrix[n - j - 1][i] = swapTemp;
            }
		}

	}

	public static void main(String[] args) {

		int n = 4;

        int addOne = 1; 
		int[][] matrix = new int[n][n];
		for (int i = 0; i < n; i ++) {
			for (int j = 0; j < n; j ++) {
				matrix[i][j] = addOne ++;
			}
		}

		for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
        		System.out.print(matrix[i][j] + " ");
    		}
    		System.out.print("\n");
		}

		matrixRotation2(matrix, n);
		System.out.println();

		for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
        		System.out.print(matrix[i][j] + " ");
    		}
    		System.out.print("\n");
		}
	}
}