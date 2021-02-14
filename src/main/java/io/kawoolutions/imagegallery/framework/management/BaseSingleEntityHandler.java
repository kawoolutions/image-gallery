package io.kawoolutions.imagegallery.framework.management;

import io.kawoolutions.imagegallery.framework.cdi.BaseHandler;
import io.kawoolutions.imagegallery.framework.entity.Entity;
import io.kawoolutions.imagegallery.framework.exception.RetrieveException;

/**
 * An instance of this class maintains a single entity.
 *
 * @param <T>
 */
public abstract class BaseSingleEntityHandler<K, T extends Entity<K>> extends BaseHandler implements SingleEntityHandler<K, T>
{
    private static final long serialVersionUID = 1L;
    
    protected T entity;

    @Override
    public T getEntity()
    {
        return entity;
    }
    
    @Override
    public void setEntity( T entity )
    {
        this.entity = entity;
    }
    
    @Override
    public void loadEntity()
    {
        try
        {
            T entity = findEntity();

            // null acceptable... only for testing
//            if ( entity == null )
//            {
//                throw new RetrieveException( getClass().getSimpleName() + ": Loading single entity resulted in null!" );
//            }
            
            setEntity( entity );
        }
        catch ( Exception e )
        {
            log.error( getClass().getSimpleName() + ".findEntity() threw " + e.getClass().getSimpleName(), e );
        }
    }

    protected abstract T findEntity() throws RetrieveException;
}