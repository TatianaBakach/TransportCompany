<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>


<h4 class="header">Models</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesModel}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesModel}" column="name">name</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesModel}" column="brand">brand</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesModel}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesModel}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="model" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${model.id}" /></td>
                <td><c:out value="${model.name}" /></td>
                <td><c:out value="${model.brandName}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${model.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${model.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesModel}/${model.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesModel}/${model.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red disabled" href="${pagesModel}/${model.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesModel}/add"><i class="material-icons">add</i></a>
