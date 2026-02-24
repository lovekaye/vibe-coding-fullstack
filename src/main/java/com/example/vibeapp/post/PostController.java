package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

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
        List<PostListDto> posts = postService.getPosts(page, pageSize);
        int totalPages = postService.getTotalPages(pageSize);

        // Add attributes for pagination handling in view
        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "post/posts";
    }

    @GetMapping("/posts/new")
    public String newForm(Model model) {
        model.addAttribute("postCreateDto", new PostCreateDto("", "", ""));
        return "post/post_new_form";
    }

    @PostMapping("/posts/add")
    public String add(@Valid @ModelAttribute("postCreateDto") PostCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/post_new_form";
        }
        postService.save(dto);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        PostResponseDto post = postService.getPost(no);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable("no") Long no, Model model) {
        PostResponseDto postDetail = postService.getPost(no);
        String tagsStr = String.join(", ", postDetail.tags());
        PostUpdateDto dto = new PostUpdateDto(postDetail.title(), postDetail.content(), tagsStr);

        model.addAttribute("postUpdateDto", dto);
        model.addAttribute("no", no);
        model.addAttribute("post", postDetail);
        return "post/post_edit_form";
    }

    @PostMapping("/posts/{no}/save")
    public String edit(@PathVariable("no") Long no,
            @Valid @ModelAttribute("postUpdateDto") PostUpdateDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            PostResponseDto postDetail = postService.getPost(no);
            model.addAttribute("no", no);
            model.addAttribute("post", postDetail);
            return "post/post_edit_form";
        }
        postService.update(no, dto);
        return "redirect:/posts/" + no;
    }

    @PostMapping("/posts/{no}/delete")
    public String delete(@PathVariable("no") Long no) {
        postService.delete(no);
        return "redirect:/posts";
    }
}
