package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedByteQueueTest
{
    @Test
    void testInit()
    {
        {
            final byte[] b = {0,1,2,3};
            {
                final FixedByteQueue q = new FixedByteQueue(b,0,2,false);
                assertSame(b,q.data());
                assertEquals(2,q.size());
                assertFalse(q.empty());
            }
            {
                final FixedByteQueue q = new FixedByteQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                assertTrue(q.empty());
            }
        }
        {
            final FixedByteQueue q = new FixedByteQueue(10);
            assertEquals(0,q.size());
            assertTrue(q.empty());
            assertArrayEquals(new byte[10],q.data());
        }
        {
            final FixedByteQueue q  = new FixedByteQueue(new byte[] {1,0,0,0},0,1,false),
                                 q2 = new FixedByteQueue(q);
            assertEquals(q,q2);
            q.push((byte)2);
            q2.push((byte)3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final FixedByteQueue q = new FixedByteQueue(1);
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
        final FixedByteQueue q = new FixedByteQueue(new byte[] {3,0,1,2},2,1,false);
        assertEquals(q,q.clone());
    }
}