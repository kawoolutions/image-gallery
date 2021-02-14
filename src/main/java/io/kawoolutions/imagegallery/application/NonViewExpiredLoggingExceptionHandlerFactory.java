package io.kawoolutions.imagegallery.application;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

import org.primefaces.application.exceptionhandler.PrimeExceptionHandlerFactory;

public class NonViewExpiredLoggingExceptionHandlerFactory extends PrimeExceptionHandlerFactory
{
    public NonViewExpiredLoggingExceptionHandlerFactory( ExceptionHandlerFactory wrapped )
    {
        super( wrapped );
    }

    @Override
    public ExceptionHandler getExceptionHandler()
    {
        return new NonViewExpiredLoggingExceptionHandler( super.getExceptionHandler() );
    }
}
