package com.sbs.exam.controller;

import com.sbs.exam.Rq;
import com.sbs.exam.dto.ResultData;
import com.sbs.exam.service.MemberService;
import com.sbs.exam.util.DBUtil;
import com.sbs.exam.util.SecSql;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.util.Map;

public class MemberController extends Controller {
  private MemberService memberService;

  public MemberController(Connection conn) {
    memberService = new MemberService(conn);
  }

  @Override
  public void performAction(Rq rq) {
    switch (rq.getActionMethodName()) {
      case "login":
        actionShowLogin(rq);
        break;
      case "doLogin":
        actionDoLogin(rq);
        break;
      default:
        rq.println("존재하지 않는 페이지입니다.");
        break;
    }
  }

  private void actionDoLogin(Rq rq) {
    String loginId = rq.getParam("loginId", "");
    String loginPw = rq.getParam("loginPw", "");

    if (loginId.length() == 0) {
      rq.historyBack("loginId를 입력해주세요.");
      return;
    }

    if (loginPw.length() == 0) {
      rq.historyBack("loginPw를 입력해주세요.");
      return;
    }

    ResultData loginRd = memberService.login(loginId, loginPw);

    if(loginRd.isFail()) {
      rq.historyBack(loginRd.getMsg());
    }

    rq.replace(loginRd.getMsg(), "../home/main");
  }

  private void actionShowLogin(Rq rq) {
    rq.jsp("../member/login");
  }
}
