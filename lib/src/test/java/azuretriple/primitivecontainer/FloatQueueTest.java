package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FloatQueueTest
{
    @Test
    void testEquals()
    {
        final FixedFloatQueue a = new FixedFloatQueue(1);
        assertEquals(a,a);
        final FixedFloatQueue b = a.clone();
        assertEquals(a,b);
        b.push((float)1);
        assertNotEquals(a,b);
        a.push((float)2);
        assertNotEquals(a,b);
        b.pop();
        b.push((float)2);
        assertEquals(a,b);
    }
    
    @Test
    void testPush()
    {
        final FixedFloatQueue q = new FixedFloatQueue(4);
        q.push((float)0);
        q.push(new float[] {1,2,3});
        assertArrayEquals(new float[] {0,1,2,3},q.data());
    }
    
    @Test
    void testPushRange()
    {
        final FixedFloatQueue q = new FixedFloatQueue(4);
        q.push((float)0);
        q.push(new float[] {-1,1,2,3,-1},1,3);
        assertArrayEquals(new float[] {0,1,2,3},q.data());
    }
    
    @Test
    void testPop()
    {
        final FixedFloatQueue q = new FixedFloatQueue(new float[] {1},0,1,false);
        assertEquals((float)1,q.pop());
    }
    
    @Test
    void testFront()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedFloatQueue.class);
        try
        {
            {
                final Object q = pair[0].
                    getDeclaredConstructor(float[].class,int.class,int.class,boolean.class).
                    newInstance(new float[] {1},0,1,false);
                final Method m = pair[0].getMethod("front");
                assertEquals((float)1,(float)m.invoke(q));
                pair[0].getMethod("clear").invoke(q);
                assertThrows(InvocationTargetException.class,() -> m.invoke(q));
            }
            {
                final Object q = pair[1].
                    getDeclaredConstructor(float[].class,int.class,int.class,boolean.class).
                    newInstance(new float[] {1},0,1,false);
                final Method m = pair[1].getMethod("front");
                assertEquals((float)1,(float)m.invoke(q));
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
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedFloatQueue.class);
        try
        {
            {
                final Object q = pair[0].
                    getDeclaredConstructor(float[].class,int.class,int.class,boolean.class).
                    newInstance(new float[] {1},0,1,false);
                final Method m = pair[0].getMethod("back");
                assertEquals((float)1,(float)m.invoke(q));
                pair[0].getMethod("delete").invoke(q);
                assertThrows(InvocationTargetException.class,() -> m.invoke(q));
            }
            {
                final Object q = pair[1].
                    getDeclaredConstructor(float[].class,int.class,int.class,boolean.class).
                    newInstance(new float[] {1},0,1,false);
                final Method m = pair[1].getMethod("back");
                assertEquals((float)1,(float)m.invoke(q));
                pair[1].getMethod("delete").invoke(q);
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(q);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
}