package io.kawoolutions.imagegallery.application;

public abstract class NamingConventions
{
    
    private NamingConventions()
    {
        // not instantiable
    }
    
    public static final String COMMON_KEY_PREFIX                   = "common";
    public static final String ENTITY_KEY_PREFIX                   = "entity";
    
    // general key suffixes
    
    public static final String LABEL_KEY_SUFFIX                    = "label";                                             // "Effective
                                                                                                                          // rate"
    public static final String SHORT_LABEL_KEY_SUFFIX              = "shortLabel";                                        // "Eff.
                                                                                                                          // rate"
    public static final String HEADER_KEY_SUFFIX                   = "header";                                            // "Effective
                                                                                                                          // Rate"
    public static final String SHORT_HEADER_KEY_SUFFIX             = "shortHeader";                                       // "Eff.
                                                                                                                          // Rate"
    
    public static final String SINGULAR_LABEL_KEY_SUFFIX           = "singular." + LABEL_KEY_SUFFIX;                      // "Effective
                                                                                                                          // rate"
    public static final String SINGULAR_SHORT_LABEL_KEY_SUFFIX     = "singular." + SHORT_LABEL_KEY_SUFFIX;                // "Eff.
                                                                                                                          // rate"
    public static final String SINGULAR_HEADER_KEY_SUFFIX          = "singular." + HEADER_KEY_SUFFIX;                     // "Effective
                                                                                                                          // Rate"
    public static final String SINGULAR_SHORT_HEADER_KEY_SUFFIX    = "singular." + SHORT_HEADER_KEY_SUFFIX;               // "Eff.
                                                                                                                          // Rate"
    
    public static final String PLURAL_LABEL_KEY_SUFFIX             = "plural." + LABEL_KEY_SUFFIX;                        // "Effective
                                                                                                                          // rates"
    public static final String PLURAL_SHORT_LABEL_KEY_SUFFIX       = "plural." + SHORT_LABEL_KEY_SUFFIX;                  // "Eff.
                                                                                                                          // rates"
    public static final String PLURAL_HEADER_KEY_SUFFIX            = "plural." + HEADER_KEY_SUFFIX;                       // "Effective
                                                                                                                          // Rates"
    public static final String PLURAL_SHORT_HEADER_KEY_SUFFIX      = "plural." + SHORT_HEADER_KEY_SUFFIX;                 // "Eff.
                                                                                                                          // Rates"
    
    public static final String HINT_KEY_SUFFIX                     = "hint";                                              // "Save
                                                                                                                          // effective
                                                                                                                          // rate"
    public static final String TITLE_KEY_SUFFIX                    = "title";                                             // "Saving
                                                                                                                          // Effective
                                                                                                                          // Rate"
    
    public static final String FORMAT_KEY_SUFFIX                   = "format";
    public static final String SHORT_FORMAT_KEY_SUFFIX             = "shortFormat";
    
    // for faces messages
    
    public static final String CREATE_KEY_PART                     = "create";
    public static final String UPDATE_KEY_PART                     = "update";
    public static final String DELETE_KEY_PART                     = "delete";
    public static final String RETRIEVE_KEY_PART                   = "retrieve";
    
    public static final String SUCCESS_KEY_PART                    = "success";
    public static final String FAILURE_KEY_PART                    = "failure";
    
    public static final String SUMMARY_KEY_SUFFIX                  = "summary";                                           // "Saving
                                                                                                                          // effective
                                                                                                                          // rate
                                                                                                                          // failed"
    public static final String MESSAGE_KEY_SUFFIX                  = "message";                                           // "The
                                                                                                                          // effective
                                                                                                                          // rate
                                                                                                                          // could
                                                                                                                          // not
                                                                                                                          // be
                                                                                                                          // saved!
                                                                                                                          // Please
                                                                                                                          // contact
                                                                                                                          // your
                                                                                                                          // system
                                                                                                                          // administrator."
    
    public static final String REQUIRED_MESSAGE_KEY_SUFFIX         = "requiredMessage";                                   // "Effective
                                                                                                                          // rate
                                                                                                                          // is
                                                                                                                          // required."
    public static final String EMPTY_MESSAGE_KEY_SUFFIX            = "emptyMessage";                                      // "No
                                                                                                                          // effective
                                                                                                                          // rates."
    
    public static final String VALIDATOR_MESSAGE_KEY_SUFFIX        = "validatorMessage";
    
    // compound key suffixes for manager framework
    
    public static final String SUCCESS_SUMMARY_KEY_SUFFIX          = SUCCESS_KEY_PART + "." + SUMMARY_KEY_SUFFIX;
    public static final String SUCCESS_MESSAGE_KEY_SUFFIX          = SUCCESS_KEY_PART + "." + MESSAGE_KEY_SUFFIX;
    public static final String FAILURE_SUMMARY_KEY_SUFFIX          = FAILURE_KEY_PART + "." + SUMMARY_KEY_SUFFIX;
    public static final String FAILURE_MESSAGE_KEY_SUFFIX          = FAILURE_KEY_PART + "." + MESSAGE_KEY_SUFFIX;
    
    public static final String CREATE_SUCCESS_SUMMARY_KEY_SUFFIX   = CREATE_KEY_PART + "." + SUCCESS_SUMMARY_KEY_SUFFIX;
    public static final String CREATE_SUCCESS_MESSAGE_KEY_SUFFIX   = CREATE_KEY_PART + "." + SUCCESS_MESSAGE_KEY_SUFFIX;
    public static final String CREATE_FAILURE_SUMMARY_KEY_SUFFIX   = CREATE_KEY_PART + "." + FAILURE_SUMMARY_KEY_SUFFIX;
    public static final String CREATE_FAILURE_MESSAGE_KEY_SUFFIX   = CREATE_KEY_PART + "." + FAILURE_MESSAGE_KEY_SUFFIX;
    public static final String UPDATE_SUCCESS_SUMMARY_KEY_SUFFIX   = UPDATE_KEY_PART + "." + SUCCESS_SUMMARY_KEY_SUFFIX;
    public static final String UPDATE_SUCCESS_MESSAGE_KEY_SUFFIX   = UPDATE_KEY_PART + "." + SUCCESS_MESSAGE_KEY_SUFFIX;
    public static final String UPDATE_FAILURE_SUMMARY_KEY_SUFFIX   = UPDATE_KEY_PART + "." + FAILURE_SUMMARY_KEY_SUFFIX;
    public static final String UPDATE_FAILURE_MESSAGE_KEY_SUFFIX   = UPDATE_KEY_PART + "." + FAILURE_MESSAGE_KEY_SUFFIX;
    public static final String DELETE_SUCCESS_SUMMARY_KEY_SUFFIX   = DELETE_KEY_PART + "." + SUCCESS_SUMMARY_KEY_SUFFIX;
    public static final String DELETE_SUCCESS_MESSAGE_KEY_SUFFIX   = DELETE_KEY_PART + "." + SUCCESS_MESSAGE_KEY_SUFFIX;
    public static final String DELETE_FAILURE_SUMMARY_KEY_SUFFIX   = DELETE_KEY_PART + "." + FAILURE_SUMMARY_KEY_SUFFIX;
    public static final String DELETE_FAILURE_MESSAGE_KEY_SUFFIX   = DELETE_KEY_PART + "." + FAILURE_MESSAGE_KEY_SUFFIX;
    public static final String RETRIEVE_SUCCESS_SUMMARY_KEY_SUFFIX = RETRIEVE_KEY_PART + "." + SUCCESS_SUMMARY_KEY_SUFFIX;
    public static final String RETRIEVE_SUCCESS_MESSAGE_KEY_SUFFIX = RETRIEVE_KEY_PART + "." + SUCCESS_MESSAGE_KEY_SUFFIX;
    public static final String RETRIEVE_FAILURE_SUMMARY_KEY_SUFFIX = RETRIEVE_KEY_PART + "." + FAILURE_SUMMARY_KEY_SUFFIX;
    public static final String RETRIEVE_FAILURE_MESSAGE_KEY_SUFFIX = RETRIEVE_KEY_PART + "." + FAILURE_MESSAGE_KEY_SUFFIX;
}
