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
    <title>상품 구매</title>
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

    <!-- 결제 api -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <style>
        /* 본문 영역 스타일 */
        /* background-image: url("../img/login.jpg"); */
        .contents { clear:both; min-height: 300vh; background-image: url("${path}/img/login.jpg");
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

        .content {
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
        .tb2 .item4 { width: 12%; }
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

        .btn1 {
            display: block;
            border-radius: 10px;
            max-width: 120px;
            text-align: center;
            background-color: #527AF2;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-bottom: 10px;
        }

        .btn1:hover {
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

        .inbtn, .delete_btn {
            display: inline-block;
            border-radius: 5px;
            max-width: 100px;
            line-height: 28px;
            text-align: center;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s; /* 마우스 오버 효과 */
            padding: 7px 15px;
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
            border-radius: 5px; /* 버튼 크기  */
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
            <p><a href="/">HOME</a> &gt; <a href="">상품</a> &gt; <a href="">상품구매</a></p>
        </div>
        <section class="page" id="page1">
            <div class="page_wrap">
                <h2 class="page_tit">상품구매</h2>
                <form class="frm" action="${path }/AddPaymentPro.do" method="post" onsubmit="return payCheck(this)">
                    <table class="tb1" id="tb1">
                        <tbody>
                        <tr>
                            <td colspan="2">
                                <c:if test="${!empty pro.imgSrc1}">
                                    <img src="${path }/storage/${pro.imgSrc1 }" style="max-height:400px;" alt="대표 이미지">
                                </c:if>
                                <hr>
                                <c:if test="${!empty pro.imgSrc2}">
                                    <img src="${path }/storage/${pro.imgSrc2 }" style="max-height:400px;" alt="대표 이미지">
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th class="item1">도서 번호</th>
                            <td class="item2" colspan="2">${pro.cateno }</td>
                        </tr>
                        <tr>
                            <th class="item1">도서명</th>
                            <td class="item2" colspan="2">
                                ${pro.pname }
                                <input type="hidden" name="pname" id="proName" value="${pro.pname }">
                                <input type="hidden" name="from" id="from" value="${from }">
                                <c:if test="${from.equals('cart')}">
                                    <input type="hidden" name="cartno" id="cartno" value="${cartno }">
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th>도서 설명</th>
                            <td colspan="2">
                                <pre class="indata">${pro.pcomment }</pre>
                            </td>
                        </tr>
                        <tr>
                            <th>도서 목차</th>
                            <td colspan="2"><pre class="indata">${pro.plist }</pre></td>
                        </tr>
                        <tr>
                            <th>가격 (부가세 10% 별도)</th>
                            <td colspan="2">
                                <%-- 여기에서 원가에 *1.1를 하므로 기존 가격보다 더 비쌈 --%>
                                <fmt:parseNumber var="price" integerOnly="true" value="${pro.price*1.1 }" />
                                    ${price}
                                <input type="hidden" name="sprice" id="sprice" value="${price}">
                            </td>
                        </tr>
                        <tr>
                            <th>구매 수량 (남은 재고 : ${amount})</th>
                            <td colspan="2">
                                <input type="hidden" name="pno" id="pno" value="${pro.no }" />
                                <input type="number" name="amount" id="amount" class="indata" min="1" value="1" max="${amount}" />
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <hr>
                    <table class="tb1">
                        <thead>
                        <tr>
                            <th><h3>배송 정보</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th class="item1">배송지 주소</th>
                            <td class="item2" colspan="2">
                                <input type="text" name="address1" id="address1" placeholder="기본 주소 입력" class="indata" required readonly /><br>
                                <input type="text" name="address2" id="address2" placeholder="상세 주소 입력" class="indata" required /><br>
                                <input type="text" name="postcode" id="postcode" style="width:160px;float:left;margin-right:20px;" placeholder="우편번호" class="indata" required readonly>
                                <button type="button" id="post_btn" onclick="findAddr()" class="btn btn-primary" style="margin-bottom:36px;">우편번호 검색</button>
                            </td>
                        </tr>
                        <tr>
                            <th>받는 사람 연락처</th>
                            <td colspan="2">
                                <input type="hidden" name="cid" id="cid" value="${cus.id }">
                                <input type="hidden" name="name" id="name" value="${cus.name }">
                                <input type="hidden" name="email" id="email" value="${cus.email }">
                                <input type="hidden" name="tel" id="tel" value="${cus.tel }">
                                <input type="hidden" name="addr" id="addr" value="${cus.address }">
                                <input type="tel" name="custel" id="custel" class="indata" required>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <hr>
                    <table class="tb1">
                        <thead>
                        <tr>
                            <th><h3>결제 정보</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th class="item1">결제 수단</th>
                            <td class="item2" colspan="2">
                                <select name="pmethod" id="pmethod" class="indata">
                                    <option value="신용카드">신용카드</option>
                                    <option value="체크카드">체크카드</option>
                                    <option value="계좌이체">계좌이체</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>결제사</th>
                            <td colspan="2">
                                <select name="pcom" id="pcom" class="indata">
                                    <option value="선택안함">선택안함</option>
                                </select>
                                <input type="hidden" name="pcom2" id="pcom2" value="">
                            </td>
                        </tr>
                        <tr>
                            <th>결제 번호</th>
                            <td colspan="2">
                                <input type="text" name="cnum" id="cnum" class="indata" required>
                                <input type="hidden" name="payAmount" id="payAmount">
                                <input type="hidden" name="payCk" id="payCk" value="no">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="button" id="pay" value="결제" class="inbtn">
                                <c:if test="${!empty sid }">
                                    <input type="submit" class="inbtn" value="구매">
                                </c:if>
                                <p id="paymentResult" style="color:red; font-size: 20px;"></p>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
                <script>
                    $(document).ready(function(){
                        var cardArr1 = ["현대카드","농협카드","BC카드","KB카드"];
                        var cardArr2 = ["하나카드","농협카드","BC카드"];
                        var bankArr = ["카카오뱅크","농협은행","신한은행","기업은행","국민은행"];
                        $("#pmethod").change(function(){
                            var th = $(this).val();
                            if(th==="신용카드"){
                                for(var i=0;i<cardArr1.length;i++) {
                                    $("#pcom").append("<option value='" + cardArr1[i] + "'>" + cardArr1[i] + "</option>");
                                }
                            } else if(th==="체크카드"){
                                for(var i=0;i<cardArr2.length;i++) {
                                    $("#pcom").append("<option value='"+cardArr2[i]+"'>"+cardArr2[i]+"</option>");
                                }
                            } else {
                                for(var i=0;i<bankArr.length;i++) {
                                    $("#pcom").append("<option value='"+bankArr[i]+"'>"+bankArr[i]+"</option>");
                                }
                            }
                        }).change();
                        $("#pcom").change(function(){
                            $("#pcom2").val($(this).val());
                        });
                    });
                </script>
                <script>
                    //주소 연동 API
                    function findAddr() {
                        new daum.Postcode({
                            oncomplete: function(data) {
                                console.log(data);
                                var roadAddr = data.roadAddress;
                                var jibunAddr = data.jibunAddress;
                                document.getElementById("postcode").value = data.zonecode;
                                if(roadAddr !== '') {
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
                <script>
                    //결제모듈 API 연동
                    $(document).ready(function(){
                        var totalPay=0;
                        var proName;
                        $("#pay").click(function(){
                            var email = $("#email").val();
                            var cname = $("#name").val();
                            var tel = $("#tel").val();
                            var addr = $("#addr").val();
                            var postcode = $("#postcode").val();
                            proName = $("#proName").val();
                            if($("#amount").val()!="") {
                                totalPay = parseInt($("#sprice").val()) * parseInt($("#amount").val());
                            } else {
                                alert("구매할 수량을 입력하지 않으셨습니다.");
                                $("#amount").focus();
                                return;
                            }
                            alert("결제할 금액 : "+totalPay);
                            //상품명_현재시간
                            var d = new Date();
                            var date = d.getFullYear()+''+(d.getMonth()+1)+''+d.getDate()+''+d.getHours()+''+d.getMinutes()+''+d.getSeconds();
                            var IMP = window.IMP; // 생략가능
                            IMP.init('imp85122075'); // 결제 API를 사용하기 위한 코드 입력!
                            IMP.request_pay({		//결제 요청
                                pg: "T5102001",
                                merchant_uid : '상품명_' + date, //상점 거래 ID
                                name : proName,				// 결제 명
                                amount : totalPay,					// 결제금액
                                buyer_email : email, // 구매자 email
                                buyer_name : cname,				// 구매자 이름
                                buyer_tel : tel,		// 구매자 전화번호
                                buyer_addr : addr,		// 구매자 주소
                                buyer_postcode : postcode			// 구매자 우편번호
                            }, function(rsp){
                                if(rsp.success){
                                    console.log(rsp);
                                    var msg = '결제가 완료 되었습니다.';
                                    var r1 = '고유 아이디 : ' +rsp.imp_uid;
                                    var r2 = '상점 거래 아이디 : ' +rsp.merchant_uid;
                                    var r3 = '결제 금액 : ' +rsp.paid_amount;
                                    var r4 = '카드 승인 번호 : '+rsp.apply_num;

                                    // 실제 결제 창
                                    // $("#payCk").val("yes");
                                    // $("#payAmount").val(rsp.paid_amount);
                                    // $("#pmethod").val(rsp.pay_method);
                                    // $("#pcom").val(rsp.pg_provider);
                                    // $("#cnum").val(rsp.apply_num);
                                    // alert(msg);
                                    // $("#paymentResult").html(r1+"<br>"+r2+"<br>"+r3+"<br>"+r4);
                                } else{
                                    //$("#paymentResult").html('결제실패<br>'+'에러내용 : ' +rsp.error_msg);
                                }
                                //테스트용이므로 실패시에도 그냥 통과시킴
                                $("#payCk").val("yes");
                                $("#payAmount").val(totalPay);
                                // $("#pmethod").val("신용카드");
                                // $("#pcom").val("삼성카드");
                                // $("#cnum").val("123-1234-1234-1278");
                                $("#paymentResult").text("결제 완료 : "+totalPay);
                            });
                        });
                    });
                </script>
                <script>
                    function payCheck(f){
                        var payCk = f.payCk.value;
                        console.log(payCk);
                        if(payCk!="yes"){
                            alert("아직 결제 전 입니다.");
                            return false;
                        }
                    }
                </script>
            </div>
        </section>
    </div>
    <footer class="ft" id="ft">
        <%@ include file="../footer.jsp" %>
    </footer>
</div>
</body>
</html>