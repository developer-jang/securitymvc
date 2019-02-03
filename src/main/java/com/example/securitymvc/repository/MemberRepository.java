package com.example.securitymvc.repository;


import com.example.securitymvc.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
}
