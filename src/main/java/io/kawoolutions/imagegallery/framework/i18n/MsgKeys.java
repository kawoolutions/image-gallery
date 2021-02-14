package io.kawoolutions.imagegallery.framework.i18n;

import static io.kawoolutions.imagegallery.framework.i18n.NamingConventions.COMMON_KEY_PREFIX;
import static io.kawoolutions.imagegallery.framework.i18n.NamingConventions.FAILURE_KEY_PART;
import static io.kawoolutions.imagegallery.framework.i18n.NamingConventions.FORMAT_KEY_SUFFIX;
import static io.kawoolutions.imagegallery.framework.i18n.NamingConventions.LABEL_KEY_SUFFIX;
import static io.kawoolutions.imagegallery.framework.i18n.NamingConventions.MESSAGE_KEY_SUFFIX;
import static io.kawoolutions.imagegallery.framework.i18n.NamingConventions.SHORT_FORMAT_KEY_SUFFIX;
import static io.kawoolutions.imagegallery.framework.i18n.NamingConventions.SHORT_LABEL_KEY_SUFFIX;
import static io.kawoolutions.imagegallery.framework.i18n.NamingConventions.SUMMARY_KEY_SUFFIX;

public abstract class MsgKeys
{
    // date, time, date-time formats
    
    public static final String COMMON_DATE_FORMAT = COMMON_KEY_PREFIX + ".date." + FORMAT_KEY_SUFFIX;
    
    public static final String COMMON_DATE_MONTH_FORMAT = COMMON_KEY_PREFIX + ".date.month." + FORMAT_KEY_SUFFIX;
    public static final String COMMON_DATE_MONTH_SHORT_FORMAT = COMMON_KEY_PREFIX + ".date.month." + SHORT_FORMAT_KEY_SUFFIX;
    
    public static final String COMMON_TIME_FORMAT = COMMON_KEY_PREFIX + ".time." + FORMAT_KEY_SUFFIX;
    
    public static final String COMMON_DATETIME_FORMAT = COMMON_KEY_PREFIX + ".dateTime." + FORMAT_KEY_SUFFIX;
    public static final String COMMON_DATETIME_SECOND_FORMAT = COMMON_KEY_PREFIX + ".dateTime.second" + FORMAT_KEY_SUFFIX;
    
    public static final String COMMON_MONTH_LABEL = COMMON_KEY_PREFIX + ".month." + LABEL_KEY_SUFFIX;
    public static final String COMMON_QUARTER_LABEL = COMMON_KEY_PREFIX + ".quarter." + LABEL_KEY_SUFFIX;
    
    // labels and headers
    
    public static final String COMMON_YES_LABEL = COMMON_KEY_PREFIX + ".yes." + LABEL_KEY_SUFFIX;
    public static final String COMMON_NO_LABEL = COMMON_KEY_PREFIX + ".no." + LABEL_KEY_SUFFIX;
    
    public static final String COMMON_ACTIVE_LABEL = COMMON_KEY_PREFIX + ".active." + LABEL_KEY_SUFFIX;
    public static final String COMMON_INACTIVE_LABEL = COMMON_KEY_PREFIX + ".inactive." + LABEL_KEY_SUFFIX;
    
    public static final String COMMON_EMPTY_LABEL = COMMON_KEY_PREFIX + ".empty." + LABEL_KEY_SUFFIX;
    
    public static final String COMMON_PERMITTED_LABEL = COMMON_KEY_PREFIX + ".permitted." + LABEL_KEY_SUFFIX;
    public static final String COMMON_CRITICAL_LABEL = COMMON_KEY_PREFIX + ".critical." + LABEL_KEY_SUFFIX;
    public static final String COMMON_EXCEEDED_LABEL = COMMON_KEY_PREFIX + ".exceeded." + LABEL_KEY_SUFFIX;
    
    public static final String COMMON_UNLIMITED_LABEL = COMMON_KEY_PREFIX + ".unlimited." + LABEL_KEY_SUFFIX;
    
    public static final String COMMON_UNKNOWN_LABEL = COMMON_KEY_PREFIX + ".unknown." + LABEL_KEY_SUFFIX;
    public static final String COMMON_UNKNOWN_SHORT_LABEL = COMMON_KEY_PREFIX + ".unknown." + SHORT_LABEL_KEY_SUFFIX;
    
    // download keys
    
    public static final String COMMON_DOWNLOAD_FAILURE_SUMMARY = COMMON_KEY_PREFIX + ".download." + FAILURE_KEY_PART + "." + SUMMARY_KEY_SUFFIX;
    public static final String COMMON_DOWNLOAD_FAILURE_MESSAGE = COMMON_KEY_PREFIX + ".download." + FAILURE_KEY_PART + "." + MESSAGE_KEY_SUFFIX;
    
    public static final String COMMON_DOWNLOAD_ABORTED_SUMMARY = COMMON_KEY_PREFIX + ".download.aborted." + SUMMARY_KEY_SUFFIX;
    public static final String COMMON_DOWNLOAD_ABORTED_MESSAGE = COMMON_KEY_PREFIX + ".download.aborted." + MESSAGE_KEY_SUFFIX;
    public static final String CHANGE_THEME_LABEL_KEY = "common.action.changeTheme.label";
}
