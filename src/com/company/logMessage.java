package com.company;


/**
 * Created by ReceCoffin on 10/12/2017.
 * Store the information from a single log message into a JSON format
 */
public class logMessage {
    private String wholeMessage;
    public String level;
    public String timeStamp;
    public String file;
    public String fileLine;
    public String message;
    public logMessage(String wholeMesage){
        this.wholeMessage = wholeMesage;
        //parse the entire string into parts
        parseMessage(wholeMesage);
    }

    private void parseMessage(String wholeMesage) {
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
    }

    private void parseFileAndLine(String s) {
        s = s.substring(1, s.length() - 1);
        //split and store
        String line[] = s.split(":");
        file = line[0];
        fileLine = line[1];
    }

    //TODO: Correctly Parse the time and date
    private void parseTimeDate(String date, String time) {
/*        date = date.substring(1, date.length());
        time = date + " " + time.substring(0, time.length() - 1);
        System.out.println(time);
        SimpleDateFormat template = new SimpleDateFormat("yyyyMMdd HHmmss");
        try {
            Date date1 = template.parse("time");
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            System.out.println(dt.format(date1));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }

    //compare log messages in order to delete duplicates
    public boolean equals(logMessage other) {
        //all attributes
        if(this.wholeMessage.equalsIgnoreCase(other.wholeMessage)) return true;
        return false;
    }
}
