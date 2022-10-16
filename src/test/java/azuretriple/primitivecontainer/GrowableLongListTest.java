package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableLongListTest
{
    @Test
    void testInit()
    {
        {
            final long[] b = {0,1,2,3};
            {
                final GrowableLongList l = new GrowableLongList(b,2);
                assertSame(b,l.data());
                assertEquals(2,l.size());
                assertFalse(l.empty());
            }
            {
                final GrowableLongList l = new GrowableLongList(b);
                assertSame(b,l.data());
                assertEquals(0,l.size());
                assertTrue(l.empty());
            }
        }
        {
            final GrowableLongList l = new GrowableLongList(3);
            assertArrayEquals(new long[3],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableLongList l = new GrowableLongList();
            assertArrayEquals(new long[Growable.DEFAULT_SIZE],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableLongList l = new GrowableLongList(new long[] {1,0,0,0},1),l2 = new GrowableLongList(l);
            assertEquals(l,l2);
            l.add(2);
            l2.add(3);
            assertNotEquals(l,l2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableLongList l = new GrowableLongList(1);
        {
            final boolean b = l.equals(new Object());
            assertFalse(b);
        }
        {
            //noinspection EqualsWithItself
            final boolean b = l.equals(l);
            assertTrue(b);
        }
    }
    
    @Test
    void testClone()
    {
        final GrowableLongList l = new GrowableLongList(new long[] {0,1,2,3},2);
        assertEquals(l,l.clone());
    }
}