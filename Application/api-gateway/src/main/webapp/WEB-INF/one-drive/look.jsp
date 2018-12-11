<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<div id="table_list">
    <table>
        <c:forEach var="file" items="${fileList}">
            <tr>
                <td><c:out value="${file.fileName}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="footer.jsp" %>