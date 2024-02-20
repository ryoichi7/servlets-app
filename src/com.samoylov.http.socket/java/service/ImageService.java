package service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.PropertiesUtil;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {
    private static final ImageService INSTANCE = new ImageService();
    private final String basePath = PropertiesUtil.get("image.base.url");

    @SneakyThrows
    public void upload(String imapePath, InputStream imageContent){
        var imageFullPath = Path.of(basePath + imapePath);
        try(imageContent){
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<InputStream> get(String imagePath){
        var imageFullPath = Path.of(basePath + imagePath);
        return Files.exists(imageFullPath) ? Optional.of(Files.newInputStream(imageFullPath)) : Optional.empty();
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }
}
