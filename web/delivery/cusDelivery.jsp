 <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <c:set var="path" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>배송정보</title>
    <%@ include file="/head.jsp" %>

    <!-- 스타일 초기화 : reset.css 또는 normalize.css -->
    <link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" rel="stylesheet">

    <!-- 플러그인 연결-->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <!-- 스타일 초기화 -->
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

        .page { clear:both; width: 100vw; height: 200vh; position:relative; }
        .page::after { content:""; display:block; width: 100%; clear:both; }

        .page_wrap { clear:both; width: 1200px; height: auto; margin:0 auto; }
        .page_tit { font-size:48px; text-align: center;  color:#fff;
            padding-bottom: 1.3em; }

        .breadcrumb { clear:both;
            width:1200px; margin: 0 auto; text-align: right; color:#fff;
            padding-top: 28px; padding-bottom: 28px; }
        .breadcrumb a { color:#fff; }

        .tb1 { width:600px; margin:0 auto; font-size: 24px;}
        .tb1 th { width: 200px; line-height: 32px; padding-top:16px; padding-bottom:16px;
            border-bottom: 1px solid #333; border-top: 1px solid #333; box-sizing: border-box; text-align: center;}
        .tb1 td { width: 400px; line-height: 32px; padding-top:16px; padding-bottom:16px;
            border-bottom: 1px solid #333; border-top: 1px solid #333; box-sizing: border-box; text-align: center;}

        .tb1 td:last-child { line-height: 48px; padding-top:24px; padding-bottom:24px; }


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
        .indata {
            display:inline-block;
            width: 590px;
            height: 32px;
            line-height: 32px;
            text-indent:10px;
            font-size:16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 5px 15px;
        }

        textarea {
            resize: none;
            width: 600px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="wrap">
    <header class="hd" id="hd">
        <%@ include file="/header.jsp" %>
    </header>
    <div class="contents" id="contents">
        <div class="breadcrumb">
            <p><a href="/">HOME</a> &gt; <a href="/">배송정보</a>
                &gt; <a href="/">배송정보</a></p>
        </div>
        <section class="page" id="page1">
            <div class="page_wrap">
                <h2 class="page_tit">배송정보</h2>
                <table class="tb1">
                    <tbody>
                    <tr>
                        <th>배송번호</th>
                        <td colspan="2">${deli.dno}</td>
                    </tr>
                    <tr>
                        <th>구매번호</th>
                        <td colspan="2">${deli.sno}</td>
                    </tr>
                    <tr>
                        <th>구매상품</th>
                        <td colspan="2">${deli.pname}</td>
                    </tr>
                    <tr>
                        <th>구매수량</th>
                        <td colspan="2">${deli.amount}</td>
                    </tr>
                    <tr>
                        <th>구매자</th>
                        <td colspan="2">${deli.cid}</td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td colspan="2">${deli.daddr}</td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td colspan="2">${deli.custel}</td>
                    </tr>
                    <tr>
                        <th>운송회사</th>
                        <td colspan="2">${deli.pcom}</td>
                    </tr>
                    <tr>
                        <th>운송번호</th>
                        <td colspan="2">${deli.bcode}</td>
                    </tr>
                    <tr>
                        <th>기사 전화번호</th>
                        <td colspan="2">${deli.ptel}</td>
                    </tr>
                    <tr>
                        <th>구매번호</th>
                        <td colspan="2">${deli.sno}</td>
                    </tr>
                    <tr>
                        <th>배송정보</th>
                        <c:if test="${deli.pstate == 0}">
                            <td colspan="2">출고처리중</td>
                        </c:if>
                        <c:if test="${deli.pstate == 1}">
                            <td colspan="2">배송중</td>
                        </c:if>
                        <c:if test="${deli.pstate == 2}">
                            <td colspan="2">배송완료</td>
                        </c:if>
                        <c:if test="${deli.pstate == 3}">
                            <td colspan="2">구매완료</td>
                        </c:if>
                    </tr>
                    <tr>
                        <th>결제날짜</th>
                        <td colspan="2">${deli.sdate}</td>
                    </tr>
                    <tr>
                        <th>출고날짜</th>
                        <td colspan="2">${deli.rdate}</td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </section>
    </div>
    <footer class="ft" id="ft">
        <%@ include file="/footer.jsp" %>
    </footer>
</div>
</body>
</html>
