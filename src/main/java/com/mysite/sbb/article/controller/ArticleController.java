package com.mysite.sbb.article.controller;

import com.mysite.sbb.article.dao.ArticleRepository;
import com.mysite.sbb.article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/list")
    @ResponseBody
    public List<Article> showList(){ //단건이 아니니까 배열로 받는다.
        return articleRepository.findAll();
    }

    // 단건조회
    @RequestMapping("/detail")
    @ResponseBody
    public Article showArticle(){ // 단건 조회는 배열로 받을 필요가 없음
        return articleRepository.findById(1L).get();
    }
}
