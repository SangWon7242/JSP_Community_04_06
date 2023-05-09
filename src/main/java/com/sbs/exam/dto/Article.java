package com.sbs.exam.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Article {
  private int id;
  private String regDate;
  private String updateDate;
  private String title;
  private String body;
  private int memberId;

  private boolean extra__writeName;
  private boolean extra__actorCanModify;
  private boolean extra__actorCanDelete;

  public Article(Map<String, Object> row) {
    this.id = (int) row.get("id");
    this.regDate = (String) row.get("regDate");
    this.updateDate = (String) row.get("updateDate");
    this.title = (String) row.get("title");
    this.body = (String) row.get("body");
    this.memberId = (int) row.get("memberId");
  }
}
