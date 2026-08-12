package StringMethods;


public class StringMethods {
    public static void main(String[] args) {
        String name = "Bro Code";

        int length = name.length();
        System.out.println("Character count: " + length);


        // Returns the character at a specified index (0-indexed)
        char letter = name.charAt(0);
        System.out.println("Character at index 0: " + letter); // Prints 'B'


        // Finds the first occurrence of a character or substring
        int index = name.indexOf("o");
        System.out.println("First occurrence of 'o': " + index); // Prints 2


        // Finds the last occurrence of a character or substring
        int lastIndex = name.lastIndexOf("o");
        System.out.println("Last occurrence of 'o': " + lastIndex);  // Prints 5


        // Converts all characters in the string to uppercase
        String upperName = name.toUpperCase();
        System.out.println("All uppercase: " + upperName); // Prints "BRO CODE"

        // Converts all characters in the string to lowercase
        String lowerName = name.toLowerCase();
        System.out.println("All lowercase: " + lowerName); // Prints "bro code"


        // Removes any leading and trailing whitespace
        String nameWithSpaces = "   Bro Code   ";
        String trimmedName = nameWithSpaces.trim();
        System.out.println("Trimmed spaces: '" + trimmedName + "'"); // Prints "Bro Code"


        // Replaces all occurrences of a specified character with a new character
        String replacedName = name.replace('o', 'a');
        System.out.println("Replaced name: " + replacedName); // Prints "Bra Cade"


        // Returns true if the string is empty (length is 0), false otherwise
        boolean emptyResult = name.isEmpty();
        System.out.println("Is empty: " + emptyResult); // Prints false


        // Returns true if the string contains the specified character sequence
        if (name.contains(" ")) {
            System.out.println("Your name contains a space");
        } else {
            System.out.println("Your name doesn't contain any spaces");
        }


        // Case sensitivity demo (capital P)
        String caseMismatchPassword = "Password";
        System.out.println("    equals(\"password\") on \"Password\" returns: " + caseMismatchPassword.equals("password")); // Prints false

        // Compares two strings for equality, ignoring case sensitivity
        System.out.println("Answer: " + caseMismatchPassword.equalsIgnoreCase("password")); // Prints true
    }
}
