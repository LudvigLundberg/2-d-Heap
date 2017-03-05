package alda.adv;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleKeyHeapTest {
    DoubleKeyHeap<DoubleKeyIntegerPair> doubleHeap = new DoubleKeyHeap<>();

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
}