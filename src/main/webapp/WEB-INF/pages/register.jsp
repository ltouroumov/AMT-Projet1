<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html class="js">
<head>
    <%@include file="slices/head.jsp" %>
</head>
<body>
<%@include file="slices/nav.jsp" %>

<div class="container">
    <div class="col-xs-12">
        <h1>Register</h1>

        <form class="form-horizontal" method="post">
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">Username</label>
                <div class="col-sm-10">
                    <input type="text" name="username" id="username" class="form-control" />
                </div>
            </div>

            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">E-Mail</label>
                <div class="col-sm-10">
                    <input type="text" name="email" id="email" class="form-control" />
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" name="password" id="password" class="form-control" />
                </div>
            </div>

            <div class="form-group">
                <label for="password-confirm" class="col-sm-2 control-label">Confirm</label>
                <div class="col-sm-10">
                    <input type="password" name="password-confirm" id="password-confirm" class="form-control" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-primary" type="submit">Register</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- All the scripts -->
<%@include file="slices/scripts.jsp" %>
<!-- All the scripts Ends-->
</body>
</html>