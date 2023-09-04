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
    <title>리뷰관리</title>
    <%@ include file="../../head.jsp" %>

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
    <link rel="stylesheet" href="${path}/css/asidebar.css">
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
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .tb1 .item2 {
            width: 50%;
            text-align: left;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .tb1 .item3 {
            width: 15%;
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



    </style>
</head>
<body>
<div class="wrap">
    <header class="hd" id="hd">
        <%@ include file="../../header.jsp" %>
    </header>
    <%@ include file="./adminAside.jsp"%>
    <div class="contents" id="contents">
        <div class="breadcrumb">
            <p><a href="/">HOME</a> &gt; <a href="/board/boardList.jsp">리뷰관리</a></p>
        </div>
        <section class="page" id="page1">
            <div class="page_wrap">
                <h2 class="page_tit">리뷰관리</h2>
                <table class="tb1" id="myTable">
                    <thead>
                    <tr>
                        <th class="item1">상품번호</th>
                        <th class="item1">상품명</th>
                        <th class="item1">작성자</th>
                        <th class="item2">내용</th>
                        <th class="item3">비고</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="rv" items="${rvList }" varStatus="status">
                        <tr>
                            <td class="item1">
                                <span>${rv.no }</span>
                            </td>
                            <td class="item1">
                                <span>${rv.pname }</span>
                            </td>
                            <td class="item1">
                                <span>${rv.cid }</span>
                            </td>
                            <td class="item2">
                                ${rv.content }
                            </td>
                            <td class="item3">
                                <a href="${path}/Product.do?no=${rv.par}" class="btn1">상세</a>
                                <a href="${path}/DeleteReview.do?cid=${rv.cid}&par=${rv.par}" class="btn1">제거</a>
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
                        order: [[0, 'asc']], // 0번째 컬럼을 기준으로 내림차순 정렬
                        info: false,
                        dom: 't<f>p',
                        language: {
                            emptyTable: '리뷰 내역이 없습니다.'
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
        <%@ include file="../../footer.jsp" %>
    </footer>
</div>
</body>
</html>