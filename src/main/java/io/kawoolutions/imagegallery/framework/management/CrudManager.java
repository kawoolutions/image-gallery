package io.kawoolutions.imagegallery.framework.management;

import io.kawoolutions.imagegallery.framework.entity.Entity;

public interface CrudManager<K, T extends Entity<K>> extends Manager<K, T>
{
    // C(R)UD operations
    
    public void createSelection();
    public void updateSelection();
    public void deleteSelection();
    
    // create or update
    public void persistSelection();
    
    // reload
    public void reloadSelection();
}
