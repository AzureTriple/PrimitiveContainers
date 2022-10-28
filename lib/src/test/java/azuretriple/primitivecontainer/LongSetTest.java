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
    void testUnion()
    {
        {
            final LongSet a = new LongSet(new OrderedLongList(new FixedLongList(3))),
                          b = new LongSet(new OrderedLongList(new FixedLongList(2)));
            a.add(1);
            a.add(3);
            b.add(1);
            b.add(2);
            a.union(b);
            assertArrayEquals(new long[] {1,2,3},a.data().data().data());
            assertEquals(3,a.data().data().size());
        }
        {
            final LongSet a = new LongSet(new OrderedLongList(new FixedLongList(4))),
                          b = new LongSet(new OrderedLongList(new FixedLongList(3)));
            a.add(1);
            a.add(2);
            b.add(1);
            b.add(3);
            b.add(4);
            a.union(b);
            assertArrayEquals(new long[] {1,2,3,4},a.data().data().data());
            assertEquals(4,a.data().data().size());
        }
    }
    
    @Test
    void testIntersect()
    {
        {
            final LongSet a = new LongSet(new OrderedLongList(new FixedLongList(2))),
                          b = new LongSet(new OrderedLongList(new FixedLongList(2)));
            a.add(1);
            a.add(2);
            b.add(1);
            b.add(2);
            a.intersect(b);
            assertArrayEquals(new long[] {1,2},a.data().data().data());
            assertEquals(2,a.data().data().size());
        }
        {
            final LongSet a = new LongSet(new OrderedLongList(new FixedLongList(2))),
                          b = new LongSet(new OrderedLongList(new FixedLongList(2)));
            a.add(1);
            a.add(3);
            b.add(2);
            b.add(3);
            a.intersect(b);
            assertArrayEquals(new long[] {3,3},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
        {
            final LongSet a = new LongSet(new OrderedLongList(new FixedLongList(3))),
                          b = new LongSet(new OrderedLongList(new FixedLongList(2)));
            a.add(1);
            a.add(3);
            a.add(4);
            b.add(2);
            b.add(3);
            a.intersect(b);
            assertArrayEquals(new long[] {3,3,4},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
    }
    
    @Test
    void testSubtract()
    {
        {
            final LongSet a = new LongSet(new OrderedLongList(new FixedLongList(3))),
                          b = new LongSet(new OrderedLongList(new FixedLongList(2)));
            a.add(1);
            a.add(2);
            a.add(4);
            b.add(2);
            b.add(3);
            a.subtract(b);
            assertArrayEquals(new long[] {1,4,4},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
        {
            final LongSet a = new LongSet(new OrderedLongList(new FixedLongList(2))),
                          b = new LongSet(new OrderedLongList(new FixedLongList(3)));
            a.add(1);
            a.add(2);
            b.add(2);
            b.add(3);
            b.add(4);
            a.subtract(b);
            assertArrayEquals(new long[] {1,2},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
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