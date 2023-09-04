<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 정보수정</title>
    <c:set var="path" value="<%=request.getContextPath() %>"/>
    <%--   <%@ include file="../../common.jsp"%>--%>
    <%@ include file="../../head.jsp" %>

    <!-- 스타일 초기화 : reset.css 또는 normalize.css -->
    <link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" rel="stylesheet">


    <!-- 플러그인 연결-->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <!-- 스타일 초기화 -->
    <link rel="stylesheet" href="${path }/css/reset.css">
    <!-- 웹 폰트 -->
    <link rel="stylesheet" href="${path }/css/font.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <!-- css 모듈화 -->
    <link rel="stylesheet" href="${path }/css/common.css">
    <link rel="stylesheet" href="${path }/css/hd.css">
    <link rel="stylesheet" href="${path }/css/ft.css">
    <%--<link rel="stylesheet" href="${path}/css/asidebar.css">--%>


    <style>


        /* 본문 영역 스타일 */
        .contents {
            clear: both;
            min-height: 200vh;
            background-color: #ffffff;
            /*background-image: url("
        ${path} /img/login.jpg");
            background-repeat: no-repeat;
            background-position: center -250px;*/
        }

        .contents::after {
            content: "";
            clear: both;
            display: block;
            width: 100%;
        }


        /* 본문 */

        .page {
            float: right;
            width: 85%;
            height: 100vh;
            margin-top: 50px;
            margin-bottom: 10px;
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
            padding-top: 0.7em;
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
        }

        .breadcrumb a {
            color: #fff;
        }

        /*본문 내용*/
        .adminContent {
            width: 600px;
            margin: 50px;
            background-color: #ffffff;

            display: inline-block;


        }
        /*사이드 바*/
        /* 사이드바 시작 */
        aside{
            float: left;
            width: 30%;
            min-height: 250px;
            z-index: 999;
            position: absolute;    /*절대값으로 위치를 설정*/
            left: 0px;
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

            position: fixed; /* 스크롤을 따라오도록 지정 */
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
            padding-left: 30px;padding-right: 30px;
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
            width: 120%; /* 1차 메뉴의 너비를 상속 */
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


        /* 메인 */
        .nav-container {
            background-color: white;
            width: 100%;
            height: 60px;
            margin-top: 50px;
            box-shadow: 0px 2px 0px 0px #eee;
            display: flex;
            justify-content: space-between;
        }

        .search-box {
            display: inline-flex;
            align-items: center;
            margin-left: 100px;
        }

        .search-box-input {
            padding: 5px;
            border-radius: 5px 0px 0px 5px;
            border: 1px solid lightgrey;
            width: 150px;
            transition: width 1s;
        }

        .search-box-input:focus {
            width: 300px;
        }

        .search-box-btn {
            background-color: #343940;
            color: white;
            border: none;
            border-radius: 0px 5px 5px 0px;
            height: 36px;
            width: 40px;
        }

        .notification {
            display: flex;
            margin-right: 20px;
            align-items: center;
        }

        .notification-icon {
            font-size: x-large;
            margin-left: 60px;
            color: #808080;
            margin-bottom: 20px;
        }

        .notification-badge {
            position: relative;
            left: 15px;
            top: 16px;
            background-color: #DC3545;
            width: 20px;
            height: 20px;
            border-radius: 5px;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            font-size: small;
            font-weight: 600;
        }

        #notification-name {
            margin-left: 40px;
            color: grey;
        }




    </style>

</head>
<body>
<header class="hd">
    <%@ include file="../../header.jsp" %>
</header>
<div class="container-fluid">
    <%@ include file="./adminAside.jsp" %>
    <div class="contents" style="min-height:200vh">

        <nav class="nav-container">
            <div class="search-box">
                <input type="text" class="search-box-input" placeholder="Search"/>
                <button class="search-box-btn">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </div>

            <div class="notification">
                <div class="notification-icon">
                    <span class="notification-badge">5</span>
                    <i class="fa-solid fa-bell"></i>
                </div>
                <div class="notification-icon">
                    <span class="notification-badge">9</span>
                    <i class="fa-solid fa-envelope"></i>
                </div>
                <span id="notification-name">admin</span>
            </div>
        </nav>

        <div class="page">

            <%-- 대쉬보드 --%>
            <div class="container">
                <h4 class="my-4"></h4>
                <div class="row">
                    <div class="col-lg-3">
                        <div class="card monthly">
                            <div class="card-body d-flex justify-content-between">
                                <div>
                                    <p class="card-text mb-2">신규 회원 수</p>
                                    <h4>23 명</h4>
                                </div>
                                <i
                                        class="fas fa-calendar"
                                        style="font-size: 30px; margin-top: 20px; color: #6c757d"
                                ></i>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="card annual">
                            <div class="card-body d-flex justify-content-between">
                                <div>
                                    <p class="card-text mb-2">매출 (일)</p>
                                    <h4>500,000 원</h4>
                                </div>
                                <i
                                        class="fa-solid fa-dollar-sign"
                                        style="font-size: 30px; margin-top: 20px; color: #6c757d"
                                ></i>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="card tasks">
                            <div class="card-body d-flex justify-content-between">
                                <div>
                                    <p class="card-text mb-2">일일 방문자 수</p>
                                    <h4>100 명</h4>
                                </div>
                                <i
                                        class="fa-solid fa-list-check"
                                        style="font-size: 30px; margin-top: 20px; color: #6c757d"
                                ></i>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="card requests">
                            <div class="card-body d-flex justify-content-between">
                                <div>
                                    <p class="card-text mb-2">답변대기 문의</p>
                                    <h4>13 개</h4>
                                </div>
                                <i
                                        class="fa-solid fa-comments"
                                        style="font-size: 30px; margin-top: 20px; color: #6c757d"
                                ></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container mt-3">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="card-header h5">방문자 현황</div>
                        <div class="card-body"><canvas id="myChart"></canvas></div>
                    </div>
                    <div class="col-lg-4">
                        <div class="card">
                            <div class="card-header h5">Tasks</div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">신규주문</li>
                                <li class="list-group-item">취소관리</li>
                                <li class="list-group-item">반품관리</li>
                                <li class="list-group-item">교환관리</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    var ctx = document.getElementById("myChart").getContext("2d");
    var myChart = new Chart(ctx, {
        type: "line",
        data: {
            labels: ["January", "February", "March", "April", "May", "June"],
            datasets: [
                {
                    label: "Monthly",
                    data: [12, 19, 3, 5, 2, 3],
                    backgroundColor: "rgba(255, 99, 132, 0.2)",
                    borderColor: "rgba(255, 99, 132, 1)",
                    borderWidth: 1,
                    fill: true,
                    tension: 0.2,
                },
            ],
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                },
            },
        },
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
<%@ include file="../../footer.jsp" %>
</body>
</html>


