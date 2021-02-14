package io.kawoolutions.imagegallery.framework.entity;

import java.io.Serializable;

public interface Identifiable extends Serializable
{
    public Integer getId();
    public void setId( Integer id );
}
