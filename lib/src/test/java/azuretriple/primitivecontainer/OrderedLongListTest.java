package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedLongListTest
{
    @Test
    void testInit()
    {
        final FixedLongList l = new FixedLongList(new long[] {0,1,2,3},2);
        final OrderedLongList o = new OrderedLongList(l);
        assertSame(l,o.data());
        assertEquals(o,new OrderedLongList(o));
    }
    
    @Test
    void testFind()
    {
        final OrderedLongList o = new OrderedLongList(new FixedLongList(new long[] {0,2,4,6},3));
        assertEquals(1,o.find(1));
        assertEquals(~1,o.find(2));
    }
    
    @Test
    void testAdd()
    {
        final FixedLongList l = new FixedLongList(new long[] {0,2,-1,-1},2);
        final OrderedLongList o = new OrderedLongList(l);
        assertEquals(1,o.add(1));
        assertArrayEquals(new long[] {0,1,2,-1},l.data());
        assertEquals(2,o.add(2));
        assertArrayEquals(new long[] {0,1,2,2},l.data());
    }
    
    @Test
    void testClone()
    {
        final OrderedLongList o = new OrderedLongList(new FixedLongList(new long[] {0,1,2,3},2));
        assertEquals(o,o.clone());
    }
}