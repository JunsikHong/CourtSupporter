package com.court.supporter.user.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_018VO;

import lombok.RequiredArgsConstructor;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
   
   private final UserMapper userMapper;
   private final JavaMailSender javaMailSender;

   //회원가입
   @Override
   public int join(TB_001VO tb_001vo) {
      return userMapper.join(tb_001vo);
   }

   //로그인 시 아이디로 회원 찾기
   @Override
   public TB_018VO findByMemberId(String memberId) {
      return userMapper.findByMemberId(memberId).orElseThrow(RuntimeException::new);
   }

	//세션에서 claims 받을 때 claims의 proper_num로 회원 찾기
	@Override
	public TB_018VO findByMemberProperNum(String memberProperNum) {
		return userMapper.findByMemberProperNum(memberProperNum).orElseThrow(RuntimeException::new);
	}

	//회원가입 시 아이디 중복 확인
	@Override
	public int checkId(String userId) {
		return userMapper.checkId(userId);
	}

	//로그인 시 아이디로 user 탈퇴 여부 확인
	@Override
	public TB_001VO findByUserId(String userId) {
		return userMapper.findByUserId(userId);
	}

	//메일 인증
	@Override
	public String sendMail(TB_001VO tb_001vo) {
		int number = (int)(Math.random() * 90000) + 100000;
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			message.setRecipients(MimeMessage.RecipientType.TO, tb_001vo.getUser_email());
			message.setFrom("904lhw@gmail.com");
			message.setSubject("메일 인증 두둔");
			
			String body = "";
			body += "<h3>요청하신 인증번호입니다.</h3>";
			body += "<h1>" + number + "</h1>";
			body += "<h3>감사합니다.</h3>";
			
			message.setText(body, "UTF-8", "html");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
		return String.valueOf(number);
	}

	//아이디 찾기 정보에 맞는 user 확인
	@Override
	public String findUsersForId(TB_001VO tb_001vo) {
		return userMapper.findUsersForId(tb_001vo);
	}

	//비밀번호 찾기 정보에 맞는 user 확인
	@Override
	public String findUsersForPw(TB_001VO tb_001vo) {
		return userMapper.findUsersForPw(tb_001vo);
	}

}

