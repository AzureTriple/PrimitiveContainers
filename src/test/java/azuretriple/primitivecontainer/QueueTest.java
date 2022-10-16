package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest
{
    @Test
    void testRearrange()
    {
        {
            final byte[] b = {0,1,2,-1,-1};
            Queue.rearrange(b,0,3);
            assertArrayEquals(new byte[] {0,1,2,-1,-1},b);
        }
        {
            final byte[] b = {-1,0,1,2,-1};
            Queue.rearrange(b,1,4);
            assertArrayEquals(new byte[] {0,1,2,2,-1},b);
        }
        {
            final byte[] b = {2,-1,-1,0,1};
            Queue.rearrange(b,3,1);
            assertArrayEquals(new byte[] {0,1,2,0,1},b);
        }
        {
            final byte[] b = {1,2,-1,-1,0};
            Queue.rearrange(b,4,2);
            assertArrayEquals(new byte[] {0,1,2,-1,0},b);
        }
        
        final byte[] o = new byte[3];
        {
            final byte[] b = {0,1,2,-1,-1};
            Queue.rearrange(b,o,0,3);
            assertArrayEquals(new byte[] {0,1,2},o);
        }
        {
            final byte[] b = {-1,0,1,2,-1};
            Queue.rearrange(b,o,1,4);
            assertArrayEquals(new byte[] {0,1,2},o);
        }
        {
            final byte[] b = {2,-1,-1,0,1};
            Queue.rearrange(b,o,3,1);
            assertArrayEquals(new byte[] {0,1,2},o);
        }
        {
            final byte[] b = {1,2,-1,-1,0};
            Queue.rearrange(b,o,4,2);
            assertArrayEquals(new byte[] {0,1,2},o);
        }
    }
    
    @Test
    void testInit()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedByteQueue.class);
        try
        {
            {
                final Constructor<?> init = pair[0].getDeclaredConstructor(byte[].class,int.class,int.class,boolean.class);
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,0,true));
                assertThrows(InvocationTargetException.class,() -> init.newInstance(new byte[1],-1,0,true));
                assertThrows(InvocationTargetException.class,() -> init.newInstance(new byte[1],1,0,true));
                assertThrows(InvocationTargetException.class,() -> init.newInstance(new byte[1],0,-1,true));
                assertThrows(InvocationTargetException.class,() -> init.newInstance(new byte[1],0,2,true));
            }
            {
                final Constructor<?> init = pair[1].getDeclaredConstructor(byte[].class,int.class,int.class,boolean.class);
                assertDoesNotThrow(() -> init.newInstance(new byte[1],0,0,true));
                // Dummy calls to satisfy the coverage report for assertions.
                init.newInstance(new byte[1],-1,0,true);
                init.newInstance(new byte[1],1,0,true);
                init.newInstance(new byte[1],0,-1,true);
                init.newInstance(new byte[1],0,2,true);
            }
        }
        catch(InvocationTargetException|InstantiationException|IllegalAccessException|NoSuchMethodException e) {fail(e);}
        
        final FixedByteQueue q1 = new FixedByteQueue(new byte[] {2,0,1},2,1,false);
        assertArrayEquals(new byte[] {1,2,0},new FixedByteQueue(q1).data());
    }
    
    @Test void testOffset() {assertEquals(1,new FixedByteQueue(new byte[] {-1,0,1,2,-1},1,4,false).offset(0));}
}