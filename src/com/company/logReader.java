package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/**
Rece Coffin
Read data from a log file, print in a JSON format
 */

public class logReader {
    private String fileName;
    private String levelFilter;
    private int levelFilterIndex;
    //all possible levels
    public static final List<String> LEVELS = Collections.unmodifiableList(Arrays.asList(
                                            "ERROR", "WARN", "INFO", "DEBUG", "TRACE"));
    public logReader(String[] args) {
        checkValidArgs(args);
        //user input invalid min level
        if(!LEVELS.contains(levelFilter)){
            System.out.println("Invalid level \"" + levelFilter + "\"");
            System.exit(3);
        }
        levelFilterIndex = LEVELS.indexOf(levelFilter);
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

    private void checkValidArgs(String[] args){
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
    }

    //read in the log file
    private void readLogFile() throws IOException {
        //print each line of the file
        FileReader reader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(reader);
        //loop and read
        String message;
        String lastLine = "null";
        while((message = br.readLine()) != null){
            if(message.equals(lastLine)) continue;
            logMessage lm = new logMessage(message);
            //only print if above a specific level
            if(LEVELS.indexOf(lm.level) <=  levelFilterIndex) lm.printMessage();
            lastLine = message;
        }
    }

    public static void main(String[] args) {
        logReader reader = new logReader(args);
    }
}
