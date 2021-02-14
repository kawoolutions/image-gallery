package io.kawoolutions.imagegallery.framework.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import io.kawoolutions.imagegallery.framework.entity.Entity;
import io.kawoolutions.imagegallery.framework.service.CommonService;

/**
 * Returns a string representation as implemented by entity.getPk().toString(). This class
 * saves half the number of methods needed to implement a composite key entity converter.
 */
public abstract class BaseEntityConverter<K, T extends Entity<K>> implements Converter<T>
{
    @Inject
    protected CommonService commonService;
    
    @Override
    public String getAsString( @SuppressWarnings( "unused" ) FacesContext context, @SuppressWarnings( "unused" ) UIComponent component, T value )
    {
        if ( value == null )
        {
            return "";
        }
        
        K pk = value.getPk();
        
        return pk != null ? pk.toString() : null;
    }
}
