package io.kawoolutions.imagegallery.framework.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterCollectionBuilder
{
    private Map<String,Object> parameterMap  = null;
    private List<Object> parameterList = null;
    
    public static ParameterCollectionBuilder with( String name, Object value )
    {
        // calls *Map* constructor
        return new ParameterCollectionBuilder( name, value );
    }
    
    public static ParameterCollectionBuilder with( Object value )
    {
        // calls *List* constructor
        return new ParameterCollectionBuilder( value );
    }
    
    private ParameterCollectionBuilder( String name, Object value )
    {
        parameterMap = new HashMap<>();
        parameterMap.put( name, value );
    }
    
    private ParameterCollectionBuilder( Object value )
    {
        parameterList = new ArrayList<>();
        parameterList.add( value );
    }
    
    public ParameterCollectionBuilder and( String name, Object value )
    {
        if ( name == null )
        {
            throw new IllegalArgumentException( "Parameter map builder: parameter name cannot be null!" );
        }
        
        if ( value == null )
        {
            throw new IllegalArgumentException( "Parameter map builder: parameter value cannot be null!" );
        }
        
        parameterMap.put( name, value );
        
        return this;
    }
    
    public ParameterCollectionBuilder and( Object value )
    {
        if ( value == null )
        {
            throw new IllegalArgumentException( "Parameter list builder: parameter value cannot be null!" );
        }
        
        parameterList.add( value );
        
        return this;
    }
    
    public Map<String,Object> map()
    {
        return parameterMap;
    }
    
    public List<Object> list()
    {
        return parameterList;
    }
}
