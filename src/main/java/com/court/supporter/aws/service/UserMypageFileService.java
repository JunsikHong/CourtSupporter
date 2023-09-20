package com.court.supporter.aws.service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;


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

   	//업로드 경로
 	private String uploadPath = "usermypage/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

 	
 	public String imgFileRegist(MultipartFile file) {
 		String savepath = "";
 		try {
 			//원본파일이름
 			String originName = file.getOriginalFilename();

 			//브라우저 별로 파일의 경로가 다를 수 있기 때문에 \\기준으로 파일명만 잘라서 다시 저장
 			String filename = originName.substring(originName.lastIndexOf("\\")+1);

 			//동일한 파일 재업로드시 기존파일을 덮어씌우므로 난수 이름으로 파일명을 바꿔서 올림
 			String uuid = UUID.randomUUID().toString();

 			//파일 저장 경로 
 			savepath = uploadPath+"/"+uuid+"_"+filename;

 			// S3에 업로드
 			PutObjectRequest request = PutObjectRequest.builder()
 					.bucket(aws_bucket_name)
 					.key(savepath)
 					.build();

 			PutObjectResponse response = s3.putObject(request, RequestBody.fromBytes(file.getBytes()));

 		} catch (Exception e) {
 			e.printStackTrace();
 		}

 		return savepath;
 	}
}