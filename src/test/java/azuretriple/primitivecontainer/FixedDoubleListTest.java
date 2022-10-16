package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedDoubleListTest
{
    @Test
    void testInit()
    {
        {
            final double[] b = {0,1,2,3};
            {
                final FixedDoubleList l = new FixedDoubleList(b,2);
                assertSame(b,l.data());
                assertEquals(2,l.size());
                assertFalse(l.empty());
            }
            {
                final FixedDoubleList l = new FixedDoubleList(b);
                assertSame(b,l.data());
                assertEquals(0,l.size());
                assertTrue(l.empty());
            }
        }
        {
            final FixedDoubleList l = new FixedDoubleList(3);
            assertArrayEquals(new double[3],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final FixedDoubleList l = new FixedDoubleList(new double[] {1,0,0,0},1),l2 = new FixedDoubleList(l);
            assertEquals(l,l2);
            l.add(2);
            l2.add(3);
            assertNotEquals(l,l2);
        }
    }
    
    @Test
    void testEquals()
    {
        final FixedDoubleList l = new FixedDoubleList(1);
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
        final FixedDoubleList l = new FixedDoubleList(new double[] {0,1,2,3},2);
        assertEquals(l,l.clone());
    }
}