package io.kawoolutions.imagegallery.framework.service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import io.kawoolutions.imagegallery.framework.entity.Entity;
import io.kawoolutions.imagegallery.framework.exception.CreateException;
import io.kawoolutions.imagegallery.framework.exception.DeleteException;
import io.kawoolutions.imagegallery.framework.exception.EntityNotFoundException;
import io.kawoolutions.imagegallery.framework.exception.EntityQueryException;
import io.kawoolutions.imagegallery.framework.exception.RetrieveException;
import io.kawoolutions.imagegallery.framework.exception.UpdateException;

/**
 * Adds two generic types to the general service interface, the ability to access the generic type etc.
 * 
 * The find*() methods in this class generally determine the class automatically (see getEntityClass()).
 * 
 * @param <K> - primary key type
 * @param <T> - entity type
 */
@Transactional( TxType.REQUIRED )
public abstract class BaseEntityService<K, T extends Entity<K>> extends BaseService implements EntityService<K, T>
{
    // TODO
//    @Inject
//    protected SessionData sessionData;
    
    protected Class<K> pkClass;
    protected Class<T> entityClass;
    
    @SuppressWarnings( "unchecked" )
    @Transactional( TxType.SUPPORTS )
    protected Class<K> getPkClass()
    {
        if ( pkClass == null )
        {
            // read PK class from first class of generics
            pkClass = ( Class<K> ) ( ( ParameterizedType ) getClass().getGenericSuperclass() ).getActualTypeArguments()[0];
        }
        
        return pkClass;
    }
    
    @SuppressWarnings( "unchecked" )
    @Transactional( TxType.SUPPORTS )
    protected Class<T> getEntityClass()
    {
        if ( entityClass == null )
        {
            // read entity class from second class of generics
            entityClass = ( Class<T> ) ( ( ParameterizedType ) getClass().getGenericSuperclass() ).getActualTypeArguments()[1];
        }
        
        return entityClass;
    }
    
    @Override
    @Transactional( TxType.REQUIRED )
    public List<T> persist( List<T> entities, boolean joinEntities ) throws CreateException, UpdateException
    {
//        log.info( BaseEntityService.class.getSimpleName() + ": " + getClass().getSimpleName() + ".persist(multiple)!" );
        
        Objects.requireNonNull( entities, "Entities are null!" );
        
        List<T> managedEntities = new ArrayList<>();
        
        for ( T entity : entities )
        {
            T managedEntity = persist( entity, joinEntities );
            
            managedEntities.add( managedEntity );
        }
        
        return managedEntities;
    }
    
    @Override
    @Transactional( TxType.REQUIRED )
    public T persist( T entity, boolean joinEntities ) throws CreateException, UpdateException
    {
//        log.info( BaseEntityService.class.getSimpleName() + ": " + getClass().getSimpleName() + ".persist(" + entity + ")!" );
        
        T managedEntity = null;
        
        // also see http://stackoverflow.com/q/1069992/396732
        if ( entity.isNewEntity() )
        {
            // always merge when on create
            managedEntity = create( entity );
        }
        else
        {
            managedEntity = update( entity, joinEntities );
        }
        
        return managedEntity;
    }
    
    /*
     * Important methods that merge tightly-coupled, related entities into the entity manager.<br /><br />
     * 
     * It doesn't apply for every entity type, so the default implementation is empty.<br /><br />
     * 
     * Override this if needed. Use case is User, as it always requires a Person + Contact + PostAddress + ...
     * entity to be saved.<br /><br />
     * 
     * @param entity
     * @param mode
     * @throws PersistException
     */
    
    /**
     * @param entity
     * @throws CreateException
     */
    @Transactional( TxType.REQUIRED )
    protected void createJoinedEntitiesBefore( T entity ) throws CreateException
    {
        // do nothing by default
    }
    
    @Override
    @Transactional( TxType.REQUIRED )
    public T create( T entity ) throws CreateException
    {
//        log.info( BaseEntityService.class.getSimpleName() + ": " + getClass().getSimpleName() + ".create(" + entity + ")!" );
        
        Objects.requireNonNull( entity, "Entity to create is null!" );
        
        // always create joined entities before actually creating
        createJoinedEntitiesBefore( entity );
        
//        log.info( BaseEntityService.class.getSimpleName() + ": " + getClass().getSimpleName() + ".create(" + entity + "): PK = " + entity.getPk() + "!" );
        
        T managedEntity = null;
        
        try
        {
            managedEntity = super.create( entity );
        }
        catch ( Exception e )
        {
            throw new CreateException( "Create failed!", e );
        }
        
        createJoinedEntitiesAfter( entity, managedEntity );

        // now set to live after create successful
        managedEntity.setLiveEntity();
        
        return managedEntity;
    }
    
    /**
     * @param entity
     * @param managedEntity
     * @throws CreateException
     */
    @Transactional( TxType.REQUIRED )
    protected void createJoinedEntitiesAfter( T entity, T managedEntity ) throws CreateException
    {
        // do nothing by default
    }
    
    /**
     * @param entity
     * @throws UpdateException
     */
    @Transactional( TxType.REQUIRED )
    protected void updateJoinedEntitiesBefore( T entity ) throws UpdateException
    {
        // do nothing by default
    }
    
    @Override
    @Transactional( TxType.REQUIRED )
    public T update( T entity, boolean joinEntities ) throws UpdateException
    {
//        log.info( BaseEntityService.class.getSimpleName() + ": " + getClass().getSimpleName() + ".update(" + entity + ")!" );
        
        Objects.requireNonNull( entity, "Entity to update is null!" );
        
        if ( joinEntities )
        {
            // update (merge) joined entities before actually updating
            updateJoinedEntitiesBefore( entity );
        }
        
        T managedEntity = null;
        
        try
        {
            managedEntity = super.update( entity );
        }
        catch ( Exception e )
        {
            throw new UpdateException( "Update failed!", e );
        }
        
        if ( joinEntities )
        {
            // update (merge) joined entities before actually updating
            updateJoinedEntitiesAfter( entity, managedEntity );
        }
        
        return managedEntity;
    }
    
    /**
     * @param entity
     * @param managedEntity
     * @throws UpdateException
     */
    @Transactional( TxType.REQUIRED )
    protected void updateJoinedEntitiesAfter( T entity, T managedEntity ) throws UpdateException
    {
        // do nothing by default
    }
    
    @Override
    @Transactional( TxType.REQUIRED )
    public void delete( List<T> entities, boolean joinEntities ) throws DeleteException
    {
//        log.info( BaseEntityService.class.getSimpleName() + ": " + getClass().getSimpleName() + ".delete(multiple)!" );
        
        Objects.requireNonNull( entities, "Entities are null!" );
        
        for ( T entity : entities )
        {
            delete( entity, joinEntities );
        }
    }
    
    /**
     * @param entity
     * @throws DeleteException
     */
    @Transactional( TxType.REQUIRED )
    protected void deleteJoinedEntitiesBefore( T entity ) throws DeleteException
    {
        // do nothing by default
    }
    
    @Override
    @Transactional( TxType.REQUIRED )
    public void delete( T entity, boolean joinEntities ) throws DeleteException
    {
//        log.info( BaseEntityService.class.getSimpleName() + ": " + getClass().getSimpleName() + ".delete(" + entity + ")!" );
        
        Objects.requireNonNull( entity, "Entity to delete is null!" );

        if ( joinEntities )
        {
            deleteJoinedEntitiesBefore( entity );
        }
        
        try
        {
            delete( entity.getClass(), entity.getPk() );
        }
        catch ( Exception e )
        {
            throw new DeleteException( "Delete failed!", e );
        }

        if ( joinEntities )
        {
            deleteJoinedEntitiesAfter( entity );
        }
    }
    
    /**
     * @param entity
     * @param managedEntity
     * @throws DeleteException
     */
    @Transactional( TxType.REQUIRED )
    protected void deleteJoinedEntitiesAfter( T entity ) throws DeleteException
    {
        // do nothing by default
    }
    
    @Override
    @Transactional( TxType.SUPPORTS )
    public T findByPk( K pk ) throws RetrieveException
    {
        return findByPk( null, pk );
    }
    
    @Override
    @Transactional( TxType.SUPPORTS )
    public T findByPk( String graphName, K pk ) throws RetrieveException
    {
        try
        {
            return findByPk( getEntityClass(), graphName, pk );
        }
        catch ( Exception e )
        {
            throw new EntityNotFoundException( "Entity not found!", e );
        }
    }
    
    /**
     * Takes the order of PKs into account by delegating to findByPk(K pk). This may be somewhat slow.
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( TxType.SUPPORTS )
    public List<T> findByPks( K... pks ) throws RetrieveException
    {
        return findByPks( null, pks );
    }
    
    /**
     * Takes the order of PKs into account by delegating to findByPk(K pk). This may be somewhat slow.
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( TxType.SUPPORTS )
    public List<T> findByPks( String graphName, K... pks ) throws RetrieveException
    {
        try
        {
            return findByPks( getEntityClass(), graphName, pks );
        }
        catch ( Exception e )
        {
            throw new EntityNotFoundException( "Entity not found!", e );
        }
    }
    
    /**
     * Takes the order of PKs into account by delegating to findByPk(K pk). This may be somewhat slow.
     * 
     * TODO: improve performance?
     */
    @Override
    @Transactional( TxType.SUPPORTS )
    public List<T> findByPks( List<K> pks ) throws RetrieveException
    {
        return findByPks( null, pks );
    }
    
    /**
     * Takes the order of PKs into account by delegating to findByPk(K pk). This may be somewhat slow.
     * 
     * TODO: improve performance?
     */
    @Override
    @Transactional( TxType.SUPPORTS )
    public List<T> findByPks( String graphName, List<K> pks ) throws RetrieveException
    {
        try
        {
            return findByPks( getEntityClass(), graphName, pks );
        }
        catch ( Exception e )
        {
            throw new EntityNotFoundException( "Entity not found!", e );
        }
    }
    
    @Override
    @Transactional( TxType.SUPPORTS )
    public List<T> findAll() throws RetrieveException
    {
        return findAllWithFetchGraph( null );
    }
    
    @Override
    @Transactional( TxType.SUPPORTS )
    public List<T> findAllWithFetchGraph( String graphName ) throws RetrieveException
    {
        // cannot generate query, but only query name by convention: <entity name> + ".findAll"
        String queryName = getEntityClass().getSimpleName() + ".findAll";
        
        try
        {
            return findByNamedQuery( queryName, graphName );
        }
        catch ( Exception e )
        {
            throw new EntityQueryException( "Entities (all by graph name) not found! Query: " + queryName, e );
        }
    }
    
    // named query entity finders
    
    @Transactional( TxType.SUPPORTS )
    public T findSingleByNamedQuery( String queryName ) throws Exception
    {
        return findSingleByNamedQuery( queryName, ( String ) null, null );
    }
    
    @Transactional( TxType.SUPPORTS )
    public T findSingleByNamedQuery( String queryName, String graphName ) throws Exception
    {
        return findSingleByNamedQuery( queryName, graphName, null );
    }
    
    @Transactional( TxType.SUPPORTS )
    public T findSingleByNamedQuery( String queryName, Map<String, Object> parameterMap ) throws Exception
    {
        return findSingleByNamedQuery( queryName, ( String ) null, parameterMap );
    }
    
    @Transactional( TxType.SUPPORTS )
    public T findSingleByNamedQuery( String queryName, String graphName, Map<String, Object> parameterMap ) throws Exception
    {
        // call super
        return super.findSingleByNamedQuery( queryName, getEntityClass(), graphName != null ? em.getEntityGraph( graphName ) : null, parameterMap );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByNamedQuery( String queryName ) throws Exception
    {
        return findByNamedQuery( queryName, ( String ) null );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByNamedQuery( String queryName, String graphName ) throws Exception
    {
        return findByNamedQuery( queryName, graphName, null );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByNamedQuery( String queryName, int maxResults ) throws Exception
    {
        return findByNamedQuery( queryName, ( String ) null, maxResults );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByNamedQuery( String queryName, String graphName, int maxResults ) throws Exception
    {
        return findByNamedQuery( queryName, graphName, null, maxResults );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByNamedQuery( String queryName, Map<String, Object> parameterMap ) throws Exception
    {
        return findByNamedQuery( queryName, parameterMap, 0 );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByNamedQuery( String queryName, String graphName, Map<String, Object> parameterMap ) throws Exception
    {
        return findByNamedQuery( queryName, graphName, parameterMap, 0 );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByNamedQuery( String queryName, Map<String, Object> parameterMap, int maxResults ) throws Exception
    {
        return findByNamedQuery( queryName, ( String ) null, parameterMap, maxResults );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByNamedQuery( String queryName, String graphName, Map<String, Object> parameterMap,
                                     int maxResults ) throws Exception
    {
        // call super
        return super.findByNamedQuery( queryName, getEntityClass(), graphName != null ? em.getEntityGraph( graphName ) : null, parameterMap, maxResults );
    }
    
    // JPQL string entity finders
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByJpqlQueryString( String jpqlQueryString ) throws Exception
    {
        return findByJpqlQueryString( jpqlQueryString, ( Map<String, Object> ) null );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByJpqlQueryString( String jpqlQueryString, int maxResults ) throws Exception
    {
        return findByJpqlQueryString( jpqlQueryString, ( Map<String, Object> ) null, maxResults );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByJpqlQueryString( String jpqlQueryString, Map<String, Object> parameterMap ) throws Exception
    {
        return findByJpqlQueryString( jpqlQueryString, parameterMap, 0 );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findByJpqlQueryString( String jpqlQueryString, Map<String, Object> parameterMap,
                                          int maxResults ) throws Exception
    {
        // call super
        return super.findByJpqlQueryString( jpqlQueryString, getEntityClass(), parameterMap, maxResults );
    }
    
    // native query entity finders
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findBySqlQueryString( String sqlQueryString ) throws Exception
    {
        return findBySqlQueryString( sqlQueryString, ( List<Object> ) null );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findBySqlQueryString( String sqlQueryString, int maxResults ) throws Exception
    {
        return findBySqlQueryString( sqlQueryString, ( List<Object> ) null, maxResults );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findBySqlQueryString( String sqlQueryString, List<Object> parameterList ) throws Exception
    {
        return findBySqlQueryString( sqlQueryString, parameterList, 0 );
    }
    
    @Transactional( TxType.SUPPORTS )
    public List<T> findBySqlQueryString( String sqlQueryString, List<Object> parameterList, int maxResults ) throws Exception
    {
        return super.findBySqlQueryString( sqlQueryString, getEntityClass(), parameterList, maxResults );
    }
}
