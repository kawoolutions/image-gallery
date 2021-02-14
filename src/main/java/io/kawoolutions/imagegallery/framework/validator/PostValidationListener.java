package io.kawoolutions.imagegallery.framework.validator;

import javax.faces.component.UIInput;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

/**
 * Bei Bedarf so in der faces-config.xml zu registrieren: <system-event-listener>
 * <source-class>javax.faces.component.html.HtmlInputText</source-class>
 * <system-event-class>javax.faces.event.PostValidateEvent</system-event-class>
 * <system-event-listener-class>de.fisp.cld.helaba.validator.PostValidationListener</system-event-listener-class>
 * </system-event-listener>
 */
public class PostValidationListener implements SystemEventListener
{
    @Override
    public boolean isListenerForSource( @SuppressWarnings( "unused" ) Object source )
    {
        return true;
    }
    
    @Override
    public void processEvent( SystemEvent event ) throws AbortProcessingException
    {
        UIInput source = ( UIInput ) event.getSource();
        
        if ( !source.isValid() )
        {
            // Validierungsfehler: Orignal styleClass merken
            String os = ( String ) source.getAttributes().get( "styleClass" );
            source.getAttributes().put( "ORIGINAL_STYLE_SET", os );
            source.getAttributes().put( "styleClass", os + " input-invalid" );
        }
        else
        {
            String os = ( String ) source.getAttributes().get( "ORIGINAL_STYLE_SET" );
            if ( os != null )
            {
                // Wenn bereits ein Validierungsfehler eintrat, so müssen jetzt die original styleClass wieder
                // zurückgesetzt werden
                source.getAttributes().put( "styleClass", os );
            }
        }
    }
}
