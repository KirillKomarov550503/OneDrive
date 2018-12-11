<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.jsp" %>
<div id="table_list">
    <table>
        <c:forEach var="file" items="${fileList}">
            <tr>
                <td>
                    <form action="/one-drive/users/files/download" method="get">
                        <c:out value="${file.fileName}"/>
                        <input type="hidden" name="id" value="${file.id}">
                        <input type="submit" value="Скачать">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="footer.jsp" %>