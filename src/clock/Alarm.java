package clock;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class Alarm {

    public LocalTime alarmTime;
    Object hour, minute;

    //Date time formatter for standard time formats
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m:ss", Locale.UK);

    public Alarm(LocalTime time){
        setAlarmTime(time);
    }

    public Alarm(){

    }

    /* Gets and Sets */

    public LocalTime getAlarmTime() {
        return alarmTime;
    }

    private void setAlarmTime(LocalTime time){
        this.alarmTime = time;
    }

    private LocalTime getSystemTime(){
        return LocalTime.now();
    }

    /* Class Functions */

    public static void main(String args[]){
        Alarm a = new Alarm();

        a.addAlarm();
    }

    public void addAlarm(){

        this.hour = null;
        this.minute = null;

        Dialog d = new Dialog();

        Integer[] hourArray = new Integer[24];
        Integer[] minuteArray = new Integer[60];

        for(Integer i = 0; i < hourArray.length; i++){
            hourArray[i] = i;
        }

        for(Integer i = 0; i < minuteArray.length; i++){
            minuteArray[i] = i;
        }

        do{
            this.hour = d.selectDialog("What hour should the alarm go off?", hourArray);
        } while (this.hour == null);

        do{
            this.minute = d.selectDialog("What minute should the alarm go off in the " + hour + "hour of the day?", minuteArray);
        } while (this.minute == null);

        String formatTime = hour + ":" + minute + ":00";

        this.alarmTime = LocalTime.parse(formatTime, this.timeFormatter);

        d.messageDialog("Alarm set", "Your alarm will go off at " + this.alarmTime);
    }

    private void ring() {
        if(getSystemTime().equals(alarmTime)){
            System.out.println("Alarm time has rung");
        };
    }

    protected void checkForAlarm(){



    }
}
