<%-- 
    Document   : viewCart.jsp
    Created on : Jul 16, 2024, 5:32:49 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mytag" uri="/WEB-INF/tlds/customtag_library.tld" %>
<%@page import="blo.CategoryBLO" %>
<%@page import="model.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic Page Needs
  ================================================== -->
        <meta charset="utf-8">
        <title>Aviato - Cart</title>

        <!-- Mobile Specific Metas
        ================================================== -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Construction Html5 Template">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0">
        <meta name="author" content="Themefisher">
        <meta name="generator" content="Themefisher Constra HTML Template v1.0">

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="images/favicon.png" />

        <!-- Themefisher Icon font -->
        <link rel="stylesheet" href="plugins/themefisher-font/style.css">
        <!-- bootstrap.min css -->
        <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">

        <!-- Animate css -->
        <link rel="stylesheet" href="plugins/animate/animate.css">
        <!-- Slick Carousel -->
        <link rel="stylesheet" href="plugins/slick/slick.css">
        <link rel="stylesheet" href="plugins/slick/slick-theme.css">

        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%@include file="home-header.jspf" %>
        <%@include file="home-navbar.jspf" %>
        <section class="page-header">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="content">
                            <h1 class="page-name">Cart</h1>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <div class="page-wrapper">
            <div class="cart shopping">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8 col-md-offset-2">
                            <div class="block">
                                <div class="product-list">
                                    <form action="cart-manager" method="post">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th class="">Item Name</th>
                                                    <th class="">Item Price</th>
                                                    <th class="">Quantity</th>
                                                    <th class="">Total</th>
                                                    <th class="">Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${not empty Cart}">
                                                    <c:forEach items="${sessionScope.Cart.getCart().values()}"  var="item">
                                                        <tr class="">
                                                            <td class="">
                                                                <div class="product-info">
                                                                    <img width="80" src="./${item.getProduct().getProductImage()}" alt="" />
                                                                    <a href="#!">${item.getProduct().getProductName()}</a>
                                                                </div>
                                                            </td>
                                                            <td class=""><mytag:PriceFormaterHandler price="${item.getProduct().getPrice()}"></mytag:PriceFormaterHandler></td>
                                                            <td class="">${item.getQuantity()}</td>
                                                            <c:set value="${item.getQuantity() * item.getProduct().getPrice()}" var="total"></c:set>
                                                            <td class=""><mytag:PriceFormaterHandler price="${total}"></mytag:PriceFormaterHandler></td>
                                                            <td class="">
                                                                <input class="product-remove" type="submit" name="action" value="remove" >
                                                                <input type="text" name="productId" value="${item.getProduct().getProductId()}" hidden="">
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </tbody>

                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 
        Essential Scripts
        =====================================-->

        <!-- Main jQuery -->
        <script src="plugins/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.1 -->
        <script src="plugins/bootstrap/js/bootstrap.min.js"></script>
        <!-- Bootstrap Touchpin -->
        <script src="plugins/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
        <!-- Instagram Feed Js -->
        <script src="plugins/instafeed/instafeed.min.js"></script>
        <!-- Video Lightbox Plugin -->
        <script src="plugins/ekko-lightbox/dist/ekko-lightbox.min.js"></script>
        <!-- Count Down Js -->
        <script src="plugins/syo-timer/build/jquery.syotimer.min.js"></script>

        <!-- slick Carousel -->
        <script src="plugins/slick/slick.min.js"></script>
        <script src="plugins/slick/slick-animation.min.js"></script>

        <!-- Google Mapl -->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCC72vZw-6tGqFyRhhg5CkF2fqfILn2Tsw"></script>
        <script type="text/javascript" src="plugins/google-map/gmap.js"></script>

        <!-- Main Js File -->
        <script src="js/script.js"></script>



    </body>
</html>
