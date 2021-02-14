package io.kawoolutions.imagegallery.framework.management;

import java.io.Serializable;
import java.util.List;

import io.kawoolutions.imagegallery.framework.Clearable;
import io.kawoolutions.imagegallery.framework.Resettable;
import io.kawoolutions.imagegallery.framework.entity.Entity;
import io.kawoolutions.imagegallery.framework.entity.EntityUtils;

public interface MultiEntityHandler<K, T extends Entity<K>> extends Resettable, Clearable, Serializable
{
    /**
     * Reset everything to initial state.
     */
    @Override
    default public void reset()
    {
        clearEntities();
        clear();
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
    
    public void loadEntities();
    
    default public List<K> getPks()
    {
        return getEntities() != null ? EntityUtils.getPksFor( getEntities() ) : null;
    }
    
    public abstract List<K> newPks();
}
