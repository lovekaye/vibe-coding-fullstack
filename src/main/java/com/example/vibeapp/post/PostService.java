package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;

    public PostService(PostRepository postRepository, PostTagRepository postTagRepository) {
        this.postRepository = postRepository;
        this.postTagRepository = postTagRepository;
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

        List<String> tags = postTagRepository.findAllByPostNo(no).stream()
                .map(PostTag::getTagName)
                .collect(Collectors.toList());

        return PostResponseDto.from(post, tags);
    }

    @Transactional
    public void update(Long no, PostUpdateDto dto) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.update(post);

        // Update tags: Delete all and re-insert
        postTagRepository.deleteByPostNo(no);
        saveTags(no, dto.tags());
    }

    @Transactional
    public void save(PostCreateDto dto) {
        Post post = dto.toEntity();
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);
        postRepository.save(post);

        saveTags(post.getNo(), dto.tags());
    }

    private void saveTags(Long postNo, String tagsStr) {
        if (tagsStr == null || tagsStr.isBlank()) {
            return;
        }

        try {
            String[] tags = tagsStr.split(",");
            for (String tag : tags) {
                String trimmedTag = tag.trim();
                if (!trimmedTag.isEmpty()) {
                    postTagRepository.save(new PostTag(null, postNo, trimmedTag));
                }
            }
        } catch (Exception e) {
            try (PrintWriter out = new PrintWriter(new FileWriter("debug_error.log", true))) {
                out.println("Error saving tags for postNo: " + postNo);
                e.printStackTrace(out);
            } catch (Exception ex) {
                // ignore
            }
            throw e;
        }
    }

    public void delete(Long no) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        postRepository.deleteByNo(no);
    }
}
