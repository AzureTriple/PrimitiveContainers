package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortSetTest
{
    @Test
    void testInit()
    {
        final ShortSet s  = new ShortSet(new OrderedShortList(new FixedShortList(1))),
                       s2 = new ShortSet(s);
        assertEquals(s,s2);
        s.add((short)1);
        assertNotEquals(s,s2);
    }
    
    @Test
    void testAdd()
    {
        final ShortSet s = new ShortSet(new OrderedShortList(new FixedShortList(1)));
        assertEquals(0,s.add((short)1));
        assertEquals(-1,s.add((short)1));
    }
    
    @Test
    void testRemove()
    {
        final ShortSet s = new ShortSet(new OrderedShortList(new FixedShortList(1)));
        assertEquals(-1,s.remove((short)1));
        s.add((short)1);
        assertEquals(0,s.remove((short)1));
    }
    
    @Test
    void testContains()
    {
        final ShortSet s = new ShortSet(new OrderedShortList(new FixedShortList(1)));
        assertFalse(s.contains((short)1));
        s.add((short)1);
        assertTrue(s.contains((short)1));
    }
    
    @Test
    void testClone()
    {
    
        final ShortSet s  = new ShortSet(new OrderedShortList(new FixedShortList(1))),
                       s2 = s.clone();
        assertEquals(s,s2);
        s.add((short)1);
        assertNotEquals(s,s2);
    }
}