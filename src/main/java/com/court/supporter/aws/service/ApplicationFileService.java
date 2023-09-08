package com.court.supporter.aws.service;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.aws.config.Awsconfig;
import com.court.supporter.command.TB_009VO;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import software.amazon.awssdk.services.lambda.model.LambdaException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Delete;
import software.amazon.awssdk.services.s3.model.DeleteObjectsRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectsResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.services.s3.model.PutObjectAclResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service("applicationFileService")
public class ApplicationFileService {

	@Autowired
	private S3Client s3;

	@Autowired
	private LambdaClient lambdaClient;

	// 버킷명
	@Value("${aws_bucket_name}")
	private String aws_bucket_name;

	public void getBucketList() {

		// s3기능사용

		// List buckets
		ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
		ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
		listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));

	}
	//------------------------------------------------------------------------------------------------------------------------------------
	//업로드 경로
	private String uploadPath = "Application/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));


	public List<String> ApplicationFileRegist(List<MultipartFile> list, String user_id) {
		
		List<String> filelist = new ArrayList();
		
		try {
			//업로드 처리
			for(MultipartFile file : list) {
				
				//원본파일이름
				String originName = file.getOriginalFilename();
				
				//브라우저 별로 파일의 경로가 다를 수 있기 때문에 \\기준으로 파일명만 잘라서 다시 저장
				String filename = originName.substring(originName.lastIndexOf("\\")+1);
				//동일한 파일 재업로드시 기존파일을 덮어씌우므로 난수 이름으로 파일명을 바꿔서 올림
				String uuid = UUID.randomUUID().toString();
				
				//파일 저장 경로 
				String savepath = uploadPath+"/"+user_id+"/"+uuid+"_"+filename;
				
				// S3에 업로드
				if (!filename.isEmpty()) {
					PutObjectRequest request = PutObjectRequest.builder().bucket(aws_bucket_name).key(savepath).build();

					PutObjectResponse response = s3.putObject(request, RequestBody.fromBytes(file.getBytes()));

					filelist.add(savepath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filelist;
	}


	//------------------------------------------------------------------------------------------------------------------------------------
    //s3 파일 삭제
	public void ApplicationFileDelete(List<String> keyNames) {

        ArrayList<ObjectIdentifier> keys = new ArrayList<>();
        for(String file : keyNames) {
        	//삭제할 객체
	        ObjectIdentifier objectId = ObjectIdentifier.builder().key(file).build();
	
	        //리스트에 추가
	        keys.add(objectId);
        }
        //s3 파일 삭제
        Delete del = Delete.builder().objects(keys).build();

        try {
            DeleteObjectsRequest multiObjectDeleteRequest = DeleteObjectsRequest.builder().bucket(aws_bucket_name).delete(del).build();

            //삭제요청
            DeleteObjectsResponse result = s3.deleteObjects(multiObjectDeleteRequest);
            
            System.out.println("삭제완료");
            System.out.println(result.sdkHttpResponse().statusCode());
        
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

}