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
    void testUnion()
    {
        {
            final ByteSet a = new ByteSet(new OrderedByteList(new FixedByteList(3))),
                          b = new ByteSet(new OrderedByteList(new FixedByteList(2)));
            a.add((byte)1);
            a.add((byte)3);
            b.add((byte)1);
            b.add((byte)2);
            a.union(b);
            assertArrayEquals(new byte[] {1,2,3},a.data().data().data());
            assertEquals(3,a.data().data().size());
        }
        {
            final ByteSet a = new ByteSet(new OrderedByteList(new FixedByteList(4))),
                          b = new ByteSet(new OrderedByteList(new FixedByteList(3)));
            a.add((byte)1);
            a.add((byte)2);
            b.add((byte)1);
            b.add((byte)3);
            b.add((byte)4);
            a.union(b);
            assertArrayEquals(new byte[] {1,2,3,4},a.data().data().data());
            assertEquals(4,a.data().data().size());
        }
    }
    
    @Test
    void testIntersect()
    {
        {
            final ByteSet a = new ByteSet(new OrderedByteList(new FixedByteList(2))),
                          b = new ByteSet(new OrderedByteList(new FixedByteList(2)));
            a.add((byte)1);
            a.add((byte)2);
            b.add((byte)1);
            b.add((byte)2);
            a.intersect(b);
            assertArrayEquals(new byte[] {1,2},a.data().data().data());
            assertEquals(2,a.data().data().size());
        }
        {
            final ByteSet a = new ByteSet(new OrderedByteList(new FixedByteList(2))),
                          b = new ByteSet(new OrderedByteList(new FixedByteList(2)));
            a.add((byte)1);
            a.add((byte)3);
            b.add((byte)2);
            b.add((byte)3);
            a.intersect(b);
            assertArrayEquals(new byte[] {3,3},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
        {
            final ByteSet a = new ByteSet(new OrderedByteList(new FixedByteList(3))),
                          b = new ByteSet(new OrderedByteList(new FixedByteList(2)));
            a.add((byte)1);
            a.add((byte)3);
            a.add((byte)4);
            b.add((byte)2);
            b.add((byte)3);
            a.intersect(b);
            assertArrayEquals(new byte[] {3,3,4},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
    }
    
    @Test
    void testSubtract()
    {
        {
            final ByteSet a = new ByteSet(new OrderedByteList(new FixedByteList(3))),
                          b = new ByteSet(new OrderedByteList(new FixedByteList(2)));
            a.add((byte)1);
            a.add((byte)2);
            a.add((byte)4);
            b.add((byte)2);
            b.add((byte)3);
            a.subtract(b);
            assertArrayEquals(new byte[] {1,4,4},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
        {
            final ByteSet a = new ByteSet(new OrderedByteList(new FixedByteList(2))),
                          b = new ByteSet(new OrderedByteList(new FixedByteList(3)));
            a.add((byte)1);
            a.add((byte)2);
            b.add((byte)2);
            b.add((byte)3);
            b.add((byte)4);
            a.subtract(b);
            assertArrayEquals(new byte[] {1,2},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
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