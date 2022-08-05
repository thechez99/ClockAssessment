package clock;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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

    protected void ring() {
        final String url = "chime.wav";
        Dialog d = new Dialog();



        //Set up for playing alarm chime from sound file.
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Alarm.class.getResourceAsStream("media/" + url));
                    clip.open(inputStream);
                    clip.start();
                    d.warningDialog("Alarm Time!", "This is your alarm! \n This selecting OK will remove the alarm from your queue");
                } catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }

        }).start();
    }
}

/*
* SOURCES:
* ring(): How Can I play a sound in Java? StackOverflow https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
*
*
* */