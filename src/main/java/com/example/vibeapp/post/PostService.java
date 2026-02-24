package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostListDto> getPosts(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findAll(offset, size).stream()
                .map(PostListDto::from)
                .collect(Collectors.toList());
    }

    public int getTotalPages(int size) {
        int total = postRepository.count();
        return (int) Math.ceil((double) total / size);
    }

    public PostResponseDto getPost(Long no) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        post.setViews(post.getViews() + 1);
        postRepository.update(post);
        return PostResponseDto.from(post);
    }

    public void update(Long no, PostUpdateDto dto) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.update(post);
    }

    public void save(PostCreateDto dto) {
        Post post = dto.toEntity();
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
