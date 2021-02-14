package io.kawoolutions.imagegallery.framework.exception;

/**
 * General exception for delete operations (the "D" in CRUD).
 */
public class DeleteException extends PersistException
{
    private static final long serialVersionUID = 1L;
    
    public DeleteException()
    {
        super();
    }
    
    public DeleteException( String message )
    {
        super( message );
    }
    
    public DeleteException( Throwable cause )
    {
        super( cause );
    }
    
    public DeleteException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
