import clock.pQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import clock.pQueue.Node;

public class test {

    @Test
    void addAlarmAgainstCurrentTime(){

       pQueue pQ = new pQueue();

       pQ.add();

       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

       LocalTime time = pQ.getHeadNode().getNodeData().getAlarmTime();

       Assertions.assertEquals(time, LocalTime.parse(formatter.format(LocalTime.now())));



    }

}
