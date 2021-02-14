package io.kawoolutions.imagegallery.util;

import java.text.SimpleDateFormat;

public class IsoDateFormat extends SimpleDateFormat
{
    private static final long serialVersionUID = 1L;
    
    public IsoDateFormat()
    {
        this( true );
    }
    
    public IsoDateFormat( boolean useTime )
    {
        this( useTime, false, false, false );
    }
    
    public IsoDateFormat( boolean useTime, boolean useSec, boolean useMsec, boolean useTimeZone )
    {
        super( "yyyy-MM-dd" + ( useTime ? " HH:mm" + ( useSec ? ":ss" : "" ) + ( useMsec ? ".SSS" : "" ) + ( useTimeZone ? " Z" : "" ) : "" ) );
    }
}
