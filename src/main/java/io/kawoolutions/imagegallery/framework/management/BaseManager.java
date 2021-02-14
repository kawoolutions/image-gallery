package io.kawoolutions.imagegallery.framework.management;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import io.kawoolutions.imagegallery.framework.cdi.Select;
import io.kawoolutions.imagegallery.framework.cdi.Unselect;
import io.kawoolutions.imagegallery.framework.entity.Entity;

public abstract class BaseManager<K, T extends Entity<K>> extends BaseSelectionHandler<T> implements Manager<K, T>
{
    private static final long serialVersionUID = 1L;
    
    protected EMode mode;
    
    // CDI event producers
    
    @Inject
    @Select
    protected Event<T> selectEvent;
    
    @Inject
    @Unselect
    protected Event<T> unselectEvent;

    protected EDialogMode dialogMode;

    private boolean readOnly;

    @Override
    @PostConstruct
    public void init()
    {
//        log.info( BaseManager.class.getSimpleName() + ": @PostConstruct on " + getClass().getSimpleName() + ", generic class = " + getGenericClass() );
    }
    
    @Override
    public EMode getMode()
    {
        return mode;
    }
    
    @Override
    public void setMode( EMode mode )
    {
        this.mode = mode;
    }
    
    @Override
    public boolean isReadOnly()
    {
        return readOnly;
    }
    
    @Override
    public void setReadOnly( boolean readOnly )
    {
        this.readOnly = readOnly;
    }
    
    /**
     * Requires a call to setSelectedEntity() or setSelectedEntities() prior to being called.
     */
    @Override
    public void view()
    {
        setMode( EMode.VIEW );
        setDialogMode( EDialogMode.HIDDEN );
        
        notifySelectObservers();
    }
    
    /**
     * Requires a call to setSelectedEntity() or setSelectedEntities() prior to being called.
     */
    @Override
    public void add()
    {
        newSelection();
        setMode( EMode.ADD );
        
        notifySelectObservers();
    }
    
    /**
     * Requires a call to setSelectedEntity() or setSelectedEntities() prior to being called.
     */
    @Override
    public void edit()
    {
        setMode( EMode.EDIT );
        
        notifySelectObservers();
    }
    
    /**
     * Requires a call to setSelectedEntity() or setSelectedEntities() prior to being called.
     */
    @Override
    public void remove()
    {
        setMode( EMode.REMOVE );
        setDialogMode( EDialogMode.DETAILS );
        
        notifySelectObservers();
    }

    /**
     * Override default method as patch for:
     * 
     * https://issues.jboss.org/browse/WELD-2407
     */
    @Override
    public void clear()
    {
        clearMode();
        setDialogMode( EDialogMode.HIDDEN );
        
        clearSelection();
    }
    
    @Override
    public void cancel()
    {
//        log.info( "cancel(): " + getMode() );
        
        if ( isViewMode() || isAddMode() /*|| isRemoveMode()*/ )
        {
            // cancel VIEW or ADD: clear selection + mode
            
//            System.out.println( "clearMode() + unselect" );
            
            clear();
            notifyUnselectObservers();
        }
        else
        {
            // cancel EDIT or REMOVE: just go into view mode
//            System.out.println( "view() + keep selection" );
            
            view();
        }
    }

    public void close()
    {
//        log.info( "close(): " + getMode() );
        
        if ( isCleared() )
        {
            // deleting already through: no selection + no mode -> do nothing
//            log.info( "OK / ENTER" );
            return;
        }
        
        if ( isViewing() )
        {
            // dialog canceled: selection + view mode -> do nothing
//            log.info( "CANCEL" );
        }
        else
        {
            // dialog close via X: selection + some mode -> set to view
//            log.info( "X / ESC" );
            view();
        }
    }
    
    @Override
    public void select()
    {
        if ( isSelected() )
        {
            view();
        }
        
        super.select();
    }
    
    @Override
    public void unselect()
    {
        if ( !isSelected() )
        {
            clearMode();
        }
        
        super.unselect();
    }
    
    public void selectCheckbox()
    {
        if ( isSelected() )
        {
            view();
        }
        
//        addInfoMessage( "selectCheckbox()!" );
    }
    
    public void unselectCheckbox()
    {
        if ( !isSelected() )
        {
            clearMode();
        }
        
//        addInfoMessage( "unselectCheckbox()!" );
    }
    
    public void toggleAllCheckboxes()
    {
        if ( isSelected() )
        {
            view();
        }
        else
        {
            clearMode();
        }

//        addInfoMessage( "toggleAllCheckboxes()!" );
    }
    
    protected abstract T newEntity();
    
    protected void newSelection()
    {
        // init entity
        T newEntity = newEntity();
        
        setSelectedEntity( newEntity );
    }
    
    protected void notifySelectObservers()
    {
        // fire CDI event to 0-n observers
//        selectEvent.fire( getSelectedEntity() );
    }
    
    protected void notifyUnselectObservers()
    {
        // fire CDI event to 0-n observers (events cannot be null, use new entity instead)
//        unselectEvent.fire( newEntity() );
    }

    public EDialogMode getDialogMode()
    {
        return dialogMode;
    }

    public void setDialogMode( EDialogMode dialogMode )
    {
        this.dialogMode = dialogMode;
    }
}
