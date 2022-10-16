package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongSetTest
{
    @Test
    void testInit()
    {
        final LongSet s  = new LongSet(new OrderedLongList(new FixedLongList(1))),
                      s2 = new LongSet(s);
        assertEquals(s,s2);
        s.add(1);
        assertNotEquals(s,s2);
    }
    
    @Test
    void testAdd()
    {
        final LongSet s = new LongSet(new OrderedLongList(new FixedLongList(1)));
        assertEquals(0,s.add(1));
        assertEquals(-1,s.add(1));
    }
    
    @Test
    void testRemove()
    {
        final LongSet s = new LongSet(new OrderedLongList(new FixedLongList(1)));
        assertEquals(-1,s.remove(1));
        s.add(1);
        assertEquals(0,s.remove(1));
    }
    
    @Test
    void testContains()
    {
        final LongSet s = new LongSet(new OrderedLongList(new FixedLongList(1)));
        assertFalse(s.contains(1));
        s.add(1);
        assertTrue(s.contains(1));
    }
    
    @Test
    void testClone()
    {
        
        final LongSet s  = new LongSet(new OrderedLongList(new FixedLongList(1))),
                      s2 = s.clone();
        assertEquals(s,s2);
        s.add(1);
        assertNotEquals(s,s2);
    }
}