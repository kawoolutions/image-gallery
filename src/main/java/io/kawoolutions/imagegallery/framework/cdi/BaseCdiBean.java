package io.kawoolutions.imagegallery.framework.cdi;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

public abstract class BaseCdiBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    protected Logger log;
    
    // see https://arjan-tijms.omnifaces.org/p/jsf-23.html#cdi
//    @Inject
//    protected FacesContext facesContext;
//
//    @Inject
//    protected ExternalContext externalContext;
    
    @Inject
    protected HttpServletRequest httpRequest;
    
    @PostConstruct
    public void init()
    {
//        log.info( "----------" + BaseCdiBean.class.getSimpleName() + ": @PostConstruct on " + getClass().getSimpleName() );
    }

    public void doNothing()
    {
        // dummy for includes
    }
    
    public void throwViewExpiredException()
    {
        throw new ViewExpiredException( "A ViewExpiredException!", FacesContext.getCurrentInstance().getViewRoot().getViewId() );
    }
    
    public void throwNullPointerException()
    {
        throw new NullPointerException( "A NullPointerException!" );
    }
    
    public void throwWrappedIllegalStateException()
    {
        throw new FacesException( new IllegalStateException( "A wrapped IllegalStateException!" ) );
    }
}
