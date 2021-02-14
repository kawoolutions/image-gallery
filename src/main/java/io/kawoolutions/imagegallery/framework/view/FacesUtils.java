package io.kawoolutions.imagegallery.framework.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FacesUtils
{
    private FacesUtils()
    {
    }
    
    public static FacesContext getFacesContext()
    {
        return FacesContext.getCurrentInstance();
    }
    
    // public static void redirectAbsolute( String url )
    // {
    // if ( !getFacesContext().getResponseComplete() )
    // {
    // try
    // {
    // getFacesContext().getExternalContext().redirect( url );
    // }
    // catch ( IOException e )
    // {
    // throw new FacesException( e.getMessage(), e );
    // }
    // }
    // }
    //
    // public static void redirect( String url )
    // {
    // redirect( CommonKeys.FACES_PATH, url );
    // }
    //
    // public static void redirect( String pattern, String url )
    // {
    // redirect( getFacesContext().getExternalContext().getRequestContextPath(), pattern, url );
    // }
    //
    // public static void redirect( String context, String pattern, String url )
    // {
    // redirectAbsolute( context + pattern + url );
    // }
    
    public static String getApplicationByRequestContextPath()
    {
        return getFacesContext().getExternalContext().getRequestContextPath().replace( "/", "" );
    }
    
    public static Flash getFlash()
    {
        return getFacesContext().getExternalContext().getFlash();
    }
    
    public static Map<String, Object> getRequestMap()
    {
        return getFacesContext().getExternalContext().getRequestMap();
    }
    
    public static Map<String, String> getRequestParameterMap()
    {
        return getFacesContext().getExternalContext().getRequestParameterMap();
    }
    
    public static String getRequestParameter( String key )
    {
        return getRequestParameterMap().get( key );
    }
    
    public static ServletRequest getRequest()
    {
        return ( ServletRequest ) getFacesContext().getExternalContext().getRequest();
    }
    
    public static HttpServletRequest getHttpRequest()
    {
        return ( HttpServletRequest ) getRequest();
    }
    
    public static ServletResponse getResponse()
    {
        return ( ServletResponse ) getFacesContext().getExternalContext().getResponse();
    }
    
    public static HttpServletResponse getHttpResponse()
    {
        return ( HttpServletResponse ) getResponse();
    }
    
    public static Map<String, Object> getSessionMap()
    {
        return getFacesContext().getExternalContext().getSessionMap();
    }
    
    public static HttpSession getSession()
    {
        return ( HttpSession ) getFacesContext().getExternalContext().getSession( false );
    }
    
    public static void invalidateSession()
    {
        getFacesContext().getExternalContext().invalidateSession();
    }
    
    /**
     * Sucht die Bean mit dem übergebenen Namen vom Typ <T>.
     * 
     * @param <T>
     * @param managedBeanName
     * @return
     */
    @SuppressWarnings( "unchecked" )
    public static <T> T findBean( String managedBeanName )
    {
        FacesContext context = getFacesContext();
        return ( T ) context.getApplication().evaluateExpressionGet( context, "#" + "{" + managedBeanName + "}", Object.class );
    }
    
    public static List<Locale> getSupportedLocales( FacesContext context )
    {
        Application application = context.getApplication();
        List<Locale> supportedLocales = new ArrayList<Locale>();
        Locale defaultLocale = application.getDefaultLocale();
        
        if ( defaultLocale != null )
        {
            supportedLocales.add( defaultLocale );
        }
        
        for ( Iterator<Locale> iter = application.getSupportedLocales(); iter.hasNext(); )
        {
            Locale supportedLocale = iter.next();
            
            if ( !supportedLocale.equals( defaultLocale ) )
            {
                supportedLocales.add( supportedLocale );
            }
        }
        
        return supportedLocales;
    }
    
    private static ValueExpression createValueExpression( String expression, Class<?> expectedType )
    {
        return getFacesContext().getApplication().getExpressionFactory().createValueExpression( getFacesContext().getELContext(),
                                                                                                expression, expectedType );
    }
    
    public static ValueExpression createValueExpression( String expression )
    {
        return createValueExpression( "#" + "{" + expression + "}", Object.class );
    }
    
    public static ValueExpression createValueExpression( String valueObject, String property )
    {
        return createValueExpression( valueObject + "." + property );
    }
    
    public static ValueExpression createValueExpressionString( String expression )
    {
        return createValueExpression( expression, String.class );
    }
    
    public static void responseReset()
    {
        getFacesContext().getExternalContext().responseReset();
    }
    
    public static void setContextAttribute( String name, Object value )
    {
        getFacesContext().getAttributes().put( name, value );
    }
    
    public static void addRenderId( String id )
    {
        getFacesContext().getPartialViewContext().getRenderIds().add( id );
    }
}
