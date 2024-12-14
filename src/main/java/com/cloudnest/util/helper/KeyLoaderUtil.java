package com.cloudnest.util.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class KeyLoaderUtil {
    public static Map<String, SecretKey> KEY_MAP = new HashMap<>();
    public static SecretKey loadSecretKey(String keyName) {
        if (KEY_MAP.containsKey(keyName)) {
            return KEY_MAP.get(keyName);
        } else {
            Path keyPaths = Paths.get("C:\\learnings\\workspace\\spring-boot\\Utilities\\src\\main\\resources");
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(keyPaths)) {
                for (Path file : directoryStream) {
                    System.out.println(file.getFileName());
                    String fileName = file.getFileName().toString().split("\\.")[0];
                    byte[] bytes = Files.readAllBytes(file);
                    if (fileName.startsWith("hs256")) {
                        KEY_MAP.put(fileName, new SecretKeySpec(bytes, "HmacSHA256"));
                    } else {
                        KEY_MAP.put(fileName, new SecretKeySpec(bytes, "AES"));
                    }
                }
                return KEY_MAP.get(keyName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static String convertDataToString(Map<String, Object> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public static Map<String, Object> convertStringToData(String data) {
        try {
            return new ObjectMapper().readValue(data, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
