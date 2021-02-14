package io.kawoolutions.imagegallery.framework.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.management.timer.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingInterceptor
{
    // TODO: ?
    public static final Logger log = LoggerFactory.getLogger( LoggingInterceptor.class );
    
    @AroundInvoke
    public Object intercept( InvocationContext ctx ) throws Exception
    {
        Timer timer = new Timer();
        timer.start();
        
        Object result = ctx.proceed();
        
        timer.stop();
        
        log.info( ctx.getMethod().getDeclaringClass().getName() + "." + ctx.getMethod().getName() + ": " + timer );
        
        return result;
    }
}
