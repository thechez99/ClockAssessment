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
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * pQueue is a sorted linked list priority queue.
 * <p>Used for storing alarm objects in a way in which is easily sortable and scannable</p>
 * <p>Also contains subclass Node</p>
 *
 * @extends Alarm
 *
 */

public class pQueue extends Alarm {

private Node headNode;
private int size;

    /* Class Constructor */
    public pQueue(){

        this.headNode = null;
        this.size = 0;

    }

    /* Gets and Sets */
    public Node getHeadNode(){
        return this.headNode;
    }

    /* Class Functions */
    /**
    * This function adds the alarm into the priority queue and then sorts the queue
    *
    * @return void
    * */
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

    /**
     * This removes a node from the queue in place.
     * It then ensures the queue is spliced back together.
     *
     * @param priority The time of the alarm to be removed is the priority in this instance.
     * @return void
     */

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
    /**
     * Returns a boolean value based on if the queue is empty or not.
     *
     * @return boolean
     */

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * This function returns the alarm object at the head of the priority queue.
     * @return Alarm (Alarm object at head of queue)
     */
    public Alarm head(){
        return headNode.getNodeData();
    }

    /**
     * This will check for alarms to see if there is one in the queue
     * that is ready to ring. It will then remove the ringing alarm from the queue.
     *
     * <p>Logs to the console the next alarm time, no of alarms in queue and also the time</p>
     *
     * @return void
     */

    public void checkForAlarms(){

        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(format.format(LocalTime.now()));

        for(int i = 0; i < size; i++){
            Node currentNode = headNode;
            Node nextNode = currentNode.nextNode;
            for(int j = 0; j < size; j++){
                if(currentNode.getNodeData().getAlarmTime().equals(time)){
                    this.ring();
                    remove(currentNode.getNodeData().getAlarmTime());
                }
            }
            currentNode = nextNode;
            nextNode = nextNode.getNextNode();
        }

        try{
            System.out.println("Checked for alarm, there are " + size + " alarms in the queue. The next alarm is at: " + headNode.getNodeData().getAlarmTime());
            System.out.println("Local time is " + format.format(time));
        } catch (Exception e){
            System.out.println("No alarms set");
        }

    }

    /**
     * Sorts the priority queue. Is a utility function for the queue.
     */
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

    /**
     * Returns the list of alarms as a string
     * @return String
     */
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

    /**
     * Opens a dialog with the list of alarms contained within.
     *
     * @return void
     */
    public void listAlarmDialog(){
        Dialog d = new Dialog();
        if(this.isEmpty()){
            d.messageDialog("No Alarms", "There are no alarms currently set.");
        } else{
            d.messageDialog("Alarm Times", "Your alarms will go off at: " + this.toString());
        }
    }

    /**
     * Node Subclass
     * <p>This is a node for sorted linked priority queue</p>
     * <p>Stores an alarm object within the node</p>
     */
    public class Node{

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
