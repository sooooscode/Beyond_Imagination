package com.beyondImagination.bookChatter.repository;

import com.beyondImagination.bookChatter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> { // ID 타입을 String으로 수정

  // 사용자 이름으로 User를 조회하는 메서드
  Optional<User> findByUsername(String username);

  // 이메일로 User를 조회하는 메서드
  Optional<User> findByEmail(String email);

  // 특정 조건에 맞는 추가적인 쿼리 메서드를 작성할 수 있습니다.
}
