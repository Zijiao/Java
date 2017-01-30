import java.util.HashSet;

class UniqueCharacter {
	// An algorithm to determin if a string has all unique characters.

    public static boolean uniqueTest(String input) {
        // this method use another data structure and extra data structure

        if (input == null) {
        	throw new IllegalArgumentException("The input is null value!");
        }
        if input.length() > 256 {
        	return false;
        }

        HashSet set = new HashSet();

        for (int i = 0; i < input.length(); i += 1) {
        	if (set.contains(input.charAt(i))) {
        		return false;
        	} else {
        		set.add(input.charAt(i));       		
        	}
        }

        return true;
    }


	public static void main(String[] args) {
        String str = "Foo bar";
        System.out.println(uniqueTest(s));
	}
}