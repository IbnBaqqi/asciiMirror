package asciimirror;

import java.util.*;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static Scanner scan = new Scanner(System.in);
    private static List<String> mirror = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        readFile();
    }

    public static void readFile() throws IOException{
        System.out.println("Input the file path: ");
        String filePath = scan.nextLine();
        //cow(filePath);
        Boolean rowsLeft = true;
        String readRow = null;
        //BufferedReader read = null;
        try ( BufferedReader read = new BufferedReader(new FileReader(filePath))){
            while(rowsLeft){
                try {
                    readRow = read.readLine();
                } catch (IOException e) {
                    System.out.println("Unable to read file");
                }
                if (readRow == null) {
                    rowsLeft = false;
                }else {
                    mirrorLine(readRow, mirror);
                    //System.out.println(readRow);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        //Using try -with -resources, java automatically closes the stream so no need to close manually
        //read.close();
        printModString(formatWhitespace(mirror));
    }

    //Add read line to list
    public static void mirrorLine(String line, List<String> mirror) {
        mirror.add(line);
    }

    //Print modified String read from file
    public static void printModString(List<String> mirrorMod) {
        if (mirrorMod.isEmpty()) {
            return;
        }
        for (String str : mirrorMod) {
            String reversed = reversedString(str);
            System.out.println(str + " | " + reversed);
        }
    }

    //Print the String from the end to front
    public static String reversedString(String line){
        StringBuilder reversed = new StringBuilder();

        for (int i = line.length() - 1; i >= 0; i--){
            char ch = line.charAt(i);
            reversed.append(getMirroredChar(ch));
        }
        return reversed.toString();
    }

    //To add whitespace to end of lines not long as the longest
    public static List<String> formatWhitespace(List<String> mirror) {
        int longValue = longestString(mirror);
        List<String> modString = new ArrayList<>();
        for (String str : mirror) {
            modString.add(String.format("%-" + longValue + "s", str));
        }
        return modString;
    }

    //To get the longest String in the list
    public static int longestString(List<String> mirror) {
        String longest = "";
        for (String str : mirror) {
            if (str.length() > longest.length()) {
                longest = str;
            }
        }
        return longest.length();
    }

    // Helper method to return the horizontally opposite character if applicable
    public static char getMirroredChar(char ch) {
        return switch (ch) {
            case '<' -> '>';
            case '>' -> '<';
            case '[' -> ']';
            case ']' -> '[';
            case '{' -> '}';
            case '}' -> '{';
            case '(' -> ')';
            case ')' -> '(';
            case '/' -> '\\';
            case '\\' -> '/';
            default -> ch;
        };
    }

    /*public static void cow (String filePath) {
        ArrayList<String> cow = new ArrayList<>();

        cow.add("            ^__^");
        cow.add("    _______/(oo)");
        cow.add("/\\/(       /(__)");
        cow.add("   | w----||    ");
        cow.add("   ||     ||    ");

        System.out.println(filePath);

        for (String part : cow) {
            System.out.println(part);
        }
    }*/
}