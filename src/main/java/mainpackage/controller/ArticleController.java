package mainpackage.controller;

import mainpackage.model.ArticlePostResponse;
import mainpackage.model.Articles;
import mainpackage.model.LoginRequest;
import mainpackage.model.LoginResponse;
import mainpackage.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping(value = "/postarticle", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArticlePostResponse> postArticle(@RequestBody Articles articles) {
     ArticlePostResponse articlePostResponse=articleService.postArticle(articles);
   return new ResponseEntity<ArticlePostResponse>(articlePostResponse,HttpStatus.OK);}

    @GetMapping(value = "/getarticle")
    public ResponseEntity<Articles> postArticle(@RequestParam("articleId") long articleId) {
        Articles articles=articleService.getArticle(articleId);
        return new ResponseEntity<Articles>(articles,HttpStatus.OK);
    }
}
