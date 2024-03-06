package EncoderDecoder;

import java.util.Scanner;

public class EncoderDecoder {
	private static final String REFERENCE_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
	
	public static void main(String[] args) {
        EncoderDecoder encoderDecoder = new EncoderDecoder();
        Scanner scanner = new Scanner(System.in);

        /* Uncomment this if you want the original answer
        String plainText = "HELLO WORLD";

        // Using offset character 'F'
        char offsetChar = 'F';
        
        */
        // Example using user's input
        System.out.print("Enter the plain text: ");
        String plainText = scanner.nextLine();

        // Using offset character from user input
        System.out.print("Enter the offset character: ");
        char offsetChar = scanner.next().toUpperCase().charAt(0); // Read the first character of the input string
        
        
        int numberOfLettersToMove = getIndex(REFERENCE_TABLE, offsetChar);
        System.out.println("Index of " + offsetChar + ": " + numberOfLettersToMove);
        System.out.println("-------------------------");
        
        String transformedTable = shiftLettersToBeginning(REFERENCE_TABLE, numberOfLettersToMove);
        
        System.out.println("Original text: " + REFERENCE_TABLE);
        System.out.println("Transformed text: " + transformedTable);
        System.out.println("-------------------------");
      
        String encodedTextB = encoderDecoder.encode(plainText, offsetChar);
        System.out.println("-------------------------");
        System.out.println("Encoded with offset '" + offsetChar + "': " + encodedTextB);
        System.out.println("Answer Format (Encoded): " + offsetChar + encodedTextB);
        System.out.println("-------------------------");
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
        
        String shiftedTable = shiftLettersToBeginning(REFERENCE_TABLE, offsetIndex);
        System.out.println("Encode(OffsetIndex) : " + offsetIndex);
        System.out.println("Encode(ShiftedTable) : " + shiftedTable);

        StringBuilder encodedMessage = new StringBuilder();
        for (char c : plainText.toCharArray()) {
            int index = REFERENCE_TABLE.indexOf(c);
            if (index != -1) {
                encodedMessage.append(shiftedTable.charAt(index));
            } else {
                encodedMessage.append(c);
            }
        }
        return encodedMessage.toString();
    }

    public String decode(String encodedText, char offsetChar) {
    	 int offsetIndex = REFERENCE_TABLE.indexOf(offsetChar);
    	    if (offsetIndex == -1) {
    	        return "Invalid offset character";
    	    }
    	    
    	    String originalTable = shiftLettersToBeginning(REFERENCE_TABLE, offsetIndex);
    	    
    	    System.out.println("Encode(OriginalTable) : " + originalTable);

    	    StringBuilder decodedMessage = new StringBuilder();
    	    for (char c : encodedText.toCharArray()) {
    	        int index = originalTable.indexOf(c);
    	        if (index != -1) {
    	            decodedMessage.append(REFERENCE_TABLE.charAt(index));
    	        } else {
    	            decodedMessage.append(c);
    	        }
    	    }
    	    return decodedMessage.toString();
    }
}
