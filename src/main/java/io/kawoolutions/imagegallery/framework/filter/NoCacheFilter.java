package io.kawoolutions.imagegallery.framework.filter;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter( "/*" )
public class NoCacheFilter implements Filter
{
    @Override
    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException, ServletException
    {
        HttpServletRequest request = ( HttpServletRequest ) req;
        HttpServletResponse response = ( HttpServletResponse ) res;
        
        // context path + resource identfier: CSS/JS/images etc. resources are identified this way
        String resourcePath = request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER;
        
        // if not a resource being requested
        if ( !request.getRequestURI().startsWith( resourcePath ) )
        {
            // change caching mode
            response.setHeader( "Cache-Control", "no-store, must-revalidate" ); // HTTP 1.1
            response.setHeader( "Pragma", "no-cache" ); // HTTP 1.0
            response.setDateHeader( "Expires", 0 ); // proxies
        }
        
        chain.doFilter( request, response );
    }
}
