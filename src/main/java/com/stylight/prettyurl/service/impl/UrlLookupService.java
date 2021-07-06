package com.stylight.prettyurl.service.impl;

import com.stylight.prettyurl.db.InMemoryPrettyUrlMap;
import com.stylight.prettyurl.service.IUrlLookupService;
import com.stylight.prettyurl.service.UrlType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * author @Madhan
 * version 1.0
 * since 1.8
 *
 * The class <p>UrlLookupService</p>
 * constructs the Map of urls
 */

@Service
@AllArgsConstructor
public class UrlLookupService implements IUrlLookupService {

    private final InMemoryPrettyUrlMap inMemoryPrettyUrlMap;

    @Override
    public Map<String, String> buildUrl(List<String> urls, UrlType urlType) {
        return urlType.generate(urls, inMemoryPrettyUrlMap);
    }
}
