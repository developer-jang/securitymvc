package com.example.securitymvc;

import com.example.securitymvc.domain.SecurityUser;
import com.example.securitymvc.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return memberRepository.findById(username);
//        error : User 형으로 리턴됨...userDetails 타입으로 리턴 불가

        return memberRepository.findById(username).filter(member -> member != null).map(member -> new SecurityUser(member)).get();
    }
}
