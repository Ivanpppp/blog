package com.ivan.blog.web.admin;


import com.ivan.blog.po.Blog;
import com.ivan.blog.service.BlogService;
import com.ivan.blog.service.TypeService;
import com.ivan.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog,Model model) {
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        model.addAttribute("types",typeService.listType());
        return "admin/blogs";
    }

    @GetMapping("/blogs/search")
    public String search(Model model, @PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog) {
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "/admin/blogs :: blogList";
    }
}
