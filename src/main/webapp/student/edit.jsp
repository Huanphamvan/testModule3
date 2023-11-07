<%--
  Created by IntelliJ IDEA.
  User: huan
  Date: 11/7/2023
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Home</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

  <link rel="stylesheet" href="../css/edit.css">

</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-12">
      <nav class="navbar navbar-expand-lg bg-color">
        <a class="navbar-brand" href="http://localhost:8080/student?action=home">HOME</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">EDIT STUDENT FORM <span class="sr-only">(current)</span></a>
            </li>
          </ul>
          <!--<form class="form-inline my-2 my-lg-0">-->
          <!--<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">-->
          <!--<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>-->
          <!--</form>-->
        </div>
      </nav>
    </div>
    <div class="col-12 mt-5"  >
      <form action="http://localhost:8080/student?action=edit&id=${id}" method="post">
        <div class="form-row">
          <div class="form-group col-md-6">
            <label for="inputEmail4">Id</label>
            <input type="number" class="form-control" id="inputEmail3" name="id" placeholder="id" value="${id}">

          </div>
          <div class="form-group col-md-6">
            <label for="inputEmail4">Name</label>
            <input type="text" class="form-control" id="inputEmail4" name="name" placeholder="Name" value="${student.name}">

          </div>
          <div class="form-group col-md-6">
            <label for="inputPassword4">Email</label>
            <input type="text" class="form-control" id="inputPassword4" name="email" placeholder="Email" value="${student.email}">

          </div>
        </div>
        <div>
          <div class="form-group">
            <label for="birthday">Birthday</label>
            <input type="text" class="form-control" id="birthday" name="birthday" placeholder="Birthday" value="${student.birthday}">
          </div>
          <div class="form-group">
            <label for="Address">Address</label>
            <input type="text" class="form-control"id="Address" name="address" placeholder="Address" value="${student.address}">

          </div>

          <div class="form-group">
            <label for="phoneNumber">Phone Number</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Phone Number" value="${student.phoneNumber}">

          </div>

          <div class="form-group">
            <label for="classroom">Classroom</label>
            <input type="text" class="form-control" id="classroom" name="classroom" placeholder="Classroom" value="${student.classroom}">
          </div>


        </div>


        <div class="form-row">

          <div class="form-group col-md-4">
            <label for="idClassroom">Classroom</label>
            <select id="idClassroom" class="form-control" name="idClassroom" >
              <c:forEach var="item" items="${classroomList}">
                <option value="${item.id}">${item.name}</option>

              </c:forEach>

            </select>

          </div>

        </div>

        <button type="submit" class="btn btn-primary">EDIT</button>
      </form>

    </div>
  </div>
</div>


</body>
</html>

