package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class LongQueueTest
{
    @Test
    void testEquals()
    {
        final FixedLongQueue a = new FixedLongQueue(1);
        assertEquals(a,a);
        final FixedLongQueue b = a.clone();
        assertEquals(a,b);
        b.push(1);
        assertNotEquals(a,b);
        a.push(2);
        assertNotEquals(a,b);
        b.pop();
        b.push(2);
        assertEquals(a,b);
    }
    
    @Test
    void testPush()
    {
        final FixedLongQueue q = new FixedLongQueue(4);
        q.push(0);
        q.push(new long[] {1,2,3});
        assertArrayEquals(new long[] {0,1,2,3},q.data());
    }
    
    @Test
    void testPop()
    {
        final FixedLongQueue q = new FixedLongQueue(new long[] {1},0,1,false);
        assertEquals(1,q.pop());
    }
    
    @Test
    void testFront()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedLongQueue.class);
        try
        {
            {
                final Object q = pair[0].
                    getDeclaredConstructor(long[].class,int.class,int.class,boolean.class).
                    newInstance(new long[] {1},0,1,false);
                final Method m = pair[0].getMethod("front");
                assertEquals(1,(long)m.invoke(q));
                pair[0].getMethod("clear").invoke(q);
                assertThrows(InvocationTargetException.class,() -> m.invoke(q));
            }
            {
                final Object q = pair[1].
                    getDeclaredConstructor(long[].class,int.class,int.class,boolean.class).
                    newInstance(new long[] {1},0,1,false);
                final Method m = pair[1].getMethod("front");
                assertEquals(1,(long)m.invoke(q));
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
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedLongQueue.class);
        try
        {
            {
                final Object q = pair[0].
                    getDeclaredConstructor(long[].class,int.class,int.class,boolean.class).
                    newInstance(new long[] {1},0,1,false);
                final Method m = pair[0].getMethod("back");
                assertEquals(1,(long)m.invoke(q));
                pair[0].getMethod("delete").invoke(q);
                assertThrows(InvocationTargetException.class,() -> m.invoke(q));
            }
            {
                final Object q = pair[1].
                    getDeclaredConstructor(long[].class,int.class,int.class,boolean.class).
                    newInstance(new long[] {1},0,1,false);
                final Method m = pair[1].getMethod("back");
                assertEquals(1,(long)m.invoke(q));
                pair[1].getMethod("delete").invoke(q);
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(q);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
}