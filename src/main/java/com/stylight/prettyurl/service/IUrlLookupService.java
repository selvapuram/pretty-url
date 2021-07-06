package com.stylight.prettyurl.service;

import java.util.List;
import java.util.Map;

public interface IUrlLookupService {

    Map<String, String> buildUrl(List<String> urls, UrlType urlType);

}
