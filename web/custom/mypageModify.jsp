<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="<%=request.getContextPath() %>" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>정보수정</title>
    <%@ include file="../head.jsp" %>

    <!-- 스타일 초기화 : reset.css 또는 normalize.css -->
    <link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" rel="stylesheet">

    <!-- 플러그인 연결-->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <!-- 스타일 초기화 -->
    <link rel="stylesheet" href="${path}/css/reset.css">
    <!-- 웹 폰트 -->
    <link rel="stylesheet" href="${path}/css/font.css">

    <!-- css 모듈화 -->
    <link rel="stylesheet" href="${path}/css/common.css">
    <link rel="stylesheet" href="${path}/css/hd.css">
    <link rel="stylesheet" href="${path}/css/ft.css">
    <style>
        .contents {
            clear: both;
            min-height: 250vh;
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
            height: 150vh;
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
            padding-top: 1em;
            color: #fff;
            padding-bottom: 2.4rem;
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


        d {
            box-sizing: border-box;
            font-family: 'Noto Sans KR', sans-serif;
        }

        .join_form {
            margin: 0 auto;
            margin-top: 50px;
            background: #eaf4fa;
            border-radius: 5px;
            width: 600px;
            height: auto;
            max-width: 100%;
            overflow: hidden;
            box-shadow: 0 0 25px rgba(0, 0, 0, 0.2);
        }

        .header {
            padding: 40px 40px;
            text-align: center;

        }

        .header img {

            display: inline-block;

        }

        .header h2 {
            display: inline-block;
            margin: 0;
            font-size: 30px;
            font-weight: bolder;
            padding-bottom: 15px;
            padding-left: 5px;

        }


        .frm1 {
            padding: 30px 40px;
        }

        form inf {

            padding-bottom: 10px;

        }


        .register_info_id {
            position: relative;
        }

        .register_info_id button {
            position: absolute;
            width: 90px;
            height: 40px;
            top: 0;
            bottom: 0;
            right: 5px;
            margin: 32px 0;
            border-radius: 3px;
            background: cornflowerblue;
            color: #fff;
            border: 0;
        }


        .register_info input {
            border: 1.5px solid #535053;
            border-radius: 3px;
            display: block;
            font-size: 11px;
            padding: 5px;
            width: 80%;


        }


        .submit {
            background-color: cornflowerblue;
            border: 2px solid cornflowerblue;
            width: 120px;
            border-radius: 5px;
            padding: 5px;
            color: #ffffff;
            float: left;
        }


        .agree_fr {
            margin: 20px auto;
            border: 1px solid #eee;
            padding: 20px;
            overflow-y: auto;
            height: 100px;
            white-space: pre-wrap;
        }


        .agree_fr2 {
            border-radius: 5px;
            height: 100px;
            border: 1px solid #d9d6d6;
            background-color: white;
            white-space: pre-line;
            overflow-y: scroll;
            margin: 0 auto 20px;
            padding: 10px;
        }

        .frm1 p {
            text-align: right;
        }


        .frm1 input {
            display: inline-block;
            width: 100%;
            border: 1px solid #d9d6d6;
            padding: 17px 25px;
            box-sizing: border-box;
            margin: 0;
            border-radius: 5px;
        }

        .register_info {
            padding: 10px 0;
        }

        input::placeholder {
            font-size: 15px;
            color: #9d9d9d;
        }

        label {
            padding-bottom: 10px;
            display: block;
            font-weight: bold;
            font-size: 16px;
            color: #000;
        }

        label > span {
            font-weight: normal;
            color: red;
            font-size: 13px;
            padding-left: 10px;
        }

        .btn_fr input {
            display: inline-block;
            width: auto;
            float: left;
        }

        .btn_fr label {
            padding-left: 25px;
        }

        input.inbtn {
        }

        button.submit {
            display: block;
            height: 50px;
            background: cornflowerblue;
            border: 0;
            border-radius: 5px;
            width: 50%;
            color: #fff;
            font-size: 18px;
            float: left;
            margin: 0 5px;
            padding: 0;
            box-shadow: 2px 3px 7px 0px #0000005e;
        }

        button.reset {
            height: 50px;
            background: gray;
            border: 0;
            border-radius: 5px;
            width: 50%;
            color: #fff;
            font-size: 18px;
            padding: 0;
            margin: 0 5px;
            box-shadow: 2px 3px 7px 0px #0000005e;
        }

        .btn1 {
            color: #fff;
            width: 70%;
            display: flex;
            margin: 20px auto;
        }
    </style>
    <c:set var="path" value="<%=request.getContextPath() %>" />
</head>

<body>
<div class="wrap">
    <header class="hd" id="hd">
        <%@ include file="../header.jsp" %>
    </header>
    <div class="contents" id="contents">
        <div class="breadcrumb">
            <p><a href="">HOME</a> &gt; <span>정보수정</span></p>
        </div>
        <section class="page" id="page1">
            <div class="page_wrap">
                <h2 class="page_tit">정보수정</h2>
                <div class="join_form">
                    <form class="frm1" action="${path }/MypageModifyPro.do" method="post" onsubmit="return joinCheck(this)">
                        <div class="register_info">
                            <label>아이디<%--<span>20자 이내 비밀번호를 입력하세요</span>--%></label>
                            <input type="text" name="id" id="id" class="indata"
                                   placeholder="비밀번호 (문자, 숫자, 특수문자 포함 8~20자)" value="${cus.id}" readonly>
                        </div>

                        <div class="register_info">
                            <label>기존 비밀번호<%--<span>20자 이내 비밀번호를 입력하세요</span>--%></label>
                            <input type="password" name="pw" id="pw" class="indata"
                                   placeholder="비밀번호 (문자, 숫자, 특수문자 포함 8~20자)" required="">
                        </div>

                        <div class="register_info">
                            <label>새로운 비밀번호<%--<span>비밀번호가 일치하지 않습니다</span>--%></label>
                            <input type="password" name="pw2" id="pw2" class="indata" placeholder="비밀번호 확인" required="">
                        </div>

                        <div class="register_info">
                            <label>새로운 비밀번호 확인<%--<span>비밀번호가 일치하지 않습니다</span>--%></label>
                            <input type="password" name="pw3" id="pw3" class="indata" placeholder="비밀번호 확인" required="">
                        </div>

                        <div class="register_info">
                            <label>이름</label>
                            <input type="text" name="name" id="name" class="indata" placeholder="이름" value="${cus.name}" readonly>
                        </div>

                        <div class="register_info">
                            <label>생년월일</label>
                            <td><input type="date" name="birth" id="birth" placeholder="생년월일 입력" class="form-control" value="${cus.birth}" required></td>
                        </div>

                        <div class="register_info">
                            <label>전화번호</label>
                            <input type="tel" name="tel" id="tel" class="indata" placeholder="전화번호" value="${cus.tel}" required="">
                        </div>

                        <div class="register_info">
                            <label>이메일 주소</label>
                            <input type="email" name="email" id="email" class="indata" placeholder="이메일" value="${cus.email}" required="">
                        </div>

                        <div class="register_info">
                            <input type="text" name="address1" id="address1" placeholder="기본 주소 입력" class="form-control" value="<c:out value='${fn:split(cus.address, "<br>")[0]}' />" readonly required><br>
                            <input type="text" name="address2" id="address2" placeholder="상세 주소 입력" class="form-control" value="<c:out value='${fn:split(cus.address, "<br>")[1]}' />" required ><br>
                            <input type="text" name="postcode" id="postcode" style="width:160px;float:left;margin-right:20px;" placeholder="우편번호" class="form-control" value="<c:out value='${fn:split(cus.address, "<br>")[2]}' />" readonly required>
                            <button type="button" id="post_btn" onclick="findAddr()" class="btn btn-primary">우편번호 검색</button>
                        </div>

                        <div class="btn1">
                            <button class="submit btn btn-primary" type="submit">정보 수정</button>
                            <button class="reset btn" type="reset">메인으로</button>
                        </div>
                    </form>
                </div>
            </div>
            <script>
                function findAddr(){
                    new daum.Postcode({
                        oncomplete:function(data){
                            console.log(data);
                            var roadAddr = data.roadAddress;
                            var jibunAddr = data.jibunAddress;
                            document.getElementById("postcode").value = data.zonecode;
                            if(roadAddr !== ''){
                                document.getElementById("address1").value = roadAddr;
                            } else if(jibunAddr !== ''){
                                document.getElementById("address1").value = jibunAddr;
                            }
                            document.getElementById("address2").focus();
                        }
                    }).open();
                }
            </script>
            <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        </section>
    </div>
    <footer class="ft" id="ft">
        <%@ include file="../footer.jsp" %>
    </footer>
</div>
</body>
</html>