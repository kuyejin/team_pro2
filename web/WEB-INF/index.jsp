<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/head.jsp"%> <%-- head 영역 호출 --%>
    <!-- 플러그인 연결-->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

    <!-- 스타일 초기화 : reset.css 또는 normalize.css -->
    <link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" rel="stylesheet">
    <!-- 웹 폰트 -->
    <link rel="stylesheet" href="${path}/css/font.css">

    <!-- css 모듈화 -->
    <link rel="stylesheet" href="${path}/css/common.css">
    <link rel="stylesheet" href="${path}/css/hd.css">
    <link rel="stylesheet" href="${path}/css/ft.css">
    <link rel="stylesheet" href="${path}/css/change_img.css">
    <link rel="stylesheet" href="${path}/css/slidebox.css">

    <style>
        .img_box li.item1 .bg_box { background-image: url("${path}/img/006.png");}
        .img_box li.item2 .bg_box { background-image: url("${path}/img/008.png");}

        .page {clear: both; width: 100vw; height: 100vh; position: relative;}
        .page::after {content: ""; display: block; width: 100vw; clear: both;}
        .page_wrap { clear: both; width: 1200px; height: auto; margin: 0 auto;}
        .page_tit {font-size: 48px; text-align: center; padding-top: 50px; padding-top: 0.5em;}

        .productList{
            height: 600px;
            padding-top: 40px;
        }
        .listTitle{
            border-bottom: 1px solid #cccccc;
            height: 60px;
            margin-bottom: 30px;
        }
        .listTitle h2{
            text-align: center;
            font-size: 30px;
            line-height: 60px;
        }

        .productCard {
            width:250px;
            height: 420px;
            margin-right: 60px;
            background-color: #fff;
            border: 1px solid #e0e0e0;
            border-bottom-left-radius: 10px;
            border-bottom-right-radius: 10px;
            overflow: hidden;
            transition: transform 0.3s;
            float: left;
        }
        .productCard:last-child{
            margin-right: 0;
        }

        .productCard:hover {
            transform: translateY(-10px);
            box-shadow: 0 0 0 1px #c7c7c7 inset;
        }

        .productImage {
            height: 320px;
            /*margin: 0 auto;*/
            text-align: center;

        }
        .productImage img {
            height: 320px;
            overflow: hidden;
        }

        .productInfo {
            padding: 15px;
        }
        .productCate {
            font-size: 16px;
            margin-bottom: 10px;
            color: #6b6b6b;
        }
        .productTitle {
            font-size: 20px;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .productPrice {
            font-size: 16px;
            font-weight: bold;
        }

    </style>
</head>
<body>
<header class="hd" id="hd">
    <%@include file="/header.jsp"%>
</header>

<div class="contents">
    <figure class="vs">
        <ul class="img_box">
            <li class="item1 active">
                <input type="radio" name="vs_ra" id="vs_ra1" class="vs_ra" checked>
                <div class="bg_box"></div>
<%--                <h2 class="vs_tit">“나눔의 힘! 실천의 힘!”<br>--%>
<%--                    <strong>작은 따뜻함으로 세상을 <br>바꿉니다</strong>--%>
<%--                </h2>--%>
            </li>
            <li class="item2">
                <input type="radio" name="vs_ra" id="vs_ra2" class="vs_ra">
                <div class="bg_box"></div>
<%--                <h2 class="vs_tit">교육에 대한 끊임없는 도전<br>--%>
<%--                    <strong>행복한 내일을 함께 합니다</strong></h2>--%>
            </li>
        </ul>
        <ul class="btn_box">
            <li class="item1 active"><label for="vs_btn1" class="vs_btn"></label></li>
            <li class="item2"><label for="vs_btn2" class="vs_btn"></label></li>
        </ul>
        <button type="button" class="play_btn"></button>
    </figure>
    <script src="./js/rotation.js"></script>


    <div class="page_wrap">


            <div class="productList">
                <div class="listTitle">
                    <h2>베스트 도서</h2>
                </div>
                <c:forEach var="pro" items="${bestList }" varStatus="status">
                <div class="productCard">
                    <div class="productImage"><a href="${path}/Product.do?no=${pro.no}"><img src="${path }/storage/${pro.imgSrc1 }"/></a></div>
                    <div class="productInfo">
                        <div class="productCate">${pro.cname }</div>
                        <div class="productTitle">${pro.pname }</div>
                        <div class="productPrice">${pro.price }원</div>
                    </div>
                </div>
                </c:forEach>


        </div>

        <div class="productList">
            <div class="listTitle">
                <h2>최신 도서</h2>
            </div>
            <c:forEach var="newpro" items="${newList }" varStatus="status">
                <div class="productCard">
                    <div class="productImage"><a href="${path}/Product.do?no=${newpro.no}"><img src="${path }/storage/${newpro.imgSrc1 }"/></a></div>
                    <div class="productInfo">
                        <div class="productCate">${newpro.cname }</div>
                        <div class="productTitle">${newpro.pname }</div>
                        <div class="productPrice">${newpro.price }원</div>
                    </div>
                </div>
            </c:forEach>
        </div>



    </div>

</div>

<footer class="ft" id="ft">
    <%@include file="/footer.jsp"%>
</footer>
</body>
</html>
