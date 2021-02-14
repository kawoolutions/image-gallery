package io.kawoolutions.imagegallery.util;

import java.beans.Introspector;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public abstract class StringUtils
{
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    
    public static String lowerFirst( String str )
    {
        // stupid lowercase first to avoid another project dependency
//        return str.substring( 0, 1 ).toLowerCase() + str.substring( 1 );
        
        // see https://stackoverflow.com/a/12308665/396732
        return Introspector.decapitalize( str );
    }
    
    public static String upperFirst( String str )
    {
        // stupid uppercase first to avoid another project dependency
        return str.substring( 0, 1 ).toUpperCase() + str.substring( 1 );
    }
    
    public static boolean isNumeric( String str )
    {
        for ( char c : str.toCharArray() )
        {
            if ( !Character.isDigit( c ) )
            {
                return false;
            }
        }
        
        return true;
    }
    
    public static final String encodeUrlParameter( final String parameterValue )
    {
        try
        {
            return URLEncoder.encode( parameterValue, "UTF-8" );
        }
        catch ( UnsupportedEncodingException e )
        {
            // UTF-8 ist immer vorhanden
            throw new RuntimeException( e );
        }
    }
    
    public static String bytesToHex( byte[] bytes )
    {
        char[] hexChars = new char[bytes.length * 2];
        
        for ( int j = 0; j < bytes.length; j++ )
        {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        
        return new String( hexChars );
    }
}
