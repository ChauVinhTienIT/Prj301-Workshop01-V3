<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mytag" uri="/WEB-INF/tlds/customtag_library.tld" %>
<%@page import="blo.CategoryBLO" %>
<%@page import="model.Product" %>
<!DOCTYPE html>

<html lang="en">
    <head>

        <!-- Basic Page Needs
        ================================================== -->
        <meta charset="utf-8">
        <title>Aviato - Shop</title>

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

    <body id="body">
        <%@include file="home-header.jspf" %>
        <%@include file="home-navbar.jspf" %>

        <section class="single-product">
            <div class="container">
                <div class="row mt-20">
                    <div class="col-md-5">
                        <div class="single-product-slider">
                            <div id='carousel-custom' class='carousel slide' data-ride='carousel'>
                                <div class='carousel-outer'>
                                    <!-- me art lab slider -->
                                    <div class='carousel-inner '>
                                        <div class='item active'>
                                            <img src='.${product.getProductImage()}' alt='' data-zoom-image="images/shop/single-products/product-1.jpg" />
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7">
                        <div class="single-product-details">
                            <h2>${product.getProductName()}</h2>
                            <c:if test="${product.getDiscount() > 0}">
                                <p class="product-price"><mytag:PriceFormaterHandler price="${product.getPriceAfterDiscount()}"></mytag:PriceFormaterHandler></p>
                            </c:if>
                            <c:if test="${product.getDiscount() == 0}">
                                <p class="product-price"><mytag:PriceFormaterHandler price="${product.getPrice()}"></mytag:PriceFormaterHandler></p>
                            </c:if>

                            <p class="product-description mt-20">${product.getBrief()}</p>
                            <form action="cart-manager?action=add" method="post">
                                <div class="product-quantity">
                                <span>Quantity:</span>
                                <div class="product-quantity-slider">
                                    <input id="quantity" type="number" value="1" name="quantity" min="1">
                                    <input type="text" name="productId" value="${product.getProductId()}" hidden="">
                                </div>

                                </div>
                                <input type="submit" value="Add To Cart" class="btn btn-main mt-20">
                            </form>
                        </div>
                    </div>
                </div>

        </section>
                                
        <section class="products related-products section">
            <div class="container">
                <div class="row">
                    <!-- Product -->
                    <c:if test="${relateProductList != null}">
                        <c:forEach items="${relateProductList}" var="product">
                            <div class="col-md-4">
                                <div class="product-item">
                                    <div class="product-thumb">
                                        <c:if test="${product.getDiscount() > 0}">
                                            <span class="bage">Sale</span>
                                        </c:if>

                                        <img class="img-responsive" src=".${product.getProductImage()}" alt="product-img" />
                                        <div class="preview-meta">
                                            <ul>
                                                <li>
                                                    <span  data-toggle="modal" data-target="#product-modal">
                                                        <i class="tf-ion-ios-search-strong"></i>
                                                    </span>
                                                </li>
                                                <li>
                                                    <a href="#!" ><i class="tf-ion-ios-heart"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#!"><i class="tf-ion-android-cart"></i></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-content">
                                        <h4><a href="home?action=detail&productId=${product.getProductId()}">${product.getProductName()}</a></h4>
                                            <c:if test="${product.getDiscount() > 0}">
                                            <span class="text-muted text-decoration-line-through">${product.getPrice()} vnd</span>
                                            <p class="price">${product.getPriceAfterDiscount()} vnd</p>
                                        </c:if>
                                        <c:if test="${product.getDiscount() == 0}">
                                            ${product.getPrice()} vnd
                                        </c:if>

                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </section>

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