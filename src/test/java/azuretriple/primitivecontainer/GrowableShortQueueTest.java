package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableShortQueueTest
{
    @Test
    void testInit()
    {
        {
            final short[] b = {0,1,2,3};
            {
                final GrowableShortQueue q = new GrowableShortQueue(b,0,1,GrowableQueue.NORMAL);
                assertSame(b,q.data());
                assertEquals(1,q.size());
                Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
            }
            {
                final GrowableShortQueue q = new GrowableShortQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
            }
        }
        {
            final GrowableShortQueue q = new GrowableShortQueue(10);
            assertArrayEquals(new short[10],q.data());
            assertEquals(0,q.size());
            Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
        }
        {
            final GrowableShortQueue q = new GrowableShortQueue();
            assertArrayEquals(new short[Growable.DEFAULT_SIZE],q.data());
            assertEquals(0,q.size());
            assertEquals(Growable.DEFAULT_SIZE,q.data().length);
        }
        {
            final GrowableShortQueue q  = new GrowableShortQueue(new short[] {1,0,0,0},0,1,GrowableQueue.NORMAL),
                                     q2 = new GrowableShortQueue(q);
            assertEquals(q,q2);
            q.push((short)2);
            q2.push((short)3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableShortQueue q = new GrowableShortQueue();
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
        final GrowableShortQueue q = new GrowableShortQueue(new short[] {3,0,1,2},2,1,GrowableQueue.NORMAL);
        assertEquals(q,q.clone());
    }
}