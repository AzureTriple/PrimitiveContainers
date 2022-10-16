package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedShortListTest
{
    @Test
    void testInit()
    {
        {
            final short[] b = {0,1,2,3};
            {
                final FixedShortList l = new FixedShortList(b,2);
                assertSame(b,l.data());
                assertEquals(2,l.size());
                assertFalse(l.empty());
            }
            {
                final FixedShortList l = new FixedShortList(b);
                assertSame(b,l.data());
                assertEquals(0,l.size());
                assertTrue(l.empty());
            }
        }
        {
            final FixedShortList l = new FixedShortList(3);
            assertArrayEquals(new short[3],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final FixedShortList l = new FixedShortList(new short[] {1,0,0,0},1),l2 = new FixedShortList(l);
            assertEquals(l,l2);
            l.add((short)2);
            l2.add((short)3);
            assertNotEquals(l,l2);
        }
    }
    
    @Test
    void testEquals()
    {
        final FixedShortList l = new FixedShortList(1);
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
        final FixedShortList l = new FixedShortList(new short[] {0,1,2,3},2);
        assertEquals(l,l.clone());
    }
}