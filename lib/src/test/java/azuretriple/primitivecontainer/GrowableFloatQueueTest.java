package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableFloatQueueTest
{
    @Test
    void testInit()
    {
        {
            final float[] b = {0,1,2,3};
            {
                final GrowableFloatQueue q = new GrowableFloatQueue(b,0,1,GrowableQueue.NORMAL);
                assertSame(b,q.data());
                assertEquals(1,q.size());
                Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
            }
            {
                final GrowableFloatQueue q = new GrowableFloatQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
            }
        }
        {
            final GrowableFloatQueue q = new GrowableFloatQueue(10);
            assertArrayEquals(new float[10],q.data());
            assertEquals(0,q.size());
            Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
        }
        {
            final GrowableFloatQueue q = new GrowableFloatQueue();
            assertArrayEquals(new float[Growable.DEFAULT_SIZE],q.data());
            assertEquals(0,q.size());
            assertEquals(Growable.DEFAULT_SIZE,q.data().length);
        }
        {
            final GrowableFloatQueue q  = new GrowableFloatQueue(new float[] {1,0,0,0},0,1,GrowableQueue.NORMAL),
                                     q2 = new GrowableFloatQueue(q);
            assertEquals(q,q2);
            q.push((float)2);
            q2.push((float)3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableFloatQueue q = new GrowableFloatQueue();
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
        final GrowableFloatQueue q = new GrowableFloatQueue(new float[] {3,0,1,2},2,1,GrowableQueue.NORMAL);
        assertEquals(q,q.clone());
    }
}