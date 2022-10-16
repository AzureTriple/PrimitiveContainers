package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class GrowableTest
{
    static byte nextPow2(final int inSize,final int currentSize,final int arrLength,final Method m)
    {
        try {return (byte)m.invoke(null,inSize,currentSize,arrLength);}
        catch(IllegalAccessException|InvocationTargetException e) {fail(e); return -1;}
    }
    
    @Test
    void testNextPow2()
    {
        try
        {
            final Class<?>[] pair = AssertionUtil.getAssertionPair("azuretriple.primitivecontainer.Growable");
            {
                final Method m = pair[0].getDeclaredMethod("nextPow2",int.class,int.class,int.class);
                m.setAccessible(true);
                
                assertThrows(AssertionError.class,() -> nextPow2(0,-1,0,m));
                assertThrows(AssertionError.class,() -> nextPow2(1,-1,0,m));
                assertThrows(AssertionError.class,() -> nextPow2(1,0,0,m));
                assertEquals((byte)1,nextPow2(8,5,7,m));
            }
            {
                final Method m = pair[1].getDeclaredMethod("nextPow2",int.class,int.class,int.class);
                m.setAccessible(true);
                
                assertEquals((byte)1,nextPow2(8,5,7,m));
                assertEquals((byte)3,nextPow2(12,6,3,m));
        
                // Dummy calls to satisfy the coverage report for assertions.
                nextPow2(0,-1,-1,m);
                nextPow2(1,-1,-1,m);
                nextPow2(1,0,-1,m);
            }
        }
        catch(ClassNotFoundException|NoSuchMethodException e) {fail(e);}
    }
}