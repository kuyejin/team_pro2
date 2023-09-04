<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path4" value="<%=request.getContextPath() %>" />
<aside class="side-bar">
    <section class="side-bar__icon-box">
        <section class="side-bar__icon-1">
            <div></div>
            <div></div>
            <div></div>
        </section>
    </section>
    <ul>
        <li>
            <%-- <a href="#"><i class="fa-solid fa-cat"></i> menu1</a>--%>
            <a href="${path4}/Mypage.do">마이페이지</a>
        </li>
        <li>
            <a href="${path4}/PaymentList.do">결제정보</a>
        </li>
        <li>
            <a href="${path2}/CartList.do">장바구니</a>
        </li>
        <li>
            <a href="${path2}/LikeList.do">찜목록</a>
        </li>
        <li>
            <a href="${path2}/ReviewList.do">내가쓴 후기</a>
        </li>
    </ul>
</aside>
