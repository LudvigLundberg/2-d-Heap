package alda.adv;

/**
 *
 * @author Ludvig Lundberg
 * @param <K> an object that implements the DoubleKeyInterface
 */

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
     * Pull the value with the highest first key priority from the Heap.
     * @return The element with the highest priority comparing the first key.
     * @throws IllegalStateException When the method is invoked on a empty heap.
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
     * Pull the value with the highest second key priority from the Heap.
     * @return The element with the highest priority comparing the second key.
     * @throws IllegalStateException When the method is invoked on a empty heap.
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
     * Adds the specified element to the Heap, depending on the priority of the item, the heap structure might change.
     * @param x The item to be inserted.
     * @throws IllegalArgumentException when inserted value is null;
     */

    public void insert(K x){
        if(x == null){throw new IllegalArgumentException("Value can't be null");}

        if(size == array.length-1){
            enlargeArray();
        }

        array[++size] = x;
        percolateUp(size);

    }

    /**
     * Private helper method that takes an index in the array, and then compares the key with the index children and grandchildren.
     * The method compares the index key to all children and grandchildren of a node and if there is one with higher-priority, it exchanges these indexes and recursively continuous
     * Which key it compares to the children or the grandchildren depends on which height the index currently is at.
     * The method continues until the item is at the correct height, or there are no children left to compare with.
     * @param index the index to be percolated downwards.
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

    }

    /**
     * Private helper method that takes an index in the array, and then compares the key with the index grandparents and grandparents.
     * If the item att index should be moved up in the heap, the method will exchange these values and recursively keep on going until no switches has been made,
     * or the top of the heap has been reached.
     * @param index the index in the array that will be percolated up.
     */

    private void percolateUp(int index){

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
     * Compares two indexes in the internal array.
     * @param index index of the node to be compared.
     * @param parentIndex index of the parental node.
     * @param even a boolean containing whether the comparison shall be made with the even key or not.
     * @return -1 if the index is less than the parent, 0 if they are the same, and 1 if the parent is less than the index.
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
     * Changes elements of two given indexes.
     * @param first index of the first element
     * @param second index of the first element
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
     * Doubles the size of the array.
     */

    private void enlargeArray(){
        DoubleKeyInterface[] tempArray = array;
        array = new DoubleKeyInterface[array.length * 2 + 1];
        for(int i = 1; i <= size; i++){
            array[i] = tempArray[i];
        }
    }

    /**
     * @return True if the heap is empty.
     */

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return The numbers of elements in the heap.
     */

    public int size(){
        return size;
    }
}
