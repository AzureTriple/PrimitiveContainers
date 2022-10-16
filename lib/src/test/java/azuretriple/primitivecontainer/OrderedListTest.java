package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedListTest
{
    @Test
    void testInit()
    {
        final FixedByteList l = new FixedByteList(1);
        final OrderedByteList o = new OrderedByteList(l);
        assertSame(o.list,l);
    }
    
    @Test
    void testData()
    {
        final FixedByteList l = new FixedByteList(1);
        assertSame(l,new OrderedByteList(l).data());
    }
    
    @Test
    void testEquals()
    {
        final FixedByteList a = new FixedByteList(new byte[] {1,2},1);
        final OrderedByteList o1 = new OrderedByteList(a);
        assertEquals(o1,o1);
        final OrderedByteList o2 = new OrderedByteList(o1);
        assertEquals(o1,o2);
        a.add((byte)0);
        assertNotEquals(o1,o2);
        assertNotEquals(o1,new Object());
    }
}