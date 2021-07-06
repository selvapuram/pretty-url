package com.stylight.prettyurl.db;

import com.stylight.prettyurl.exception.InvalidUrlException;
import com.stylight.prettyurl.library.BiMap;
import com.stylight.prettyurl.util.URIUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryPrettyUrlMap {

    /**
     * dictory to store pretty url
     */
    private static final BiMap<String, String> dictionary = new BiMap<>();
    /**
     * The basePath for all relative urls
     */
    @Value("${app.base.domain}")
    @Getter
    private String basePath;

    /**
     * @param key
     * @return the value of the matching if exists, otherwise go for best match
     */
    public String getByKey(String key) {
        return dictionary.getOrDefault(key, getBestMatch(key, true));
    }

    public String getByValue(String val) {
        return dictionary.getKeyOrDefault(val, getBestMatch(val, false));
    }

    /**
     * @param key
     * @return best matched value
     */
    private String getBestMatch(String key, boolean isKey) {
        try {
            URL url = new URL(key);
            List<String> queryParamMap = URIUtils.splitQuery(url);
            if (CollectionUtils.isEmpty(queryParamMap)) {
                return key;
            }
            String matchedKey = queryParamMap.stream()
                    .map(q -> URIUtils.removeQueryParam(key, q))
                    .filter(q -> {
                        String val = getFromDict(q, key, isKey);
                        return (val != key);
                    }).findFirst().orElse(key);
            return getFromDict(matchedKey, key, isKey);

        } catch (MalformedURLException | UnsupportedEncodingException e) {
            throw new InvalidUrlException();
        }

    }


    private String getFromDict(String q, String defaultVal, boolean isKey) {
        if (isKey) {
            return dictionary.getOrDefault(q, defaultVal);
        } else {
            return dictionary.getKeyOrDefault(q, defaultVal);
        }
    }

    /**
     * @param key   key of the dictionary
     * @param value value of the dictionary
     */
    public void loadLookup(String key, String value) {
        dictionary.put(basePath + key, basePath + value);
    }

    /**
     * @param preLoadedData to load some values
     */
    public void loadList(Map<String, String> preLoadedData) {
        preLoadedData.entrySet().stream().forEach(entry -> loadLookup(entry.getKey(), entry.getValue()));
    }

    public String appendDomain(String uri) {
        return basePath + uri;
    }

}
