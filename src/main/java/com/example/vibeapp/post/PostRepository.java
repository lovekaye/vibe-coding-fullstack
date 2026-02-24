package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Optional;

@Mapper
public interface PostRepository {
    void save(Post post);

    List<Post> findAll(@Param("offset") int offset, @Param("limit") int limit);

    int count();

    Optional<Post> findByNo(Long no);

    void update(Post post);

    void deleteByNo(Long no);
}
