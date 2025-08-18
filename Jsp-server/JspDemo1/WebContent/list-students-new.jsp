<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/auth.jsp" %>

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
    <div class="d-flex align-items-center justify-content-between mb-3">
      <h1 class="h3 m-0">Student Directory</h1>
      <a class="btn btn-primary"
         href="${pageContext.request.contextPath}/add-student-form-new.jsp">Add Student</a>
    </div>

    <%-- <c:if test="${not empty GLOBAL_ERROR}">
      <div class="alert alert-danger" role="alert">
        ${GLOBAL_ERROR}
      </div>
    </c:if> --%>

    <div class="card">
      <div class="card-body p-0">
        <div class="table-responsive">
          <table class="table table-striped table-hover m-0">
            <thead class="table-light">
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
          <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Logout</a>
          
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
