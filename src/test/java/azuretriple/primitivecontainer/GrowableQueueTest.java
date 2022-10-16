package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class GrowableQueueTest
{
    @Test
    void testInit()
    {
        try
        {
            final Class<?>[] pair = AssertionUtil.getAssertionPair(GrowableByteQueue.class);
            {
                final Constructor<?> init = pair[0].getDeclaredConstructor(byte[].class,int.class,int.class,byte.class);
                // state == empty  | start == end
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,0,GrowableQueue.EMPTY));
                // state == empty  | start == end % arr.length
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,1,GrowableQueue.EMPTY));
                // state == full   | start == end
                assertDoesNotThrow(() -> init.newInstance(new byte[2],1,1,GrowableQueue.FULL));
                // state == full   | start == end % arr.length
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,1,GrowableQueue.FULL));
                // state == normal | start != end
                assertDoesNotThrow(() -> init.newInstance(new byte[2],0,1,GrowableQueue.NORMAL));
                // state == normal | start != end % arr.length
                assertDoesNotThrow(() -> init.newInstance(new byte[2],1,2,GrowableQueue.NORMAL));
                
                // state == empty  | start != end % arr.length
                assertThrows(InvocationTargetException.class,() -> init.newInstance(new byte[2],0,1,GrowableQueue.EMPTY));
                // state == full   | start != end % arr.length
                assertThrows(InvocationTargetException.class,() -> init.newInstance(new byte[2],0,1,GrowableQueue.FULL));
                // state == normal | start == end % arr.length
                assertThrows(InvocationTargetException.class,() -> init.newInstance(new byte[1],0,0,GrowableQueue.NORMAL));
                // state == ~0
                assertThrows(InvocationTargetException.class,() -> init.newInstance(new byte[1],0,0,(byte)~0));
            }
            {
                final Constructor<?> init = pair[1].getDeclaredConstructor(byte[].class,int.class,int.class,byte.class);
                // state == empty  | start == end
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,0,GrowableQueue.EMPTY));
                // state == empty  | start == end % arr.length
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,1,GrowableQueue.EMPTY));
                // state == full   | start == end
                assertDoesNotThrow(() -> init.newInstance(new byte[2],1,1,GrowableQueue.FULL));
                // state == full   | start == end % arr.length
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,1,GrowableQueue.FULL));
                // state == normal | start != end
                assertDoesNotThrow(() -> init.newInstance(new byte[2],0,1,GrowableQueue.NORMAL));
                // state == normal | start != end % arr.length
                assertDoesNotThrow(() -> init.newInstance(new byte[2],1,2,GrowableQueue.NORMAL));
        
                // state == empty  | start != end % arr.length
                init.newInstance(new byte[2],0,1,GrowableQueue.EMPTY);
                // state == full   | start != end % arr.length
                init.newInstance(new byte[2],0,1,GrowableQueue.FULL);
                // state == normal | start == end % arr.length
                init.newInstance(new byte[1],0,0,GrowableQueue.NORMAL);
                // state == ~0     | start == end % arr.length
                init.newInstance(new byte[1],0,0,(byte)~0);
                // state == ~0     | start != end % arr.length
                init.newInstance(new byte[2],0,1,(byte)~0);
            }
        }
        catch(InvocationTargetException|NoSuchMethodException|InstantiationException|IllegalAccessException e) {fail(e);}
    
        Assertions.assertEquals(GrowableQueue.EMPTY,new GrowableByteQueue(new GrowableByteQueue(1)).state);
        Assertions.assertEquals(GrowableQueue.EMPTY,new GrowableByteQueue(new FixedByteQueue(new byte[1],0,0,true)).state);
        Assertions.assertEquals(GrowableQueue.FULL,new GrowableByteQueue(new FixedByteQueue(new byte[1],0,1,false)).state);
        Assertions.assertEquals(GrowableQueue.NORMAL,new GrowableByteQueue(new FixedByteQueue(new byte[2],0,1,false)).state);
    }
    
    @Test
    void testClear()
    {
        final GrowableByteQueue q = new GrowableByteQueue(Growable.DEFAULT_SIZE*2);
        q.push((byte)1);
        q.clear();
        assertTrue(q.empty());
        assertEquals(Growable.DEFAULT_SIZE,q.data().length);
    }
    
    @Test
    void testSize()
    {
        assertEquals(0,new GrowableByteQueue().size());
        assertEquals(1,new GrowableByteQueue(new byte[1],0,1,GrowableQueue.FULL).size());
        assertEquals(1,new GrowableByteQueue(new byte[2],0,1,GrowableQueue.NORMAL).size());
    }
    
    @Test
    void testPush()
    {
        final GrowableByteQueue q = new GrowableByteQueue(1);
        q.push(new byte[0]);
        assertTrue(q.empty());
        q.push(new byte[] {1});
        assertArrayEquals(new byte[] {1},q.data());
        Assertions.assertEquals(GrowableQueue.FULL,q.state);
        q.push(new byte[] {2,3});
        assertArrayEquals(new byte[] {1,2,3,0},q.data());
        Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
        q.clear();
        q.push(new byte[] {1,2});
        assertArrayEquals(new byte[] {1,2,3,0},q.data());
        Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
    }
    
    @Test
    void testPushLogic()
    {
        final GrowableByteQueue q = new GrowableByteQueue(2);
        q.pushLogic();
        ++q.end;
        assertEquals(2,q.data().length);
        Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
        q.pushLogic();
        ++q.end;
        assertEquals(2,q.data().length);
        Assertions.assertEquals(GrowableQueue.FULL,q.state);
        q.pushLogic();
        ++q.end;
        assertEquals(4,q.data().length);
        Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
    }
    
    @Test
    void testDelete()
    {
        try
        {
            final Class<?>[] pair = AssertionUtil.getAssertionPair(GrowableByteQueue.class);
            {
                final Constructor<?> init = pair[0].getDeclaredConstructor(byte[].class,int.class,int.class,byte.class);
                final Method m = pair[0].getMethod("delete");
                assertThrows(InvocationTargetException.class,() -> m.invoke(init.newInstance(new byte[1],0,0,GrowableQueue.EMPTY)));
                assertDoesNotThrow(() -> m.invoke(init.newInstance(new byte[2],0,1,GrowableQueue.NORMAL)));
            }
            {
                final Constructor<?> init = pair[1].getDeclaredConstructor(byte[].class,int.class,int.class,byte.class);
                final Method m = pair[1].getMethod("delete");
                assertDoesNotThrow(() -> m.invoke(init.newInstance(new byte[1],0,0,GrowableQueue.EMPTY)));
                // Dummy call to satisfy the coverage report for assertions.
                assertDoesNotThrow(() -> m.invoke(init.newInstance(new byte[2],0,1,GrowableQueue.NORMAL)));
            }
        }
        catch(final NoSuchMethodException e) {fail(e);}
    
        {
            final GrowableByteQueue q = new GrowableByteQueue(new byte[] {1,0,0,0},0,1,GrowableQueue.NORMAL);
            q.delete();
            Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
            assertEquals(2,q.data().length);
        }
        {
            final GrowableByteQueue q = new GrowableByteQueue(new byte[] {1,2,3,0},0,3,GrowableQueue.NORMAL);
            q.delete();
            Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
            assertEquals(4,q.data().length);
        }
        {
            final GrowableByteQueue q = new GrowableByteQueue(new byte[] {1,2,0,0,0,0,0,0},0,2,GrowableQueue.NORMAL);
            q.delete();
            Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
            assertEquals(4,q.data().length);
        }
    }
    
    @Test
    void testArrayImpl()
    {
        assertArrayEquals(new byte[] {0,1,2},(byte[])new GrowableByteQueue(new byte[] {2,-1,0,1},2,1,GrowableQueue.NORMAL).arrayImpl());
    }
}