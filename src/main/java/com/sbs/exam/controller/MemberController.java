package com.sbs.exam.controller;

import com.sbs.exam.Rq;
import com.sbs.exam.container.Container;
import com.sbs.exam.dto.Member;
import com.sbs.exam.dto.ResultData;
import com.sbs.exam.service.MemberService;
import com.sbs.exam.util.Util;

public class MemberController extends Controller {
 private MemberService memberService = Container.memberService;

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

    Member member = (Member) loginRd.getBody().get("member");

    rq.setSessionAttr("loginedMemberJson", Util.toJson(member, ""));

    rq.replace(loginRd.getMsg(), "../article/list");
  }

  private void actionShowLogin(Rq rq) {
    rq.jsp("../member/login");
  }
}