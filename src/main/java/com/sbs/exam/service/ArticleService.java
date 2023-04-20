package com.sbs.exam.service;

import com.sbs.exam.Rq;
import com.sbs.exam.dto.Article;
import com.sbs.exam.dto.ResultData;
import com.sbs.exam.repository.ArticleRepository;
import com.sbs.exam.util.Util;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ArticleService {
  private ArticleRepository articleRepository;
  public ArticleService(Connection conn) {
    articleRepository = new ArticleRepository(conn);
  }

  public int getItemsInAPage() {
    return 10;
  }

  public int getForPrintListTotalPage() {
    int itemInAPage = getItemsInAPage();

    int totalCount = articleRepository.getTotalCount();
    int totalPage = (int) Math.ceil((double) totalCount / itemInAPage);

    return totalPage;
  }

  public List<Article> getForPrintArticles(int page) {
    int itemInAPage = getItemsInAPage();
    int limitFrom = (page - 1) * itemInAPage;

    List<Article> articles = articleRepository.getArticles(limitFrom, itemInAPage);

    return articles;
  }

  public ResultData write(String title, String body, int loginedMemberId) {
    int id = articleRepository.write(title, body, loginedMemberId);
    return ResultData.from("S-1", Util.f("%d번 게시물이 생성되었습니다", id), "id", id);
  }
}
