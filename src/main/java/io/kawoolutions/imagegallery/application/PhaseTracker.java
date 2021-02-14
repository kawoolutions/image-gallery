package io.kawoolutions.imagegallery.application;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class PhaseTracker implements PhaseListener
{
    private static final long serialVersionUID = 1L;
    
    @Override
    public void beforePhase( PhaseEvent event )
    {
        System.out.println( "Before " + event.getPhaseId() );
    }
    
    @Override
    public void afterPhase( PhaseEvent event )
    {
        System.out.println( "After " + event.getPhaseId() );
    }
    
    @Override
    public PhaseId getPhaseId()
    {
        return PhaseId.ANY_PHASE;
    }
}
