package io.kawoolutions.imagegallery.framework.service;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import io.kawoolutions.imagegallery.framework.entity.Entity;
import io.kawoolutions.imagegallery.framework.exception.CreateException;
import io.kawoolutions.imagegallery.framework.exception.DeleteException;
import io.kawoolutions.imagegallery.framework.exception.PersistException;
import io.kawoolutions.imagegallery.framework.exception.RetrieveException;
import io.kawoolutions.imagegallery.framework.exception.UpdateException;

@Local
public interface EntityService<K, T extends Entity<K>>
{
    public EntityManager getEntityManager();
    
    default public T persist( T entity ) throws CreateException, UpdateException
    {
        return persist( entity, true );
    }
    
    public T persist( T entity, boolean joinEntities ) throws CreateException, UpdateException;
    
    default public List<T> persist( List<T> entities ) throws CreateException, UpdateException
    {
        return persist( entities, true );
    }
    
    public List<T> persist( List<T> entities, boolean joinEntities ) throws CreateException, UpdateException;
    
    /**
     * Actual method to create an entity (with refresh).
     * 
     * @param entity
     * @return
     */
    public T create( T entity ) throws CreateException;
    
    default public T update( T entity ) throws UpdateException
    {
        return update( entity, true );
    }
    
    /**
     * Actual method to update an entity.
     * 
     * @param entity
     * @param joinEntities
     * @return
     */
    public T update( T entity, boolean joinEntities ) throws UpdateException;
        
    default public void delete( T entity ) throws DeleteException
    {
        delete( entity, true );
    }
    
    /**
     * Actual method to delete an entity.
     * 
     * @param entity
     * 
     * @return
     * @throws PersistException
     */
    public void delete( T entity, boolean joinEntities ) throws DeleteException;
    
    default public void delete( List<T> entities ) throws DeleteException
    {
        delete( entities, true );
    }
    
    /**
     * Actual method to delete multiple entities.
     * 
     * @param entity
     * 
     * @return
     * @throws PersistException
     */
    public void delete( List<T> entities, boolean joinEntities ) throws DeleteException;
    
    /**
     * Finds a single entity by PK.
     * 
     * @param pk
     * @return
     */
    public T findByPk( K pk ) throws RetrieveException;
    
    /**
     * Finds a single entity by PK.
     * 
     * @param pk
     * @return
     */
    public T findByPk( String graphName, K pk ) throws RetrieveException;
//    default public T findByPk( K pk ) throws RetrieveException
//    {
//        @SuppressWarnings( "unchecked" )
//        List<T> entities = findByPks( pk );
//
//        Objects.requireNonNull( entities, "Entities is null!" );
//
//        if ( entities.isEmpty() )
//        {
//            throw new IllegalStateException( "Entities is empty!" );
//        }
//
//        return entities.get( 0 );
//    }
    
    /**
     * Finds multiple entities by PK, e.g. for reloading multiple entities.
     * 
     * @param pks
     * @return
     */
    public List<T> findByPks( @SuppressWarnings( "unchecked" ) K... pks ) throws RetrieveException;
    
    /**
     * Finds multiple entities by PK, e.g. for reloading multiple entities.
     * 
     * @param pks
     * @return
     */
    public List<T> findByPks( String graphName, @SuppressWarnings( "unchecked" ) K... pks ) throws RetrieveException;
    
    /**
     * Finds multiple entities by PK, e.g. for reloading multiple entities.
     * 
     * @param pks
     * @return
     * @throws RetrieveException
     */
    public List<T> findByPks( List<K> pks ) throws RetrieveException;
    
    /**
     * Finds multiple entities by PK, e.g. for reloading multiple entities.
     * 
     * @param pks
     * @return
     * @throws RetrieveException
     */
    public List<T> findByPks( String graphName, List<K> pks ) throws RetrieveException;
    
    /**
     * Finds all entities.
     * 
     * @return
     */
    public List<T> findAll() throws RetrieveException;
    
    /**
     * Finds all entities with an optional graph name.
     * 
     * @return
     */
    public List<T> findAllWithFetchGraph( String graphName ) throws RetrieveException;
}
