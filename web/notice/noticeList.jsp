<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>고객센터</title>
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
            min-height: 100vh;
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
            width: 300px;
            margin: 100px;
            font-size: 20px;
            border-collapse: collapse;
        }
        .tb1 th {
            background-color: #527AF2;
            color: #fff;
            padding: 16px;
            border: 1px solid #527AF2;

        }
        .tb1 td {
            padding: 12px 16px;
            border: 1px solid #ddd;
            text-align: center;
            line-height: 24px;

        }
        .tb1 th:first-child {
            width: 40px;
        }

        .tb1 tbody {

        }

        .tb1 .item1 {
            width: 12%;
        }
        .tb1 .item2 {
            width: 58%;

            text-align: left;
        }
        .tb1 .item3 {
            width: 15%;
        }

        /* 기타 버튼 스타일 */
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


        .content {
            width: 900px;
            margin: 0 auto;
        }
        .helpmenu {
            width: 250px;
            border-right: 1px solid #e0e0e0;
            padding: 20px 0;
            display: inline-block;
            float: left;
        }

        .helpmenu ul {
            list-style-type: none;
            padding-left: 0;
            margin: 0;
        }

        .helpmenu ul li {
            margin-bottom: 15px;
            line-height: 40px;
        }

        .helpmenu ul li a {
            display: block;
            text-decoration: none;
            padding-left: 30px;
            font-size: 22px;
            font-weight: bold;
        }

        #selectedmenu {
            background-color: #527AF2;

            border-radius: 5px;
        }
        #selectedmenu a {
            color: white;
        }


        .helpcontent {
            width: 600px;
            padding: 20px;
            background-color: #ffffff;
            border-left: 1px solid #e0e0e0;
            display: inline-block;
            float: left;
        }
        #myTable {
            font-size: 14px;
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
            <p><a href="/">HOME</a> &gt; <a href="/help/help.jsp">공지사항</a></p>
        </div>
        <section class="page" id="page1">
            <div class="page_wrap">
                <h2 class="page_tit">공지사항</h2>

                <div class="content">
                    <div class="helpmenu">
                        <ul>
                            <li id="selectedmenu"><a href="${path }/NoticeList.do">공지사항</a></li>
                            <li><a href="${path }/FaqList.do">FAQ</a></li>
                            <%-- <li><a href="helpQuestion.jsp">문의하기</a></li> --%>
                        </ul>
                    </div>
                    <div class="helpcontent">
                        <table class="tb1" id="myTable">
                            <thead>
                            <tr>
                                <th class="item1" style="text-align: center">번호</th>
                                <th class="item2" style="text-align: center">제목</th>
                                <th class="item3" style="text-align: center">작성일</th>
                                <th class="item3" style="text-align: center">조회수</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="noti" items="${notiList }" varStatus="status">
                                <tr>
                                    <td class="item1">${status.count}</td>
                                    <td class="item2"><a href="${path}/Notice.do?no=${noti.no}">${noti.title}</a></td>
                                    <td class="item3">${noti.resdate}</td>
                                    <td class="item3">${noti.visited}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <script>
                            $(document).ready( function () {
                                $('#myTable').DataTable({
                                    order: [[0, 'desc']], // 0번째 컬럼을 기준으로 내림차순 정렬
                                    info: false,
                                    //dom: 't<f>p',
                                    dom: 'ftp',
                                    language: {
                                        emptyTable: '작성된 글이 없습니다.'
                                    }

                                });
                            } );
                            $(document).ready(function() {
                                $('.dataTables_paginate').css({
                                    'textAlign':'left',
                                    'float': 'none',
                                    'margin-top':'40px',
                                    'padding-top' :'50px'
                                });
                                $('.dataTables_filter').css({
                                    'float': 'left',
                                    'margin-top':'14px',
                                    'margin-right':'280px'
                                });
                                $('#myTable_paginate').css({
                                    'margin-top':'-30px',
                                    'float':'right'
                                });


                            });

                        </script>
                        <div class="btn_group">
                            <c:if test="${sid eq 'admin'}">
                                <a href="${path2 }/AddNotice.do" class="inbtn"> 글 작성 </a>
                            </c:if>
                        </div>
                    </div>

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