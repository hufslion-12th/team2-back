package com.likelion.mini2team.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {
    private final Path rootLocation = Paths.get("/images");

    // 이미지 로드
    public Resource loadImage(String filename) throws MalformedURLException {
        Path file = rootLocation.resolve(filename);
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("이미지 로드에 실패하였습니다: " + filename);
        }
    }

    // 이미지 생성
    public String saveImage(MultipartFile resource) {
        String originalFilename = resource.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new RuntimeException("유효한 파일 이름이 필요합니다.");
        }
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String randomFileName = UUID.randomUUID().toString() + "." + fileExtension;

        Path file = rootLocation.resolve(randomFileName);

        try {
            Files.createDirectories(rootLocation);
            Files.deleteIfExists(file);

            BufferedImage originalImage = ImageIO.read(resource.getInputStream());
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int targetWidth = 842;
            int targetHeight = (originalHeight * targetWidth) / originalWidth;  // 비율 유지

            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
            g.dispose();

            ImageIO.write(resizedImage, fileExtension, file.toFile());
        } catch (IOException e) {
            throw new RuntimeException("이미지 저장 중 오류 발생: " + e.getMessage());
        }

        return "/images/" + randomFileName;
    }


    public boolean deleteFile(String filename) {
        try {
            Files.deleteIfExists(rootLocation.resolve(filename.substring(filename.lastIndexOf("/images")+1)));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
