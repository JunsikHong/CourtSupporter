package com.court.supporter.aws.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_005VO;

import io.awspring.cloud.sqs.annotation.SqsListener;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;


@Service
@Qualifier("emailService")
public class EmailService {

	// access key
	@Value("${aws_access_key_id}")
	private String aws_access_key_id;

	// secret key
	@Value("${aws_secret_access_key}")
	private String aws_secret_access_key;

	// 평가완료 이메일 발송
	public int sendEvaluationEmail(TB_005VO tb_005VO, String aplcn_dtls_sts) {

		AwsBasicCredentials credentials = AwsBasicCredentials.create(aws_access_key_id, aws_secret_access_key);
		SnsClient snsClient = SnsClient.builder().region(Region.AP_NORTHEAST_2)
				.credentialsProvider(StaticCredentialsProvider.create(credentials)).build();

		String topicArn = "여기에 토픽.fifo arn 넣어야 함.";

		try {
			String subject = "재판조력자 신청 결과";
			String dedupId = UUID.randomUUID().toString();
			String groupId = "Group_Name"; // 동일한 내용들을 동일한 그룹명으로 묶어줌
			String payload = tb_005VO.getUser_id() + " 님의 " + tb_005VO.getAplcn_dtls_proper_num() + " 신청결과는 " + aplcn_dtls_sts + "입니다.";
			String attributeName = "application-complete-result"; // 정책키
			String attributeValue = "y"; // 정책값 (해당정책을 허용한 queue에게만 전달)
			
			MessageAttributeValue msgAttValue = MessageAttributeValue.builder().dataType("String")
					.stringValue(attributeValue).build();

			Map<String, MessageAttributeValue> attributes = new HashMap<>();
			attributes.put(attributeName, msgAttValue);
			PublishRequest pubRequest = PublishRequest.builder().topicArn(topicArn).subject(subject).message(payload)
					.messageGroupId(groupId).messageDeduplicationId(dedupId).messageAttributes(attributes).build();

			snsClient.publish(pubRequest);
			return 1;
		} catch (SnsException e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			return 0;
		}
	}

	//이메일 풀링
	@SqsListener("${aws_sqs_url}")
	public void listen(String message) {
	  System.out.println(message);
	}
}
