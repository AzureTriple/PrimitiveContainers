package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteSetTest
{
    @Test
    void testInit()
    {
        final ByteSet s  = new ByteSet(new OrderedByteList(new FixedByteList(1))),
                      s2 = new ByteSet(s);
        assertEquals(s,s2);
        s.add((byte)1);
        assertNotEquals(s,s2);
    }
    
    @Test
    void testAdd()
    {
        final ByteSet s = new ByteSet(new OrderedByteList(new FixedByteList(1)));
        assertEquals(0,s.add((byte)1));
        assertEquals(-1,s.add((byte)1));
    }
    
    @Test
    void testRemove()
    {
        final ByteSet s = new ByteSet(new OrderedByteList(new FixedByteList(1)));
        assertEquals(-1,s.remove((byte)1));
        s.add((byte)1);
        assertEquals(0,s.remove((byte)1));
    }
    
    @Test
    void testContains()
    {
        final ByteSet s = new ByteSet(new OrderedByteList(new FixedByteList(1)));
        assertFalse(s.contains((byte)1));
        s.add((byte)1);
        assertTrue(s.contains((byte)1));
    }
    
    @Test
    void testClone()
    {
    
        final ByteSet s  = new ByteSet(new OrderedByteList(new FixedByteList(1))),
                      s2 = s.clone();
        assertEquals(s,s2);
        s.add((byte)1);
        assertNotEquals(s,s2);
    }
}