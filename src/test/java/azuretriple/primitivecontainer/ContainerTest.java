package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest
{
    @Test
    void testInit()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedByteList.class);
        final byte[] arr = {};
        try
        {
            {
                final Constructor<?> init = pair[0].getDeclaredConstructor(byte[].class,int.class);
                init.setAccessible(true);
                
                assertSame(arr,pair[0].getMethod("data").invoke(init.newInstance(arr,0)));
                assertThrows(InvocationTargetException.class,() -> init.newInstance(null,0));
            }
            {
                final Constructor<?> init = pair[1].getDeclaredConstructor(byte[].class,int.class);
                init.setAccessible(true);
    
                assertSame(arr,pair[1].getMethod("data").invoke(init.newInstance(arr,0)));
                // Dummy call to satisfy the coverage report for assertions.
                init.newInstance(null,0);
            }
        }
        catch(NoSuchMethodException|InvocationTargetException|InstantiationException|IllegalAccessException e) {fail(e);}
    }
    
    @Test
    void testArrInst()
    {
        final byte[] arr = {};
        final Object inst = Container.arrInst(arr,1);
        assertEquals(arr.getClass(),inst.getClass());
        assertEquals(1,((byte[])inst).length);
    }
}