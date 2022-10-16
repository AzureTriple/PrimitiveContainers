package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedByteListTest
{
    @Test
    void testInit()
    {
        final FixedByteList l = new FixedByteList(new byte[] {0,1,2,3},2);
        final OrderedByteList o = new OrderedByteList(l);
        assertSame(l,o.data());
        assertEquals(o,new OrderedByteList(o));
    }
    
    @Test
    void testFind()
    {
        final OrderedByteList o = new OrderedByteList(new FixedByteList(new byte[] {0,2,4,6},3));
        assertEquals(1,o.find((byte)1));
        assertEquals(~1,o.find((byte)2));
    }
    
    @Test
    void testAdd()
    {
        final FixedByteList l = new FixedByteList(new byte[] {0,2,-1,-1},2);
        final OrderedByteList o = new OrderedByteList(l);
        assertEquals(1,o.add((byte)1));
        assertArrayEquals(new byte[] {0,1,2,-1},l.data());
        assertEquals(2,o.add((byte)2));
        assertArrayEquals(new byte[] {0,1,2,2},l.data());
    }
    
    @Test
    void testClone()
    {
        final OrderedByteList o = new OrderedByteList(new FixedByteList(new byte[] {0,1,2,3},2));
        assertEquals(o,o.clone());
    }
}