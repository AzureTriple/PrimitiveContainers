package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedDoubleQueueTest
{
    @Test
    void testInit()
    {
        {
            final double[] b = {0,1,2,3};
            {
                final FixedDoubleQueue q = new FixedDoubleQueue(b,0,2,false);
                assertSame(b,q.data());
                assertEquals(2,q.size());
                assertFalse(q.empty());
            }
            {
                final FixedDoubleQueue q = new FixedDoubleQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                assertTrue(q.empty());
            }
        }
        {
            final FixedDoubleQueue q = new FixedDoubleQueue(10);
            assertEquals(0,q.size());
            assertTrue(q.empty());
            assertArrayEquals(new double[10],q.data());
        }
        {
            final FixedDoubleQueue q  = new FixedDoubleQueue(new double[] {1,0,0,0},0,1,false),
                q2 = new FixedDoubleQueue(q);
            assertEquals(q,q2);
            q.push(2);
            q2.push(3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final FixedDoubleQueue q = new FixedDoubleQueue(1);
        {
            final boolean b = q.equals(new Object());
            assertFalse(b);
        }
        {
            //noinspection EqualsWithItself
            final boolean b = q.equals(q);
            assertTrue(b);
        }
    }
    
    @Test
    void testClone()
    {
        final FixedDoubleQueue q = new FixedDoubleQueue(new double[] {3,0,1,2},2,1,false);
        assertEquals(q,q.clone());
    }
}