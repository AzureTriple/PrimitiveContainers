package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedIntListTest
{
    @Test
    void testInit()
    {
        final FixedIntList l = new FixedIntList(new int[] {0,1,2,3},2);
        final OrderedIntList o = new OrderedIntList(l);
        assertSame(l,o.data());
        assertEquals(o,new OrderedIntList(o));
    }
    
    @Test
    void testFind()
    {
        final OrderedIntList o = new OrderedIntList(new FixedIntList(new int[] {0,2,4,6},3));
        assertEquals(1,o.find(1));
        assertEquals(~1,o.find(2));
    }
    
    @Test
    void testAdd()
    {
        final FixedIntList l = new FixedIntList(new int[] {0,2,-1,-1},2);
        final OrderedIntList o = new OrderedIntList(l);
        assertEquals(1,o.add(1));
        assertArrayEquals(new int[] {0,1,2,-1},l.data());
        assertEquals(2,o.add(2));
        assertArrayEquals(new int[] {0,1,2,2},l.data());
    }
    
    @Test
    void testClone()
    {
        final OrderedIntList o = new OrderedIntList(new FixedIntList(new int[] {0,1,2,3},2));
        assertEquals(o,o.clone());
    }
}