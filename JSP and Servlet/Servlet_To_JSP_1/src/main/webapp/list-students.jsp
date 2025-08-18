<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Students</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container py-4">
    
    <!-- Header Section -->
    <div class="row align-items-center mb-4">
      <div class="col-12 text-center">
        <h1 class="h3 fw-bold m-0">Student Directory</h1>
      </div>
      <div class="col-12 d-flex justify-content-center gap-2 mt-3">
        <a class="btn btn-primary"
           href="${pageContext.request.contextPath}/add-student-form.jsp">➕ Add Student</a>
        <a class="btn btn-danger"
           href="${pageContext.request.contextPath}/LogoutServlet">🚪 Logout</a>
      </div>
    </div>

    <!-- Student Table -->
    <div class="card shadow-sm">
      <div class="card-body p-0">
        <div class="table-responsive">
          <table class="table table-striped table-hover m-0">
            <thead class="table-dark">
              <tr>
                <th style="width:22%">First Name</th>
                <th style="width:22%">Last Name</th>
                <th style="width:36%">Email</th>
                <th style="width:20%">Actions</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="s" items="${STUDENT_LIST}">
                <tr>
                  <td><c:out value="${s.firstName}"/></td>
                  <td><c:out value="${s.lastName}"/></td>
                  <td><c:out value="${s.email}"/></td>
                  <td>
                    <a class="btn btn-outline-secondary btn-sm me-1"
                       href="${pageContext.request.contextPath}/StudentControllerServlet?command=LOAD&studentId=${s.id}">
                      Edit
                    </a>
                    <form class="d-inline" method="post"
                          action="${pageContext.request.contextPath}/StudentControllerServlet"
                          onsubmit="return confirm('Delete this student?');">
                      <input type="hidden" name="command" value="DELETE"/>
                      <input type="hidden" name="studentId" value="${s.id}"/>
                      <button class="btn btn-outline-danger btn-sm" type="submit">Delete</button>
                    </form>
                  </td>
                </tr>
              </c:forEach>

              <c:if test="${empty STUDENT_LIST}">
                <tr>
                  <td colspan="4" class="text-center text-muted py-4">
                    No students yet.
                  </td>
                </tr>
              </c:if>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
