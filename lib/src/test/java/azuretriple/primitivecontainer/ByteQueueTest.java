package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ByteQueueTest
{
    @Test
    void testEquals()
    {
        final FixedByteQueue a = new FixedByteQueue(1);
        assertEquals(a,a);
        final FixedByteQueue b = a.clone();
        assertEquals(a,b);
        b.push((byte)1);
        assertNotEquals(a,b);
        a.push((byte)2);
        assertNotEquals(a,b);
        b.pop();
        b.push((byte)2);
        assertEquals(a,b);
    }
    
    @Test
    void testPush()
    {
        final FixedByteQueue q = new FixedByteQueue(4);
        q.push((byte)0);
        q.push(new byte[] {1,2,3});
        assertArrayEquals(new byte[] {0,1,2,3},q.data());
    }
    
    @Test
    void testPushRange()
    {
        final FixedByteQueue q = new FixedByteQueue(4);
        q.push((byte)0);
        q.push(new byte[] {-1,1,2,3,-1},1,3);
        assertArrayEquals(new byte[] {0,1,2,3},q.data());
    }
    
    @Test
    void testPop()
    {
        final FixedByteQueue q = new FixedByteQueue(new byte[] {1},0,1,false);
        assertEquals((byte)1,q.pop());
    }
    
    @Test
    void testFront()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedByteQueue.class);
        try
        {
            {
                final Object q = pair[0].
                    getDeclaredConstructor(byte[].class,int.class,int.class,boolean.class).
                    newInstance(new byte[] {1},0,1,false);
                final Method m = pair[0].getMethod("front");
                assertEquals((byte)1,(byte)m.invoke(q));
                pair[0].getMethod("clear").invoke(q);
                assertThrows(InvocationTargetException.class,() -> m.invoke(q));
            }
            {
                final Object q = pair[1].
                    getDeclaredConstructor(byte[].class,int.class,int.class,boolean.class).
                    newInstance(new byte[] {1},0,1,false);
                final Method m = pair[1].getMethod("front");
                assertEquals((byte)1,(byte)m.invoke(q));
                pair[1].getMethod("clear").invoke(q);
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(q);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testBack()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedByteQueue.class);
        try
        {
            {
                final Object q = pair[0].
                    getDeclaredConstructor(byte[].class,int.class,int.class,boolean.class).
                    newInstance(new byte[] {1},0,1,false);
                final Method m = pair[0].getMethod("back");
                assertEquals((byte)1,(byte)m.invoke(q));
                pair[0].getMethod("delete").invoke(q);
                assertThrows(InvocationTargetException.class,() -> m.invoke(q));
            }
            {
                final Object q = pair[1].
                    getDeclaredConstructor(byte[].class,int.class,int.class,boolean.class).
                    newInstance(new byte[] {1},0,1,false);
                final Method m = pair[1].getMethod("back");
                assertEquals((byte)1,(byte)m.invoke(q));
                pair[1].getMethod("delete").invoke(q);
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(q);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
}