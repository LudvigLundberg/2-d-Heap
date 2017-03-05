package alda.adv;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class DoubleKeyHeapTest {
    private DoubleKeyHeap<DoubleKeyIntegerPair> doubleHeap = new DoubleKeyHeap<>();

    @Before
    public void setUp() throws Exception {
        doubleHeap.insert(new DoubleKeyIntegerPair(10,10));
        doubleHeap.insert(new DoubleKeyIntegerPair(100,1));
        doubleHeap.insert(new DoubleKeyIntegerPair(5,5));
        doubleHeap.insert(new DoubleKeyIntegerPair(1,100));
        doubleHeap.insert(new DoubleKeyIntegerPair(2,100));
        doubleHeap.insert(new DoubleKeyIntegerPair(100,2));
    }

    @Test
    public void testHeightCalculator(){
        assertEquals(doubleHeap.evenHeight(1), false );
        assertEquals(doubleHeap.evenHeight(2), true );
        assertEquals(doubleHeap.evenHeight(3), true );
        assertEquals(doubleHeap.evenHeight(6), false );
        assertEquals(doubleHeap.evenHeight(8), true );
        assertEquals(doubleHeap.evenHeight(14), true );
        assertEquals(doubleHeap.evenHeight(17), false );
        assertEquals(doubleHeap.evenHeight(19), false );

    }

    @Test
    public void testPullFirstKey(){
        assertTrue(doubleHeap.size() == 6);
        assertTrue(doubleHeap.pullFirstKey().equals(new DoubleKeyIntegerPair(1, 100)));
        assertTrue(doubleHeap.size() == 5);
        assertTrue(doubleHeap.pullSecondKey().equals(new DoubleKeyIntegerPair(100, 1)));
        assertTrue(doubleHeap.size() == 4);
    }

    @Test
    public void testPercolateUp1000Elements(){
        doubleHeap = new DoubleKeyHeap<>();
        for(int i = 0; i< 1000; i++){
            doubleHeap.insert(new DoubleKeyIntegerPair(1000,1000));
        }
        doubleHeap.insert(new DoubleKeyIntegerPair(1,2));
        doubleHeap.insert(new DoubleKeyIntegerPair(2,1));


        assertTrue(doubleHeap.pullSecondKey().equals(new DoubleKeyIntegerPair(2,1)));
        assertTrue(doubleHeap.pullFirstKey().equals(new DoubleKeyIntegerPair(1,2)));
    }

    @Test
    public void pullSecondWithIndex1(){
        assertTrue(doubleHeap.pullFirstKey().equals(new DoubleKeyIntegerPair(1,100)));
        assertTrue(doubleHeap.pullSecondKey().equals(new DoubleKeyIntegerPair(100,1)));
        assertEquals(doubleHeap.size(), 4);

        doubleHeap.insert(new DoubleKeyIntegerPair(1,1));
        assertTrue(doubleHeap.pullSecondKey().equals(new DoubleKeyIntegerPair(1,1)));

        doubleHeap.insert(new DoubleKeyIntegerPair(1,1));
        assertTrue(doubleHeap.pullFirstKey().equals(new DoubleKeyIntegerPair(1,1)));
    }

    @Test
    public void insertionOrderPullFirst(){
        doubleHeap = new DoubleKeyHeap<>();
        DoubleKeyHeap<DoubleKeyIntegerPair> doubleHeap2 = new DoubleKeyHeap<>();

        for(int i = 1; i < 1000; i++){
            doubleHeap.insert(new DoubleKeyIntegerPair(i,i));
        }
        for(int i = 999; i > 0; i--){
            doubleHeap2.insert(new DoubleKeyIntegerPair(i,i));
        }
        assertEquals(doubleHeap.size(), doubleHeap2.size());





        for(int i = 1 ; i < 999; i++){
            assertEquals(doubleHeap.pullFirstKey(), doubleHeap2.pullFirstKey());
        }
    }

    @Test
    public void insertionOrderPullSecond(){
        doubleHeap = new DoubleKeyHeap<>();
        DoubleKeyHeap<DoubleKeyIntegerPair> doubleHeap2 = new DoubleKeyHeap<>();

        for(int i = 1; i < 1000; i++){
            doubleHeap.insert(new DoubleKeyIntegerPair(i,i));
        }
        for(int i = 999; i > 0; i--){
            doubleHeap2.insert(new DoubleKeyIntegerPair(i,i));
        }

        for(int i = 1; i< 1000; i++){
            assertEquals(doubleHeap.pullSecondKey(), doubleHeap2.pullSecondKey());
        }

    }

    @Test
    public void randomMix(){
        Random random = new Random();

        doubleHeap = new DoubleKeyHeap<>();
        DoubleKeyHeap<DoubleKeyIntegerPair> doubleHeap2 = new DoubleKeyHeap<>();

        ArrayList<DoubleKeyIntegerPair> array1 = new ArrayList<>();
        ArrayList<DoubleKeyIntegerPair> array2 = new ArrayList<>();

        for(int i = 1; i < 10000; i++){
            array1.add(new DoubleKeyIntegerPair(i,i));
            array2.add(new DoubleKeyIntegerPair(i,i));
        }

        while (!array1.isEmpty()){
            doubleHeap.insert(array1.remove(random.nextInt(array1.size())));
            doubleHeap2.insert(array2.remove(random.nextInt(array2.size())));
        }

        while (doubleHeap.size()>1 && doubleHeap2.size()>1){
            assertEquals(doubleHeap.pullFirstKey(), doubleHeap2.pullFirstKey());
            assertEquals(doubleHeap.pullSecondKey(), doubleHeap2.pullSecondKey());
        }


    }


    @Test
    public void otherTypeFirstTest(){
        DoubleKeyHeap<DoubleKeyIntegerStringPair> doubleHeap = new DoubleKeyHeap<>();
        doubleHeap.insert(new DoubleKeyIntegerStringPair(3,"C"));
        doubleHeap.insert(new DoubleKeyIntegerStringPair(5,"A"));
        doubleHeap.insert(new DoubleKeyIntegerStringPair(2,"D"));
        doubleHeap.insert(new DoubleKeyIntegerStringPair(1,"E"));
        doubleHeap.insert(new DoubleKeyIntegerStringPair(4,"B"));

        assertEquals(doubleHeap.pullFirstKey(), new DoubleKeyIntegerStringPair(1,"E"));
        assertEquals(doubleHeap.pullFirstKey(), new DoubleKeyIntegerStringPair(2,"D"));
        assertEquals(doubleHeap.pullFirstKey(), new DoubleKeyIntegerStringPair(3,"C"));
        assertEquals(doubleHeap.pullFirstKey(), new DoubleKeyIntegerStringPair(4,"B"));
        assertEquals(doubleHeap.pullFirstKey(), new DoubleKeyIntegerStringPair(5,"A"));
    }

    @Test
    public void otherTypeSecondTest(){
        DoubleKeyHeap<DoubleKeyIntegerStringPair> doubleHeap = new DoubleKeyHeap<>();
        doubleHeap.insert(new DoubleKeyIntegerStringPair(3,"C"));
        doubleHeap.insert(new DoubleKeyIntegerStringPair(5,"A"));
        doubleHeap.insert(new DoubleKeyIntegerStringPair(2,"D"));
        doubleHeap.insert(new DoubleKeyIntegerStringPair(1,"E"));
        doubleHeap.insert(new DoubleKeyIntegerStringPair(4,"B"));

        assertEquals(doubleHeap.pullSecondKey(), new DoubleKeyIntegerStringPair(5,"A"));
        assertEquals(doubleHeap.pullSecondKey(), new DoubleKeyIntegerStringPair(4,"B"));
        assertEquals(doubleHeap.pullSecondKey(), new DoubleKeyIntegerStringPair(3,"C"));
        assertEquals(doubleHeap.pullSecondKey(), new DoubleKeyIntegerStringPair(2,"D"));
        assertEquals(doubleHeap.pullSecondKey(), new DoubleKeyIntegerStringPair(1,"E"));

    }

    @Test

    public void otherTypeLargeFirstTest(){
        Random random = new Random();

        DoubleKeyHeap<DoubleKeyIntegerStringPair> doubleHeap = new DoubleKeyHeap<>();
        DoubleKeyHeap<DoubleKeyIntegerStringPair> doubleHeap2 = new DoubleKeyHeap<>();

        ArrayList<DoubleKeyIntegerStringPair> array1 = new ArrayList<>();
        ArrayList<DoubleKeyIntegerStringPair> array2 = new ArrayList<>();

        String string = "";
        for(int i = 1; i < 100; i++){
            string += "A";
            array1.add(new DoubleKeyIntegerStringPair(i,string));
            array2.add(new DoubleKeyIntegerStringPair(i,string));
        }
        while (!array1.isEmpty()){
            doubleHeap.insert(array1.remove(random.nextInt(array1.size())));
            doubleHeap2.insert(array2.remove(random.nextInt(array2.size())));
        }
        string = "";
        for(int i = 1; i < 100; i++){
            string += "A";
            DoubleKeyIntegerStringPair temp = new DoubleKeyIntegerStringPair(i, string);
            assertEquals(temp, doubleHeap.pullFirstKey());
            assertEquals(temp, doubleHeap2.pullFirstKey());
            temp = null;

        }

    }

    @Test
    public void otherTypeLargeSecondTest(){
        Random random = new Random();

        DoubleKeyHeap<DoubleKeyIntegerStringPair> doubleHeap = new DoubleKeyHeap<>();
        DoubleKeyHeap<DoubleKeyIntegerStringPair> doubleHeap2 = new DoubleKeyHeap<>();

        ArrayList<DoubleKeyIntegerStringPair> array1 = new ArrayList<>();
        ArrayList<DoubleKeyIntegerStringPair> array2 = new ArrayList<>();

        String string = "";
        for(int i = 99; i > 0; i--){
            string += "A";
            array1.add(new DoubleKeyIntegerStringPair(i,string));
            array2.add(new DoubleKeyIntegerStringPair(i,string));
        }
        while (!array1.isEmpty()){
            doubleHeap.insert(array1.remove(random.nextInt(array1.size())));
            doubleHeap2.insert(array2.remove(random.nextInt(array2.size())));
        }
        string = "";
        for(int i = 99; i > 0; i--){
            int tempInt = 100-i;
            while (tempInt > 0){
                string += "A";
                tempInt--;
            }
            DoubleKeyIntegerStringPair temp = new DoubleKeyIntegerStringPair(i, string);
            assertEquals(temp, doubleHeap.pullSecondKey());
            assertEquals(temp, doubleHeap2.pullSecondKey());
            string = "";

        }

    }
}