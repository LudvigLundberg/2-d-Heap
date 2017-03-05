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

    /**
     *
     * @return
     */

    public K pullFirstKey(){
        if(!isEmpty()) {
            K returnValue = (K) array[1];
            array[1] = array[size--];
            percolateDown(1);

            return returnValue;

        }
        else{
            throw new IllegalStateException("Heap is empty");
        }
    }

    /**
     *
     * @return
     */

    public K pullSecondKey() {
        if(!isEmpty()){

            int highestPriorityIndex = 1;
            int i = 1;
            while (i < size+1 && i < 4 ){
                if(array[i].compareSecondKey(array[highestPriorityIndex]) < 0){
                    highestPriorityIndex = i;
                }
                i++;
            }

            K returnValue = (K) array[highestPriorityIndex];
            array[highestPriorityIndex] = array[size--];

            percolateDown(highestPriorityIndex);

            return returnValue;

        }
        else{
            throw new IllegalStateException("Heap is empty");
        }
    }

    /**
     *
     * @param x
     */

    public void insert(K x){
        if(x == null){throw new IllegalArgumentException("Value can't be null");}

        if(size == array.length-1){
            enlargeArray();
        }
        //Add x to Last place
        //percolate up

        array[++size] = x;
        percolateUp(size);

    }

    /**
     *
     * @param index
     */

    private void percolateDown(int index){
        boolean evenHeight = evenHeight(index);
        int child = index * NODES;
        int grandChild = index * NODES * NODES;
        int highestPriority = index;

        while (child < size + 1  && child < index*NODES + 3){
            if(evenHeight){
                if(array[child].compareSecondKey(array[highestPriority]) < 0) {
                    highestPriority = child;
                }
            }
            else{
                if(array[child].compareFirstKey(array[highestPriority]) < 0) {
                    highestPriority = child;
                }
            }
            child++;
        }

        while (grandChild < size + 1 && grandChild < index*NODES*NODES + 5 ){
            if(evenHeight){
                if(array[grandChild].compareSecondKey(array[highestPriority]) < 0) {
                    highestPriority = grandChild;
                }
            }
            else{
                if(array[grandChild].compareFirstKey(array[highestPriority]) < 0) {
                    highestPriority = grandChild;
                }
            }
            grandChild++;
        }

        if(highestPriority != index) {
            exchange(index, highestPriority);
            percolateDown(highestPriority);
        }


        //get the last element in the array add to hole
        //compare key with children and grandchildren, switch with highest priority, keep on until no children.
    }

    /**
     *
     * @param index
     */

    private void percolateUp(int index){
        //check if x.key1 has higher priority than grandparent, if true, switch
        //else, check if x.key2 has higher priority than parent, if true, switch
        //if both is false or cant move up more, break

        boolean evenHeight = evenHeight(index);

        int grandParentIndex = index/4;

        if(grandParentIndex > 0){
            int compareInt = compareWithIndex(index, grandParentIndex, evenHeight);

            if(compareInt < 0){
                exchange(index, grandParentIndex);
                percolateUp(index);
                percolateUp(grandParentIndex);
                return;
            }
        }

        int parentIndex = index/2;

        if(parentIndex > 0){
            int compareInt = compareWithIndex(index, parentIndex, !evenHeight);

            if(compareInt < 0){
                exchange(index, parentIndex);
                percolateUp(parentIndex);
            }
        }
    }

    /**
     *
     * @param index
     * @param parentIndex
     * @param even
     * @return
     */
    private int compareWithIndex(int index, int parentIndex, boolean even){
        if(even) {
            return array[index].compareSecondKey(array[parentIndex]);
        }
        else{
            return array[index].compareFirstKey(array[parentIndex]);
        }
    }

    /**
     *
     * @param first
     * @param second
     */

    private void exchange(int first, int second){
        DoubleKeyInterface k = array[first];
        array[first] = array[second];
        array[second] = k;
    }

    /**
     * Calculates if the height of an index is even or uneven. index 1 has height 1.
     * @param index the index to be calculated
     * @return true if even (2,4,6,8... etc.), false otherwise
     */

    protected boolean evenHeight(int index){
        int height = 0;
        while(index > 0){
            index = index/2;
            height++;
        }
        return height % 2 == 0;
    }

    /**
     *
     */

    private void enlargeArray(){
        DoubleKeyInterface[] tempArray = array;
        array = new DoubleKeyInterface[array.length * 2 + 1];
        for(int i = 1; i <= size; i++){
            array[i] = tempArray[i];
        }
    }

    /**
     *
     * @return
     */

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @return
     */

    public int size(){
        return size;
    }
}
