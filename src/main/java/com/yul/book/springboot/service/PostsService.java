package com.yul.book.springboot.service;

import com.yul.book.springboot.domain.posts.Posts;
import com.yul.book.springboot.domain.posts.PostsRepository;
import com.yul.book.springboot.web.dto.PostsResponseDto;
import com.yul.book.springboot.web.dto.PostsSaveRequestDto;
import com.yul.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        //update 기능에서 데이터베이스에 쿼리를 날리는 부분이 없음. 가능한 이유는 JPA의 영속성 컨텍스트 때문
        //영속성 컨텍스트: 엔티티를 영구 저장하는 환경
        //더티 체킹
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}
