<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<html class="js">
<head>
    <%@include file="slices/head.jsp" %>
</head>
<body>

<div class="container">

    <%@include file="slices/nav.jsp" %>

    <div class="col-xs-12">
        <h1>Users</h1>

        <table class="table table-hover table-condensed">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody id="users-list">

            </tbody>
        </table>
    </div>
</div>

<!-- All the scripts -->
<%@include file="slices/scripts.jsp" %>
<script type="text/javascript">

    $(function() {
        $.get('${base}/api/users', function(data) {
            var $table = $('#users-list');
            $table.empty();
            data.forEach(function(row) {
                var $row = $('<tr>' +
                                '<td>' + row.username + '</td>' +
                                '<td>' + row.firstname + '</td>' +
                                '<td>' + row.lastname + '</td>' +
                                '<td>' + row.email + '</td>' +
                            '</tr>');

                $table.append($row);
            });
        })
    });

</script>
<!-- All the scripts Ends-->
</body>
</html>