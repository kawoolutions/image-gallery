package io.kawoolutions.imagegallery.application;

import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;

import org.primefaces.application.exceptionhandler.PrimeExceptionHandler;

public class NonViewExpiredLoggingExceptionHandler extends PrimeExceptionHandler
{
    public NonViewExpiredLoggingExceptionHandler( ExceptionHandler wrapped )
    {
        super( wrapped );
    }
    
    @Override
    protected void logException( Throwable rootCause )
    {
        System.out.println( "--------------- logException(): " + rootCause.getClass().getName() );
        
        if ( rootCause instanceof ViewExpiredException )
        {
            System.out.println( "--------------- logException(): suppressing VEE!" );
            
            return;
        }
        
        super.logException( rootCause );
    }
}
