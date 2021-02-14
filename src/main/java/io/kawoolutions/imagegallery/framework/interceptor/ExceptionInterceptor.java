package io.kawoolutions.imagegallery.framework.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ExceptionInterceptor
{
    @AroundInvoke
    public Object intercept( InvocationContext ctx ) throws Exception
    {
        Object result = null;
        try
        {
            result = ctx.proceed();
        }
        catch ( RuntimeException re )
        {
//            throw new BusinessException( re.getMessage(), re );
        }
        
        return result;
    }
}
