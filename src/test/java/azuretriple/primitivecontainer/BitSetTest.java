package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
class BitSetTest
{
    @Test void testMajor() {assertEquals(1,BitSet.major(1<<6));}
    @Test void testMask() {assertEquals(1L<<3,BitSet.mask(3|(1<<6)));}
    
    @Test
    void testEquals()
    {
        // A == B
        {
            final FixedBitSet A = new FixedBitSet(1);
            assertEquals(A,A);
        }
        // A instanceof ArrayBitSet && B instanceof ArrayBitSet
        {
            // A instanceof GrowableBitSet && B instanceof GrowableBitSet
            {
                final GrowableBitSet A = new GrowableBitSet(),
                                     B = new GrowableBitSet();
                // A.highest != B.highest
                {
                    A.set(0);
                    assertNotEquals(A,B);
                }
                // A[i] != B[i]
                {
                    B.set(0);
                    A.set(64);
                    B.set(65);
                    assertNotEquals(A,B);
                }
                // A[i] == B[i]
                {
                    A.set(65);
                    B.set(64);
                    assertEquals(A,B);
                }
            }
            // ||A|| < ||B||
            {
                // B instanceof GrowableBitSet
                {
                    final FixedBitSet A = new FixedBitSet(1);
                    final GrowableBitSet B = new GrowableBitSet(1);
                    B.set(64);
                    assertNotEquals(A,B);
                }
                // A instanceof GrowableBitSet
                {
                    final GrowableBitSet A = new GrowableBitSet(1);
                    final FixedBitSet B = new FixedBitSet(65);
                    assertEquals(A,B);
                }
            }
            // ||A|| > ||B||
            {
                // A instanceof GrowableBitSet
                {
                    final GrowableBitSet A = new GrowableBitSet(65);
                    final FixedBitSet B = new FixedBitSet(1);
                    A.set(64);
                    assertNotEquals(A,B);
                }
                // B instanceof GrowableBitSet
                {
                    final FixedBitSet A = new FixedBitSet(65);
                    final GrowableBitSet B = new GrowableBitSet(1);
                    assertEquals(A,B);
                    A.set(0);
                    assertNotEquals(A,B);
                }
            }
            // ||A|| == ||B||
            {
                // A instanceof GrowableBitSet
                {
                    final GrowableBitSet A = new GrowableBitSet(1);
                    final FixedBitSet B = new FixedBitSet(1);
                    assertEquals(A,B);
                    A.set(0);
                    assertNotEquals(A,B);
                }
                // B instanceof GrowableBitSet
                {
                    final FixedBitSet A = new FixedBitSet(1);
                    final GrowableBitSet B = new GrowableBitSet(1);
                    assertEquals(A,B);
                    A.set(0);
                    assertNotEquals(A,B);
                }
                // A instanceof FixedBitSet && B instanceof FixedBitSet
                {
                    final FixedBitSet A = new FixedBitSet(1);
                    final FixedBitSet B = new FixedBitSet(1);
                    assertEquals(A,B);
                    A.set(0);
                    assertNotEquals(A,B);
                }
            }
            // A[i] != B[i]
            {
                final FixedBitSet A = new FixedBitSet(1);
                final FixedBitSet B = new FixedBitSet(1);
                A.set(0);
                assertNotEquals(A,B);
            }
            // B[i <= ||A||] != 0
            {
                final FixedBitSet A = new FixedBitSet(1);
                final FixedBitSet B = new FixedBitSet(65);
                B.set(64);
                assertNotEquals(A,B);
            }
            // A[i] == B[i] && B[j <= ||A||] == 0
            {
                final FixedBitSet A = new FixedBitSet(1);
                final FixedBitSet B = new FixedBitSet(65);
                A.set(0);
                B.set(0);
                assertEquals(A,B);
            }
        }
        // A instanceof MapBitSet && B instanceof MapBitSet
        {
            // A.keys != B.keys
            {
                final MapBitSet A = new MapBitSet();
                final MapBitSet B = new MapBitSet();
                A.set(0);
                B.set(65);
                assertNotEquals(A,B);
            }
            // A.values != B.values
            {
                final MapBitSet A = new MapBitSet();
                final MapBitSet B = new MapBitSet();
                A.set(0);
                B.set(1);
                assertNotEquals(A,B);
            }
            // A.keys == B.keys && A.values == B.values
            {
                final MapBitSet A = new MapBitSet();
                final MapBitSet B = new MapBitSet();
                A.set(0);
                A.set(65);
                B.set(0);
                B.set(65);
                assertEquals(A,B);
            }
        }
        // A instanceof ArrayBitSet != B instanceof ArrayBitSet
        {
            // A instanceof GrowableBitSet
            {
                final GrowableBitSet A = new GrowableBitSet();
                final MapBitSet B = new MapBitSet();
                assertEquals(A,B);
                A.set(0);
                assertNotEquals(A,B);
                B.set(0);
                B.set(64);
                assertNotEquals(A,B);
                A.set(64);
                assertEquals(A,B);
            }
            // A instanceof FixedBitSet
            {
                final FixedBitSet A = new FixedBitSet(65);
                final MapBitSet B = new MapBitSet();
                assertEquals(A,B);
                A.set(0);
                assertNotEquals(A,B);
                B.set(0);
                B.set(64);
                assertNotEquals(A,B);
                A.set(64);
                assertEquals(A,B);
            }
            // B instanceof GrowableBitSet
            {
                final MapBitSet A = new MapBitSet();
                final GrowableBitSet B = new GrowableBitSet();
                assertEquals(A,B);
                A.set(0);
                assertNotEquals(A,B);
                B.set(0);
                B.set(64);
                assertNotEquals(A,B);
                A.set(64);
                assertEquals(A,B);
            }
            // B instanceof FixedBitSet
            {
                final MapBitSet A = new MapBitSet();
                final FixedBitSet B = new FixedBitSet(65);
                assertEquals(A,B);
                A.set(0);
                assertNotEquals(A,B);
                B.set(0);
                B.set(64);
                assertNotEquals(A,B);
                A.set(64);
                assertEquals(A,B);
            }
            // A instanceof MapBitSet && ||A|| == 0
            {
                final MapBitSet A = new MapBitSet();
                final FixedBitSet B = new FixedBitSet(1);
                // B[i] == 0
                assertEquals(A,B);
                // B[i] != 0
                {
                    B.set(0);
                    assertNotEquals(A,B);
                }
            }
            // A instanceof MapBitSet && ||A|| != 0
            {
                // ||B|| < A.keys[-1]+1
                {
                    final MapBitSet A = new MapBitSet();
                    final GrowableBitSet B = new GrowableBitSet();
                    A.set(0);
                    assertNotEquals(A,B);
                }
                // B[A.keys[i-1] < j < A.keys[i] : 0 < i < ||A.keys||] != 0
                {
                    final MapBitSet A = new MapBitSet();
                    final GrowableBitSet B = new GrowableBitSet();
                    A.set(0);
                    A.set(128);
                    B.set(0);
                    B.set(64);
                    B.set(128);
                    assertNotEquals(A,B);
                    B.unset(64);
                    assertEquals(A,B);
                }
                // B[A.keys[i]] != A.values[A.keys[i]]
                {
                    final MapBitSet A = new MapBitSet();
                    final GrowableBitSet B = new GrowableBitSet();
                    A.set(0);
                    B.set(1);
                    assertNotEquals(A,B);
                }
                // B[i > A.keys[-1]] != 0
                {
                    final MapBitSet A = new MapBitSet();
                    final FixedBitSet B = new FixedBitSet(65);
                    A.set(0);
                    B.set(0);
                    B.set(64);
                    assertNotEquals(A,B);
                }
                // B[i > A.keys[-1]] == 0
                {
                    final MapBitSet A = new MapBitSet();
                    final FixedBitSet B = new FixedBitSet(65);
                    A.set(0);
                    B.set(0);
                    assertEquals(A,B);
                }
            }
        }
    }
}