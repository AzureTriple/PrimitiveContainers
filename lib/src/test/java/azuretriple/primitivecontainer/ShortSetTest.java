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
    void testUnion()
    {
        {
            final ShortSet a = new ShortSet(new OrderedShortList(new FixedShortList(3))),
                           b = new ShortSet(new OrderedShortList(new FixedShortList(2)));
            a.add((short)1);
            a.add((short)3);
            b.add((short)1);
            b.add((short)2);
            a.union(b);
            assertArrayEquals(new short[] {1,2,3},a.data().data().data());
            assertEquals(3,a.data().data().size());
        }
        {
            final ShortSet a = new ShortSet(new OrderedShortList(new FixedShortList(4))),
                           b = new ShortSet(new OrderedShortList(new FixedShortList(3)));
            a.add((short)1);
            a.add((short)2);
            b.add((short)1);
            b.add((short)3);
            b.add((short)4);
            a.union(b);
            assertArrayEquals(new short[] {1,2,3,4},a.data().data().data());
            assertEquals(4,a.data().data().size());
        }
    }
    
    @Test
    void testIntersect()
    {
        {
            final ShortSet a = new ShortSet(new OrderedShortList(new FixedShortList(2))),
                           b = new ShortSet(new OrderedShortList(new FixedShortList(2)));
            a.add((short)1);
            a.add((short)2);
            b.add((short)1);
            b.add((short)2);
            a.intersect(b);
            assertArrayEquals(new short[] {1,2},a.data().data().data());
            assertEquals(2,a.data().data().size());
        }
        {
            final ShortSet a = new ShortSet(new OrderedShortList(new FixedShortList(2))),
                           b = new ShortSet(new OrderedShortList(new FixedShortList(2)));
            a.add((short)1);
            a.add((short)3);
            b.add((short)2);
            b.add((short)3);
            a.intersect(b);
            assertArrayEquals(new short[] {3,3},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
        {
            final ShortSet a = new ShortSet(new OrderedShortList(new FixedShortList(3))),
                           b = new ShortSet(new OrderedShortList(new FixedShortList(2)));
            a.add((short)1);
            a.add((short)3);
            a.add((short)4);
            b.add((short)2);
            b.add((short)3);
            a.intersect(b);
            assertArrayEquals(new short[] {3,3,4},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
    }
    
    @Test
    void testSubtract()
    {
        {
            final ShortSet a = new ShortSet(new OrderedShortList(new FixedShortList(3))),
                           b = new ShortSet(new OrderedShortList(new FixedShortList(2)));
            a.add((short)1);
            a.add((short)2);
            a.add((short)4);
            b.add((short)2);
            b.add((short)3);
            a.subtract(b);
            assertArrayEquals(new short[] {1,4,4},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
        {
            final ShortSet a = new ShortSet(new OrderedShortList(new FixedShortList(2))),
                           b = new ShortSet(new OrderedShortList(new FixedShortList(3)));
            a.add((short)1);
            a.add((short)2);
            b.add((short)2);
            b.add((short)3);
            b.add((short)4);
            a.subtract(b);
            assertArrayEquals(new short[] {1,2},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
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