package com.duia.mars.common.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 集合操作工具类
 * @author xingshaofei
 * @date 2017-12-19 下午 5:32
 */
public class CollectUtils {

    private CollectUtils(){}

    public static <T> List<T>[] split(List<T> list, int splitSize) {

        int size = list.size();

        if (size <= splitSize) {
            return new List[]{list};
        }

        int fromIndex = 0;
        int toIndex = splitSize;
        int remain = size;

        int page = size / splitSize + (size % splitSize > 0 ? 1 : 0);
        int currentPage = 0;
        List<T>[] colArray = new List[page];

        while (fromIndex < size) {

            // 保存
            colArray[currentPage++] = list.subList(fromIndex, toIndex);

            fromIndex = toIndex;        // 重置fromIndex

            remain = size - toIndex;

            if (remain <= splitSize)    // 重置toIndex
                toIndex += remain;
            else
                toIndex += splitSize;
        }

        return colArray;
    }


    public static <T extends KeyBean<K>, K> Collection<T> union(Collection<T>... lists) {

        if (lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];

        Map<K, T> tar = new TreeMap<>();

        boolean isFirst = true;

        for (Collection<T> l : lists) {

            if (isFirst) {
                putUseIdAsKey(tar, l);
                isFirst = false;
                continue ;
            }

            putWhenNotExists(tar, l);
        }

        return tar.values();
    }

    private static <T extends KeyBean<K>, K> void putUseIdAsKey(Map<K, T> tar, Collection<T> l) {

        for (T t : l) {

            tar.put(t.getKey(), t);
        }
    }

    private static <T extends KeyBean<K>, K> void putWhenNotExists(Map<K, T> tar, Collection<T> l) {

        for (T t : l) {

            K key = t.getKey();

            if (!tar.containsKey(key))
                tar.put(key, t);
        }
    }


}
