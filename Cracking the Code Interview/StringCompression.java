import java.lang.StringBuffer;

class StringCompression {
	/* A method to perform basic string compression using the counts of repeated
	characters. For example, the string aabbcccccaaa would be a2b1c5a3. If the 
	"compressed" string would not become smaller than the original string, the mehod
	just return the original string */

	public static String stringCompression1(String str) {
		// use a StringBuffer to help build the compressed string, O(n)

		if (str.length() == 0 || str == null) {
			throw new IllegalArgumentException();
		} else if (str.length() == 1) {
			return str;
		}

		System.out.println("Input string is: " + str);
        		
        StringBuffer output = new StringBuffer(str.length());
		char[] input = str.toCharArray(); // to traverse
		char currentChar = input[0];
        int currentCount = 1;

        // begin scanning at position 1 instead of position 0
		for (int i = 1; i < str.length(); i ++) {
              if (input[i] == currentChar) {
              	currentCount ++;
              } else if (currentCount == 1) {
              	output.append(currentChar);
              	currentChar = input[i];
              } else {
              	output.append(currentChar);
              	output.append(currentCount);
              	currentCount = 1;
              	currentChar = input[i];
              }
		}
		output.append(currentChar);
        if (currentCount != 1) {
            output.append(currentCount);
        }

		return output.toString();
	}

	public static void main(String[] args) {
		String str = "aabbbc";
		
		System.out.println(stringCompression1(str));
	} 
}