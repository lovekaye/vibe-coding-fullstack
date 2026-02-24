package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostRepository {
    List<Post> findAll(@Param("offset") int offset, @Param("limit") int limit);

    int count();

    Optional<Post> findByNo(Long no);

    void save(Post post);

    void update(Post post);

    void delete(Post post);

    void deleteByNo(Long no);
}
