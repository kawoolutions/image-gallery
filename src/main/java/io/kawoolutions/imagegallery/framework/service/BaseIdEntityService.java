package io.kawoolutions.imagegallery.framework.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import io.kawoolutions.imagegallery.framework.entity.IdEntity;
import io.kawoolutions.imagegallery.framework.exception.CreateException;
import io.kawoolutions.imagegallery.framework.exception.MaxIdNotFoundException;
import io.kawoolutions.imagegallery.framework.exception.RetrieveException;

@Transactional( TxType.REQUIRED )
public abstract class BaseIdEntityService<T extends IdEntity> extends BaseEntityService<Integer, T>
{
    // the pseudo ID creator will inc this by one!
    private static final int DEFAULT_ID = 0;
    
    @Override
    @Transactional( TxType.SUPPORTS )
    protected Class<Integer> getPkClass()
    {
        return Integer.class;
    }
    
    @Override
    @SuppressWarnings( "unchecked" )
    @Transactional( TxType.SUPPORTS )
    protected Class<T> getEntityClass()
    {
        if ( entityClass == null )
        {
            // read entity class from first class of generics
            entityClass = ( Class<T> ) ( ( ParameterizedType ) getClass().getGenericSuperclass() ).getActualTypeArguments()[0];
        }
        
        return entityClass;
    }
    
    /**
     * Make this method return non-null to automatically determine the next ID
     * by a MAX(...) query to this field. You must return null for this field
     * if the entity at hand has a @GeneratedValue, otherwise the code to
     * assign an ID artificially will kick in, in effect messing up the AI
     * generation to run, which requires null to be set to work.
     * 
     * @return
     */
//    protected abstract String getPseudoAutoIncFieldName();
    protected String getPseudoAutoIncFieldName()
    {
//        return "id";
        return null;
    }
    
    @Override
    public T create( T entity ) throws CreateException
    {
        // if the service has a pseudo auto-inc field name, then we must calculate the next ID
        if ( getPseudoAutoIncFieldName() != null )
        {
            int maxId = DEFAULT_ID;
            
            try
            {
                maxId = findMaxId();
            }
            catch ( RetrieveException e )
            {
                throw new CreateException( "Max ID could not be determined for " + getEntityClass().getSimpleName() + "!", e );
            }
            
            // get next ID
            entity.setId( Integer.valueOf( maxId + 1 ) );
        }
        
        return super.create( entity );
    }
    
    @Transactional( TxType.SUPPORTS )
    public int findMaxId() throws RetrieveException
    {
        if ( !Integer.class.equals( getPkClass() ) )
        {
            throw new UnsupportedOperationException( "PK class is not an integral type!" );
        }
        
        try
        {
            String queryString = "SELECT MAX(t." + getPseudoAutoIncFieldName() + ") FROM " + getEntityClass().getSimpleName() + " t";
            
            // sometimes returns list with one null entry
            List<Integer> maxIds = findByJpqlQueryString( queryString, Integer.class );
            
            if ( maxIds == null || maxIds.isEmpty() )
            {
                return DEFAULT_ID;
            }
            
            Integer maxId = maxIds.get( 0 );
            
            if ( maxId == null )
            {
                return DEFAULT_ID;
            }
            
            return maxId.intValue();
        }
        catch ( Exception e )
        {
            throw new MaxIdNotFoundException( "Could not determine max ID!", e );
        }
    }
}
