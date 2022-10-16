package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedLongListTest
{
    @Test
    void testInit()
    {
        {
            final long[] b = {0,1,2,3};
            {
                final FixedLongList l = new FixedLongList(b,2);
                assertSame(b,l.data());
                assertEquals(2,l.size());
                assertFalse(l.empty());
            }
            {
                final FixedLongList l = new FixedLongList(b);
                assertSame(b,l.data());
                assertEquals(0,l.size());
                assertTrue(l.empty());
            }
        }
        {
            final FixedLongList l = new FixedLongList(3);
            assertArrayEquals(new long[3],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final FixedLongList l = new FixedLongList(new long[] {1,0,0,0},1),l2 = new FixedLongList(l);
            assertEquals(l,l2);
            l.add(2);
            l2.add(3);
            assertNotEquals(l,l2);
        }
    }
    
    @Test
    void testEquals()
    {
        final FixedLongList l = new FixedLongList(1);
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
        final FixedLongList l = new FixedLongList(new long[] {0,1,2,3},2);
        assertEquals(l,l.clone());
    }
}