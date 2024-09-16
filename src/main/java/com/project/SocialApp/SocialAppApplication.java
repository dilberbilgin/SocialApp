package com.project.SocialApp;

//import com.project.SocialApp.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialAppApplication.class, args);

		// TODO: 1.09.2024 DockerFile ve docker-compose.yml dosyalarini ve dizinleri kontrol et
		// TODO: 1.09.2024 exceptionlar duzenlenecek 
		// TODO: 1.09.2024 security 
		// TODO: 1.09.2024 react ile frontend tamamlanacak

	}



}
