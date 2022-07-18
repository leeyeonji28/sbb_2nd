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

    // title, body로 검색
    @RequestMapping("/list")
    @ResponseBody
    public List<Article> showArticleList(String title, String body) {
        if(title != null && body == null) {
            if(articleRepository.existsByTitle(title) == false) {
                System.out.println("제목과 일치하는 게시물이 없습니다.");
                return null;
            }
            return articleRepository.findByTitle(title);

        } else if(title == null && body != null) {
            if(articleRepository.existsByBody(body) == false) {
                System.out.println("내용과 일치하는 게시물이 없습니다.");
                return null;
            }
            return articleRepository.findByBody(body);

        } else if(title != null && body != null) {
            if(articleRepository.existsByTitleAndBody(title, body) == false) {
                System.out.println("제목, 내용과 일치하는 게시물이 없습니다.");
                return null;
            }
            return articleRepository.findByTitleAndBody(title, body);
        }

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

    // 게시물 삭제
    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(long id){ // 삭제할 때는 id값만 필요
        if (articleRepository.existsById(id) == false){
            return "%d번 게시물은 이미 삭제되었거나 존재하지 않습니다.".formatted(id);
        }

        articleRepository.deleteById(id);

        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }

    // title로 검색
    @RequestMapping("/findByTitle")
    @ResponseBody
    public List<Article> findByTitle(String title){
        List<Article> articles = articleRepository.findByTitle(title);
        return articles;
    }

    // 게시물 작성기능
    @RequestMapping("/doWrite")
    @ResponseBody
    public String doWrite(String title, String body){
        if (title == null || title.trim().length() == 0){
            return "제목을 입력해주세요";
        }
        if (body == null || body.trim().length() == 0){
            return "내용을 입력해주세요";
        }

        title = title.trim();
        body = body.trim();

        Article article = new Article();

        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setTitle(title);
        article.setBody(body);

        articleRepository.save(article);

        return "%d번 게시물이 생성되었습니다.".formatted(article.getId());
    }
}
