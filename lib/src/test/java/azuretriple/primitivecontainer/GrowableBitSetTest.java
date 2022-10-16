package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableBitSetTest
{
    @Test
    void testNextHighest()
    {
        final long[] s = {0L,1L,0L,1L};
        assertEquals(3,GrowableBitSet.nextHighest(s,4));
        assertEquals(1,GrowableBitSet.nextHighest(s,3));
        assertEquals(-1,GrowableBitSet.nextHighest(s,1));
    }
    
    @Test
    void testInit()
    {
        {
            final long[] work = {1L};
            {
                final GrowableBitSet g = new GrowableBitSet(work,false);
                assertSame(work,g.set);
                assertEquals(1L,work[0]);
                assertEquals(0,g.highest);
            }
            {
                final GrowableBitSet g = new GrowableBitSet(work,true);
                assertSame(work,g.set);
                assertEquals(0L,work[0]);
                assertEquals(-1,g.highest);
                work[0] = 1L;
            }
            {
                final GrowableBitSet g = new GrowableBitSet(work);
                assertSame(work,g.set);
                assertEquals(0L,work[0]);
                assertEquals(-1,g.highest);
            }
        }
        {
            final GrowableBitSet g = new GrowableBitSet(1);
            assertArrayEquals(new long[1],g.set);
            assertEquals(-1,g.highest);
        }
        {
            final GrowableBitSet g = new GrowableBitSet();
            assertArrayEquals(new long[(Growable.DEFAULT_SIZE+63)/64],g.set);
            assertEquals(-1,g.highest);
        }
        {
            final FixedBitSet f = new FixedBitSet(1);
            f.set(0);
            final GrowableBitSet g = new GrowableBitSet(f);
            assertArrayEquals(new long[] {1L},g.set);
            assertEquals(0,g.highest);
        }
        {
            final GrowableBitSet g = new GrowableBitSet(1);
            g.set(0);
            final GrowableBitSet g2 = new GrowableBitSet(g);
            assertArrayEquals(new long[] {1L},g2.set);
            assertEquals(0,g2.highest);
        }
        {
            final MapBitSet m = new MapBitSet();
            {
                final GrowableBitSet g = new GrowableBitSet(m);
                assertArrayEquals(new long[Growable.DEFAULT_SIZE],g.set);
                assertEquals(-1,g.highest);
            }
            m.set(0);
            {
                final GrowableBitSet g = new GrowableBitSet(m);
                assertArrayEquals(new long[] {1L},g.set);
                assertEquals(0,g.highest);
            }
        }
    }
    
    @Test
    void testData()
    {
        final GrowableBitSet g = new GrowableBitSet();
        assertSame(g.set,g.data());
    }
    
    @Test
    void testSizeLogic()
    {
        final GrowableBitSet g = new GrowableBitSet(1);
        g.sizeLogic(0);
        assertEquals(0,g.highest);
        g.sizeLogic(4);
        assertEquals(4,g.highest);
        assertEquals(8,g.data().length);
        g.sizeLogic(0);
        assertEquals(4,g.highest);
        assertEquals(8,g.data().length);
    }
    
    @Test
    void testUnset()
    {
        final GrowableBitSet g = new GrowableBitSet(2);
        g.unset(0);
        assertEquals(1,g.set.length);
        g.set(0);
        g.set(1);
        g.unset(1);
        assertEquals(1,g.set.length);
        g.set(256);
        g.unset(256);
        assertEquals(1,g.set.length);
        g.unset(0);
        assertEquals(1,g.set.length);
        assertEquals(-1,g.highest);
    }
    
    @Test
    void testClear()
    {
        final GrowableBitSet g = new GrowableBitSet();
        g.set(1);
        g.clear();
        assertEquals(1,g.set.length);
        assertEquals(-1,g.highest);
    }
    
    @Test
    void testClone()
    {
        final GrowableBitSet g = new GrowableBitSet(1);
        g.set(0);
        final GrowableBitSet g2 = g.clone();
        assertArrayEquals(new long[] {1L},g2.set);
        assertEquals(0,g2.highest);
    }
}