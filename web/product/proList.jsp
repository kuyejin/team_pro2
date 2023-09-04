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
    <title>도서목록</title>
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
            height: 280px;
            overflow: hidden; /* Overflow 처리 (필요한 경우) */
            vertical-align: middle; /* 상단 정렬로 변경 */

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
            max-height: 280px;
            overflow: hidden;
        }


        .tb1 .item1 {
            width: 25%;
        }
        .tb1 .item2 {
            width: 20%;
            text-align: left;
            font-weight: bold;
        }
        .tb1 .item3 {
            width: 12%;

        }
        .tb1 .item4 {
            width: 23%;

        }
        .tb1 .item5 {
            width: 20%;

        }

        .tb1 p {
            padding: 4px;
        }
        /* 기타 버튼 스타일 */

        .btn1 {
            display: inline-block;
            border-radius: 4px;
            line-height: 36px;
            width: 90px;
            padding: 4px;
            /*max-width: 120px;*/
            text-align: center;
            background-color: #527AF2;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-bottom: 10px;
            margin-left: 10px;
            float: right;
        }

        .btn1:hover {
            background-color: #666666;
        }
        .likebtn {
            display: block;
            border-radius: 4px;
            width: 34px;
            line-height: 36px;
            padding: 4px 8px 4px 8px;
            /*max-width: 120px;*/
            text-align: center;
            background-color: #527AF2;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
            float: right;
            font-size: 24px;

        }

        .likebtn:hover {
            background-color: #666666;
        }


        .inbtn {
            display: block;
            border-radius: 10px;
            min-width: 60px;
            padding-right: 10px;
            padding-left: 10px;
            margin-left: 15px;
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
    <link rel="stylesheet" href="${path}/css/asidebar.css">
</head>
<body>
<div class="wrap">
    <header class="hd" id="hd">
        <%@ include file="../header.jsp" %>
    </header>
    <c:if test="${sid eq 'admin'}">
        <%@ include file="../WEB-INF/admin/adminAside.jsp"%>
    </c:if>
    <div class="contents" id="contents">
        <div class="breadcrumb">
            <p><a href="/">HOME</a> &gt; <a href="${path }/ProList.do?cate=${cate }">${catename } 도서 목록</a></p>
        </div>
        <section class="page" id="page1">
            <div class="page_wrap">
                <h2 class="page_tit">${catename } 도서 목록</h2>
                <table class="tb1" id="myTable">
                    <thead>
                    <tr>
                        <th></th>
                        <th>제목</th>
                        <th>가격</th>
                        <th>정보</th>
                        <th>비고</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="pro" items="${proList }">
                        <tr>
                            <td class="item1">
                                <div class="img_tit"><a href="${path}/Product.do?no=${pro.no}"><img src="${path }/storage/${pro.imgSrc1 }"/></a></div>
                            </td>
                            <td class="item2">
                                <p>${pro.pname}</p>
                            </td>
                            <td class="item3">
                                <p>${pro.price}</p>
                            </td>
                            <td class="item4">
                                <p>${pro.cname }</p>
                                <p>출간일 ${pro.resdate}</p>
                            </td>
                            <td class="item5">
                                <c:if test="${not empty sid}">
                                    <c:set var="isLiked" value="${likedProductIds.contains(pro.no)}" />
                                        <a href="${path }/AddPayment.do?pno=${pro.no }" class="btn1">구매하기</a>
                                        <a href="${path }/AddCart.do?pno=${pro.no }&cate=${pro.cate}" class="btn1">장바구니</a>
                                    <c:choose>
                                        <c:when test="${isLiked }">
                                            <%-- 눌러도 새로고침 안되게 처리 ///                         현재 로그인한 사용자 ID                 pro.no을 저장하기 위한 역할 --%>
                                            <a href="javascript:void(0);" onclick="toggleLike(${pro.no}, '${sessionScope.sid}');" class="likebtn" data-product-id="${pro.no}" style="color: #ff5050">♥</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript:void(0);" onclick="toggleLike(${pro.no}, '${sessionScope.sid}');" class="likebtn" data-product-id="${pro.no}"  style="color: #b4b4b4">♥</a>
                                        </c:otherwise>
                                    </c:choose>

                                </c:if>
                                <script>
                                    function toggleLike(productNo, ${sid }) {
                                        $.ajax({
                                            url: "ProductLike.do",
                                            method: "POST",
                                            data: {
                                                pno: productNo,
                                                sid: ${sid }
                                            },
                                            success: function(response) {
                                                var likeButton = $("[data-product-id='" + productNo + "']");

                                                if (response.trim() === "liked") {
                                                    alert("상품을 좋아요 했습니다!");
                                                    likeButton.css("color","#ff5050");
                                                } else if (response.trim() === "unliked") {
                                                    alert("상품의 좋아요를 취소했습니다.");
                                                    likeButton.css("color","#b4b4b4");                                                } else {
                                                    alert("오류가 발생했습니다. 다시 시도해주세요.");
                                                }
                                            }
                                        });
                                    }
                                    $(document).ready(function() {
                                        // 좋아요 상태를 기반으로 버튼 색 변경
                                        $(".inbtn").each(function() {
                                            var isLiked = $(this).hasClass("liked");
                                            if (isLiked) {
                                                $(this).addClass("liked");
                                            }
                                        });
                                    });
                                </script>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <script>
                    $(document).ready( function () {
                        $('#myTable').DataTable({
                            pageLength : 5,
                            order: [[3, 'desc']], // 0번째 컬럼을 기준으로 내림차순 정렬
                            info: false,
                            dom: 't<f>p',
                            language: {
                                emptyTable: '등록된 상품이 없습니다.'
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
                            'margin-right':'120px'
                        });


                    });

                </script>
                <div class="btn_group">
                    <c:if test="${sid eq 'admin' }">
                        <a href="${path}/AddProduct.do" class="inbtn">상품 등록</a>
                        <a href="${path}/AddReceive.do" class="inbtn">상품 입고</a>
                        <a href="${path}/UpdateReceive.do" class="inbtn">추가 입고</a>
                    </c:if>
                </div>
            </div>
        </section>
    </div>
    <footer class="ft" id="ft">
        <%@ include file="../footer.jsp" %>
    </footer>
</div>
</body>
</html>