<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="<%=request.getContextPath() %>" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 상세</title>
    <%@ include file="../head.jsp" %>

    <!-- 스타일 초기화 : reset.css 또는 normalize.css -->
    <link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" rel="stylesheet">

    <!-- 플러그인 연결-->
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
    <link rel="stylesheet" href="${path}/css/video.css">
    <link rel="stylesheet" href="${path}/css/productpage.css">
    <style>
        .contents { clear:both; min-height: 150vh; background-image: url("${path}/img/login.jpg");
            background-repeat: no-repeat; background-position: center -250px; }
        .contents::after { content:""; clear:both; display:block; width:100%; }
    </style>
</head>

<body>
<div class="wrap">
    <header class="hd" id="hd">
        <%@ include file="../header.jsp" %>
    </header>
    <div class="contents" id="contents">
        <div class="breadcrumb">
            <p><a href="/">HOME</a> &gt; <a href="">도서 목록</a> &gt; <a href="">상품 상세</a></p>
        </div>
        <section class="page" id="page1">
            <div class="page_wrap">
                <h2 class="page_tit">상품 상세</h2>

                <div class="product">
                    <div class="productimg">
                        <img src="${path}/storage/${pro.imgSrc1}" width="300"/>
                    </div>
                    <div class="productdesc">
                        <table class="productdesctable">
                            <tr class="pname">
                                <th>${pro.pname}</th>
                            </tr>
                            <tr class="pprice">
                                <td>
                                    ${pro.price} 원
                                </td>
                            </tr>
                            <tr class="pcate">
                                <td>
                                    카테고리 : ${pro.cname}
                                </td>
                            </tr>
                            <tr class="pdate">
                                <td>
                                    출간일 : ${pro.resdate}
                                </td>
                            </tr>
                            <tr class="pbtn">
                                <c:if test="${not empty sid}">
                                <c:set var="isLiked" value="${likedProductIds.contains(pro.no)}" />
                                    <td><a href="${path }/AddPayment.do?pno=${pro.no }" class="inbtn">구매하기</a>
                                    <a href="${path }/AddCart.do?pno=${pro.no }&cate=${pro.cate}" class="inbtn">장바구니</a>
                                        <c:choose>
                                        <c:when test="${isLiked }">
                                            <%-- 눌러도 새로고침 안되게 처리 ///                         현재 로그인한 사용자 ID                 pro.no을 저장하기 위한 역할 --%>
                                        <a href="javascript:void(0);" onclick="toggleLike(${pro.no}, '${sessionScope.sid}');" class="inbtn" data-product-id="${pro.no}" style="color: #ff5050">♥</a>
                                        </c:when>
                                        <c:otherwise>
                                        <a href="javascript:void(0);" onclick="toggleLike(${pro.no}, '${sessionScope.sid}');" class="inbtn" data-product-id="${pro.no}"  style="color: #b4b4b4">♥</a>
                                        </c:otherwise>
                                        </c:choose>

                                        </c:if>
                            </tr>
                            <tr>
                                <td class="adminbtn">
                                    <c:if test="${sid eq 'admin'}">
                                        <a href="${path}/UpdateProduct.do?no=${pro.no}" class="inbtn">수정</a>
                                        <a href="${path}/DeleteProduct.do?no=${pro.no}" class="inbtn delete_btn">삭제</a>
                                    </c:if>
                                </td>
                            </tr>

                        </table>
                    </div>
                </div>
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



                <table class="tb2">
                    <thead>
                        <tr class="title">
                            <th colspan="5">상세 설명</th>
                        </tr>
                    </thead>

                </table>
                <table class="tb4">
                    <tr>
                        <td colspan="5" class="desc-area">
                            <img src="${path}/storage/${pro.imgSrc1}" width="320"/>
                            <br><br><br>
                            ${pro.plist}
                            <br><br><br>
                            ${pro.pcomment}
                            <br><br><br>
                            ${pro.imgSrc1}
                            <br><br><br>
                        </td>
                    </tr>
                </table>


                            <c:if test="${not empty pro.imgSrc2}">
                            <table class="tb1">
                                <thead>
                                <tr class="title">
                                    <th colspan="5">시범 강의</th>
                                </tr>
                                </thead>
                            </table>

                            <div class="player">
                                <div class="vdo_fr">
                                    <video id="video">
                                        <source src="${path}/storage/${pro.imgSrc3}" type="video/mp4" />
                                    </video>
                                </div>
                                <div id="timebar">
                                    <span id="currentTime"></span>
                                </div>
                                <div id="buttonbar">
                                    <button id="restart" class="player_btn"></button>
                                    <button id="rew" class="player_btn"></button>
                                    <button id="play" class="player_btn"></button>
                                    <button id="pause" class="player_btn"></button>
                                    <button id="stop" class="player_btn"></button>
                                    <button id="fastFwd" class="player_btn"></button>
                                    <button id="mute" class="player_btn"></button>
                                    <button id="unmute" class="player_btn"></button>
                                    <input id="vol" type="range" value="500" min="0" max="1000">
                                    <button id="vmup" class="player_btn"></button>
                                    <button id="vmdown" class="player_btn"></button>
                                    <button id="volTxt">100%</button>
                                    <button id="sizeup" class="player_btn"></button>
                                    <button id="sizedown" class="player_btn"></button>
                                    <button id="full" class="player_btn"></button>
                                    <button id="original" class="player_btn"></button>
                                </div>
                                <div id="progress">
                                    <div id="progressBar"></div>
                                </div>
                            </div>
                            </c:if>
                            <script src="./js/vdo.js"></script>


                <table class="tb2" id="myTable">
                    <thead>
                    <tr class="title">
                        <th colspan="5">후기</th>
                    </tr>
                    <tr>
                        <th class="item1">작성자</th>
                        <th class="item2">댓글</th>
                        <th class="item3">작성일</th>
                        <th class="item4"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="lev" items="${revList }">
                    <tr>
                        <td class="item1">${lev.cid}</td>
                        <td class="item2">${lev.content}</td>
                        <td class="item3">${lev.resdate}</td>
                        <td class="item4">
                            <c:if test="${sid eq lev.cid || sid eq 'admin'}">
                                <a href="${path}/UpdateReview.do?cid=${lev.cid}&par=${pro.no}" class="inbtn">수정</a>
                                <a href="${path}/DeleteReview.do?cid=${lev.cid}&par=${pro.no}" class="inbtn delete_btn"> 삭제 </a>
                            </c:if>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <script>
                    $(document).ready( function () {
                        $('#myTable').DataTable({
                            // sorting 화살표 제거
                            "targets": 'no-sort',
                            "bSort": false,

                            // 3번째 컬럼을 기준으로 내림차순 정렬
                            order: [[3, 'asc']],
                            pageLength : 5,
                            searching: false, //검색 제거
                            lengthChange: false, // show entries 제거
                            info: false,

                            language: {
                                emptyTable: '작성된 후기가 없습니다.'
                            }
                        });
                        $('#myTable').css({
                            'border':'none',
                        });

                    } );
                </script>
                <form action="${path}/AddReview.do" id="login_frm" class="frm">
                    <table class="tb3">
                        <tbody>
                        <tr>
                            <c:choose>
                                <c:when test="${check == '2'}">
                                    <th>${sid}</th>
                                    <th><textarea name="content" id="content" cols="100" rows="5" placeholder="리뷰 작성" required ></textarea></th>
                                    <th><input type="submit" value="글쓰기" class="inbtn" id="ans_btn"></th>
                                    <input type="hidden" name="pno" value="${pro.no}" readonly>
                                </c:when>
                                <c:when test="${check == '1'}">
                                    <p id="nologin_comment">구매 확정을 해야 후기 작성이 가능합니다.</p>
                                </c:when>
                                <c:otherwise>
                                    <p id="nologin_comment">도서를 구입한 고객님만 후기 작성이 가능합니다.</p>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        </tbody>
                    </table>
                </form>


            </div>
            <footer class="ft" id="ft">
            <%@ include file="../footer.jsp" %>
        </footer>
        </section>
    </div>



</div>
</body>
</html>