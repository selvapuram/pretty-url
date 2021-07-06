package com.stylight.prettyurl.service;


import com.stylight.prettyurl.db.InMemoryPrettyUrlMap;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum UrlType {
    PRETTY {
        @Override
        public Map<String, String> generate(List<String> urls, InMemoryPrettyUrlMap inMemoryPrettyUrlMap) {
            return urls.stream().map(url -> inMemoryPrettyUrlMap.appendDomain(url)).collect(Collectors.toMap(Function.identity(),
                    key -> inMemoryPrettyUrlMap.getByKey(key)));
        }
    },
    CANONICAL {
        @Override
        public Map<String, String> generate(List<String> urls, InMemoryPrettyUrlMap inMemoryPrettyUrlMap) {
            return urls.stream().map(url -> inMemoryPrettyUrlMap.appendDomain(url)).collect(Collectors.toMap(Function.identity(),
                    val -> inMemoryPrettyUrlMap.getByValue(val)));
        }
    };

    public abstract Map<String, String> generate(List<String> urls, InMemoryPrettyUrlMap inMemoryPrettyUrlMap);

}