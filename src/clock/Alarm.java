package clock;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class Alarm {

    public LocalTime alarmTime;

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

    public LocalTime addAlarm(){

        Object hour = null;

        Integer[] hourArray = new Integer[24];

        for(Integer i = 0; i < 24; i++){
            hourArray[i] = i;
        }

        Integer[] minuteArray = new Integer[60];

        for(Integer i = 0; i < 60; i++){
            minuteArray[i] = i;
        }

        Dialog d = new Dialog();
        do{
            hour = d.selectDialog("What hour should the alarm go off?", hourArray);
        } while (hour == null);

        Object minute = d.selectDialog("What minute should the alarm go off in the " + hour + "hour of the day?", minuteArray);

        String formatTime = hour + ":" + minute + ":00";

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m:ss", Locale.UK);

        LocalTime aT = LocalTime.parse(formatTime, timeFormatter);

        d.messageDialog("Alarm Time", "Your alarm will go off at " + aT);

        return aT;
    }

    private void ring() {
        if(getSystemTime().equals(alarmTime)){
            System.out.println("Alarm time has rung");
        };
    }

    protected void checkForAlarm(){



    }
}
