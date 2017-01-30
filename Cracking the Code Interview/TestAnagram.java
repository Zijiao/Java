import java.util.Arrays;

class TestAnagram {
	// A method to decide if two strings are anagrams or not

    public static boolean testAnagram1(String str1, String str2) {
    	// using sorting, running time is O(nlogn)
    	if (str1 == null || str2 == null) {
    		// notice that two null strings are equivent, but meaningless
    		throw new IllegalArgumentException("No null values please!");
    	}

    	if (str1.length() != str2.length()) {
    		// different length, cannot be anagrams
    		return false;
    	}
    	System.out.println("test point1");
    	
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }

    public static boolean testAnagram2(String str1, String str2) {
    	// using scanning, running time is O(n)
    	if (str1 == null || str2 == null) {
    		throw new IllegalArgumentException("No null values please!");
    	}

    	if (str1.length() != str2.length()) {
    		// different length, cannot be anagrams
    		return false;
    	}

    	char[] count = new char[256];
    	char[] chars1 = str1.toCharArray();
    	char[] chars2 = str2.toCharArray();
    	for (int i = 0; i < chars1.length; i++) {
    		count[chars1[i]] ++;
    	}
    	for (int i = 0; i < chars2.length; i++) {
    		count[chars2[i]] --;
    	}
    	for (int i = 0; i < 256; i++) {
    		if(count[i] != 0) {
    			return false;
    		}
    	}
        return true;
    }

    public static void main(String[] args) {
    	String str1 = "abbcd";
        String str2 = "bcdab";
        System.out.println(testAnagram1(str1, str2));
        System.out.println(testAnagram2(str1, str2));
    }
}