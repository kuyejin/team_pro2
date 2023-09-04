<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path2" value="<%=request.getContextPath() %>" />
<div class="hd_wrap">
    <nav class="tnb"> <!-- .hd_wrap:first-child  -->
        <ul class="menu">
            <c:if test="${!empty sid}">
            <li>안녕하세요,  ${sid}님</li> <!-- 로그인 한 회원의 이름 -->
                <li><a href="${path2}/MypageIndex.do">마이페이지</a></li>
                <li><a href="${path2}/CartList.do">장바구니 (${cntCart })</a></li>
                <c:if test="${sid eq 'admin'}">
                <li><a href="${path2}/Manage.do">관리자페이지</a></li>
                </c:if>
                <li><a href="${path2}/Logout.do">로그아웃</a></li>
            </c:if>
            <c:if test="${empty sid}">
            <li><a href="${path2}/Login.do">로그인</a></li>
            <li><a href="${path2}/Join.do">회원가입</a></li>
            </c:if>
            <li><a href="${path2}/NoticeList.do">고객센터</a></li>
        </ul>
    </nav>
</div>

<div class="hd_wrap"> <!-- .hd_wrap:first-child  -->
    <a href="/pro02" class="logo">
        <img src="./img/logo.png" alt="">
    </a>
    <nav class="gnb">
        <ul class="menu">
            <li class="item1">
                <a href="${path2}/ProList.do?cate=초등" class="dp1">초등 도서</a>
                <ul class="sub">
                    <li><a class="move" href="${path2}/ProList.do?cate=A">교과서</a></li>
                    <li><a class="move" href="${path2}/ProList.do?cate=B">참고서</a></li>
                    <li><a class="move" href="${path2}/ProList.do?cate=C">문제집</a></li>
                    <li><a class="move" href="${path2}/ProList.do?cate=D">기타</a></li>
                </ul>
            </li>
            <li class="item2">
                <a href="${path2}/ProList.do?cate=중등" class="dp1">중등 도서</a>
                <ul class="sub">
                    <li><a class="move" href="${path2}/ProList.do?cate=E">교과서</a></li>
                    <li><a class="move" href="${path2}/ProList.do?cate=F">참고서</a></li>
                    <li><a class="move" href="${path2}/ProList.do?cate=G">문제집</a></li>
                    <li><a class="move" href="${path2}/ProList.do?cate=H">기타</a></li>
                </ul>
            </li>
            <li class="item3">
                <a href="${path2}/ProList.do?cate=고등" class="dp1">고등 도서</a>
                <ul class="sub">
                    <li><a class="move" href="${path2}/ProList.do?cate=I">교과서</a></li>
                    <li><a class="move" href="${path2}/ProList.do?cate=J">참고서</a></li>
                    <li><a class="move" href="${path2}/ProList.do?cate=K">문제집</a></li>
                    <li><a class="move" href="${path2}/ProList.do?cate=L">기타</a></li>
                </ul>
            </li>
            <li class="item1">
                <a href="${path2}/ProList.do?cate=기타" class="dp1">기타</a>
                <ul class="sub">
                    <li><a class="move" href="${path2}/ProList.do?cate=M">일반</a></li>
                    <li><a class="move" href="${path2}/ProList.do?cate=N">유아</a></li>
                    <li><a class="move" href="${path2}/ProList.do?cate=O">해외</a></li>
                </ul>
            </li>

        </ul>
    </nav>
    <script src="/js/load.js"></script>
</div>