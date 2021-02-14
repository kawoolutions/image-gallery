package io.kawoolutions.imagegallery.framework.management;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

/**
 * Base class for beans that have a list of entities and a selection (single or multiple instances),
 * but no mode.
 */
public abstract class BaseSelectionHandler<T> extends BaseProvider<T> implements SelectionHandler<T>
{
    private static final long serialVersionUID = 1L;
    
    protected List<T> selectedEntities;
    
    @Override
    @PostConstruct
    public void init()
    {
        // do nothing
    }
    
    @Override
    public void reset()
    {
        clearSelection();
        
        super.reset();
    }
    
    @Override
    public void clear()
    {
        clearSelection();
    }
    
    @Override
    public List<T> getSelectedEntities()
    {
        return selectedEntities;
    }
    
    @Override
    public void setSelectedEntities( List<T> selectedEntities )
    {
        this.selectedEntities = selectedEntities;
    }
    
    /**
     * Convenience method, so that all managers can potentially have multi-selection.
     */
    public T getSelectedEntity()
    {
        return selectedEntities != null && !selectedEntities.isEmpty() ? selectedEntities.get( 0 ) : null;
    }
    
    /**
     * Convenience method, so that all managers can potentially have multi-selection.
     */
    public void setSelectedEntity( T selectedEntity )
    {
//        log.info( BaseManager.class.getSimpleName() + ": " + getClass().getSimpleName() + ".setSelectedEntity(" + Objects.toString( selectedEntity, ProjectGlobals.NULL_STRING ) + ")!" );
        
        if ( selectedEntity == null )
        {
            clearSelectedEntities();
            return;
        }
        
        if ( selectedEntities == null )
        {
            setSelectedEntities( new ArrayList<>() );
        }
        
        if ( selectedEntities.isEmpty() )
        {
            selectedEntities.add( selectedEntity );
        }
        else
        {
            selectedEntities.set( 0, selectedEntity );
        }
    }
    
    @Override
    public void select()
    {
//        addInfoMessage( "select()!" );
    }
    
    @Override
    public void unselect()
    {
//        addInfoMessage( "unselect()!" );
    }
}
