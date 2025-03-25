package io.github.fogeid.testeSeplag.services;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    /**
     * Faz o upload de um arquivo para o MinIO e retorna o identificador único (hash) do arquivo.
     *
     * @param file O arquivo a ser enviado.
     * @return O identificador único (hash) do arquivo no MinIO.
     * @throws Exception Se ocorrer um erro durante o upload.
     */
    public String uploadFile(MultipartFile file) throws Exception {
        try {
            // Gerar um identificador único para o arquivo
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            // Fazer o upload do arquivo para o MinIO
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
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

    /**
     * Faz o download de um arquivo do MinIO com base no identificador (hash).
     *
     * @param fileName O identificador único (hash) do arquivo.
     * @return O InputStream do arquivo.
     * @throws Exception Se ocorrer um erro durante o download.
     */
    public InputStream downloadFile(String fileName) throws Exception {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
        } catch (MinioException e) {
            throw new Exception("Erro ao fazer download do arquivo do MinIO: " + e.getMessage(), e);
        }
    }
}