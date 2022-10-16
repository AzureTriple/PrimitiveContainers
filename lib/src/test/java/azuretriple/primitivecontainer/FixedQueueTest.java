package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FixedQueueTest
{
    @Test
    void testInit()
    {
        try
        {
            final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedByteQueue.class);
            {
                final Constructor<?> init = pair[0].getDeclaredConstructor(byte[].class,int.class,int.class,boolean.class);
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,0,true));
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,0,false));
                assertThrows(InvocationTargetException.class,() -> init.newInstance(new byte[1],0,1,true));
            }
            {
                final Constructor<?> init = pair[1].getDeclaredConstructor(byte[].class,int.class,int.class,boolean.class);
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,0,true));
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,0,false));
                // Dummy call to satisfy the coverage report for assertions.
                init.newInstance(new byte[1],0,1,true);
            }
        }
        catch(InvocationTargetException|NoSuchMethodException|InstantiationException|IllegalAccessException e) {fail(e);}
        
        final FixedByteQueue q = new FixedByteQueue(new byte[] {0,1,2,-1},0,3,false);
        assertEquals(q,new FixedByteQueue(q));
    }
    
    @Test
    void testClear()
    {
        final FixedByteQueue q = new FixedByteQueue(new byte[] {0,1,2,-1},0,3,false);
        q.clear();
        assertTrue(q.empty());
    }
    
    @Test
    void testSize()
    {
        assertEquals(0,new FixedByteQueue(new byte[] {-1,-1,-1,-1},0,0,true).size());
        assertEquals(3,new FixedByteQueue(new byte[] {0,1,2,-1},0,3,false).size());
        assertEquals(3,new FixedByteQueue(new byte[] {2,-1,0,1},2,1,false).size());
    }
    
    @Test
    void testEmpty()
    {
        assertTrue(new FixedByteQueue(new byte[] {-1,-1,-1,-1},0,0,true).empty());
        assertFalse(new FixedByteQueue(new byte[] {0,1,2,-1},0,3,false).empty());
    }
    
    @Test
    void testPush()
    {
        final FixedByteQueue q = new FixedByteQueue(4);
        q.push(new byte[0]);
        assertTrue(q.empty());
        q.push(new byte[] {1,2});
        assertArrayEquals(new byte[] {1,2,0,0},q.data());
        assertEquals(2,q.size());
        q.push(new byte[] {3,4});
        assertArrayEquals(new byte[] {1,2,3,4},q.data());
        assertEquals(4,q.size());
    }
    
    @Test
    void testPushLogic()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedByteQueue.class);
        try
        {
            {
                final Constructor<?> init = pair[0].getDeclaredConstructor(byte[].class,int.class,int.class,boolean.class);
                final Method m = pair[0].getSuperclass().getDeclaredMethod("pushLogic");
                m.setAccessible(true);
                assertDoesNotThrow(() -> m.invoke(init.newInstance(new byte[1],0,0,true)));
                assertDoesNotThrow(() -> m.invoke(init.newInstance(new byte[2],0,1,false)));
                assertThrows(InvocationTargetException.class,() -> m.invoke(init.newInstance(new byte[1],0,1,false)));
            }
            {
                final Constructor<?> init = pair[1].getDeclaredConstructor(byte[].class,int.class,int.class,boolean.class);
                final Method m = pair[1].getSuperclass().getDeclaredMethod("pushLogic");
                m.setAccessible(true);
                assertDoesNotThrow(() -> m.invoke(init.newInstance(new byte[1],0,0,true)));
                assertDoesNotThrow(() -> m.invoke(init.newInstance(new byte[2],0,1,false)));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(init.newInstance(new byte[1],0,1,false));
            }
        }
        catch(InvocationTargetException|NoSuchMethodException|InstantiationException|IllegalAccessException e) {fail(e);}
    }
    
    @Test
    void testDelete()
    {
        final FixedByteQueue q = new FixedByteQueue(new byte[] {1,2,-1,0},3,2,false);
        q.delete();
        assertArrayEquals(new byte[] {1,2},q.array());
    }
    
    @Test
    void testArrayImpl()
    {
        assertArrayEquals(new byte[] {0,1,2},(byte[])new FixedByteQueue(new byte[] {2,-1,0,1},2,1,false).arrayImpl());
    }
}