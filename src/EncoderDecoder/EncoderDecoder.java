package EncoderDecoder;

import java.util.Scanner;

public class EncoderDecoder {
    // Reference Table
    private static final String REFERENCE_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
    
    // Main method to run the program
    public static void main(String[] args) {
        // Create an instance of EncoderDecoder
        EncoderDecoder encoderDecoder = new EncoderDecoder();
        // Create a scanner object to take user input
        Scanner scanner = new Scanner(System.in);
        
        /* Uncomment this if you want the original answer
        String plainText = "HELLO WORLD";

        // Using offset character 'F'
        char offsetChar = 'F';
        
        */

        // Prompt the user to enter the plain text
        System.out.print("Enter the plain text: ");
        // Read the plain text entered by the user
        String plainText = scanner.nextLine();

        // Prompt the user to enter the offset character for encoding and decoding
        System.out.print("Enter the offset character: ");
        // Read the first character of the input string as the offset character
        char offsetChar = scanner.next().toUpperCase().charAt(0); 
        
        // Calculate the number of letters to move based on the offset character
        int numberOfLettersToMove = getIndex(REFERENCE_TABLE, offsetChar);
        System.out.println("Index of " + offsetChar + ": " + numberOfLettersToMove);
        System.out.println("-------------------------");
        
        /* UNCOMMENT THIS IF YOU WANT TO SEE THE RESULTS

        // Shift the reference table to the beginning and end based on the offset character
        String transformedTable = shiftLettersToBeginning(REFERENCE_TABLE, numberOfLettersToMove);
        String originalTable = shiftLettersToEnd(transformedTable, numberOfLettersToMove);
        
        // Display original and transformed tables
        System.out.println("Original Table: " + originalTable);
        System.out.println("Transformed Table: " + transformedTable);
        System.out.println("-------------------------");
        
        */
        
        // Encode the plain text using the offset character and display the encoded text
        String encodedTextB = encoderDecoder.encode(plainText, offsetChar);
        System.out.println("-------------------------");
        System.out.println("Encoded with offset '" + offsetChar + "': " + encodedTextB);
        System.out.println("Answer Format (Encoded): " + offsetChar + encodedTextB);
        System.out.println("-------------------------");
        
        // Decode the encoded text using the offset character and display the decoded text
        String decodedTextB = encoderDecoder.decode(encodedTextB, offsetChar);
        System.out.println("Decoded with offset '" + offsetChar + "': " + decodedTextB);
    }


    public static String shiftLettersToBeginning(String inputString, int numberOfLettersToMove) {
        // Check if the string is empty or has only one character
        if (inputString == null || inputString.length() <= 1) {
            return inputString;
        }

        // Determine the number of characters to move
        int length = inputString.length();
        numberOfLettersToMove = numberOfLettersToMove % length; // To handle cases where numberOfLettersToMove is greater than the length

        // Extract the characters to move
        String movedCharacters = inputString.substring(length - numberOfLettersToMove);

        // Concatenate the moved characters with the rest of the string
        String modifiedString = movedCharacters + inputString.substring(0, length - numberOfLettersToMove);

        return modifiedString;
    }
    

    public static String shiftLettersToEnd(String inputString, int numberOfLettersToMove) {
        // Check if the string is empty or has only one character
        if (inputString == null || inputString.length() <= 1) {
            return inputString;
        }

        // Determine the number of characters to move
        int length = inputString.length();
        numberOfLettersToMove = numberOfLettersToMove % length; // To handle cases where numberOfLettersToMove is greater than the length

        // Extract the characters to move
        String movedCharacters = inputString.substring(0, numberOfLettersToMove);

        // Concatenate the rest of the string with the moved characters
        String modifiedString = inputString.substring(numberOfLettersToMove) + movedCharacters;

        return modifiedString;
    }
    

    public static int getIndex(String text, char characterToFind) {
        // Use indexOf method to find the index of the character
        return text.indexOf(characterToFind);
    }
    

    public String encode(String plainText, char offsetChar) {

        int offsetIndex = REFERENCE_TABLE.indexOf(offsetChar);

        if (offsetIndex == -1) {
            return "Invalid offset character";
        }
        
        // Shift the reference table to the beginning based on the offset index
        String shiftedTable = shiftLettersToBeginning(REFERENCE_TABLE, offsetIndex);

        // Create a StringBuilder to store the encoded message
        StringBuilder encodedMessage = new StringBuilder();
        // Encode each character in the plain text
        for (char c : plainText.toCharArray()) {
            // Find the index of the character in the reference table
            int index = REFERENCE_TABLE.indexOf(c);
            // If the character is found, append the corresponding shifted character to the encoded message
            if (index != -1) {
                encodedMessage.append(shiftedTable.charAt(index));
            } else {
                // If the character is not found, append the character as is
                encodedMessage.append(c);
            }
        }
        // Return the encoded message as a string
        return encodedMessage.toString();
    }


    public String decode(String encodedText, char offsetChar) {

        int offsetIndex = REFERENCE_TABLE.indexOf(offsetChar);

        if (offsetIndex == -1) {
            return "Invalid offset character";
        }
        
        // Shift the reference table to the beginning based on the offset index
        String originalTable = shiftLettersToBeginning(REFERENCE_TABLE, offsetIndex);

        // Create a StringBuilder to store the decoded message
        StringBuilder decodedMessage = new StringBuilder();
        // Decode each character in the encoded text
        for (char c : encodedText.toCharArray()) {
            // Find the index of the character in the original table
            int index = originalTable.indexOf(c);
            // If the character is found, append the corresponding character from the reference table to the decoded message
            if (index != -1) {
                decodedMessage.append(REFERENCE_TABLE.charAt(index));
            } else {
                // If the character is not found, append the character as is
                decodedMessage.append(c);
            }
        }
        // Return the decoded message as a string
        return decodedMessage.toString();
    }
}