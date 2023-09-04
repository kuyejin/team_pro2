<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 회원 상세 페이지</title>
    <%@ include file="../../head.jsp" %>

    <!-- 스타일 초기화 : reset.css 또는 normalize.css -->
    <link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" rel="stylesheet">

    <!-- 플러그인 연결-->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <!-- 스타일 초기화 -->
    <link rel="stylesheet" href="${path}/css/reset.css">
    <!-- 웹 폰트 -->
    <link rel="stylesheet" href="${path}/css/font.css">

    <!-- css 모듈화 -->
    <link rel="stylesheet" href="${path}/css/common.css">
    <link rel="stylesheet" href="${path}/css/hd.css">
    <link rel="stylesheet" href="${path}/css/ft.css">

    <style>
        /* 본문 영역 스타일 */
        .contents { clear:both; min-height: 100vh; background-image: url("${path}/img/login.jpg");
            background-repeat: no-repeat; background-position: center -250px; }
        .contents::after { content:""; clear:both; display:block; width:100%; }

        .page { clear:both; width: 100vw; height: 100vh; position:relative; }
        .page::after { content:""; display:block; width: 100%; clear:both; }

        .page_wrap { clear:both; width: 1200px; height: auto; margin:0 auto; }
        .page_tit { font-size:48px; text-align: center; padding-top:0.7em; color:#fff;
            padding-bottom: 1.3em; }

        .breadcrumb { clear:both;
            width:1200px; margin: 0 auto; text-align: right; color:#fff;
            padding-top: 28px;}
        .breadcrumb a { color:#fff; }
        .frm { clear:both; width:1200px; margin:0 auto; padding-top: 80px; }

        .tb1 {
            width:600px;
            margin:0 auto;
            font-size: 20px;
            border-collapse: separate;
            border-spacing: 0 20px;
            background-color: #e1e1e1;
            border-radius: 14px;

        }
        .tb1 th {
            width:180px;
            line-height: 30px;

            padding-left: 70px;
            border-right: 2px solid #6b6b6b;

            box-sizing: border-box;
            text-align: left;
        }

        .tb1 .data {
            width:320px;
            line-height: 30px;
            padding-top:4px;
            padding-bottom:4px;
            /*border-bottom: 1px solid #333; */
            box-sizing: border-box;
            text-align: left;
            padding-left: 90px;
        }

        .tb2 {
            width: 600px;
            margin: 0 auto;
            margin-top: 50px;
        }

        .inbtn { display:block;
            border-radius:10px;
            min-width:120px;
            padding-left: 24px;
            padding-right: 24px;
            text-align: center;
            line-height: 38px;
            background-color: #333;
            color:#fff;
            font-size: 18px;
            cursor: pointer; }
        .inbtn:first-child { float:left; }
        .inbtn:last-child { float:right; }
        .inbtn:hover {
            background-color: #666666;
        }
    </style>

</head>

<body>
<div class="wrap">
    <header class="hd" id="hd">
        <%@ include file="../../header.jsp" %>
    </header>
    <div class="contents" id="contents">
        <div class="breadcrumb">
            <p><a href="">HOME</a> &gt; <span>관리자 회원 정보 페이지</span></p>
        </div>
        <section class="page" id="page1">
            <div class="page_wrap">
                <h2 class="page_tit">관리자 회원 정보 페이지</h2>
                <table class="tb1">
                    <tbody>
                    <tr>
                        <th>이름</th>
                        <td class="data">${cus.name}</td>
                    </tr>
                    <tr>
                        <th>아이디</th>
                        <td class="data">${cus.id}</td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td class="data">${cus.pw}</td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td class="data">${cus.tel}</td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td class="data">${cus.email}</td>
                    </tr>
                    <tr>
                        <th>생일</th>
                        <td class="data">${cus.birth}</td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td class="data">${cus.address}</td>
                    </tr>
                    <tr>
                        <th>포인트</th>
                        <td class="data">${cus.point}</td>
                    </tr>
                    </tbody>
                </table>
                <table class="tb2">
                    <tr>
                        <td colspan="2">
                            <a href="${path}/MypageModify.do?sid=${cus.id }" class="inbtn">회원정보 수정</a>
                            <a href="${path}/Manage.do" class="inbtn">관리자 메인</a>
                        </td>
                    </tr>
                </table>
            </div>
        </section>
    </div>
    <footer class="ft" id="ft">
        <%@ include file="../../footer.jsp" %>
    </footer>
</div>
</body>
</html>