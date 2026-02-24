package com.example.vibeapp;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    public void save(Post post) {
        if (post.getNo() == null) {
            Long nextNo = posts.stream()
                    .mapToLong(Post::getNo)
                    .max()
                    .orElse(0L) + 1;
            post.setNo(nextNo);
            posts.add(post);
        } else {
            findByNo(post.getNo()).ifPresent(p -> {
                int index = posts.indexOf(p);
                posts.set(index, post);
            });
        }
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    public java.util.Optional<Post> findByNo(Long no) {
        return posts.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst();
    }

    public void delete(Post post) {
        posts.removeIf(p -> p.getNo().equals(post.getNo()));
    }
}
