package io.kawoolutions.imagegallery.framework.exception;

import javax.ejb.ApplicationException;

/**
 * Base class for all business / service exceptions.
 */
@ApplicationException( rollback = true )
public abstract class BusinessException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    protected BusinessException()
    {
        super();
    }
    
    protected BusinessException( String message )
    {
        super( message );
    }
    
    protected BusinessException( Throwable cause )
    {
        super( cause );
    }
    
    protected BusinessException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
