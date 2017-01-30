class IsRotation {
	/*Assume you have a method isSubstring which checks if one word is a substring of 
    another  Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using 
    only one call to isSubstring (i e , “waterbottle” is a rotation of “erbottlewat”)
    */

	public static boolean isSubString(String patterStr,String test){
		// it is assuming to be "provided" 
		if(patterStr == null || test == null)	return false;
		if((patterStr.indexOf(test)) >= 0)	return true;
		else return false;
	}

	public static boolean isRotation(String str1, String str2) {
        // only call isSubString() once
        if (str1 == "" || str2 == "") {
        	throw new IllegalArgumentException();
        }

		if (str1.length() != str2.length()) {
			return false;
		}
        
		return isSubString(str1 + str1, str2);
	}

	public static void main(String[] args) {
		String str1 = "hello";
		String str2 = "loohe";

		System.out.println(isRotation(str1, str2));
	}
}