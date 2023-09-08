package com.court.supporter.command;


import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TB_018VO {
	private String member_id;
	private String member_password;
	private String member_role;
	
	public SimpleGrantedAuthority getMember_role() {
		return new SimpleGrantedAuthority(member_role);
    }
}
