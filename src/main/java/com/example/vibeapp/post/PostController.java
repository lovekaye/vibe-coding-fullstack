package com.example.vibeapp.post;

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
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        List<Post> posts = postService.getPosts(page, pageSize);
        int totalPages = postService.getTotalPages(pageSize);

        // Add attributes for pagination handling in view
        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "post/posts";
    }

    @GetMapping("/posts/new")
    public String newForm() {
        return "post/post_new_form";
    }

    @PostMapping("/posts/add")
    public String add(@RequestParam("title") String title,
            @RequestParam("content") String content) {
        postService.save(title, content);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        Post post = postService.getPost(no);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable("no") Long no, Model model) {
        Post post = postService.getPost(no);
        model.addAttribute("post", post);
        return "post/post_edit_form";
    }

    @PostMapping("/posts/{no}/save")
    public String edit(@PathVariable("no") Long no,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {
        postService.update(no, title, content);
        return "redirect:/posts/" + no;
    }

    @PostMapping("/posts/{no}/delete")
    public String delete(@PathVariable("no") Long no) {
        postService.delete(no);
        return "redirect:/posts";
    }
}
