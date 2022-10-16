package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ListTest
{
    @Test
    void testInit()
    {
        try
        {
            final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedByteList.class);
            final byte[] work = new byte[0];
            {
                final Constructor<?> init = pair[0].getDeclaredConstructor(byte[].class,int.class);
                assertEquals(0,(int)pair[0].getMethod("size").invoke(init.newInstance(work,0)));
                assertThrows(InvocationTargetException.class,() -> init.newInstance(work,1));
            }
            {
                final Constructor<?> init = pair[1].getDeclaredConstructor(byte[].class,int.class);
                assertEquals(0,(int)pair[1].getMethod("size").invoke(init.newInstance(work,0)));
                // Dummy call to satisfy the coverage report for assertions.
                init.newInstance(work,1);
            }
        }
        catch(InvocationTargetException|NoSuchMethodException|InstantiationException|IllegalAccessException e) {fail(e);}
        
        final FixedByteList l = new FixedByteList(new byte[] {0,1,2},2);
        assertEquals(l,new FixedByteList(l));
    }
    
    @Test
    void testEmpty()
    {
        assertFalse(new FixedByteList(new byte[] {0,1,2},2).empty());
        assertTrue(new FixedByteList(new byte[0],0).empty());
    }
    
    @Test
    void testSize()
    {
        assertEquals(2,new FixedByteList(new byte[] {0,1,2},2).size());
    }
    
    @Test
    void testClear()
    {
        final FixedByteList l = new FixedByteList(new byte[] {0,1,2},2);
        l.clear();
        assertTrue(l.empty());
    }
    
    @Test
    void testDelete()
    {
        try
        {
            final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedByteList.class);
            {
                final Object l = pair[0].
                    getDeclaredConstructor(byte[].class,int.class).
                    newInstance(new byte[] {0,1},1);
                final Method m = pair[0].getMethod("delete");
                m.invoke(l);
                assertEquals(0,(int)pair[0].getMethod("size").invoke(l));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(byte[].class,int.class).
                    newInstance(new byte[] {0,1},1);
                final Method m = pair[1].getMethod("delete");
                m.invoke(l);
                assertEquals(0,(int)pair[1].getMethod("size").invoke(l));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l);
            }
        }
        catch(InvocationTargetException|NoSuchMethodException|InstantiationException|IllegalAccessException e) {fail(e);}
    }
    
    @Test
    void testArrayImpl()
    {
        final FixedByteList l = new FixedByteList(new byte[] {0,1,2},2);
        assertArrayEquals(new byte[] {0,1},(byte[])l.arrayImpl());
    }
}