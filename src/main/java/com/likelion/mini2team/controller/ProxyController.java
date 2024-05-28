package com.likelion.mini2team.controller;

import com.likelion.mini2team.service.ProxyServiceForLife4Cut;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProxyController {

    @Autowired
    private ProxyServiceForLife4Cut proxyServiceForLife4Cut;

    @GetMapping("/4cutimage")
    public ResponseEntity<byte[]> fetchMedia(@RequestParam String url) throws IOException, URISyntaxException {
        return proxyServiceForLife4Cut.fetchMedia(url, true);
    }
}
