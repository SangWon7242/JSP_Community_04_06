<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="로그인아이디 찾기"/>
<%@ include file="../part/head.jspf" %>
  <script>
    let MemberFindLoginId__submitDone = false;

    function MemberFindLoginId__submit(form) {
      if(MemberFindLoginId__submitDone) {
        alert('처리 중입니다.');
        return;
      }

      form.name.value = form.loginPw.value.trim();

      if(form.name.value.length == 0) {
        alert('이름을 입력해주세요.');
        form.name.focus();
        return;
      }

      form.submit();
      MemberFindLoginId__submitDone = true;
    }
  </script>

  <form action="doLogin" method="POST" onsubmit="MemberFindLoginId__submit(this); return false">
    <div>이름 : <input placeholder="이름를 입력해주세요." name="name" type="text"></div>
    <div>
      <button type="submit">로그인</button>
      <button type="button">
        <a href="../home/main">취소</a>
      </button>
    </div>
  </form>

<%@ include file="../part/foot.jspf" %>