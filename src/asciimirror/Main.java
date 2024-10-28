package asciimirror;

import java.util.*;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static Scanner scan = new Scanner(System.in);

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
                    System.out.println(readRow);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        //Using try -with -resources, java authomatically closes the stream so no need to close manually
        //read.close();
    }

    public static void cow (String filePath) {
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
    }
}