package io.kawoolutions.imagegallery.framework.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

//@Transactional( TxType.REQUIRED )
//@Interceptor
//@Priority( Interceptor.Priority.PLATFORM_BEFORE + 201 )
public class JoinTransactionInterceptor
{
//    @Inject
//    private EntityManager em;
    
    @AroundInvoke
    public Object joinTransaction( InvocationContext context ) throws Exception
    {
//        if ( !em.isJoinedToTransaction() )
//        {
//            em.joinTransaction();
//        }
        
        return context.proceed();
    }
}