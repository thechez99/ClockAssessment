/*
*
* This is a take on the Unsorted Linked Priority Queue
* A priority queue was needed to store the alarms
* So this one will act on a FIFO basis.
*
* */

package clock;

import java.awt.event.ActionListener;
import java.time.LocalTime;

public class pQueue extends Alarm {

private Node headNode;
private int size;

    /* Class Constructor */
    public pQueue(){

        this.headNode = null;
        this.size = 0;

    }

    /* Class Functions */
    public void add(){

        Alarm alarm = new Alarm();
        alarm.addAlarm();

        Node addNode = new Node(alarm);
        if(isEmpty()){
            headNode = addNode;
        } else {
            Node currentNode = headNode;
            while(currentNode.nextNode != null){
                currentNode = currentNode.nextNode;
            }
            currentNode.nextNode = addNode;
        }

        size++;
        sort();
    }

    public void remove(LocalTime priority){

        if(this.size > 1){
            for (int i = 0; i < size; i++){
                Node currentNode  = headNode;
                Node nextNode = currentNode.nextNode;
                for(int j = 0; j < size; j++){
                    if(currentNode.getNodeData().getAlarmTime().equals(priority)){
                        currentNode = nextNode;

                    } else{
                        currentNode.getNextNode();
                    }
                }
            }
        } else{
            headNode = null;
        }

    }

    /* Class Supporting Functions */
    public boolean isEmpty(){
        return size == 0;
    }

    public Alarm head() throws Exception{
        return headNode.getNodeData();
    }

    public void checkForAlarms(){

        /*
        * This function will run a separate thread which will execute once a minute
        * to see if an alarm is ready to ring. If it is then the alarm will ring.
        * */

        /*new Thread(new Runnable(){
            @Override
            public void run(Node currenNode){

                try {
                    if (currenNode.getNodeData().getAlarmTime().equals(LocalTime.now())){
                        Thread.sleep(60000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();*/
    }

    protected void sort(){
        if(size > 1){
            for(int i = 0; i< size; i++){
                Node currentNode = headNode;
                Node next = currentNode.nextNode;
                for(int j = 0; j < size - 1; j++){
                    if (currentNode.getNodeData().getAlarmTime().isAfter(next.getNodeData().getAlarmTime())) {
                        Node temp = currentNode;
                        currentNode = next;
                        next = temp;
                    }
                    currentNode = next;
                    next = next.nextNode;
                }
            }
        }
    }

    public String toString(){
        String result = "";

        if(isEmpty()){
            result += " Queue is Empty ";
        } else {
            Node tempNode = headNode;
            do{
                result += tempNode.getNodeData().getAlarmTime() + ", ";
                tempNode = tempNode.nextNode;
            } while(tempNode != null);
        }

        return result;
    }

    public void removeFromQueue(){

    }

    /* pQueue Subclass */
    class Node{

        private Alarm nodeData;
        private Node nextNode;

        /* Constructor */
        public Node(Alarm dataIn){
            this.nodeData = dataIn;
        }

        public Node(){
            this.setNextNode(null);
        }

        /* Node Get and Set Functions */
        public Alarm getNodeData(){
            return this.nodeData;
        }

        public void setNodeData(Alarm dataIn){
            this.nodeData = dataIn;
        }

        public Node getNextNode(){
            return nextNode;
        }

        public void setNextNode(Node nextNode){
            this.nextNode = nextNode;
        }


    }
}
