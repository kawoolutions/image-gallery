package io.kawoolutions.imagegallery.application;

public abstract class ProjectGlobals
{
    // both below change from project to project
    public static final String PROD_PERSISTENCE_UNIT_NAME = "BBStatsPU";
    public static final String TEST_PERSISTENCE_UNIT_NAME = "BBStatsTestPU";
    
    public static final String DEFAULT_NO_SELECTION_LABEL = "Wuff";
    
    // can be "/view/" or anything
    public static final String OUTCOME_PREFIX = "/";

    public static final String ERROR_TREE_ICON = "error";

    public static final String NULL_STRING = "NulL";
}
