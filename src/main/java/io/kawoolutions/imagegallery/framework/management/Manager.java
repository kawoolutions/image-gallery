package io.kawoolutions.imagegallery.framework.management;

import java.util.Arrays;
import java.util.List;

import io.kawoolutions.imagegallery.framework.entity.Entity;
import io.kawoolutions.imagegallery.framework.entity.EntityUtils;

public interface Manager<K, T extends Entity<K>> extends SelectionHandler<T>
{
    default public boolean isRootManager()
    {
        return BaseCrudManager.class.isAssignableFrom( getClass() );
    }
    
    default public boolean isSubManager()
    {
        return !isRootManager();
    }
    
    public EMode getMode();
    public void setMode( EMode mode );
    
    public static List<EMode> panelShownModes = Arrays.asList( EMode.ADD, EMode.VIEW, EMode.EDIT, EMode.REMOVE );
    
    default public boolean isNullMode()
    {
        return getMode() == null;
    }
    
    default public boolean isViewMode()
    {
        return getMode() == EMode.VIEW;
    }
    
    default public boolean isAddMode()
    {
        return getMode() == EMode.ADD;
    }
    
    default public boolean isEditMode()
    {
        return getMode() == EMode.EDIT;
    }
    
    default public boolean isRemoveMode()
    {
        return getMode() == EMode.REMOVE;
    }
    
    /**
     * Same as "in write mode".
     * 
     * @return
     */
    default public boolean isWriteMode()
    {
//        return isAddMode() || isEditMode() || isRemoveMode();
        return isAddMode() || isEditMode();
    }
    
//    /**
//     * Same as "in C(R)UD mode".
//     *
//     * @return
//     */
//    default public boolean isCudMode()
//    {
//        return isAddMode() || isEditMode() || isRemoveMode();
//    }
    
    /**
     * Abkürzung für clearSelection() plus clearMode() ohne Events zu feuern.
     */
    @Override
    default public void clear()
    {
        clearMode();
        clearSelection();
    }
    
    default public void clearMode()
    {
        setMode( null );
    }

    // actions *which fire events*
    
    public void view();
    public void add();
    public void edit();
    public void remove();
    public void cancel();
    
    // selection + mode checks
    
    default public boolean isCleared()
    {
        return !isSelected() && isNullMode();
    }
    
    default public boolean isViewing()
    {
        return isSelected() && isViewMode();
    }
    
    default public boolean isAdding()
    {
        return isSelected() && isAddMode();
    }
    
    default public boolean isEditing()
    {
        return isSelected() && isEditMode();
    }
    
    default public boolean isRemoving()
    {
        return isSelected() && isRemoveMode();
    }
    
    /**
     * Same as "is writing": selection + ADD or EDIT mode
     * 
     * @return
     */
    default public boolean isWriting()
    {
        return isSelected() && isWriteMode();
    }
    
    /**
     * Same as "in panel mode": selection + non-null mode
     * 
     * @return
     */
    default public boolean isPanelShown()
    {
        return isSingleSelected() && panelShownModes.contains( getMode() );
    }
        
    // ADD, EDIT, REMOVE actions

    default public void addSelection()
    {
        addEntities( getSelectedEntities() );
    
        view();
    }
    
    default public void saveSelection()
    {
        if ( isAdding() )
        {
            // append selection to list
            addSelection();
        }
        else
        {
            // put selection into list (refresh)
            replaceSelection();
        }
        
        view();
    }
    
    default public void replaceSelection()
    {
        // refresh list of entities with the selected ones
        refreshEntitiesWith( getSelectedEntities() );
    }
    
    default public void refreshEntitiesWith( List<T> entities )
    {
        // replace current entities with passed ones
        EntityUtils.replaceEntitiesWith( getEntities(), entities );
    }
    
    default public void refreshEntity( T entity )
    {
        refreshEntitiesWith( Arrays.asList( entity ) );
    }
    
    default public void killSelection()
    {
        killEntities( getSelectedEntities() );
    }
    
    default public void killEntities( List<T> entities )
    {
        entities.forEach( e -> e.setDeadEntity() );
    }
    
    default public void restoreSelection()
    {
        restoreEntities( getSelectedEntities() );
    }
    
    default public void restoreEntities( List<T> entities )
    {
        entities.forEach( e -> e.setLiveEntity() );
    }
    
    default public void removeSelection()
    {
        // remove entities from list
        removeEntities( getSelectedEntities() );
        
        // then clear selection
//        clearSelection();
        
        // clear selection and mode
        clear();
    }
    
    public boolean isReadOnly();
    public void setReadOnly( boolean readOnly );
}
