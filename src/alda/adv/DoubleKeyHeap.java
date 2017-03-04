package alda.adv;


public class DoubleKeyHeap<K extends DoubleKeyInterface>{
    private final static int INITIAL_CAPACITY = 10;
    private final static int NODES = 2;
    private int size;
    private DoubleKeyInterface[] array;

    public DoubleKeyHeap(){
        array = new DoubleKeyInterface[INITIAL_CAPACITY];
        size = 0;
    }


    public K pullFirstKey(){
        return null;
    }
    public K pullSecondKey(){
        return null;
    }



    public void insert(K x){
        //Add x to Last place
        //percolate up
    }

    private void percolateDown(){
        //get the last element in the array add to hole
        //compare key with children and grandchildren, switch with highest priority, keep on until no children.
    }

    private void percolateUp(){
        //check if x.key1 has higher priority than grandparent, if true, switch
        //else, check if x.key2 has higher priority than parent, if true, switch
        //if both is false or cant move up more, break
    }

    private void enlargeArray(){
        DoubleKeyInterface[] tempArray = array;
        array = new DoubleKeyInterface[array.length * 2 + 1];
        for(int i = 0; i < tempArray.length; i++){
            array[i] = tempArray[i];
        }
    }


}
