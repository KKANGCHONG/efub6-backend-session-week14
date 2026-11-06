package com.efub.api.post;

import com.efub.api.post.dto.PostCreateRequest;
import com.efub.api.post.dto.PostResponse;
import com.efub.core.post.Post;
import com.efub.core.post.PostRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    // blog-core의 리포지토리를 그대로 주입
    private final PostRepository postRepository;

    @Transactional
    public PostResponse create(PostCreateRequest request) {
        // TODO 5-1. request를 엔티티로 바꿔 저장하고, PostResponse.from(...)으로 돌려주세요.
        Post saved = postRepository.save(request.toEntity());
        return PostResponse.from(saved);
    }

    public List<PostResponse> findVisiblePosts() {
        // TODO 5-2. TODO 3에서 만든 리포지토리 메서드로 숨기지 않은 글을 가져와 PostResponse 목록으로 바꾸세요.
        return postRepository.findAllByHiddenFalseOrderByIdDesc().stream()
                .map(PostResponse::from)
                .toList();
    }

    public PostResponse findOne(Long id) {
        Post post = postRepository.findById(id)
                .filter(p -> !p.isHidden())
                .orElseThrow(() -> new NoSuchElementException("글이 없습니다. id="+id));

        return PostResponse.from(post);
    }
}
