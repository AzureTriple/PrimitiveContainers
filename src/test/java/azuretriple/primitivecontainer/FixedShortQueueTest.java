package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedShortQueueTest
{
    @Test
    void testInit()
    {
        {
            final short[] b = {0,1,2,3};
            {
                final FixedShortQueue q = new FixedShortQueue(b,0,2,false);
                assertSame(b,q.data());
                assertEquals(2,q.size());
                assertFalse(q.empty());
            }
            {
                final FixedShortQueue q = new FixedShortQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                assertTrue(q.empty());
            }
        }
        {
            final FixedShortQueue q = new FixedShortQueue(10);
            assertEquals(0,q.size());
            assertTrue(q.empty());
            assertArrayEquals(new short[10],q.data());
        }
        {
            final FixedShortQueue q  = new FixedShortQueue(new short[] {1,0,0,0},0,1,false),
                q2 = new FixedShortQueue(q);
            assertEquals(q,q2);
            q.push((short)2);
            q2.push((short)3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final FixedShortQueue q = new FixedShortQueue(1);
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
        final FixedShortQueue q = new FixedShortQueue(new short[] {3,0,1,2},2,1,false);
        assertEquals(q,q.clone());
    }
}