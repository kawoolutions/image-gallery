package io.kawoolutions.imagegallery.framework.cdi;

import io.kawoolutions.imagegallery.framework.Clearable;
import io.kawoolutions.imagegallery.framework.Resettable;

/**
 * Adds operations reset (deep) and clear (shallow) to a bean.
 * 
 * @author Kawu
 */
public abstract class BaseHandler extends BaseCdiBean implements Resettable, Clearable
{
    private static final long serialVersionUID = 1L;
        
    @Override
    public void init()
    {
        // do nothing
    }
}
