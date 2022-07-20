package mainpackage.repository;

import mainpackage.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Articles,Long> {
  Articles findByArticleId(long articleId);
}
