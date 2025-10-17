package com.project_EyeCare.EyeCare.controller.Account;

import com.project_EyeCare.EyeCare.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer>{
}
