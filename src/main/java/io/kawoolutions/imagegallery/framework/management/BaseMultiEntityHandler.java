package io.kawoolutions.imagegallery.framework.management;

import java.util.List;

import io.kawoolutions.imagegallery.framework.cdi.BaseHandler;
import io.kawoolutions.imagegallery.framework.entity.Entity;
import io.kawoolutions.imagegallery.framework.exception.RetrieveException;

/**
 * An instance of this class maintains multiple entities.
 *
 * @param <T>
 */
public abstract class BaseMultiEntityHandler<K, T extends Entity<K>> extends BaseHandler implements MultiEntityHandler<K, T>
{
    private static final long serialVersionUID = 1L;
    
    protected List<T> entities;

    @Override
    public List<T> getEntities()
    {
        return entities;
    }
    
    @Override
    public void setEntities( List<T> entities )
    {
        this.entities = entities;
    }
    
    @Override
    public void loadEntities()
    {
        try
        {
            List<T> entities = findEntities();
            
            if ( entities == null )
            {
                throw new RetrieveException( getClass().getSimpleName() + ": Loading multiple entities resulted in null!" );
            }
            
            setEntities( entities );
        }
        catch ( Exception e )
        {
            log.error( getClass().getSimpleName() + ".findEntities() threw " + e.getClass().getSimpleName(), e );
        }
    }

    protected abstract List<T> findEntities() throws RetrieveException;
}