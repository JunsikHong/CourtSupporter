package com.court.supporter.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.court.supporter.command.TB_018VO;

import lombok.Getter;

@Getter
public class DefaultUserDetails implements UserDetails {
   private final TB_018VO tb_018vo;
    
    public DefaultUserDetails(TB_018VO tb_018vo) {
       this.tb_018vo = tb_018vo;
    }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<GrantedAuthority> list = new ArrayList<>();
      list.add(new GrantedAuthority() {
         
         @Override
         public String getAuthority() {
            return tb_018vo.getMember_role();
         }
      });
      return list;
   }

   @Override
   public String getPassword() {
      // TODO Auto-generated method stub
      return tb_018vo.getMember_password();
   }

   @Override
   public String getUsername() {
      // TODO Auto-generated method stub
      return tb_018vo.getMember_proper_num();
   }

   @Override
   public boolean isAccountNonExpired() {
      // TODO Auto-generated method stub
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      // TODO Auto-generated method stub
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      // TODO Auto-generated method stub
      return true;
   }

   @Override
   public boolean isEnabled() {
      // TODO Auto-generated method stub
      return true;
   }

}