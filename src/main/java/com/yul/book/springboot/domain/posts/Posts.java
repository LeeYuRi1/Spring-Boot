package com.yul.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //Entity 클래스에서는 Setter 메소드 만들지 않기! 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드 추가
@NoArgsConstructor //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스, 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭(SalesManager.java -> sales_manager table)
public class Posts {

    @Id //해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙. GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨
    private Long id;

    @Column(length = 500, nullable = false)//테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됨. 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용. 문자열은 VARCHAR(255)가 기본값인데 사이즈를 500으로 늘림
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) //타입을 TEXT로 변경
    private String content;

    private String author;

    // 생성자, 빌더는 생성 시점에 값을 채워주는 역할을 함. 다만, 생성자의 경우 지금 채워야 할 필드가 무엇인지 명확히 지정할 수 없음
    @Builder //해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
