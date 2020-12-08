package com.ivan.blog.service;

import com.ivan.blog.po.Comment;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
