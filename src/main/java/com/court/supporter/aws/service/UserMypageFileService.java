package com.court.supporter.aws.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;


@Service
public class UserMypageFileService {

   @Autowired
   private S3Client s3;

   @Autowired
   private LambdaClient lambdaClient;

   // 버킷명
   @Value("${aws_bucket_name}")
   private String aws_bucket_name;

   // 키 id
   @Value("${aws_access_key_id}")
   private String aws_access_key_id;

   @Value("${aws_secret_access_key}")
   private String aws_secret_access_key;

   public void getBucketList() {

      // s3기능사용

      // List buckets
      ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
      ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
      listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));

   }


}