package StringMethods;

public class CheckPalindrome {

    public static void checkPalindrome(String str) {

        String reverse = "";

        for (int i = str.length() - 1; i >= 0; i--) {

            reverse = reverse + str.charAt(i);
        }


        if (str.equals(reverse)) {
            System.out.println(str + "is a palindrome");
        } else {
            System.out.println(str + "is not a palindrome");
        }


    }


    public static void main(String[] args) {

        checkPalindrome("madam");


    }
}
