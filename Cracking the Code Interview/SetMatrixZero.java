class SetMatrixZero {
	//Write an algorithm such that if an element in an MxN matrix is 0, 
	//its entire row and column is set to 0.

	public static void setMatrixZero(int[][] matrix) {
        // use 2 arrays, time complexity O(MN), space complexity O(M+N)

		int m = matrix.length;
		int n = matrix[0].length;

		boolean[] rowTrack = new boolean[m];
		boolean[] colTrack = new boolean[n];

		for (int i = 0; i < m; i ++) {
			rowTrack[i] = false;
		}

		for (int j = 0; j < n; j ++) {
			colTrack[j] = false;
		}

		for (int i = 0; i < m; i ++) {
			for (int j = 0; j < n; j ++) {
				if (matrix[i][j] == 0) {
					rowTrack[i] = true;
					colTrack[j] = true;
				}
			}
		}

		for (int i = 0; i < m ; i ++) {
			if (rowTrack[i]) {
				for (int j = 0; j < n; j ++) {
					matrix[i][j] = 0; 
				}
			}
		}
		for (int j = 0; j < n; j ++) {
			if (colTrack[j]) {
				for (int i = 0; i < m; i ++) {
					matrix[i][j] = 0;
				}
			}
		}
	}


	public static void main(String[] args) {
        int[][] matrix = {
        	new int[] {1, 0, 3, 4},
        	new int[] {5, 6, 0, 8},
        	new int[] {9, 10, 11, 12},
        };

        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
        		System.out.print(matrix[i][j] + " ");
    		}
    		System.out.print("\n");
		}

        setMatrixZero(matrix);
        System.out.println();

        for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
        		System.out.print(matrix[i][j] + " ");
    		}
    		System.out.print("\n");
		}
	}

}