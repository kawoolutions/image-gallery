package io.kawoolutions.imagegallery.framework.service;

import static io.kawoolutions.imagegallery.framework.service.ParameterCollectionBuilder.with;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;

import io.kawoolutions.imagegallery.framework.exception.EntityNotFoundException;

/**
 * A repository is an EJB that can only read entities.
 * 
 * @author Kawu
 */
public interface Repository
{
    public abstract Logger getLogger();
    public abstract EntityManager getEntityManager();
    public abstract String getEntityGraphStrategy();
    
    @Transactional( TxType.SUPPORTS )
    default public <K, T> T findByPk( Class<T> type, String graphName, K pk ) throws Exception
    {
        EntityManager em = getEntityManager();
        
        T entity = null;
        
        if ( graphName == null )
        {
            entity = em.find( type, pk );
        }
        else
        {
            EntityGraph<?> graph = em.getEntityGraph( graphName );
            entity = em.find( type, pk, with( getEntityGraphStrategy(), graph ).map() );
        }
        
        if ( entity == null )
        {
            throw new EntityNotFoundException( "Entity not found for PK = " + pk );
        }
        
        return entity;
    }
    
    @SuppressWarnings( "unchecked" )
    @Transactional( TxType.SUPPORTS )
    default public <K, T> List<T> findByPks( Class<T> type, String graphName, K... pks ) throws Exception
    {
        return findByPks( type, graphName, Arrays.asList( pks ) );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <K, T> List<T> findByPks( Class<T> type, String graphName, List<K> pks ) throws Exception
    {
        List<T> entities = new ArrayList<>();
        
        for ( K pk : pks )
        {
            if ( pk == null )
            {
                throw new EntityNotFoundException( "PK is null!" );
            }
            
            // TODO: code sucks: multiple SQL queries
            entities.add( findByPk( type, graphName, pk ) );
        }
        
        return entities;
    }
    
    // named query finders
    
    @Transactional( TxType.SUPPORTS )
    default public <T> T findSingleByNamedQuery( String queryName, Class<T> resultClass, Map<String, Object> parameterMap ) throws Exception
    {
        return findSingleByNamedQuery( queryName, resultClass, null, parameterMap );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> T findSingleByNamedQuery( String queryName, Class<T> resultClass, EntityGraph<?> graph, Map<String, Object> parameterMap ) throws Exception
    {
        TypedQuery<T> query = getEntityManager().createNamedQuery( queryName, Objects.requireNonNull( resultClass ) );
        
        if ( graph != null )
        {
            query.setHint( getEntityGraphStrategy(), graph );
        }
        
        if ( parameterMap != null )
        {
            // TODO: Java 8
            for ( Entry<String, Object> entry : parameterMap.entrySet() )
            {
                query.setParameter( entry.getKey(), entry.getValue() );
            }
        }
        
        // DON'T USE: will flood the console with exceptions even though we catch them!!
//        return query.getSingleResult();
        
        List<T> entities = query.getResultList();
        
        if ( entities == null || entities.isEmpty() )
        {
            return null;
        }
        
        return entities.get( 0 );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByNamedQuery( String queryName, Class<T> resultClass ) throws Exception
    {
        return findByNamedQuery( queryName, resultClass, ( String ) null );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByNamedQuery( String queryName, Class<T> resultClass, String graphName ) throws Exception
    {
        return findByNamedQuery( queryName, resultClass, graphName, null );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByNamedQuery( String queryName, Class<T> resultClass, Map<String, Object> parameterMap ) throws Exception
    {
        return findByNamedQuery( queryName, resultClass, parameterMap, 0 );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByNamedQuery( String queryName, Class<T> resultClass, int maxResults ) throws Exception
    {
        return findByNamedQuery( queryName, resultClass, null, maxResults );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByNamedQuery( String queryName, Class<T> resultClass, String graphName, Map<String, Object> parameterMap ) throws Exception
    {
        return findByNamedQuery( queryName, resultClass, graphName, parameterMap, 0 );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByNamedQuery( String queryName, Class<T> resultClass, Map<String, Object> parameterMap,
                                                 int maxResults ) throws Exception
    {
        return findByNamedQuery( queryName, resultClass, ( String ) null, parameterMap, maxResults );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByNamedQuery( String queryName, Class<T> resultClass, String graphName, Map<String, Object> parameterMap,
                                                 int maxResults ) throws Exception
    {
        return findByNamedQuery( queryName, resultClass, graphName != null ? getEntityManager().getEntityGraph( graphName ) : null, parameterMap, maxResults );
    }
    
    @Transactional( TxType.SUPPORTS )
    public default <T> List<T> findByNamedQuery( String queryName, Class<T> resultClass, EntityGraph<?> graph, Map<String, Object> parameterMap,
                                                 int maxResults ) throws Exception
    {
        TypedQuery<T> query = getEntityManager().createNamedQuery( queryName, Objects.requireNonNull( resultClass ) );
        
        if ( graph != null )
        {
            query.setHint( getEntityGraphStrategy(), graph );
        }
        
        if ( parameterMap != null )
        {
            // TODO: Java 8
            for ( Entry<String, Object> entry : parameterMap.entrySet() )
            {
                query.setParameter( entry.getKey(), entry.getValue() );
            }
        }
        
        if ( maxResults > 0 )
        {
            query.setMaxResults( maxResults );
        }
        
        return query.getResultList();
    }

    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByNamedNativeQuery( String queryName, Class<T> resultClass, List<Object> parameterList ) throws Exception
    {
        return findByNamedNativeQuery( queryName, resultClass, null, parameterList, 0 );
    }

    @SuppressWarnings( "unchecked" )
    @Transactional( TxType.SUPPORTS )
    public default <T> List<T> findByNamedNativeQuery( String queryName, Class<T> resultClass, EntityGraph<?> graph, List<Object> parameterList,
                                                       int maxResults ) throws Exception
    {
        Query query = getEntityManager().createNamedQuery( queryName, Objects.requireNonNull( resultClass ) );
        
        if ( graph != null )
        {
            query.setHint( getEntityGraphStrategy(), graph );
        }
        
        if ( parameterList != null )
        {
            int position = 1;
            
            for ( Object parameter : parameterList )
            {
                query.setParameter( position++, parameter );
            }
        }
        
        if ( maxResults > 0 )
        {
            query.setMaxResults( maxResults );
        }
        
        return query.getResultList();
    }
    
    // JPQL string finders
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByJpqlQueryString( String jpqlQueryString, Class<T> resultClass ) throws Exception
    {
        return findByJpqlQueryString( jpqlQueryString, resultClass, null );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByJpqlQueryString( String jpqlQueryString, Class<T> resultClass, int maxResults ) throws Exception
    {
        return findByJpqlQueryString( jpqlQueryString, resultClass, null, maxResults );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByJpqlQueryString( String jpqlQueryString, Class<T> resultClass,
                                                      Map<String, Object> parameterMap ) throws Exception
    {
        return findByJpqlQueryString( jpqlQueryString, resultClass, parameterMap, 0 );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findByJpqlQueryString( String jpqlQueryString, Class<T> resultClass, Map<String, Object> parameterMap,
                                                      int maxResults ) throws Exception
    {
        Objects.requireNonNull( resultClass );
        
        TypedQuery<T> query = getEntityManager().createQuery( jpqlQueryString, resultClass );
        
        if ( parameterMap != null )
        {
            // TODO: Java 8
            for ( String parameterName : parameterMap.keySet() )
            {
                Object parameterValue = parameterMap.get( parameterName );
                
                // prevent null as parameter value
                if ( parameterValue == null )
                {
                    throw new IllegalArgumentException( "Parameter value for " + parameterName + " is null!" );
                }
                
                query.setParameter( parameterName, parameterValue );
            }
        }
        
        if ( maxResults > 0 )
        {
            query.setMaxResults( maxResults );
        }
        
        return query.getResultList();
    }
    
    // native query finders
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findBySqlQueryString( String sqlQueryString, Class<T> resultClass ) throws Exception
    {
        return findBySqlQueryString( sqlQueryString, resultClass, null );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findBySqlQueryString( String sqlQueryString, Class<T> resultClass, int maxResults ) throws Exception
    {
        return findBySqlQueryString( sqlQueryString, resultClass, null, maxResults );
    }
    
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findBySqlQueryString( String sqlQueryString, Class<T> resultClass, List<Object> parameterList ) throws Exception
    {
        return findBySqlQueryString( sqlQueryString, resultClass, parameterList, 0 );
    }
    
    @SuppressWarnings( "unchecked" )
    @Transactional( TxType.SUPPORTS )
    default public <T> List<T> findBySqlQueryString( String sqlQueryString, Class<T> resultClass, List<Object> parameterList,
                                                     int maxResults ) throws Exception
    {
        Query query = null;
        
        if ( resultClass == null )
        {
            query = getEntityManager().createNativeQuery( sqlQueryString );
        }
        else
        {
            query = getEntityManager().createNativeQuery( sqlQueryString, resultClass );
        }
        
        if ( parameterList != null )
        {
            int i = 0;
            
            // TODO: Java 8
            for ( Object parameterValue : parameterList )
            {
                // prevent null as parameter value
                if ( parameterValue == null )
                {
                    throw new IllegalArgumentException( "Parameter value for " + i + " is null!" );
                }
                
                // native query param indices start at 1!
                query.setParameter( ++i, parameterValue );
            }
        }
        
        if ( maxResults > 0 )
        {
            query.setMaxResults( maxResults );
        }
        
        return query.getResultList();
    }

    /**
     * Only to avoid "unused static import" IDE warning to parameter (map) builder.
     * Different method name due to shadowing.
     * 
     * @param name
     * @param value
     * @return
     */
    default public ParameterCollectionBuilder where( String name, Object value )
    {
        return with( name, value );
    }
    
    /**
     * Only to avoid "unused static import" IDE warning to parameter (list) builder.
     * Different method name due to shadowing.
     * 
     * @param name
     * @param value
     * @return
     */
    default public ParameterCollectionBuilder where( Object value )
    {
        return with( value );
    }
}
