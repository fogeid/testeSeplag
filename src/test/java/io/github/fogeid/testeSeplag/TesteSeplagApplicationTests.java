package io.github.fogeid.testeSeplag;

import io.minio.MinioClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {TesteSeplagApplication.class, TesteSeplagApplicationTests.TestConfig.class})
@ComponentScan(basePackages = "io.github.fogeid.testeSeplag")
class TesteSeplagApplicationTests {

	@Test
	void contextLoads() {
	}

	@Configuration
	static class TestConfig {

		@Bean
		public MinioClient minioClient(@Value("${minio.url}") String url,
									   @Value("${minio.access-key}") String accessKey,
									   @Value("${minio.secret-key}") String secretKey) {
			return MinioClient.builder()
					.endpoint(url)
					.credentials(accessKey, secretKey)
					.build();
		}
	}
}
