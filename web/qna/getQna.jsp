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
    <style>
        /* 본문 영역 스타일 */
        /* background-image: url("../img/login.jpg"); */
        .contents { clear:both; min-height: 250vh; background-image: url("${path}/img/login.jpg");
            background-repeat: no-repeat; background-position: center -250px; }
        .contents::after { content:""; clear:both; display:block; width:100%; }

        .page { clear:both; width: 100vw; height: 100vh; position:relative; }
        .page::after { content:""; display:block; width: 100%; clear:both; }

        .page_wrap { clear:both; width: 1200px; height: auto; margin:0 auto; }
        .page_tit { font-size:48px; text-align: center;  color:#fff;
            padding-bottom: 1.3em; }

        .breadcrumb { clear:both;
            width:1200px; margin: 0 auto; text-align: right; color:#fff;
            padding-top: 28px; padding-bottom: 28px; }
        .breadcrumb a { color:#fff; }

        .tb1 {
            width: 1200px;
            font-size: 17px;
            margin-bottom: 50px;
            border-collapse: collapse; /* 테이블 간격 없애기 */
        }

        .tb1 thead th {
            line-height: 32px;
            padding: 12px 15px; /
            /*border: 1px solid #f5f5f5; !*/
            box-sizing: border-box;
            background-color: #eeeeee; /* 배경색 조정 */
            font-size: 22px;
            font-weight: 600;
            border-radius: 10px;
        }

        .tb1 thead td {
            font-size: 16px;
            text-align: right;
            line-height: 40px;
            border-bottom: #7e7e7e 1px solid;

        }
        .tb1 thead td:nth-child(1) {
            width: 6%;
            text-align: center;
        }.tb1 thead td:nth-child(2) {
            text-align: left;
             width: 14%;
         }
        .tb1 thead td:nth-child(3) {
            font-weight: 600;
            width: 62%;
        }
        .tb1 thead td:nth-child(4) {
            width: 10%;
        }
        .tb1 thead td:nth-child(5) {
            width: 8%;

        }
        /*글 내용*/
        .tb1 tbody tr td {
            padding-left: 10px;
            padding-top: 40px;
            font-size: 18px;
            text-align: left;


        }

        .content > div {
            min-height: 400px;
            border-bottom: 1px solid #7e7e7e;
        }


        .title {
            text-align: left;
            font-weight: bold;
        }



        .tb2{
            width: 1200px;
        }

        .tb2 .item1 { width: 10%; }
        .tb2 .item2 { width: 68%;}
        .tb2 .item3 { width: 10%; }
        .tb2 .item4 { width: 12%;
        }
        .tb2 td {
            border-bottom: 1px solid #7e7e7e;
            vertical-align: middle;
            height: 60px;
        }

        .tb2 thead tr th{
            text-align: center;
        }
        .tb2 tbody tr td:first-child{
            text-align: center;
            font-weight: bold;

        }
        .tb2 tbody tr td:nth-child(2){
            word-break: break-word;

        }
        .tb2 tbody tr td:nth-child(3){
            text-align: center;
        }

        .inbtn, .delete_btn {
            display: inline-block;
            border-radius: 5px;
            width: 50px;
            line-height: 28px;
            text-align: center;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s; /* 마우스 오버 효과 */
        }

        .inbtn {
            background-color: #333;
            color: #fff;
        }

        .delete_btn {
            background-color: red;
            color: #fff;
        }

        .inbtn:hover, .delete_btn:hover {
            background-color: #555;
        }

        .frm {
            margin-top: 50px;
        }

        /*.frm tr > * {*/
        /*    margin-right: 10px;*/
        /*}*/

        .btn_group {
            margin-top: 50px;
        }

        #ans_btn {

            border-radius: 5px;
            width: 80px; /* 버튼 크기  */
            padding: 7px 15px;
            text-align: center;
        }



        .tb3{
            width: 1200px;
            height: 140px;
            margin-bottom: 50px;
            background-color: #eeeeee;
            border-radius: 10px;
        }

        .tb3 th {
            line-height: 100px;

            text-align: center;
            vertical-align: middle;

        }
        .tb3 th:first-child{
            width: 15%;
            font-weight: bold;
            font-size: 18px;

        }
        .tb3 th:nth-child(2){
            width: 70%;
        }
        .tb3 th:last-child{
            width: 15%;

        }
        textarea {
             resize: none;
             padding: 10px;
             height: 80px;
             border: 1px solid #ccc;
             border-radius: 5px;
             vertical-align: middle;
         }
        #nologin_comment {
            width: 1200px;
            text-align: center;
            padding-top: 60px;
            position: absolute;
            font-weight: bold;
        }
    </style>
</head>

<body>
<div class="wrap">
    <header class="hd" id="hd">
        <%@ include file="../header.jsp" %>
    </header>
    <div class="contents" id="contents">
        <div class="breadcrumb">
            <p><a href="/">HOME</a> &gt; <a href="">QnA</a> &gt; <a href="">QnA 상세</a></p>
        </div>
        <section class="page" id="page1">
            <div class="page_wrap">
                <h2 class="page_tit">QnA 상세</h2>
                <table class="tb1">

                    <thead>
                        <tr class="title">
                            <th colspan="5">${board.title}</th>
                        </tr>
                        <tr>
                            <td>
                                <c:if test="${sid eq board.cid}">
                                    <a href="${path}/UpdateQna.do?qno=${board.qno}&lev=0" class="inbtn">수정</a>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${sid eq board.cid || sid eq 'admin'}">
                                    <a href="${path}/DeleteQna.do?qno=${board.qno}&lev=0" class="inbtn delete_btn">삭제</a>
                                </c:if>
                            </td>
                            <td>${board.cid}</td>
                            <td>${board.cnt}</td>
                            <td>${board.resdate}</td>
                        </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="5" class="content">
                            <div>${board.content}</div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <table class="tb2" id="myTable">
                    <thead>
                    <tr>
                        <th class="item1">작성자</th>
                        <th class="item2">댓글</th>
                        <th class="item3">작성일</th>
                        <th class="item4"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="con" items="${comment }">
                        <tr>
                            <td class="item1">${con.cid}</td>
                            <td class="item2">${con.content}</td>
                            <td class="item3">${con.resdate}</td>
                            <td class="item4">
                                    <%--                            0813 댓글 수정버튼 표시안되어 코드 수정. by 백준철--%>
                                <c:if test="${sid eq con.cid}">
                                    <a href="${path}/UpdateQna.do?qno=${con.qno}&lev=1" class="inbtn">수정</a>
                                    <a href="${path}/DeleteQna.do?qno=${con.qno}&lev=1" class="inbtn delete_btn"> 삭제 </a>
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
                                emptyTable: '작성된 댓글이 없습니다.'
                            }
                        });
                        $('#myTable').css({
                            'border':'none',
                        });

                    } );
                </script>
                <form action="${path}/AddComment.do" id="login_frm" class="frm">
                    <table class="tb3">
                        <tbody>
                        <tr>
                            <c:if test="${not empty sid}">
                            <th>${sid}</th>
                            <th><textarea name="content" id="content" cols="100" rows="5" placeholder="댓글 입력" required ></textarea></th>
                            <th><input type="submit" value="글쓰기" class="inbtn" id="ans_btn"></th>
                            <input type="hidden" name="qno" value="${board.qno}" readonly>
                            <input type="hidden" name="id" value="${sid}" readonly>
                            </c:if>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </section>
    </div>
    <footer class="ft" id="ft">
        <%@ include file="../footer.jsp" %>
    </footer>
</div>
</body>
</html>