package com.mysite.sbb.article.controller;

import com.mysite.sbb.article.dao.ArticleRepository;
import com.mysite.sbb.article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
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

    // 게시물 수정
    @RequestMapping("/doModify")
    @ResponseBody
    public Article doModify(long id, String title, String body){
        Article article =  articleRepository.findById(id).get();
        if (title != null){ // title 값이 null이 아니면 실행
            article.setTitle(title);
        }

        if (body != null){ // body 값이 null이 아니면 실행
            article.setBody(body);
        }

        article.setUpdateDate(LocalDateTime.now());

        articleRepository.save(article);

        return article;
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(long id){ // 삭제할 때는 id값만 필요
        if (articleRepository.existsById(id) == false){
            return "%d번 게시물은 이미 삭제되었거나 존재하지 않습니다.".formatted(id);
        }

        articleRepository.deleteById(id);

        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }

    @RequestMapping("/findByTitle")
    @ResponseBody
    public List<Article> findByTitle(String title){
        List<Article> articles = articleRepository.findByTitle(title);
        return articles;
    }

}
