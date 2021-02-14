package io.kawoolutions.imagegallery.framework.management;

import javax.annotation.PostConstruct;

/**
 * Base class for beans that have a list of entities and a selection (single or multiple instances).
 * Extends BaseSelectionHandler by the notion of selection required and a default entity. Main use
 * of this class is for dropdown boxes of entities.
 * 
 * TODO: add converter method?
 */
public abstract class BaseSelector<T> extends BaseSelectionHandler<T>
{
    private static final long serialVersionUID = 1L;
    
    // property to signal that the selection must not be null/empty/undefined
    private boolean selectionRequired;
    
    private T defaultEntity;
    
    @Override
    @PostConstruct
    public void init()
    {
        // never call super.init() explicitly!
//        super.init();
        
        // allow null selections by default
        setSelectionRequired( false );
    }
    
    @Override
    public T getSelectedEntity()
    {
        // if not selected, but selection required, take default entity
        if ( !isSelected() && isSelectionRequired() )
        {
            super.setSelectedEntity( getDefaultEntity() );
        }
        
        return super.getSelectedEntity();
    }
    
    public boolean isSelectionRequired()
    {
        return selectionRequired;
    }
    
    public void setSelectionRequired( boolean selectionRequired )
    {
        this.selectionRequired = selectionRequired;
    }

    public T getDefaultEntity()
    {
        if ( this.defaultEntity == null )
        {
            T defaultEntity = loadDefaultEntity();
            
            if ( isSelectionRequired() && defaultEntity == null )
            {
                throw new IllegalStateException( "Default entity must not be null if a selection is required!" );
            }
            
            this.defaultEntity = defaultEntity;
        }
        
        return defaultEntity;
    }
    
    /**
     * Loads the default entity.
     * 
     * @return
     */
    protected abstract T loadDefaultEntity();
}
