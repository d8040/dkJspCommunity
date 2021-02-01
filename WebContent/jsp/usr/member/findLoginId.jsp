<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="아이디 찾기" />
<%@ include file="../../part/head.jspf"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js" integrity="sha512-szJ5FSo9hEmXXe7b5AUVtn/WnL8a5VofnFeYC2i2z03uS2LhAch7ewNLbl5flsEmTTimMN0enBZg/3sQ+YOSzQ==" crossorigin="anonymous"></script>
<main class="flex-g-1 con">
<div>
  <script>
    let DoFindLoginIdForm__submited = false;
    function DoFindLoginIdForm__submit(form){
      if (DoFindLoginIdForm__submited){
        alert('처리 중 입니다.');
        return;
      }

      form.name.value = form.name.value.trim();

      if(form.name.value.length == 0){
        alert('이름을 입력해주세요.');
        form.name.focus();

        return;
      }

      form.email.value = form.email.value.trim();

      if(form.email.value.length == 0){
        alert('가입 시 등록한 이메일을 입력해주세요.');
        form.email.focus();

        return;
      }

      form.loginPwReal.value = sha256(form.loginPw.value);
      form.loginPw.value = "";

      form.submit();
      DoFindLoginIdForm__submited = true;			
    }
  </script>
  <form action="doFindLoginId" method="POST" onsubmit="DoFindLoginIdForm__submited(this); return false;">
    <input type="hidden" name="loginPwReal" />
    <section class="login-box">
      <div class="login-box__title">${pageTitle}</div>
      <div>
        <div>
          <input name="name" type="text" placeholder="이름">
        </div>
        <div>
          <input name="email" type="text" placeholder="이메일 주소">
        </div>
        <div class="login-box__function">
          <c:if test="${isLogined == false}">
            <a href="../member/join">회원가입</a>
            <a href="../member/findLoginId">아이디 찾기</a>
            <a href="../member/findLoginPw">비밀번호 찾기</a>
          </c:if>
        </div>
        <div class="login-box__button">
          <button type="submit" class="btn btn-success">아이디 찾기</button>
          <button type="button" class="btn btn-info" onclick="history.back();">취소</button>
        </div>
      </div>
    </section>
  </form>
</div>
<%@ include file="../../part/foot.jspf"%>