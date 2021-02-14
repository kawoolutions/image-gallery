package io.kawoolutions.imagegallery.framework.management;

import io.kawoolutions.imagegallery.framework.entity.Entity;

/**
 * An instance of this class manages a single entity.
 *
 * @param <T>
 */
public abstract class BaseSingleEntityManager<K, T extends Entity<K>> extends BaseCrudManager<K, T> implements SingleEntityHandler<K, T>
{
    private static final long serialVersionUID = 1L;

    @Override
    public void reset()
    {
        // don't clear selection
        clear();
    }

    @Override
    public void clear()
    {
        // clear mode, keep selection
        clearMode();
    }

    @Override
    public T getEntity()
    {
        return getSelectedEntity();
    }

    @Override
    public void setEntity( T entity )
    {
        setSelectedEntity( entity );
    }
}