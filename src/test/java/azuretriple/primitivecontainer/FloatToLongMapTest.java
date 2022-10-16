package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FloatToLongMapTest
{
    @Test
    void testInit()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FloatToLongMap.class),
                         ksPair = AssertionUtil.getAssertionPair(FloatSet.class),
                         kolPair = AssertionUtil.getAssertionPair(OrderedFloatList.class),
                         kflPair = AssertionUtil.getAssertionPair(FixedFloatList.class),
                         vflPair = AssertionUtil.getAssertionPair(FixedLongList.class),
                         klPair = AssertionUtil.getAssertionPair(FloatList.class),
                         vlPair = AssertionUtil.getAssertionPair(LongList.class);
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
                ksPair[0].getDeclaredMethod("add",float.class).invoke(k,(float)0);
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
                ksPair[1].getDeclaredMethod("add",float.class).invoke(k,(float)0);
                // Dummy call to satisfy the coverage report for assertions.
                init.newInstance(k,v);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }

    @Test
    void testInsert()
    {
        final FloatToLongMap m = new FloatToLongMap(new FloatSet(new OrderedFloatList(new FixedFloatList(1))),new FixedLongList(1));
        m.insert((float)1,2);
        assertTrue(m.keys().contains((float)1));
        assertEquals(2,m.get((float)1));
    }

    @Test
    void testPut()
    {
        final FloatToLongMap m = new FloatToLongMap(new FloatSet(new OrderedFloatList(new FixedFloatList(1))),new FixedLongList(1));
        m.put((float)1,2);
        assertTrue(m.keys().contains((float)1));
        assertEquals(2,m.get((float)1));
        m.put((float)1,3);
        assertTrue(m.keys().contains((float)1));
        assertEquals(3,m.get((float)1));
        m.put((float)1,3);
        assertTrue(m.keys().contains((float)1));
        assertEquals(3,m.get((float)1));
    }

    @Test
    void testAdd()
    {
        final FloatToLongMap m = new FloatToLongMap(new FloatSet(new OrderedFloatList(new FixedFloatList(1))),new FixedLongList(1));
        assertTrue(m.add((float)1,2));
        assertTrue(m.keys().contains((float)1));
        assertEquals(2,m.get((float)1));
        assertTrue(m.add((float)1,3));
        assertEquals(3,m.get((float)1));
        assertFalse(m.add((float)1,3));
    }

    @Test
    void testGet()
    {
        final FloatToLongMap m = new FloatToLongMap(new FloatSet(new OrderedFloatList(new FixedFloatList(1))),new FixedLongList(1));
        m.insert((float)1,2);
        assertEquals(2,m.get((float)1));
    }

    @Test
    void testRemove()
    {
        final FloatToLongMap m = new FloatToLongMap(new FloatSet(new OrderedFloatList(new FixedFloatList(1))),new FixedLongList(1));
        assertFalse(m.remove((float)1));
        m.insert((float)1,2);
        assertTrue(m.remove((float)1));
    }

    @Test
    void testDelete()
    {
        final FloatToLongMap m = new FloatToLongMap(new FloatSet(new OrderedFloatList(new FixedFloatList(1))),new FixedLongList(1));
        m.insert((float)1,2);
        m.delete((float)1);
        assertFalse(m.keys().contains((float)1));
    }

    @Test
    void testExtract()
    {
        final FloatToLongMap m = new FloatToLongMap(new FloatSet(new OrderedFloatList(new FixedFloatList(1))),new FixedLongList(1));
        m.insert((float)1,2);
        assertEquals(2,m.extract((float)1));
    }

    @Test
    void testClone()
    {
        final FloatToLongMap m = new FloatToLongMap(new FloatSet(new OrderedFloatList(new FixedFloatList(1))),new FixedLongList(1)),
                            m2 = m.clone();
        assertEquals(m,m2);
        m.insert((float)1,2);
        assertNotEquals(m,m2);
    }

    @Test
    void testKeys()
    {
        final FloatSet k = new FloatSet(new OrderedFloatList(new FixedFloatList(1)));
        assertSame(k,new FloatToLongMap(k,new FixedLongList(1)).keys());
    }

    @Test
    void testValues()
    {
        final LongList v = new FixedLongList(1);
        assertSame(v,new FloatToLongMap(new FloatSet(new OrderedFloatList(new FixedFloatList(1))),v).values());
    }
}