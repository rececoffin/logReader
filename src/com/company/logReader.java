package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/**
Rece Coffin
Read data from a log file, print in a JSON format
 */

public class logReader {
    private String fileName;
    private String levelFilter;
    public logReader(String[] args) {
        //check for flags
        //-f [filename] is required -c [level] is optional
        //assuming the user enters flags in that order
        //user gave 4 arguments, wants to filter
        if(args.length == 4){
            //get the file name
            if(args[0].equals("-f") && args[2].equals("-l")){
                fileName = args[1];
                levelFilter = toUpperCase(args[3]);
            }
            else if (args[2].equals("-f") && args[0].equals("-c")){
                fileName = args[3];
                levelFilter = toUpperCase(args[1]);
            }
            //arguments are not valid
            else {
                System.out.println("Invalid arguments.");
                System.exit(1);
            }
        }
        //otherwise 2 arg
        else if (args.length == 2){
            levelFilter = "INFO";
            if(args[0].equals("-f")){
                fileName = args[1];
            }
            else {
                System.out.println("Invalid arguments.");
                System.exit(1);
            }
        }
        else {
            System.out.println("Invalid arguments.");
            System.exit(1);
        }
        //read in the file
/*        System.out.println(fileName);
        System.out.println(levelFilter);*/
        try {
            readLogFile();
        } catch (IOException e) {
            System.out.println(fileName + " not found.");
            System.exit(1);
        }
    }

    private void readLogFile() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        //print all lines in the file
        for(String fullMessage : lines){
            logMessage lm = new logMessage(fullMessage);
        }
    }


    public static void main(String[] args) {
        logReader reader = new logReader(args);
    }
}
