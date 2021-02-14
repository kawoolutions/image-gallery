package io.kawoolutions.imagegallery.framework.converter;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.kawoolutions.imagegallery.framework.entity.Entity;
import io.kawoolutions.imagegallery.framework.entity.IdEntity;
import io.kawoolutions.imagegallery.framework.service.CommonRepository;

@Named
@ViewScoped
public class IdEntityConverter implements Converter, Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private CommonRepository commonRepository;
    
    @Override
    public String getAsString( @SuppressWarnings( "unused" ) FacesContext context, @SuppressWarnings( "unused" ) UIComponent component, Object value )
    {
        if ( value == null )
        {
            return "";
        }
        
        if ( value instanceof Entity )
        {
            Integer pk = ( ( IdEntity ) value ).getId();
            return pk != null ? pk.toString() : null;
        }
        else
        {
            throw new ConverterException( new FacesMessage( value + " is not a valid Entity" ) );
        }
    }
    
    @Override
    @SuppressWarnings( { "unchecked" } )
    public Object getAsObject( FacesContext facesContext, UIComponent component, String value )
    {
        if ( value == null || value.isEmpty() )
        {
            return null;
        }
        
        try
        {
            Class<?> type = component.getValueExpression( "value" ).getType( facesContext.getELContext() );
            
            // the call to Integer.valueOf(value) is the problem why an even more general, composite key converter isn't
            // possible!
            return commonRepository.findByPk( ( Class<IdEntity> ) type, null, Integer.valueOf( value ) );
        }
        catch ( Exception e )
        {
            throw new ConverterException( new FacesMessage( value + " is not a valid PK of Entity" ), e );
        }
    }
}
