package io.kawoolutions.imagegallery.framework.exception;

/**
 * Exception for queries that should return multiple entities.
 */
public class EntityQueryException extends RetrieveException
{
    private static final long serialVersionUID = 1L;
    
    public EntityQueryException()
    {
        super();
    }
    
    public EntityQueryException( String message )
    {
        super( message );
    }
    
    public EntityQueryException( Throwable cause )
    {
        super( cause );
    }
    
    public EntityQueryException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
