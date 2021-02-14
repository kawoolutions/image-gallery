package io.kawoolutions.imagegallery.framework.exception;

/**
 * General exception for retrieve operations (the "R" in CRUD).
 */
public class RetrieveException extends BusinessException
{
    private static final long serialVersionUID = 1L;
    
    public RetrieveException()
    {
        super();
    }
    
    public RetrieveException( String message )
    {
        super( message );
    }
    
    public RetrieveException( Throwable cause )
    {
        super( cause );
    }
    
    public RetrieveException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
