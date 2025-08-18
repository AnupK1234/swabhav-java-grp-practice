<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/auth.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Update Student</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container py-4">
    <div class="d-flex align-items-center justify-content-between mb-3">
      <h1 class="h3 m-0">Update Student</h1>
      <a class="btn btn-secondary" href="${pageContext.request.contextPath}/StudentControllerServlet?command=LIST">
        Back to List
      </a>
    </div>

    <div class="card">
      <div class="card-body">
        <form action="${pageContext.request.contextPath}/StudentControllerServlet" method="post">
          <input type="hidden" name="command" value="UPDATE"/>
          <input type="hidden" name="studentId" value="${THE_STUDENT.id}"/>

          <div class="mb-3">
            <label class="form-label">First Name</label>
            <input type="text" class="form-control" name="firstName" 
                   value="${THE_STUDENT.firstName}" required/>
          </div>

          <div class="mb-3">
            <label class="form-label">Last Name</label>
            <input type="text" class="form-control" name="lastName" 
                   value="${THE_STUDENT.lastName}" required/>
          </div>

          <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" class="form-control" name="email" 
                   value="${THE_STUDENT.email}" required/>
          </div>

          <div class="d-flex justify-content-end">
            <button type="submit" class="btn btn-primary">Update</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
