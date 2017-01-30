class ReplaceSpace {
	// replace spaces in a string with "%20"

	public static void replaceSpace(char[] chars, int origLength) {
        // scan twice

        int spaceCount = 0;
        for (int i = 0; i < origLength; i++) {
            if (chars[i] == ' ') {
            	spaceCount ++;
            }
        }

        int replaceLength = origLength + 2 * spaceCount;
        int replacePosition = replaceLength - 1;

        chars[replaceLength] = '\0';
        System.out.println(" " + chars);

        for (int i = origLength - 1; i >= 0; i --) {
        	if (chars[i] == ' ') {
        		chars[replacePosition --] = '0';
        		chars[replacePosition --] = '2';
        		chars[replacePosition --] = '%';
        	} else {
        		chars[replacePosition --] = chars[i];
        	}
        } 
	}

	public static void main(String[] args) {
		String testS = "hello world!";
		char[] testC = testS.toCharArray();
		
		char[] input = new char[20];
		for (int i = 0; i < testC.length; i ++) {
			input[i] = testC[i];
		}
		System.out.println(input);

		replaceSpace(input, testC.length);
		System.out.println(input);
	}
}