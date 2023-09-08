
package com.court.supporter.aws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.lambda.LambdaClient;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class Awsconfig {

	
	//엑세스키
	@Value("${aws_access_key_id}")
	private String aws_access_key_id;
	//시크릿키
	@Value("${aws_secret_access_key}")
	private String aws_secret_access_key;

	//자격증명객체
	@Bean
	public StaticCredentialsProvider staticCredentialsprovider() {
		AwsBasicCredentials credentials = AwsBasicCredentials.create(aws_access_key_id, aws_secret_access_key);
		StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
		return credentialsProvider;
	}
	
	//
	@Bean
	public S3Client S3client() {
		
		Region region = Region.AP_NORTHEAST_2;
		
		S3Client s3 = S3Client.builder()
				.region(region)
				.credentialsProvider( staticCredentialsprovider() )
				.build();
		return s3;
		
	}
	
	//람다클라이언트
	@Bean
	public LambdaClient lambdaClient() {
		
		AwsBasicCredentials credentials = AwsBasicCredentials.create(aws_access_key_id, aws_secret_access_key);
		LambdaClient awsLambda = LambdaClient.builder()
				.region(Region.AP_NORTHEAST_2)// 서울 리전
				.credentialsProvider(StaticCredentialsProvider.create(credentials))
				.build();
		
		return awsLambda;
	}
}

