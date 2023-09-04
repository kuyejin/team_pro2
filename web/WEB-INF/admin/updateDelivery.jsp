 <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <c:set var="path" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>배송 정보 작성</title>
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
                <form action="${path}/UpdateDeliveryPostPro.do" class="frm" method="post">
                    <table class="tb1">
                        <tbody>
                        <tr>
                            <th>배송 분류</th>
                            <td colspan="2">
                                <select name="pstate" id="pstate" class="indata" autofocus required>
                                    <option value="0" ${deli.pstate eq "0" ? 'selected' : ''}>출고</option>
                                    <option value="1" ${deli.pstate eq "1" ? 'selected' : ''}>배송중</option>
                                    <option value="2" ${deli.pstate eq "2" ? 'selected' : ''}>배송완료</option>
                                    <option value="3" ${deli.pstate eq "3" ? 'selected' : ''}>구매완료</option>
                                </select>
                            </td>
                            <input type="hidden" name="dno" id="dno" value="${deli.dno}" readonly>
                        </tr>
                        <tr>
                            <th>구매자</th>
                            <td colspan="2"><input type="text" name="cid" id="cid" class="indata" value="${deli.cid}" readonly></td>
                        </tr>
                        <tr>
                            <th>주소</th>
                            <td colspan="2"><input type="text" name="daddr" id="daddr" class="indata" value="${deli.daddr}" readonly></td>
                        </tr>
                        <tr>
                            <th>배송회사</th>
                            <td colspan="2"><input type="text" name="pcom" id="pcom" class="indata" value="${deli.pcom}" required></td>
                        </tr>
                        <tr>
                            <th>기사번호</th>
                            <td colspan="2"><input type="text" name="ptel" id="ptel" class="indata" value="${deli.ptel}" required></td>
                        </tr>
                        <tr>
                            <th>운송번호</th>
                            <td colspan="2"><input type="text" name="bcode" id="bcode" class="indata" value="${deli.bcode}" required></td>
                        </tr>
                        <tr>
                            <th>배송날짜</th>
                            <td colspan="2"><input type="text" name="rdate" id="rdate" class="indata" value="${deli.rdate}" required maxlength="13"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <c:if test="${deli.pstate ne '3'}">
                                    <input type="submit" value="상품등록" class="inbtn">
                                </c:if>
                                <input type="reset" value="취소" class="inbtn" onclick="window.history.back();">
                                <a href="${path}/ProList.do" class="inbtn">상품목록</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </section>
    </div>
    <footer class="ft" id="ft">
        <%@ include file="/footer.jsp" %>
    </footer>
</div>
</body>
</html>
