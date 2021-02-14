package io.kawoolutions.imagegallery.framework.management;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.kawoolutions.imagegallery.framework.cdi.BaseHandler;
import io.kawoolutions.imagegallery.framework.cdi.ClassUtils;
import io.kawoolutions.imagegallery.framework.exception.RetrieveException;
import io.kawoolutions.imagegallery.framework.service.CommonRepository;

/**
 * An instance of this class maintains a (more or less read-only) list of entities. It can actually
 * be added to or removed from, but it has no abilities to persist anything to database.
 *
 * @param <T>
 */
public abstract class BaseProvider<T> extends BaseHandler implements Provider<T>
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    protected CommonRepository commonRepository;
    
    private String msgKeyPrefix;
    
    protected List<T> entities;

    protected List<T> originalEntities;
    
    protected String getGenericClassName()
    {
        return getGenericClass().getSimpleName();
    }
    
    protected Class<?> getGenericClass()
    {
        return getGenericClassFor( 0 );
    }
    
    protected Class<?> getGenericClassFor( int index )
    {
        return ClassUtils.findGenericClassFor( getClass(), index );
    }
    
    /**
     * Return a text messages key prefix for all FacesMessages being generated for all CRUD operations.
     * The default implementation uses the policy "entity." + <uncapitalized entity name> + ".action", e.g.
     * "entity.competition.action".
     * @return
     */
    protected String getMsgKeyPrefix()
    {
        if ( msgKeyPrefix == null )
        {
            msgKeyPrefix = "entity." + Introspector.decapitalize( getMsgKeyClassName() ) + ".action";
        }
        
        return msgKeyPrefix;
    }
    
    protected String getMsgKeyClassName()
    {
        return getGenericClassName();
    }

    @Override
    public List<T> getEntities()
    {
        if ( this.entities == null )
        {
            try
            {
                this.entities = loadEntities();
                
                // make shallow copy to determine original positions
                if ( this.entities != null )
                {
                    this.originalEntities = new ArrayList<>( this.entities );
                }
            }
            catch ( RetrieveException e )
            {
                log.error( getClass().getSimpleName() + ".loadEntities() threw " + e.getClass().getSimpleName(), e );
                
//                throw e;
                
//                System.out.println( "Msg list: " + facesContext.getMessageList().size() );
            }
        }
        
        return entities;
    }

    @Override
    public void setEntities( List<T> entities )
    {
        this.entities = entities;
    }

    public List<T> getOriginalEntities()
    {
        return originalEntities;
    }

    public void setOriginalEntities( List<T> originalEntities )
    {
        this.originalEntities = originalEntities;
    }

    protected abstract List<T> loadEntities() throws RetrieveException;
}