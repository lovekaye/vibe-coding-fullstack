package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostTagRepository {
    void save(PostTag postTag);

    void deleteById(Long id);

    void deleteByPostNo(Long postNo);
}
