class RemoveDuplicate {
	// Remove the duplicate characters in a string without using constant buffer
	// assuming using ASCII
    // remove duplicate by swapping the last char with it

    public static void removeDup(char[] str) {
        if (str == null) {
        	throw new IllegalArgumentException();
        }

        if (str.length < 2) {
        	// no duplicates in strings of length 1 or 0
        	return;
        }

    	boolean[] hit = new boolean[256];
    	for (int i = 0; i < 256; i ++) {
    	    hit[i] = false;	
    	}

    	int track = 0;
        
        for (int i = 0; i < str.length; i ++) {
        	if (!hit[str[i]]) {
        		// a first-time appeared character 
        		hit[str[i]] = true;
        		str[track] = str[i];
        		track += 1;
        	} 
        }
        for (int i = track ; i < str.length; i ++) {
        	str[i] = 0;
        }
    }


    public static void main(String[] args) {
        char[] str1 = "abcd".toCharArray();
        char[] str2 = "aaaa".toCharArray();
        char[] str5 = "aabbb".toCharArray();
        char[] str6 = "abab".toCharArray();
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str5);
        System.out.println(str6);
        removeDup(str1);
        removeDup(str2);
        //removeDup(str3); will throw exception (correctly)
        removeDup(str5);
        removeDup(str6);
        System.out.println("Finished running removeDup()!");
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str5);
        System.out.println(str6);
    }
}