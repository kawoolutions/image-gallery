package io.kawoolutions.imagegallery.framework.exception;

/**
 * Base exception for credentials operations.
 */
public class WrongPasswordException extends CredentialsException
{
    private static final long serialVersionUID = 1L;
    
    public WrongPasswordException()
    {
        super();
    }
    
    public WrongPasswordException( String message )
    {
        super( message );
    }
    
    public WrongPasswordException( Throwable cause )
    {
        super( cause );
    }
    
    public WrongPasswordException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
