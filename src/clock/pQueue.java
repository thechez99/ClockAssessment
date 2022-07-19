/*
*
* This is a take on the Unsorted Linked Priority Queue
* A priority queue was needed to store the alarms
* So this one will act on a FIFO basis.
*
* */

package clock;

public class pQueue {

private Node headNode;
private int size;

    /* Class Constructor */
    public pQueue(){

        this.headNode = null;
        this.size = 0;

    }

    /* Class Functions */
    public void add(Alarm item){

        Alarm alarm = new Alarm();

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

    /* Class Supporting Functions */
    private boolean isEmpty(){
        return size == 0;
    }

    public Alarm head() throws Exception{
        return headNode.getNodeData();
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
        String result = "[";

        if(isEmpty()){
            result += " Queue is Empty ]";
        } else {
            Node tempNode = headNode;

            while(tempNode != null){
                result += tempNode.getNodeData().toString() + "], ";
                tempNode = tempNode.nextNode;
            }
        }

        return result;
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
