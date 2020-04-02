<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header">Engines</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesEngine}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesEngine}" column="title">title</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesEngine}" column="volume">volume</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesEngine}" column="type">type</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesEngine}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesEngine}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="engine" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${engine.id}" /></td>
                <td><c:out value="${engine.title}" /></td>
                <td><c:out value="${engine.volume}" /></td>
                <td><c:out value="${engine.type}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${engine.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${engine.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesEngine}/${engine.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesEngine}/${engine.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red disabled" href="${pagesEngine}/${engine.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesEngine}/add"><i class="material-icons">add</i></a>
