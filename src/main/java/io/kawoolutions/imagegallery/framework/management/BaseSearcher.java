package io.kawoolutions.imagegallery.framework.management;

import java.util.List;

import javax.annotation.PostConstruct;

import io.kawoolutions.imagegallery.framework.exception.RetrieveException;

public abstract class BaseSearcher<T> extends BaseSelectionHandler<T>
{
    private static final long serialVersionUID = 1L;

    private String searchTerm;
    
    @Override
    @PostConstruct
    public void init()
    {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void reset()
    {
//        System.out.println( getClass().getSimpleName() + ".reset()!" );
        
        super.reset();
        
        // already performed by super
//        clear();
    }
    
    @Override
    public void clear()
    {
//        System.out.println( getClass().getSimpleName() + ".clear()!" );
        
        super.clear();
        
        setSearchTerm( null );
    }
    
    @Override
    public List<T> getEntities()
    {
        // no automatic loading here
        return entities;
    }
    
    public void searchEntities()
    {
        try
        {
            setEntities( loadEntities() );
        }
        catch ( RetrieveException e )
        {
            log.error( getClass().getSimpleName() + ".loadEntities() threw " + e.getClass().getSimpleName(), e );
        }
    }

    public String getSearchTerm()
    {
        return searchTerm;
    }

    public void setSearchTerm( String searchTerm )
    {
        this.searchTerm = searchTerm;
    }
}
