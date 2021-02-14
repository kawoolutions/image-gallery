package io.kawoolutions.imagegallery.framework.i18n;

import java.util.PropertyResourceBundle;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * CDI-injectable bundle producer.
 * 
 * See https://stackoverflow.com/questions/27927717/difference-between-by-applicationgetresourcebundle-and-resourcebundlegetbund
 */
public class BundleProducer
{
    @Produces
    public PropertyResourceBundle getBundle()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        
        return context.getApplication().evaluateExpressionGet( context, "#{text}", PropertyResourceBundle.class );
    }
}