package io.kawoolutions.imagegallery.framework.exception;

/**
 * Base exception for credentials operations.
 */
public class UserNotFoundException extends CredentialsException
{
    private static final long serialVersionUID = 1L;
    
    public UserNotFoundException()
    {
        super();
    }
    
    public UserNotFoundException( String message )
    {
        super( message );
    }
    
    public UserNotFoundException( Throwable cause )
    {
        super( cause );
    }
    
    public UserNotFoundException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
