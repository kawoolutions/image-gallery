package io.kawoolutions.imagegallery.framework;

public interface Clearable
{
    /**
     * Clear means to put the state of the bean back to the original in a soft/incomplete way.
     * Resettable.reset() is a deeper operation. See Manager interface for an example.
     */
    public void clear();
}
