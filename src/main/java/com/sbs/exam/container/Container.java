package com.sbs.exam.container;

import com.sbs.exam.controller.ArticleController;
import com.sbs.exam.controller.HomeController;
import com.sbs.exam.controller.MemberController;
import com.sbs.exam.dto.Member;
import com.sbs.exam.interceptor.BeforeActionInterceptor;
import com.sbs.exam.interceptor.NeedLoginInterceptor;
import com.sbs.exam.interceptor.NeedLogoutInterceptor;
import com.sbs.exam.repository.ArticleRepository;
import com.sbs.exam.repository.MemberRepository;
import com.sbs.exam.service.ArticleService;
import com.sbs.exam.service.MemberService;

import java.sql.Connection;

public class Container {
  public static BeforeActionInterceptor beforeActionInterceptor;
  public static NeedLoginInterceptor needLoginInterceptor;
  public static NeedLogoutInterceptor needLogoutInterceptor;

  public static ArticleRepository articleRepository;
  public static MemberRepository memberRepository;

  public static ArticleService articleService;
  public static MemberService memberService;


  public static HomeController homeController;
  public static ArticleController articleController;
  public static MemberController memberController;

  public static Connection conn;

  public static void init() {
    articleRepository = new ArticleRepository();
    memberRepository = new MemberRepository();

    articleService = new ArticleService();
    memberService = new MemberService();

    beforeActionInterceptor = new BeforeActionInterceptor();
    needLoginInterceptor = new NeedLoginInterceptor();
    needLogoutInterceptor = new NeedLogoutInterceptor();

    homeController = new HomeController();
    articleController = new ArticleController();
    memberController = new MemberController();
  }
}
