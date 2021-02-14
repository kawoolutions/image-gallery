package io.kawoolutions.imagegallery.framework.management;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.primefaces.PrimeFaces;

import io.kawoolutions.imagegallery.framework.entity.Entity;
import io.kawoolutions.imagegallery.framework.entity.EntityUtils;
import io.kawoolutions.imagegallery.framework.exception.CreateException;
import io.kawoolutions.imagegallery.framework.exception.DeleteException;
import io.kawoolutions.imagegallery.framework.exception.RetrieveException;
import io.kawoolutions.imagegallery.framework.service.EntityService;

public abstract class BaseCrudManager<K, T extends Entity<K>> extends BaseManager<K, T> implements CrudManager<K, T>
{
    private static final long serialVersionUID = 1L;
    
    @Override
    @PostConstruct
    public void init()
    {
//        log.info( BaseCrudManager.class.getSimpleName() + ": @PostConstruct on " + getClass().getSimpleName() + ", generic class = " + getGenericClass() );
    }
    
    /**
     * Derive the msg key prefixes used for the UI from the entity class.
     */
    @Override
    protected Class<?> getGenericClass()
    {
        return getGenericClassFor( 1 );
    }
    
    protected abstract EntityService<K, T> getEntityService();
    
    @Override
    public void newSelection()
    {
        setSelectedEntities( newEntities( 1 ) );
    }
    
    protected List<T> newEntities( int size )
    {
        List<T> newEntities = new ArrayList<>();
        
        for ( int i = 0; i < size; i++ )
        {
            T newEntity = newEntity();
            
            // by default, all entities are set to not being new: set them to new here
            newEntity.setNewEntity();
            
            newEntities.add( newEntity );
        }
        
        return newEntities;
    }
    
    // persist operations (using CRUD names)
    
    @Override
//    @Transactional( TxType.REQUIRES_NEW )
    public void persistSelection()
    {
//        log.info( BaseCrudManager.class.getSimpleName() + ".persistSelection()! (" + getClass().getSimpleName() + ")" );
        
        T selectedEntity = getSelectedEntity();
        
        boolean newEntity = selectedEntity.isNewEntity();
        
        // persist: creates or update the entity
        if ( newEntity )
        {
            // handle exceptions there
            createSelection();
        }
        else
        {
            // handle exceptions there
            updateSelection();
        }
    }

    @Override
//    @Transactional( TxType.REQUIRES_NEW )
    public void createSelection()
    {
        T selectedEntity = getSelectedEntity();
        
        T persistedEntity = null;
        
        try
        {
            persistedEntity = getEntityService().create( selectedEntity );
        }
        catch ( CreateException e )
        {
            return;
        }
        
//        log.info( BaseCrudManager.class.getSimpleName() + ".createSelection( " ++ " ): ENTITY before create: " + selectedEntity + ", ENT after create: " + persistedEntity );
        
//        entity.setSequentialNumber( selectedEntity.getSequentialNumber() );
        addEntity( persistedEntity );
        
        // refresh selected entities!
        setSelectedEntity( persistedEntity );
        
        // reload *with* entity graph
        reloadSelection();
        
        // refresh list with reloaded selected entities
        replaceSelection();
        
        PrimeFaces.current().ajax().addCallbackParam( "created", Boolean.TRUE );
        
        // view selected entity!
        view();
        
        notifySelectObservers();
    }
    
    @Override
//    @Transactional( TxType.REQUIRES_NEW )
    public void updateSelection()
    {
        T selectedEntity = getSelectedEntity();
        
        T managedEntity = null;
                
        try
        {
            managedEntity = getEntityService().update( selectedEntity );
        }
        catch ( Exception e )
        {
            return;
        }
        
//        log.info( BaseCrudManager.class.getSimpleName() + ".updateSelection(): ENTITY before persist: " + selectedEntity + ", ENT after update: " + managedEntity );
        
        // refresh selected entities!
        setSelectedEntity( managedEntity );
        
        // reload *with* entity graph
        reloadSelection();
        
        // refresh list with reloaded selected entities
        replaceSelection();

        PrimeFaces.current().ajax().addCallbackParam( "updated", Boolean.TRUE );

        // view selected entity!
        view();
        
        notifySelectObservers();
    }
    
    @Override
//    @Transactional( TxType.REQUIRES_NEW )
    public void deleteSelection()
    {
        List<T> selectedEntities = getSelectedEntities();
        
        try
        {
            // delete entities
            getEntityService().delete( selectedEntities );
            
            removeSelection();
        }
        catch ( DeleteException e )
        {
            String ids = String.join( ", ", selectedEntities.stream().map( t -> t.getPk().toString() ).collect( Collectors.toList() ) );
            log.error( BaseCrudManager.class.getSimpleName() + ".deleteSelection(): Could not delete entities " + ids, e );
            
            return;
        }
        
        PrimeFaces.current().ajax().addCallbackParam( "deleted", Boolean.TRUE );
        
        // clear selection and mode
//        clear();
        
        notifyUnselectObservers();
    }
    
    @Override
    @Transactional( TxType.SUPPORTS )
    public void reloadSelection()
    {
        List<T> reloadedSelectedEntities = reloadSelectedEntities();
        List<T> entities = getEntities();

        setSelectedEntities( reloadedSelectedEntities );

        // recalculate sequential numbers
        EntityUtils.replaceEntitiesWith( entities, reloadedSelectedEntities );
    }

    protected List<T> reloadSelectedEntities()
    {
        return reloadEntities( getSelectedEntities() );
    }

    protected List<T> reloadEntities( List<T> entities )
    {
        List<T> reloadedEntities = null;

        try
        {
            List<K> pks = EntityUtils.getPksFor( entities );
            reloadedEntities = reloadEntitiesByPks( pks );
        }
        catch ( RetrieveException e )
        {
            log.error( BaseCrudManager.class.getSimpleName() + ": Failed to reload entities!", e );
        }

        return reloadedEntities;
    }
    
    protected List<T> reloadEntitiesByPks( List<K> pks )
    {
        // by default, use no entity graph
        return getEntityService().findByPks( getDefaultGraphName(), pks );
    }
    
    protected abstract String getDefaultGraphName();
}
