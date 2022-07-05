package clock;

import java.time.LocalTime;

public class Alarm {

    public LocalTime alarmTime;

    public Alarm(LocalTime time){
        setAlarmTime(time);
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

    private void ring() {
        if(getSystemTime().equals(alarmTime)){
            System.out.println("Alarm time has rung");
        };
    }
}
