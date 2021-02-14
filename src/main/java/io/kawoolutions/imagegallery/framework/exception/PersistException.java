package io.kawoolutions.imagegallery.framework.exception;

/**
 * Base exception for create, update and delete operations.
 */
public abstract class PersistException extends BusinessException
{
    private static final long serialVersionUID = 1L;
    
    protected PersistException()
    {
        super();
    }
    
    protected PersistException( String message )
    {
        super( message );
    }
    
    protected PersistException( Throwable cause )
    {
        super( cause );
    }
    
    protected PersistException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
