<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<div xmlns:jsp="http://java.sun.com/JSP/Page"
     xmlns:c="http://java.sun.com/jsp/jstl/core"
     xmlns:spring="http://www.springframework.org/tags"
     version="2.0">
    <jsp:directive.page contentType="text/html;charset=UTF-8"/>
    <jsp:output omit-xml-declaration="yes"/>
    
    <h1>Provider Listing</h1>
    
    <c:if test="${not empty providers}">
    	<table>
    		<thead>
    			<tr>
    				<th>ID</th>
    				<th>Name</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach items="${providers}" var="provider">
    				<tr>
    					<td>${provider.id}</td>
    					<td>${provider.name}</td>
    				</tr>
    			</c:forEach>
    		</tbody>
    	</table>
    </c:if>
</div>