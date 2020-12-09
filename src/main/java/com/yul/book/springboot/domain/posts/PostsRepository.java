package com.yul.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//interface로 생성. JpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동 생성
//Entity 클래스와 Repository는 도메인 패키지에서 함께 관리
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
