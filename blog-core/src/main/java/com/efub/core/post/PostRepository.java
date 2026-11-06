package com.efub.core.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    //  TODO 3. 숨기지 않은(hidden = false) 글을 id 내림차순으로 찾는 메서드를 선언하세요.
    List<Post> findAllByHiddenFalseOrderByIdDesc();
}
