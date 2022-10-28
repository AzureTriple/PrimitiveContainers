package azuretriple.primitivecontainer;

import static java.lang.reflect.Array.newInstance;

/** The abstract base class of containers. */
public abstract class Container implements PrimitiveContainer
{
    static Object arrInst(final Object arr,final int size) {return newInstance(arr.getClass().componentType(),size);}
    
    /** The backing array for this container. Exercise caution when modifying this variable. */
    public Object arr;
    
    Container(final Object arr) {this.arr = arr; assert arr != null;}
    
    abstract Object arrayImpl();
    
    @Override public abstract Container clone();
}