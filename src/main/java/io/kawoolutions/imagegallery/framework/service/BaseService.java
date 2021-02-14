package io.kawoolutions.imagegallery.framework.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

/**
 * Extended implementation of Adam Bien's CrudServiceBean from the book "Real World Java EE Patterns - Rethinking Best
 * Practices" (1st ed.).
 * 
 * The find*() methods in this class generally need an extra Class<*> instance as argument.
 */
@Transactional( TxType.MANDATORY )
public abstract class BaseService extends BaseRepository implements Service // no need for Serializable interface
{
    // every service is also a repository
}
