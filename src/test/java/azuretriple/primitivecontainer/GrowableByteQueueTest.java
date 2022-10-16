package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableByteQueueTest
{
    @Test
    void testInit()
    {
        {
            final byte[] b = {0,1,2,3};
            {
                final GrowableByteQueue q = new GrowableByteQueue(b,0,1,GrowableQueue.NORMAL);
                assertSame(b,q.data());
                assertEquals(1,q.size());
                Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
            }
            {
                final GrowableByteQueue q = new GrowableByteQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
            }
        }
        {
            final GrowableByteQueue q = new GrowableByteQueue(10);
            assertArrayEquals(new byte[10],q.data());
            assertEquals(0,q.size());
            Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
        }
        {
            final GrowableByteQueue q = new GrowableByteQueue();
            assertArrayEquals(new byte[Growable.DEFAULT_SIZE],q.data());
            assertEquals(0,q.size());
            assertEquals(Growable.DEFAULT_SIZE,q.data().length);
        }
        {
            final GrowableByteQueue q  = new GrowableByteQueue(new byte[] {1,0,0,0},0,1,GrowableQueue.NORMAL),
                                    q2 = new GrowableByteQueue(q);
            assertEquals(q,q2);
            q.push((byte)2);
            q2.push((byte)3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableByteQueue q = new GrowableByteQueue();
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
        final GrowableByteQueue q = new GrowableByteQueue(new byte[] {3,0,1,2},2,1,GrowableQueue.NORMAL);
        assertEquals(q,q.clone());
    }
}