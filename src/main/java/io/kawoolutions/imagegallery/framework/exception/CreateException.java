package io.kawoolutions.imagegallery.framework.exception;

/**
 * General exception for create operations (the "C" in CRUD).
 */
public class CreateException extends PersistException
{
    private static final long serialVersionUID = 1L;

    public CreateException()
    {
        super();
    }

    public CreateException( String message )
    {
        super( message );
    }

    public CreateException( Throwable cause )
    {
        super( cause );
    }

    public CreateException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
