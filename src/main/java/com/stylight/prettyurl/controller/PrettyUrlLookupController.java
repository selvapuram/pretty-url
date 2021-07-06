package com.stylight.prettyurl.controller;

import com.stylight.prettyurl.service.IUrlLookupService;
import com.stylight.prettyurl.service.UrlType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PrettyUrlLookupController {

    private final IUrlLookupService iUrlLookupService;

    @Autowired
    public PrettyUrlLookupController(@Qualifier("urlLookupService") IUrlLookupService iUrlLookupService) {
        this.iUrlLookupService = iUrlLookupService;
    }

    @PostMapping("/pretty")
    public ResponseEntity<Map<String, String>> getPrettyUrls(@RequestBody List<String> canonicalUrls) {
        return ResponseEntity.ok(iUrlLookupService.buildUrl(canonicalUrls, UrlType.PRETTY));
    }

    @PostMapping("/canonical")
    public ResponseEntity<Map<String, String>> getCanonicalUrls(@RequestBody List<String> prettyUrls) {
        return ResponseEntity.ok(iUrlLookupService.buildUrl(prettyUrls, UrlType.CANONICAL));
    }
}
