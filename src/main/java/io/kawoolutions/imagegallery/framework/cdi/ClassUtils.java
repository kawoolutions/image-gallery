package io.kawoolutions.imagegallery.framework.cdi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClassUtils
{
    public static Class<?> findGenericClassFor( Class<?> cdiClass )
    {
        return findGenericClassFor( cdiClass, 0 );
    }
    
    public static Class<?> findGenericClassFor( Class<?> cdiClass, int index )
    {
        Type type = cdiClass.getGenericSuperclass();
        
        while ( type != null && ! ( type instanceof ParameterizedType ) )
        {
            // we have a generic super class, but it is not a parameterized type itself, so search hierarchy until we
            // find one
            cdiClass = ( Class<?> ) type;
            type = cdiClass.getGenericSuperclass();
        }
        
        if ( type == null )
        {
            throw new IllegalStateException( CdiUtils.class.getSimpleName() + ".findGenericClassFor() found a null type for " + cdiClass.getName() + "!" );
        }
        
        Type[] genericTypes = ( ( ParameterizedType ) type ).getActualTypeArguments();
        
        Class<?> genericClass = ( Class<?> ) genericTypes[index];
        
        return genericClass;
    }
}
