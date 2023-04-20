package com.sbs.exam.controller;

import com.sbs.exam.Rq;
import com.sbs.exam.util.DBUtil;
import com.sbs.exam.util.SecSql;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ArticleController {
  private Connection conn;
  private Rq rq;
  public ArticleController(Rq rq, Connection conn) {
    this.conn = conn;
    this.rq = rq;
  }

  public void actionList() {
    int page = rq.getIntParam("page", 1);
    int itemInAPage = 10;
    int limitFrom = (page - 1) * itemInAPage;

    SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
    sql.append("FROM article");

    int totalCount = DBUtil.selectRowIntValue(conn, sql);
    int totalPage = (int) Math.ceil((double) totalCount / itemInAPage);

    sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM article");
    sql.append("ORDER BY id DESC");
    sql.append("LIMIT ?, ?", limitFrom, itemInAPage);

    List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

    rq.getReq().setAttribute("articleRows", articleRows);
    rq.getReq().setAttribute("page", page);
    rq.getReq().setAttribute("totalPage", totalPage);

    rq.jsp("../article/list");
  }
}
