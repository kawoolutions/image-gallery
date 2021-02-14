package io.kawoolutions.imagegallery.framework.exception;

/**
 * Exception for generating pseudo auto IDs based on a query to find the maximum ID.
 */
public class MaxIdNotFoundException extends RetrieveException
{
    private static final long serialVersionUID = 1L;
    
    public MaxIdNotFoundException()
    {
        super();
    }
    
    public MaxIdNotFoundException( String message )
    {
        super( message );
    }
    
    public MaxIdNotFoundException( Throwable cause )
    {
        super( cause );
    }
    
    public MaxIdNotFoundException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
