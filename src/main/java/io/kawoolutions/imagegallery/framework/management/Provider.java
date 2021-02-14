package io.kawoolutions.imagegallery.framework.management;

import java.io.Serializable;
import java.util.List;

import io.kawoolutions.imagegallery.framework.Clearable;
import io.kawoolutions.imagegallery.framework.Resettable;

public interface Provider<T> extends Resettable, Clearable, Serializable
{
    /**
     * Reset everything to initial state.
     */
    @Override
    default public void reset()
    {
        // shallow clear (as implemented by subclasses)
        clear();
        
        // clear list
        clearEntities();
    }
    
    @Override
    default public void clear()
    {
        // does nothing, use reset to clear list
    }
    
    public List<T> getEntities();
    public void setEntities( List<T> entities );
    
    default public void clearEntities()
    {
        setEntities( null );
    }
    
    default public void addEntities( List<T> entities )
    {
        for ( T entity : entities )
        {
            addEntity( entity );
        }
    }
    
    default public void addEntity( T entity )
    {
        getEntities().add( entity );
    }
    
    default public void removeEntities( List<T> entities )
    {
        for ( T entity : entities )
        {
            removeEntity( entity );
        }
    }
    
    default public void removeEntity( T entity )
    {
        getEntities().remove( entity );
    }
}
