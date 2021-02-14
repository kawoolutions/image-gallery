package io.kawoolutions.imagegallery.framework.exception;

/**
 * Exception for single entities not found.
 */
public class EntityNotFoundException extends RetrieveException
{
    private static final long serialVersionUID = 1L;
    
    public EntityNotFoundException()
    {
        super();
    }
    
    public EntityNotFoundException( String message )
    {
        super( message );
    }
    
    public EntityNotFoundException( Throwable cause )
    {
        super( cause );
    }
    
    public EntityNotFoundException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
