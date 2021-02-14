package io.kawoolutions.imagegallery.framework.exception;

/**
 * General exception for update operations (the "U" in CRUD).
 */
public class UpdateException extends PersistException
{
    private static final long serialVersionUID = 1L;
    
    public UpdateException()
    {
        super();
    }
    
    public UpdateException( String message )
    {
        super( message );
    }
    
    public UpdateException( Throwable cause )
    {
        super( cause );
    }
    
    public UpdateException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
