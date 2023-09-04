<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="<%=request.getContextPath() %>" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>찜목록</title>
    <%@ include file="../head.jsp" %>

    <!-- 스타일 초기화 : reset.css 또는 normalize.css -->
    <link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" rel="stylesheet">

    <!-- 플러그인 연결-->
    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <!-- datatables -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css">
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>

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
        .contents {
            clear: both;
            min-height: 200vh;
            background-image: url("${path}/img/login.jpg");
            background-repeat: no-repeat;
            background-position: center -250px;
        }
        .contents::after {
            content: "";
            clear: both;
            display: block;
            width: 100%;
        }

        .page {
            clear: both;
            width: 100vw;
            height: 100vh;
            position: relative;
        }
        .page::after {
            content: "";
            display: block;
            width: 100%;
            clear: both;
        }

        .page_wrap {
            clear: both;
            width: 1200px;
            height: auto;
            margin: 0 auto;

        }
        .page_tit {
            font-size: 48px;
            text-align: center;
            /*padding-top: 0.7em;*/
            color: #fff;
            padding-bottom: 1.3em;
        }

        .breadcrumb {
            clear: both;
            width: 1200px;
            margin: 0 auto;
            text-align: right;
            color: #fff;
            padding-top: 28px;
            padding-bottom: 28px;
        }
        .breadcrumb a {
            color: #fff;

        }

        /* 테이블 스타일 */
        .tb1 {
            width: 80%;
            margin: 50px auto;
            font-size: 20px;
            border-collapse: collapse;
        }
        .tb1 th {
            background-color: #527AF2;
            color: #fff;
            padding: 16px;
            border: 1px solid #527AF2;

        }

        .tb1 th:first-child {
            width: 40px;
        }

        #myTable td {
            max-height: 200px;
            overflow: hidden; /* Overflow 처리 (필요한 경우) */
            vertical-align: top; /* 상단 정렬로 변경 */
        }

        #myTable td > div {
            text-align: center;
        }

        .img_tit {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100%;
        }

        #myTable tbody tr:not(:last-child) td {
            border-bottom: 1px solid #333;
        }

        .img_tit img {
            max-height: 180px;
        }

        .tb1 .item1 {
            width: 10%;
        }
        .tb1 .item2 {
            width: 50%;
            text-align: left;
        }
        .tb1 .item3 {
            width: 25%;
            text-align: right;
        }

        /* 기타 버튼 스타일 */

        .btn1 {
            border-radius: 10px;
            padding-left: 10px;
            padding-right: 10px;
            max-width: 100px;
            text-align: center;
            background-color: #527AF2;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn1:hover {
            background-color: #666666;
        }

        .inbtn {
            display: block;
            border-radius: 10px;
            min-width: 60px;
            padding-left: 24px;
            padding-right: 24px;
            text-align: center;
            line-height: 38px;
            background-color: #527AF2;
            color: #fff;
            font-size: 18px;
            float: right;
            cursor: pointer;
            transition: background-color 0.3s;

        }
        .inbtn:hover {
            background-color: #666666;
        }

        .btn_group {
            margin-top: -38px;
            z-index: 1000;
            position: relative;
        }
        .btn_group p {
            float: right;

        }


        /* 사이드바 시작 */
        aside{
            float: left;
            width: 30%;
            min-height: 250px;
            z-index: 999;
        }
        /* 커스텀 시작 */
        .side-bar > ul ul {
            display: none;
        }

        /* 사이드바의 너비와 높이를 변수를 통해 통제 */
        :root {
            --side-bar-width: 220px;
            --side-bar-height: 80vh;
        }

        .side-bar {
            position: absolute; /* 스크롤을 따라오도록 지정 */
            background-color: cornflowerblue;
            width: var(--side-bar-width);
            min-height: var(--side-bar-height); /* 사이드바의 높이를 전체 화면 높이의 90%로 지정 */
            margin-top: 150px; /* 사이드바 위와 아래의 마진을 동일하게 지정 */
        }

        /* 아이콘 시작 */
        .side-bar__icon-box {
            display: flex;
            justify-content: flex-end;
        }

        .side-bar__icon-1 {
            position: relative;
            width: 23px;
            height: 17px;
            margin: 15px;
            margin-top: 20px;
            transition: .5s;
        }

        :root {
            --side-bar__icon: .5s;
        }

        .side-bar__icon-1 > div {
            position: absolute;
            width: 100%;
            height: 20%;
            background-color: white;
            transition: all var(--side-bar__icon);
        }

        .side-bar__icon-1 > div:nth-of-type(1) {
            top: 0;
            width: auto;
            left: 0;
            right: 0;
            transition: all var(--side-bar__icon), left calc(var(--side-bar__icon) / 2) calc(var(--side-bar__icon) / 2), right calc(var(--side-bar__icon) / 2) calc(var(--side-bar__icon) / 2), height calc(var(--side-bar__icon) / 2) 0s;
        }

        .side-bar__icon-1 > div:nth-of-type(2) {
            top: 40%;
            transform-origin: bottom left;
        }

        .side-bar__icon-1 > div:nth-of-type(3) {
            top: 80%;
            left: auto;
            right: 0;
            transform-origin: bottom right;
        }


        .side-bar:hover .side-bar__icon-1 {
            transform: translate(-198px, 0);
        }

        .side-bar:hover .side-bar__icon-1 > div:nth-of-type(2) {
            transform: rotate(45deg);
            width: 70.5%;
            height: 25%;
        }

        .side-bar:hover .side-bar__icon-1 > div:nth-of-type(3) {
            top: 40%;
            transform: rotate(-45deg);
            width: 70.5%;
            height: 25%;
        }

        .side-bar:hover .side-bar__icon-1 > div:nth-of-type(1) {
            left: 41%;
            right: 41%;
            height: 100%;
            transition: all var(--side-bar__icon), left calc(var(--side-bar__icon) / 2) 0s, right calc(var(--side-bar__icon) / 2) 0s, height calc(var(--side-bar__icon) / 2) calc(var(--side-bar__icon) / 2);
        }
        /* 아이콘 끝 */

        /* 모든 메뉴의 a에 속성값 부여 */
        .side-bar ul > li > a {
            display: block;
            color: white;
            font-size: 1.4rem;
            font-weight: bold;
            padding-top: 20px;
            padding-bottom: 20px;
            padding-left: 50px;
            transition: .5s;
        }

        /* 자식의 position이 absolute일 때 자식을 영역 안에 가두어 준다 */
        .side-bar > ul > li {
            position: relative;
        }

        /* 모든 메뉴가 마우스 인식 시 반응 */
        .side-bar ul > li:hover > a {
            background-color: #555;
            border-bottom: 1px solid #999;
        }

        /* 1차 메뉴의 항목이 마우스 인식 시에 2차 메뉴 등장 */
        .side-bar > ul > li:hover > ul {
            display: block;
            position: absolute;
            background-color: #888;
            top: 0; /* 2차 메뉴의 상단을 1차 메뉴의 상단에 고정 */
            left: 100%; /* 2차 메뉴를 1차 메뉴의 너비만큼 이동 */
            width: 100%; /* 1차 메뉴의 너비를 상속 */
        }

        /* 사이드바 너비의 80%만큼 왼쪽으로 이동 */
        .side-bar {
            border-radius: 0px 20px 20px 0;
            /*  transform: translate(calc(var(--side-bar-width) * -0.8), 0);
              transition: .5s;*/
        }

        /* 마우스 인식 시 원래의 위치로 이동 */
        .side-bar:hover {
            transform: translate(-20px, 0); /* 둥근 모서리의 너비만큼 숨겨주기 */
        }
        /* 사이드바 끝 */
    </style>
</head>
<body>
<div class="wrap">
    <header class="hd" id="hd">
        <%@ include file="../header.jsp" %>
    </header>
    <div class="contents" id="contents">
        <div class="breadcrumb">
            <p><a href="/">HOME</a> &gt; <a href="/board/boardList.jsp">찜목록</a></p>
        </div>
        <%@ include file="../custom/customAside.jsp"%>
        <section class="page" id="page1">
            <div class="page_wrap">
                <h2 class="page_tit">찜목록</h2>
                <table class="tb1" id="myTable">
                    <thead>
                    <tr>
                        <th class="item1">연번</th>
                        <th class="item2">상품명</th>
                        <th class="item1">등록날짜</th>
                        <th class="item3">비고</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="like" items="${likeList }" varStatus="status">
                        <tr>
                            <td class="item1">
                                <span>${status.count }</span>
                            </td>
                            <td class="item2">
                                <span>${like.pname }</span>
                            </td>
                            <td class="item1">
                                <span>${like.liketime }</span>
                            </td>
                            <td class="item3">
                                <a href="${path }/AddCart.do?pno=${like.productid }" class="btn1">장바구니 추가</a>
                                <a href="${path}/DelLike.do?pno=${like.productid}&sid=${like.userid}" class="btn1">제거</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <script>
                $(document).ready( function () {
                    $('#myTable').DataTable({
                        pageLength : 5,
                        order: [[0, 'desc']], // 0번째 컬럼을 기준으로 내림차순 정렬
                        info: false,
                        dom: 't<f>p',
                        language: {
                            emptyTable: '장바구니 내역이 없습니다.'
                        }

                    });
                } );
                $(document).ready(function() {
                    $('.dataTables_paginate').css({
                        'textAlign':'left',
                        'float': 'none',
                        'margin-top':'10px',
                    });
                    $('.dataTables_filter').css({
                        'float': 'left',
                        'margin-top':'14px',
                        'margin-right':'280px'
                    });
                    $('#myTable_paginate').css({
                        'margin-right':'100px'
                    });
                });
            </script>
        </section>
    </div>
    <footer class="ft" id="ft">
        <%@ include file="../footer.jsp" %>
    </footer>
</div>
</body>
</html>