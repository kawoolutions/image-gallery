package io.kawoolutions.imagegallery.framework.service;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

/**
 * A service can create, update and delete entities, but is also able to read entities, so
 * it is also a repository.
 * 
 * @author Kawu
 */
public interface Service extends Repository
{
    default public <T> T create( T entity ) throws Exception
    {
//        Logger log = getLogger();
        EntityManager em = getEntityManager();
        
//        log.info( entity.getClass().getSimpleName() + ": 1. entity before persist = " + entity + ", EM = " + em );
        
        // also see http://stackoverflow.com/q/1069992/396732
        em.persist( entity );
        
//        log.info( entity.getClass().getSimpleName() + ": 2. entity after persist = " + entity );
        
        // optional: flush() + refresh() is "paranoia-driven"
        // see http://adambien.blog/roller/abien/entry/generic_crud_service_aka_dao#comment-1245872497610
        em.flush();
        
//        log.info( entity.getClass().getSimpleName() + ": 3. entity after flush = " + entity );
        
        em.refresh( entity );
        
//        log.info( entity.getClass().getSimpleName() + ": 4. entity after refresh = " + entity );
        
        return entity;
    }
    
    /**
     * Siehe auch see http://stackoverflow.com/q/1069992/396732
     */
    default public <T> T update( T entity ) throws Exception
    {
        EntityManager em = getEntityManager();
        
        // return instance *plus save changes after the call to merge to DB (until TA ends)*
        entity = em.merge( entity );
        
        // return instance as merged *without saving the changes after the call to merge to DB*
        // em.merge(entity);
        
        return entity;
    }
    
    /**
     * Adam Bien: "The implementation of the delete method seems to be strange at first glance.
     * Before the entity is going to be removed, a reference to it is fetched from the EntityManager.
     * This is required by the spec - only managed entities can be removed. ..."
     */
    default public <K, T> void delete( Class<T> type, K pk ) throws Exception
    {
        EntityManager em = getEntityManager();
        
        Object reference = em.getReference( type, pk );
        em.remove( reference );
    }
    
    // updates + deletes by query
    
    @Transactional( TxType.REQUIRED )
    default public int executeNamedUpdateQuery( String queryName, Map<String, Object> parameterMap ) throws Exception
    {
        EntityManager em = getEntityManager();
        
        Query query = em.createNamedQuery( queryName );
        
        if ( parameterMap != null )
        {
            // TODO: Java 8
            for ( Entry<String, Object> entry : parameterMap.entrySet() )
            {
                query.setParameter( entry.getKey(), entry.getValue() );
            }
        }
        
        return query.executeUpdate();
    }
}
