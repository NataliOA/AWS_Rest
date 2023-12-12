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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class AWSConfig {

    private static final String CREDENTIALS_FILE_PATH = "PrimeraEntrega/AWS_Creds.txt";

    private String accessKey;
    private String secretKey;
    private String sessiontoken;
    @Value("${amazon.aws.sessiontoken}")
    private String region;

    public AWSConfig() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(CREDENTIALS_FILE_PATH));
        // Asumiendo que el archivo tiene el formato especificado previamente
        accessKey = lines.get(0).trim();
        secretKey = lines.get(1).trim();
        sessiontoken = lines.get(2).trim();
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
