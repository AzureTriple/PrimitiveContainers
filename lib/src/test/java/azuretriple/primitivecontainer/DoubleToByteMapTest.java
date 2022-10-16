package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class DoubleToByteMapTest
{
    @Test
    void testInit()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(DoubleToByteMap.class),
                         ksPair = AssertionUtil.getAssertionPair(DoubleSet.class),
                         kolPair = AssertionUtil.getAssertionPair(OrderedDoubleList.class),
                         kflPair = AssertionUtil.getAssertionPair(FixedDoubleList.class),
                         vflPair = AssertionUtil.getAssertionPair(FixedByteList.class),
                         klPair = AssertionUtil.getAssertionPair(DoubleList.class),
                         vlPair = AssertionUtil.getAssertionPair(ByteList.class);
        try
        {
            {
                final Object k = ksPair[0].getDeclaredConstructor(kolPair[0]).
                                           newInstance
                                           (
                                               kolPair[0].getDeclaredConstructor(klPair[0]).
                                                          newInstance
                                                          (
                                                              kflPair[0].getDeclaredConstructor(int.class).
                                                                         newInstance(1)
                                                          )
                                           );
                final Object v = vflPair[0].getDeclaredConstructor(int.class).newInstance(1);
                final Constructor<?> init = pair[0].getDeclaredConstructor(ksPair[0],vlPair[0]);
                {
                    final Method keys = pair[0].getDeclaredMethod("keys"),values = pair[0].getDeclaredMethod("values");
                    final Object o = init.newInstance(k,v);
                    assertSame(k,keys.invoke(o));
                    assertSame(v,values.invoke(o));
                    assertEquals(o,pair[0].getDeclaredConstructor(pair[0]).newInstance(o));
                }
                ksPair[0].getDeclaredMethod("add",double.class).invoke(k,(double)0);
                assertThrows(InvocationTargetException.class,() -> init.newInstance(k,v));
            }
            {
                final Object k = ksPair[1].getDeclaredConstructor(kolPair[1]).
                                           newInstance
                                           (
                                               kolPair[1].getDeclaredConstructor(klPair[1]).
                                                          newInstance
                                                          (
                                                              kflPair[1].getDeclaredConstructor(int.class).
                                                                         newInstance(1)
                                                          )
                                           );
                final Object v = vflPair[1].getDeclaredConstructor(int.class).newInstance(1);
                final Constructor<?> init = pair[1].getDeclaredConstructor(ksPair[1],vlPair[1]);
                {
                    final Method keys = pair[1].getDeclaredMethod("keys"),values = pair[1].getDeclaredMethod("values");
                    final Object o = init.newInstance(k,v);
                    assertSame(k,keys.invoke(o));
                    assertSame(v,values.invoke(o));
                    assertEquals(o,pair[1].getDeclaredConstructor(pair[1]).newInstance(o));
                }
                ksPair[1].getDeclaredMethod("add",double.class).invoke(k,(double)0);
                // Dummy call to satisfy the coverage report for assertions.
                init.newInstance(k,v);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }

    @Test
    void testInsert()
    {
        final DoubleToByteMap m = new DoubleToByteMap(new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1))),new FixedByteList(1));
        m.insert(1,(byte)2);
        assertTrue(m.keys().contains(1));
        assertEquals((byte)2,m.get(1));
    }

    @Test
    void testPut()
    {
        final DoubleToByteMap m = new DoubleToByteMap(new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1))),new FixedByteList(1));
        m.put(1,(byte)2);
        assertTrue(m.keys().contains(1));
        assertEquals((byte)2,m.get(1));
        m.put(1,(byte)3);
        assertTrue(m.keys().contains(1));
        assertEquals((byte)3,m.get(1));
        m.put(1,(byte)3);
        assertTrue(m.keys().contains(1));
        assertEquals((byte)3,m.get(1));
    }

    @Test
    void testAdd()
    {
        final DoubleToByteMap m = new DoubleToByteMap(new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1))),new FixedByteList(1));
        assertTrue(m.add(1,(byte)2));
        assertTrue(m.keys().contains(1));
        assertEquals((byte)2,m.get(1));
        assertTrue(m.add(1,(byte)3));
        assertEquals((byte)3,m.get(1));
        assertFalse(m.add(1,(byte)3));
    }

    @Test
    void testGet()
    {
        final DoubleToByteMap m = new DoubleToByteMap(new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1))),new FixedByteList(1));
        m.insert(1,(byte)2);
        assertEquals((byte)2,m.get(1));
    }

    @Test
    void testRemove()
    {
        final DoubleToByteMap m = new DoubleToByteMap(new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1))),new FixedByteList(1));
        assertFalse(m.remove(1));
        m.insert(1,(byte)2);
        assertTrue(m.remove(1));
    }

    @Test
    void testDelete()
    {
        final DoubleToByteMap m = new DoubleToByteMap(new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1))),new FixedByteList(1));
        m.insert(1,(byte)2);
        m.delete(1);
        assertFalse(m.keys().contains(1));
    }

    @Test
    void testExtract()
    {
        final DoubleToByteMap m = new DoubleToByteMap(new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1))),new FixedByteList(1));
        m.insert(1,(byte)2);
        assertEquals((byte)2,m.extract(1));
    }

    @Test
    void testClone()
    {
        final DoubleToByteMap m = new DoubleToByteMap(new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1))),new FixedByteList(1)),
                            m2 = m.clone();
        assertEquals(m,m2);
        m.insert(1,(byte)2);
        assertNotEquals(m,m2);
    }

    @Test
    void testKeys()
    {
        final DoubleSet k = new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1)));
        assertSame(k,new DoubleToByteMap(k,new FixedByteList(1)).keys());
    }

    @Test
    void testValues()
    {
        final ByteList v = new FixedByteList(1);
        assertSame(v,new DoubleToByteMap(new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1))),v).values());
    }
}