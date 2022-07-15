package com.mysite.sbb.article.controller;

import com.mysite.sbb.article.dao.ArticleRepository;
import com.mysite.sbb.article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

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
    public Article showArticle(@RequestParam long id){
        Optional<Article> article =  articleRepository.findById(id);
        return article.orElse(null); // article에 값이 있으면 반환하고 아니면 null을 반환하겠다!
    }


}
