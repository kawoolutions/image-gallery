package io.kawoolutions.imagegallery.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public abstract class MapUtils
{
    public static <V, K> Map<V, K> invert( Map<K, V> map )
    {
        return map.entrySet().stream().collect( Collectors.toMap( Entry::getValue, Entry::getKey ) );
    }
}
