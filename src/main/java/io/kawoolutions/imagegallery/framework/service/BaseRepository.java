package io.kawoolutions.imagegallery.framework.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;

@Transactional( TxType.SUPPORTS )
public abstract class BaseRepository implements Repository // no need for Serializable interface
{
    @Inject
    protected Logger log;
    
    @PersistenceContext
    protected EntityManager em;
    
    // treat all non-specified attributes as they are specified (or whatever they default to)
    protected String entityGraphStrategy = PersistenceGlobals.JAVAX_PERSISTENCE_FETCHGRAPH;
    
    @Override
    public Logger getLogger()
    {
        return log;
    }
    
    @Override
    public EntityManager getEntityManager()
    {
        return em;
    }
    
    @Override
    public String getEntityGraphStrategy()
    {
        return entityGraphStrategy;
    }
    
    public void setEntityGraphStrategy( String entityGraphStrategy )
    {
        this.entityGraphStrategy = entityGraphStrategy;
    }
}
