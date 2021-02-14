package io.kawoolutions.imagegallery.framework.exception;

/**
 * Exception when creating new entities that already exist.
 */
public class EntityAlreadyExistsException extends CreateException
{
    private static final long serialVersionUID = 1L;
    
    public EntityAlreadyExistsException()
    {
        super();
    }
    
    public EntityAlreadyExistsException( String message )
    {
        super( message );
    }
    
    public EntityAlreadyExistsException( Throwable cause )
    {
        super( cause );
    }
    
    public EntityAlreadyExistsException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
