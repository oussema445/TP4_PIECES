<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modification des Pièces</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="header.jsp" %>
<p></p> <div class="container">
<div class="card">
<div class="card-header">
Modification des Pièces
</div>
<div class="card-body">
<form action="update.do" method="post">
<div hidden class="form-group">
<label class="control-label">ID Pièce :</label>
<input type="text" name="id" class="form-control" value="${piece.idPiece}"/>
</div>
<div class="form-group">
<label class="control-label">Nom Pièce :</label>
<input type="text" name="nom" class="form-control" value="${piece.nomPiece}"/>
</div>
<div class="form-group">
<label class="control-label">Prix :</label>
<input type="text" name="prix" class="form-control" value="${piece.prixPiece}"/>
</div>
<div>
<button type="submit" class="btn btn-primary">Modifier</button>
</div>
</form>
</div>
</div>
</div>
</body>
</html>
