package io.kawoolutions.imagegallery.framework.exception;

/**
 * Base exception for credentials operations.
 */
public abstract class CredentialsException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    protected CredentialsException()
    {
        super();
    }
    
    protected CredentialsException( String message )
    {
        super( message );
    }
    
    protected CredentialsException( Throwable cause )
    {
        super( cause );
    }
    
    protected CredentialsException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
