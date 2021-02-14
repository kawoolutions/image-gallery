package io.kawoolutions.imagegallery.framework.entity;

/**
 * Represents an entity that has meta data associated with it to store the user name,
 * state and time of the last write to DB.
 */
public interface MetaEntity<K> extends Entity<K>, MetaDataProvider
{
    // no additional interface
}
