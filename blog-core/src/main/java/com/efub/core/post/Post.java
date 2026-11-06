package com.efub.core.post;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(nullable = false)
    private boolean hidden = false; // 관리자가 숨긴 글인지

    private LocalDateTime createdAt;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // 시스템이 없어도 성립하는 규칙 = 핵심 비즈니스 로직 (엔티티에 둔다)
    public void hide() {
        this.hidden = true;
    }
}