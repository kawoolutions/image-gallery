package io.kawoolutions.imagegallery.framework.management;

import java.io.Serializable;

import io.kawoolutions.imagegallery.framework.Clearable;
import io.kawoolutions.imagegallery.framework.Resettable;
import io.kawoolutions.imagegallery.framework.entity.Entity;

public interface SingleEntityHandler<K, T extends Entity<K>> extends Resettable, Clearable, Serializable
{
    /**
     * Reset everything to initial state.
     */
    @Override
    default public void reset()
    {
        clearEntity();
        clear();
    }
    
    @Override
    default public void clear()
    {
        // does nothing, use reset to clear list
    }
    
    public T getEntity();
    public void setEntity( T entity );
    
    default public void clearEntity()
    {
        setEntity( null );
    }
    
    public void loadEntity();
    
    default public K getPk()
    {
        return getEntity() != null ? getEntity().getPk() : null;
    }
    
    public K newPk();
}
