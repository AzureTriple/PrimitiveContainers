package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ShortQueueTest
{
    @Test
    void testEquals()
    {
        final FixedShortQueue a = new FixedShortQueue(1);
        assertEquals(a,a);
        final FixedShortQueue b = a.clone();
        assertEquals(a,b);
        b.push((short)1);
        assertNotEquals(a,b);
        a.push((short)2);
        assertNotEquals(a,b);
        b.pop();
        b.push((short)2);
        assertEquals(a,b);
    }
    
    @Test
    void testPush()
    {
        final FixedShortQueue q = new FixedShortQueue(4);
        q.push((short)0);
        q.push(new short[] {1,2,3});
        assertArrayEquals(new short[] {0,1,2,3},q.data());
    }
    
    @Test
    void testPop()
    {
        final FixedShortQueue q = new FixedShortQueue(new short[] {1},0,1,false);
        assertEquals((short)1,q.pop());
    }
    
    @Test
    void testFront()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedShortQueue.class);
        try
        {
            {
                final Object q = pair[0].
                    getDeclaredConstructor(short[].class,int.class,int.class,boolean.class).
                    newInstance(new short[] {1},0,1,false);
                final Method m = pair[0].getMethod("front");
                assertEquals((short)1,(short)m.invoke(q));
                pair[0].getMethod("clear").invoke(q);
                assertThrows(InvocationTargetException.class,() -> m.invoke(q));
            }
            {
                final Object q = pair[1].
                    getDeclaredConstructor(short[].class,int.class,int.class,boolean.class).
                    newInstance(new short[] {1},0,1,false);
                final Method m = pair[1].getMethod("front");
                assertEquals((short)1,(short)m.invoke(q));
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
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedShortQueue.class);
        try
        {
            {
                final Object q = pair[0].
                    getDeclaredConstructor(short[].class,int.class,int.class,boolean.class).
                    newInstance(new short[] {1},0,1,false);
                final Method m = pair[0].getMethod("back");
                assertEquals((short)1,(short)m.invoke(q));
                pair[0].getMethod("delete").invoke(q);
                assertThrows(InvocationTargetException.class,() -> m.invoke(q));
            }
            {
                final Object q = pair[1].
                    getDeclaredConstructor(short[].class,int.class,int.class,boolean.class).
                    newInstance(new short[] {1},0,1,false);
                final Method m = pair[1].getMethod("back");
                assertEquals((short)1,(short)m.invoke(q));
                pair[1].getMethod("delete").invoke(q);
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(q);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
}