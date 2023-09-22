package com.court.supporter.aws.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.court.supporter.announce.service.AnnounceService;
import com.court.supporter.aws.config.Awsconfig;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_017VO;

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
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
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

@Service("announceFileService")
public class AnnounceFileService {

   //   //엑세스키
   //   @Value("${aws_access_key_id}")
   //   private String aws_access_key_id;
   //   //시크릿키
   //   @Value("${aws_secret_access_key}")
   //   private String aws_secret_access_key;

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
   
    @Autowired
	@Qualifier("announceService")
	private AnnounceService announceService;

   public void getBucketList() {

      // s3기능사용

      // List buckets
      ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
      ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
      listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));

   }
   //------------------------------------------------------------------------------------------------------------------------------------
     
   /**
    * S3 bucket 파일 다운로드
  
   public ResponseEntity<byte[]> getObject(String storedFileName) throws IOException {
       S3Object o = s3.getObject(new GetObjectRequest(bucket, storedFileName));
       S3ObjectInputStream objectInputStream = ((S3Object) o).getObjectContent();
       byte[] bytes = IOUtils.toByteArray(objectInputStream);

       String fileName = URLEncoder.encode(storedFileName, "UTF-8").replaceAll("\\+", "%20");
       HttpHeaders httpHeaders = new HttpHeaders();
       httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
       httpHeaders.setContentLength(bytes.length);
       httpHeaders.setContentDispositionFormData("attachment", fileName);

       return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);

   }
   */
   
   //업로드 경로
   private String uploadPath = "announce/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));


   public List<String> announcefileRegist(List<MultipartFile> list) {
	   
	   List<String> fileList = new ArrayList<>();
	
      try {
         //업로드 처리
         for(MultipartFile file : list) {
            
            //원본파일이름
            String originName = file.getOriginalFilename(); //originName
            
            //브라우저 별로 파일의 경로가 다를 수 있기 때문에 \\기준으로 파일명만 잘라서 다시 저장
            String filename = originName.substring(originName.lastIndexOf("\\")+1);
            
            //동일한 파일 재업로드시 기존파일을 덮어씌우므로 난수 이름으로 파일명을 바꿔서 올림
            String uuid = UUID.randomUUID().toString();
            
            //파일 저장 경로 
            String savepath = uploadPath+"/"+uuid+"_"+filename;
            
            // S3에 업로드
            PutObjectRequest request = PutObjectRequest.builder()
                  .bucket(aws_bucket_name)
                  .key(savepath)
                  .build();
            
            PutObjectResponse response = s3.putObject(request, RequestBody.fromBytes(file.getBytes()));
            
            fileList.add(savepath);
            
//            int announce_proper_num = 0; 
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            if (attributes != null) {
//                HttpServletRequest requests = attributes.getRequest();
//                announce_proper_num = Integer.parseInt(requests.getParameter("announce_proper_num"));
//            }
         
       
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return fileList;
   }


   //------------------------------------------------------------------------------------------------------------------------------------

   public void announceFileDelete (List<TB_017VO> filevo) {
	      
	      ArrayList<ObjectIdentifier> keys = new ArrayList<>();
	      
	      for(TB_017VO file_path : filevo) {
	         //삭제할 객체 경로
	         String filepath = file_path.getFile_path()+file_path.getAnnounce_file_uuid()+"_"+file_path.getOriginal_file_name();
	         
	         //삭제할 객체
	           ObjectIdentifier objectId = ObjectIdentifier.builder().key(filepath).build();
	   
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
   
   
   //------------------------------------------------------------------------------------------------------------------------------------
   // s3파일업로드
   public void putS3Object(String originName, byte[] originData) {

   }

   // 버킷의 객체 목록보기
   public void listBucketObjects() {

      try {
         ListObjectsRequest listObjects = ListObjectsRequest.builder().bucket(aws_bucket_name).build();

         ListObjectsResponse res = s3.listObjects(listObjects);
         List<S3Object> objects = res.contents();
         for (S3Object myValue : objects) {
            System.out.print("\n The name of the key is " + myValue.key());
            System.out.print("\n The object is " + calKb(myValue.size() / 1024) + " KBs");
            System.out.print("\n The owner is " + myValue.owner());
         }

      } catch (S3Exception e) {
         System.err.println(e.awsErrorDetails().errorMessage());
         // System.exit(1);
      }
   }

   // convert bytes to kbs.
   private static long calKb(Long val) {
      return val / 1024;
   }

   // 여러 객체 삭제
   public void deleteBucketObjects(String keyName) {
      System.out.println(keyName);
      // 여기는 업로드 파트
      // Upload three sample objects to the specfied Amazon S3 bucket.
      ArrayList<ObjectIdentifier> keys = new ArrayList<>();

      // 삭제할 객체
      ObjectIdentifier objectId = ObjectIdentifier.builder().key(keyName).build();

      // 리스트에 추가
      keys.add(objectId);

      // Delete multiple objects in one request.
      Delete del = Delete.builder().objects(keys).build();

      try {
         DeleteObjectsRequest multiObjectDeleteRequest = DeleteObjectsRequest.builder().bucket(aws_bucket_name)
               .delete(del).build();

         // 삭제요청
         DeleteObjectsResponse result = s3.deleteObjects(multiObjectDeleteRequest);
         System.out.println("Multiple objects are deleted!");
         System.out.println();

      } catch (S3Exception e) {
         System.err.println(e.awsErrorDetails().errorMessage());
         System.exit(1);
      }
   }

   ///////////////////// lambda_호출//////////////////////////////////
   // 람다 호출하기
   public void invokeFunction() {

      // aws sdk => 자격증명 얻음 => 실행시킬 서비스 객체 생성 => 호출 // 항상 이 패턴

      //실행시킬 람다함수
      String functionName = "demo-api-hello";


      InvokeResponse res = null ;
      try {
         //Need a SdkBytes instance for the payload
         String json = "{\"Hello \":\"람다야 이거좀 받아가라\"}";
         SdkBytes payload = SdkBytes.fromUtf8String(json) ;

         //Setup an InvokeRequest
         InvokeRequest request = InvokeRequest.builder()
               .functionName(functionName)
               .payload(payload)
               .build();

         res = lambdaClient.invoke(request);

         String value = res.payload().asUtf8String() ;
         System.out.println(value);

      } catch(LambdaException e) {
         System.err.println(e.getMessage());
         //System.exit(1);
      }
   }

}