package com.estudiantes.restControl.config;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Configuration
public class AWSConfig {

    private static final String CREDENTIALS_FILE_PATH = "AWS_Creds.txt";

    private String accessKey;
    private String secretKey;
    private String sessiontoken;
    @Value("${amazon.aws.region}")
    private String region;

    public AWSConfig() {
        Resource resource = new ClassPathResource(CREDENTIALS_FILE_PATH);
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            accessKey = reader.readLine().trim();
            secretKey = reader.readLine().trim();
            sessiontoken = reader.readLine().trim();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }

    private AWSStaticCredentialsProvider getCredentialsProvider() {
        AWSCredentials awsCredentials = new BasicSessionCredentials(accessKey, secretKey,sessiontoken);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(getCredentialsProvider())
                .build();
    }

    @Bean
    public AmazonSNS amazonSNS() {
        return AmazonSNSClientBuilder.standard()
                .withRegion(region)
                .withCredentials(getCredentialsProvider())
                .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(amazonDynamoDB());
    }

    private AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(region)
                .withCredentials(getCredentialsProvider())
                .build();
    }
}
