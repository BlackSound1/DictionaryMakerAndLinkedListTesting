// -----------------------------------------------------
// Written by: Matthew Segal
// ----------------------------------------------------

package PART1;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * This program writes a document acting as a list of all the words in a given file.
 */
public class Driver {
    public static void main(String[] args) {
        // Inits a Scanner
        Scanner userIn = new Scanner(System.in);

        // Intro stuff
        System.out.println("/////////////////////////////////////\n" +
                "WELCOME TO THE SUB-DICTIONARY CREATOR!\n" +
                "/////////////////////////////////////\n");

        System.out.print("Please enter the name of the file you wish to create a dictionary for: ");

        // Gets the file name without the extension
        String fileName = userIn.nextLine().trim();

        System.out.println("Your filename: " + fileName);

        System.out.println("\nThanks! Creating dictionary...");

        // Closes Scanner
        userIn.close();

        // Inits a new ArrayList that will contain all the words to add to the output file
        ArrayList<String> thingsToAddToFile = new ArrayList<>(0);

        // Reads the input file
        readFile(fileName, thingsToAddToFile);

        // Sorts the ArrayList alphabetically
        thingsToAddToFile.sort(null);

        // Writes to the output file
        writeToFile(thingsToAddToFile);

        System.out.println("\nFile written! Goodbye!");
    }

    /**
     * Checks the incoming word for rule violations and corrects them. If none, returns the word
     * @param word The word to check
     * @return The changed or unchanged version of the word, ready to add to the ArrayList
     */
    private static String conformToRules(String word){

        // Performs the necessary checks
        if (word.equalsIgnoreCase("a")){
            // Checks if word is the word A

            return "A";
        }else if (word.equalsIgnoreCase("i")){
            // Checks if word is the word I

            return "I";
        }else if (word.matches("\\d+.")) {
            // Checks if word is a number

            return null;
        }else if (word.endsWith("?") || word.endsWith("!") || word.endsWith("'") || word.endsWith("’")||
                word.endsWith(":") || word.endsWith(";") || word.endsWith(",") || word.endsWith(".")){
            // Checks if words ends with ,’'.:;!?

            return (word.substring(0, word.length() - 1)).toUpperCase();
        }else if (word.endsWith("’s")) {
            // Checks if word ends with ’s

            return word.substring(0, word.indexOf("’")).toUpperCase();
        }else if(word.endsWith("'s")){
            // Checks if word ends with 's

            return word.substring(0, word.indexOf("'")).toUpperCase();
        }else if (word.endsWith("’m")) {
            // Checks if word ends with ’m

            return word.substring(0, word.indexOf("’")).toUpperCase();
        }else if(word.endsWith("'m")) {
            // Checks if word ends with 'm

            return word.substring(0, word.indexOf("'")).toUpperCase();
        }else if (word.equals("=")){
            // Checks for the = sign

            return null;
        }else if (word.matches("[a-zA-Z]+\\d+[a-zA-Z]+|\\d+[a-zA-Z]+|[a-zA-Z]+\\d+")){
            // Checks if word has numbers within it

            return null;
        }else if (word.matches("[a-zA-Z]")){
            // Checks if word is exactly one character. Does not apply to A or I as
            // those are already checked

            return null;
        }
        return word.toUpperCase(); // Returns the word unchanged if no rules were broken
    }

    /**
     * Writes to the specified output file.
     * @param thingsToAddToFile The ArrayList the program must read from to write to the output file
     */
    private static void writeToFile(ArrayList<String> thingsToAddToFile){
        PrintWriter writer;

        // Creates a final Array containing the whole alphabet. Couldn't think of a better way
        // than just looping through it.
        final String[] alphabet = {"A","B","C","D",
                            "E","F","G","H",
                            "I","J","K","L",
                            "M","N","O","P",
                            "Q","R","S","T",
                            "U","V","W","X",
                            "Y","Z"};

        // Try to create a new PrintWriter for writing to the output file
        try{
            writer = new PrintWriter(new FileOutputStream("output files/SubDictionary.txt"),true);

            writer.println("The program produced this sub-dictionary. It has " +
                    thingsToAddToFile.size() + " entries.");

            // Loops through each letter of the alphabet
            for (String letter: alphabet) {
                boolean displayLetter = true;

                // Loops through each word in ArrayList
                for (String word: thingsToAddToFile) {
                    // Only happens when the Word starts with the Letter and displayLetter is set to true
                    if (word.startsWith(letter) && displayLetter){
                        writer.println("\n" + letter + "\n==");
                        writer.println(word);
                        displayLetter = false;
                    }else if (word.startsWith(letter)){
                        writer.println(word);
                    }
                }
            }
            // Closes the writer
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the given file
     * @param fileName The name of the file
     * @param thingsToAddToFile The Array List to be populated
     */
    private static void readFile(String fileName, ArrayList<String> thingsToAddToFile){
        Scanner reader = null;

        try {
            reader = new Scanner(new FileInputStream("samples/" + fileName),"cp1252");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        // If reader isn't null
        if (reader != null) {
            while (reader.hasNextLine()){
                // Creates a new String based on the line
                String line = reader.nextLine();
                //System.out.println(line); // Prints the line for reference

                // If the line isn't empty
                if (!line.equals("")){

                    // Creates a String Array based on the words of the line, split by spaces
                    String[] wordsInLine = line.split("\\s");

                    // Iterates through each word in the line
                    for (String word: wordsInLine) {

                        // Conforms the word to the given rules
                        String conformedWord = conformToRules(word);

                        if (conformedWord != null){

                            // Adds the word to the ArrayList only if ArrayList doesn't contain the word
                            if (!thingsToAddToFile.contains(conformedWord)){
                                thingsToAddToFile.add(conformedWord);
                            }
                        }
                    }
                }
            }
            // Closes the Scanner
            reader.close();
        }
    }
}
