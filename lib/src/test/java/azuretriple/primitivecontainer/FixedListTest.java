package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedListTest
{
    @Test void testInit() {assertDoesNotThrow(() -> new FixedByteList(new FixedByteList(new byte[0],0)));}
    
    @Test
    void testAdd()
    {
        final FixedByteList l = new FixedByteList(new byte[3],0);
        ((FixedList)l).add(new byte[] {1});
        ((FixedList)l).add(new byte[] {2,3});
        assertArrayEquals(new byte[] {1,2,3},l.data());
        assertEquals(3,l.size());
    }
    
    @Test
    void testAddLogic()
    {
        // Dummy call to satisfy the coverage report for empty function.
        assertDoesNotThrow(() -> new FixedByteList(new byte[0],0).addLogic());
    }
    
    @Test
    void testInsert()
    {
        final FixedByteList l = new FixedByteList(new byte[4],0);
        l.insert(0,new byte[] {0,3});
        l.insert(1,new byte[] {1,2});
        assertArrayEquals(new byte[] {0,1,2,3},l.data());
        assertEquals(4,l.size());
    }
    
    @Test
    void testInsertLogic()
    {
        final FixedByteList l = new FixedByteList(new byte[] {0,1,0},2);
        l.insertLogic(1);
        assertArrayEquals(new byte[] {0,1,1},l.data());
        assertEquals(3,l.size());
    }
    
    @Test
    void testRemove()
    {
        final FixedByteList l = new FixedByteList(new byte[] {0,1,2},3);
        l.remove(1);
        assertEquals(2,l.size());
        assertArrayEquals(new byte[] {0,2},l.array());
    }
}