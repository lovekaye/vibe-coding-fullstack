package com.example.vibeapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        Post post = postService.getPostByNo(no);
        model.addAttribute("post", post);
        return "post_detail";
    }

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable("no") Long no, Model model) {
        Post post = postService.getPostByNo(no);
        model.addAttribute("post", post);
        return "post_edit";
    }

    @PostMapping("/posts/{no}/edit")
    public String edit(@PathVariable("no") Long no, 
                       @RequestParam("title") String title, 
                       @RequestParam("content") String content) {
        postService.updatePost(no, title, content);
        return "redirect:/posts/" + no;
    }
}
