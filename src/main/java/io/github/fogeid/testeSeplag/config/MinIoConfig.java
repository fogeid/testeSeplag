package io.github.fogeid.testeSeplag.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "minio.enabled", havingValue = "true")
public class MinIoConfig {
    private static final Logger log = LoggerFactory.getLogger(MinIoConfig.class);

    @Value("${minio.url}")
    private String url;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucket;

    @Bean
    public MinioClient minioClient() {
        log.info("Inicializando MinIO com URL: {}, Access Key: {}, Bucket: {}", url, accessKey, bucket);
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(url)
                    .credentials(accessKey, secretKey)
                    .build();

            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
                log.info("Bucket {} criado com sucesso.", bucket);
            } else {
                log.info("Bucket {} já existe.", bucket);
            }

            return minioClient;
        } catch (Exception e) {
            log.error("Erro ao inicializar o MinIO ou criar o bucket: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao configurar o MinIO", e);
        }
    }
}
