package com.likelion.mini2team.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProxyServiceForLife4Cut {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String MEDIA_DIR = "/media/";

    public ResponseEntity<byte[]> fetchMedia(@RequestParam String mediaUrl, boolean isImage) throws IOException, URISyntaxException {
        String redirectedUrl = followRedirects(mediaUrl);
        String pathParam = extractPathParam(redirectedUrl);
        String finalMediaUrl = constructFinalUrl(redirectedUrl, pathParam);

        UUID fileName = UUID.randomUUID();
        byte[] imageData = getMediaAndReadFile(finalMediaUrl, pathParam, fileName.toString(), isImage);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(imageData);
    }

    private String followRedirects(String urlString) throws IOException {
        // 1차 url 접속
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        boolean redirect = false;
        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_SEE_OTHER)
                redirect = true;
        }
        if (redirect) {
            String newUrl = connection.getHeaderField("Location");
            connection = (HttpURLConnection) new URL(newUrl).openConnection();
            connection.setRequestProperty("User-Agent", USER_AGENT);
        }

        return connection.getURL().toString();
    }

    private String extractPathParam(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String query = uri.getQuery();
        List<String> params = Arrays.asList(query.split("&"));
        return params.stream()
                .filter(param -> param.startsWith("path="))
                .findFirst()
                .map(param -> param.split("=")[1])
                .orElse("");
    }

    private String constructFinalUrl(String baseUrl, String pathParam) {
        return baseUrl.split("/web/Public/qrPage.html")[0] + "/web/web/QRImage/" + pathParam + "/image.jpg";
    }

    private byte[] getMediaAndReadFile(String finalMediaUrl, String pathParam, String fileName, boolean isImage) throws IOException {
        URL url = new URL(finalMediaUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        Path mediaDir = Paths.get(MEDIA_DIR);
        if (!Files.exists(mediaDir)) {
            Files.createDirectories(mediaDir);
        }

//        String fileName = pathParam.substring(pathParam.lastIndexOf('/') + 1) + (isImage ? ".jpg" : ".mp4");
        Path filePath = mediaDir.resolve(fileName + (isImage ? ".jpg" : ".mp4"));

        try (InputStream in = connection.getInputStream()) {
            Files.copy(in, filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }

        return Files.readAllBytes(filePath);
    }
}
