package io.kawoolutions.imagegallery.util;

import java.security.spec.InvalidKeySpecException;

import io.kawoolutions.imagegallery.framework.security.HashUtils;

public abstract class PasswordUtils
{
    public static boolean comparePlaintextPasswordWithHashedPassword( String plaintextPassword, String hashedPassword, byte[] salt ) throws InvalidKeySpecException
    {
        String hashedString = null;
        
        if ( hashedPassword != null && !hashedPassword.trim().isEmpty() )
        {
            if ( hashedPassword.toLowerCase().startsWith( "{sha}" ) )
            {
                hashedString = HashUtils.createHashedStringSha1( plaintextPassword );
            }
            else if ( hashedPassword.toLowerCase().startsWith( "{pbkdf2}" ) )
            {
                hashedString = HashUtils.createHashedStringPBKDF2withHmacSHA512( salt, plaintextPassword );
            }
            else
            {
                return false;
            }
            
            String hashedPasswordStr = hashedPassword.substring( hashedPassword.indexOf( '}' ) + 1 );
            
            if ( hashedPasswordStr.equals( hashedString ) )
            {
                return true;
            }
        }
                
        return false;
    }
    
    public static boolean isHashedPassword( String password )
    {
        return isShaHashedPassword( password ) || isPbkdf2HashedPassword( password );
    }
    
    public static boolean isShaHashedPassword( String password )
    {
        return password.toLowerCase().startsWith( "{sha}" );
    }
    
    public static boolean isPbkdf2HashedPassword( String password )
    {
        return password.toLowerCase().startsWith( "{pbkdf2}" );
    }
}
