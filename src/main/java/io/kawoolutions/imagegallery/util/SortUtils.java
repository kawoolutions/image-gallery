package io.kawoolutions.imagegallery.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public abstract class SortUtils
{
    public static List<String> replaceWith( List<String> strings, Map<String, String> replacements )
    {
        ListIterator<String> iterator = strings.listIterator();
        
        while ( iterator.hasNext() )
        {
            String next = iterator.next();
            
            for ( String replaced : replacements.keySet() )
            {
                if ( next.equals( replaced ) )
                {
                    String replacement = replacements.get( replaced );
                    
                    // replace element
                    iterator.set( replacement );
                }
            }
        }
        
        List<String> replacedStrings = new ArrayList<>();
        iterator.forEachRemaining( replacedStrings::add );
        
        return replacedStrings;
    }
}
