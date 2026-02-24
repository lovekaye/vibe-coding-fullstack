package com.example.vibeapp.post;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 10; i++) {
            postRepository.save(new Post(
                    (long) i,
                    "게시글 제목 " + i,
                    "게시글 내용입니다. " + i,
                    LocalDateTime.now().minusDays(10 - i),
                    LocalDateTime.now().minusDays(10 - i),
                    i * 10));
        }
    }

    public List<Post> getPosts(int page, int size) {
        return postRepository.findAll(page, size);
    }

    public int getTotalPages(int size) {
        int total = postRepository.count();
        return (int) Math.ceil((double) total / size);
    }

    public Post getPost(Long no) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        post.setViews(post.getViews() + 1);
        return post;
    }

    public void update(Long no, String title, String content) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedAt(LocalDateTime.now());
    }

    public void save(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);
        postRepository.save(post);
    }

    public void delete(Long no) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        postRepository.delete(post);
    }
}
