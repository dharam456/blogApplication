package mainpackage.service;

import mainpackage.model.ArticlePostResponse;
import mainpackage.model.Articles;
import mainpackage.model.SignupResponse;
import mainpackage.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public ArticlePostResponse postArticle(Articles articles)
    {  Articles newarticles= articleRepository.save(articles);

       ArticlePostResponse articlePostResponse=new ArticlePostResponse();
        if(articleRepository.findByArticleId(articles.getArticleId())==null)
        {
            articlePostResponse.setStatus(false);
            articlePostResponse.setMessage("post failed");

        }else {
            articlePostResponse.setStatus(true);
            articlePostResponse.setMessage("post success");
        }
        return articlePostResponse;
    }


    public Articles getArticle(long articleId)
    {
        Articles articles= articleRepository.findByArticleId(articleId);
          return articles;
    }
}
