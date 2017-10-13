package com.company;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ReceCoffin on 10/12/2017.
 * Store the information from a single log message into a JSON format
 */
public class logMessage {
    //all the attributes from a single message
    private String wholeMessage;
    public String level;
    public String timeStamp;
    public String file;
    public int fileLine;
    public String message;
    public logMessage(String wholeMesage){
        this.wholeMessage = wholeMesage;
        //parse the entire string into parts
        parseMessage(wholeMesage);
    }

    private void parseMessage(String wholeMesage) {
        try {
            //split at the spaces, get rid of brackets
            String line[] = wholeMesage.split(" ");
            //first entry is the level
            level = line[0].substring(1,line[0].length() - 1);
            //remove time and date, put in correct format
            parseTimeDate(line[1], line[2]);
            parseFileAndLine(line[3]);
            //finally get the message
            message = "";
            for(int i = 4; i < line.length; i++){
                message += line[i] + " ";
            }
/*            //print the message
            printMessage();*/
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data in log file.");
            System.exit(2);
        }
    }

    //Parse out the file name and line, check the line number is valid
    private void parseFileAndLine(String s) {
        s = s.substring(1, s.length() - 1);
        //split and store
        String line[] = s.split(":");
        file = line[0];
        fileLine = Integer.parseInt(line[1]);
        if(fileLine <= 0){
            System.out.println("Invalid file line.");
            System.exit(2);
        }
    }

    //Parse the time and date fields into something more readable
    private void parseTimeDate(String date, String time) {
        date = date.substring(1, date.length());
        time = date + " " + time.substring(0, time.length() - 1);
        SimpleDateFormat template = new SimpleDateFormat("yyyyMMdd HHmmss");
        try {
            Date date1 = template.parse(time);
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            timeStamp = dt.format(date1);
        } catch (ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    //print the message in a single line, JSON formatted
    public void printMessage(){
        if(message.isEmpty()) {
            System.out.println("Invalid message type");
            System.exit(2);
        }
        System.out.println("{\"level\":\"" + level + "\", \"timestamp\":\"" + timeStamp + "\", \"file\":\""
                            + file + "\", \"line\":\"" + fileLine + "\", \"message\":\"" + message + "\"}");
    }

    //compare log messages in order to delete duplicates
    public boolean equals(logMessage other) {
        //easiest to compare the whole string at once
        if(this.wholeMessage.equalsIgnoreCase(other.wholeMessage)) return true;
        return false;
    }
}
