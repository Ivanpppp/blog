package com.ivan.blog.service;

import com.ivan.blog.NotFoundException;
import com.ivan.blog.dao.BlogRepository;
import com.ivan.blog.po.Blog;
import com.ivan.blog.po.Type;
import com.ivan.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }


    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog blog1 = blogRepository.getOne(id);
        if (blog1 == null){
            throw new NotFoundException("未找到该博客");
        }
        BeanUtils.copyProperties(blog1,blog);
        return blogRepository.save(blog1);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog getAndConvert(Long id) {
        return null;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            /**
             *
             * @param root  代表需要查询的对象
             * @param criteriaQuery     查询的条件容器
             * @param criteriaBuilder   设置具体条件的表达式
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                // 标题的判断
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                // 分类判断
                if (blog.getTypeId() != null){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                // 是否推荐
                if (blog.isRecommend()) {
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return null;
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        return null;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        return null;
    }

    @Override
    public Long countBlog() {
        return null;
    }
}
