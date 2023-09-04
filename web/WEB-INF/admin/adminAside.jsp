<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path3" value="<%=request.getContextPath() %>" />
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
            <a href="#">게시글 관리</a>
            <ul>
                <li><a href="${path3}/AdminReviewList.do">댓글관리</a></li>
                <%--<li><a href="#">text2</a></li>
                <li><a href="#">text3</a></li>
                <li><a href="#">text4</a></li>--%>
            </ul>
        </li>
        <li>
            <a href="#">상품 관리</a>
            <ul>
                <li><a href="${path3 }/ProList.do" class="nav-link">상품목록</a></li>
                <li><a href="${path3 }/AddProduct.do" class="nav-link">상품등록</a></li>
                <li><a href="${path3 }/AddReceive.do" class="nav-link">입고</a></li>
                <li><a href="${path3 }/UpdateReceive.do" class="nav-link">추가입고</a></li>
                <li><a href="${path3 }/InventoryList.do" class="nav-link">재고관리</a></li>
            </ul>
        </li>
        <li>
            <a href="#">회원 관리</a>
            <ul>
                <li><a href="${path3 }/CustomList.do">회원 목록</a></li>
               <%-- <li><a href="#">text2</a></li>
                <li><a href="#">text3</a></li>
                <li><a href="#">text4</a></li>--%>
            </ul>
        </li>
        <li>
            <a href="#">배송관리</a>
            <ul>
                <li><a href="${path3}/AdminDeliveryList.do?pstate=0">출고처리</a></li>
                <li><a href="${path3}/AdminDeliveryList.do?pstate=1">배송중</a></li>
                <li><a href="${path3}/AdminDeliveryList.do?pstate=2">배송완료</a></li>
                <li><a href="${path3}/AdminDeliveryList.do?pstate=3">구매완료</a></li>
            </ul>
        </li>
    </ul>
</aside>
