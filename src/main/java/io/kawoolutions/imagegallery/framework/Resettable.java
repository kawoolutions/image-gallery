package io.kawoolutions.imagegallery.framework;

public interface Resettable
{
    /**
     * Reset means to put the state of the bean back to the original in a hard/complete way.
     * Clearable.clear() is a shallower operation. See Manager interface for an example.
     */
    public void reset();
}
