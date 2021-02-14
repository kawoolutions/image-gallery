package io.kawoolutions.imagegallery.framework.i18n;

public abstract class NamingConventions
{
    private NamingConventions()
    {
        // not instantiable
    }
    
    public static final String COMMON_KEY_PREFIX                   = "common";
    public static final String ENTITY_KEY_PREFIX                   = "entity";
    
    // general key suffixes
    
    public static final String LABEL_KEY_SUFFIX                    = "label";
    public static final String SHORT_LABEL_KEY_SUFFIX              = "shortLabel";
    public static final String HEADER_KEY_SUFFIX                   = "header";
    public static final String SHORT_HEADER_KEY_SUFFIX             = "shortHeader";
    
//    public static final String SINGULAR_LABEL_KEY_SUFFIX           = "singular." + LABEL_KEY_SUFFIX;
//    public static final String SINGULAR_SHORT_LABEL_KEY_SUFFIX     = "singular." + SHORT_LABEL_KEY_SUFFIX;
//    public static final String SINGULAR_HEADER_KEY_SUFFIX          = "singular." + HEADER_KEY_SUFFIX;
//    public static final String SINGULAR_SHORT_HEADER_KEY_SUFFIX    = "singular." + SHORT_HEADER_KEY_SUFFIX;
//    
//    public static final String PLURAL_LABEL_KEY_SUFFIX             = "plural." + LABEL_KEY_SUFFIX;
//    public static final String PLURAL_SHORT_LABEL_KEY_SUFFIX       = "plural." + SHORT_LABEL_KEY_SUFFIX;
//    public static final String PLURAL_HEADER_KEY_SUFFIX            = "plural." + HEADER_KEY_SUFFIX;
//    public static final String PLURAL_SHORT_HEADER_KEY_SUFFIX      = "plural." + SHORT_HEADER_KEY_SUFFIX;
    
    public static final String HINT_KEY_SUFFIX                     = "hint";
    public static final String TITLE_KEY_SUFFIX                    = "title";
    
    public static final String FORMAT_KEY_SUFFIX                   = "format";
    public static final String SHORT_FORMAT_KEY_SUFFIX             = "shortFormat";
    
    // for faces messages
    
    public static final String CREATE_KEY_PART                     = "create";
    public static final String RETRIEVE_KEY_PART                   = "retrieve";
    public static final String UPDATE_KEY_PART                     = "update";
    public static final String DELETE_KEY_PART                     = "delete";
    
    public static final String SINGULAR_KEY_PART                   = "singular";
    public static final String PLURAL_KEY_PART                     = "plural";
    
    public static final String SUCCESS_KEY_PART                    = "success";
    public static final String FAILURE_KEY_PART                    = "failure";
    
    public static final String SUMMARY_KEY_SUFFIX                  = "summary";
    public static final String MESSAGE_KEY_SUFFIX                  = "message";
    public static final String REQUIRED_MESSAGE_KEY_SUFFIX         = "requiredMessage";
    public static final String EMPTY_MESSAGE_KEY_SUFFIX            = "emptyMessage";
    public static final String VALIDATOR_MESSAGE_KEY_SUFFIX        = "validatorMessage";
}
