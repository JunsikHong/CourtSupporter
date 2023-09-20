package com.court.supporter.aws.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_005VO;

@Service
@Qualifier("emailService")
public class EmailService {

	@Autowired
	JavaMailSender emailsender;

	public MimeMessage createMessage(TB_001VO tb_001VO, TB_005VO tb_005VO, String aplicn_dtls_sts) throws MessagingException, UnsupportedEncodingException {

		MimeMessage message = emailsender.createMimeMessage();

		message.addRecipients(RecipientType.TO, tb_001VO.getUser_email());// 보내는 대상
		message.setSubject(tb_005VO.getAnnounce_title() + "재판조력자 선발 결과");// 제목
		
		String result = "";
		if(aplicn_dtls_sts.equals("04")) {
			result = "서류반려(1차 불합격)";
		} else if(aplicn_dtls_sts.equals("07")) {
			result = "불합격(최종 불합격)";
		} else if(aplicn_dtls_sts.equals("05")) {
			result = "1차합격";
		} else if(aplicn_dtls_sts.equals("08")) {
			result = "최종합격";
		}
		
		String msgg = "";
		msgg += "<div style='margin:100px;'>";
		msgg += "<h1> " + tb_001VO.getUser_name() + "님 안녕하세요</h1>";
		msgg += "<p>재판조력자 선발 담당자입니다.</p>";
		msgg += "<p>" + tb_005VO.getAnnounce_title() + "공고에 대한 신청 결과를 알려드립니다</p>";
		msgg += "<br>";
		msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		msgg += "<h3 style='color:blue;'>1차 평가 결과 " + result + "입니다.</h3>";
		msgg += "</div>";
		message.setText(msgg, "utf-8", "html");
		message.setFrom(new InternetAddress("CourtSupporter@naver.com", "CourtSupporter_ADMIN"));// 보내는 사람

		return message;
	}
}