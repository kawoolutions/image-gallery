package io.kawoolutions.imagegallery.framework.cdi;

/**
 * The below utility methods are probably Weld-specific.
 *
 */
public class CdiUtils
{
    private static final String IDENTIFIER = "$";
    
    public static boolean isProxyClass( Class<?> clazz )
    {
        return clazz.getName().contains( IDENTIFIER );
    }
    
    /**
     * Returns the non-proxy class of a (bean's) class, if it is determined to be from a CDI proxy instance.
     * 
     * @param clazz
     * @return
     */
    public static Class<?> getNonProxyClass( Class<?> clazz )
    {
        if ( CdiUtils.isProxyClass( clazz ) )
        {
            return clazz.getSuperclass();
        }
        
        return clazz;
    }
}
