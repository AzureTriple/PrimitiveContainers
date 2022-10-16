package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedShortListTest
{
    @Test
    void testInit()
    {
        final FixedShortList l = new FixedShortList(new short[] {0,1,2,3},2);
        final OrderedShortList o = new OrderedShortList(l);
        assertSame(l,o.data());
        assertEquals(o,new OrderedShortList(o));
    }
    
    @Test
    void testFind()
    {
        final OrderedShortList o = new OrderedShortList(new FixedShortList(new short[] {0,2,4,6},3));
        assertEquals(1,o.find((short)1));
        assertEquals(~1,o.find((short)2));
    }
    
    @Test
    void testAdd()
    {
        final FixedShortList l = new FixedShortList(new short[] {0,2,-1,-1},2);
        final OrderedShortList o = new OrderedShortList(l);
        assertEquals(1,o.add((short)1));
        assertArrayEquals(new short[] {0,1,2,-1},l.data());
        assertEquals(2,o.add((short)2));
        assertArrayEquals(new short[] {0,1,2,2},l.data());
    }
    
    @Test
    void testClone()
    {
        final OrderedShortList o = new OrderedShortList(new FixedShortList(new short[] {0,1,2,3},2));
        assertEquals(o,o.clone());
    }
}