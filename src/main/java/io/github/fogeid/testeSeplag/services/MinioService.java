package io.github.fogeid.testeSeplag.services;

import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioService {
    private static final Logger log = LoggerFactory.getLogger(MinioService.class);

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    private final boolean minioEnabled;

    public MinioService(
            MinioClient minioClient,
            @Value("${minio.bucket}") String bucketName,
            @Value("${minio.enabled}") boolean minioEnabled) {
        this.minioClient = minioClient;
        this.bucket = bucketName;
        this.minioEnabled = minioEnabled;
        log.info("MinioService inicializado. minioEnabled: {}, bucketName: {}", minioEnabled, bucketName);
    }

    public String uploadFile(MultipartFile file) throws Exception {
        try {
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return fileName;
        } catch (MinioException e) {
            throw new Exception("Erro ao fazer upload do arquivo para o MinIO: " + e.getMessage(), e);
        }
    }

    public InputStream downloadFile(String fileName) throws Exception {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(fileName)
                            .build()
            );
        } catch (MinioException e) {
            throw new Exception("Erro ao fazer download do arquivo do MinIO: " + e.getMessage(), e);
        }
    }

    public String getPresignedUrl(String objectName) throws Exception {
        if (!minioEnabled || minioClient == null) {
            log.warn("MinIO está desativado ou não configurado. Não é possível gerar URL temporária.");
            return null;
        }

        try {
            int expiryInSeconds = 5 * 60;
            log.info("Gerando URL temporária para o objeto {} com expiração de {} segundos.", objectName, expiryInSeconds);

            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(objectName)
                            .expiry(expiryInSeconds)
                            .build());
            log.info("URL temporária gerada para o objeto {}: {}", objectName, url);
            return url;
        } catch (Exception e) {
            log.error("Erro ao gerar URL temporária para o objeto {}: {}", objectName, e.getMessage(), e);
            throw new RuntimeException("Falha ao gerar URL temporária", e);
        }
    }
}