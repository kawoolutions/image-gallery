package io.kawoolutions.imagegallery.framework.management;

import java.util.List;

public interface SelectionHandler<T> extends Provider<T>
{
    public List<T> getSelectedEntities();
    public void setSelectedEntities( List<T> selectedEntities );
    
    default public void clearSelectedEntities()
    {
        setSelectedEntities( null );
    }
    
    default public void clearSelection()
    {
        clearSelectedEntities();
    }
    
    default public boolean isSelected()
    {
        return getSelectedEntities() != null && !getSelectedEntities().isEmpty();
    }
    
    default public boolean isSingleSelected()
    {
        return isSelected() && getSelectedEntities().size() == 1;
    }

    default public boolean isMultiSelected()
    {
        return isSelected() && getSelectedEntities().size() > 1;
    }
    
    // TODO: the implementation of getSelectedEntity() and setSelectedEntity() should be put here,
    // however this will cause a warning in Eclipse '"property xyz" cannot be resolved' in all XHTMLs for
    // expressions like #{benutzerManager.selectedEntity.passwort}
    
//    /**
//     * Convenience method, so that all managers can potentially have multi-selection.
//     */
//    default public T getSelectedEntity()
//    {
//        return getSelectedEntities() != null && !getSelectedEntities().isEmpty() ? getSelectedEntities().get( 0 ) : null;
//    }
//
//    /**
//     * Convenience method, so that all managers can potentially have multi-selection.
//     */
//    default public void setSelectedEntity( T selectedEntity )
//    {
//        if ( selectedEntity == null )
//        {
//            clearSelectedEntities();
//            return;
//        }
//
//        if ( getSelectedEntities() == null )
//        {
//            setSelectedEntities( new ArrayList<>() );
//        }
//
//        if ( getSelectedEntities().isEmpty() )
//        {
//            getSelectedEntities().add( selectedEntity );
//        }
//        else
//        {
//            getSelectedEntities().set( 0, selectedEntity );
//        }
//    }
    
    public abstract void select();
    public abstract void unselect();
}
