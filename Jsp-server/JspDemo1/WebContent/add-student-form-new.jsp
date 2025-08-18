<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/auth.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Add Student</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container py-4">
    <div class="d-flex align-items-center justify-content-between mb-3">
      <h1 class="h3 m-0">Add New Student</h1>
      <a class="btn btn-outline-secondary"
         href="${pageContext.request.contextPath}/StudentControllerServlet?command=LIST">Back to List</a>
    </div>

    <c:if test="${not empty GLOBAL_ERROR}">
      <div class="alert alert-danger" role="alert">
        ${GLOBAL_ERROR}
      </div>
    </c:if>

    <div class="card">
      <div class="card-body">
        <form method="post" action="${pageContext.request.contextPath}/StudentControllerServlet" novalidate>
          <input type="hidden" name="command" value="ADD"/>

          <div class="mb-3">
            <label for="firstName" class="form-label">First Name</label>
            <input id="firstName" name="firstName"
                   class="form-control ${not empty ERRORS.firstName ? 'is-invalid' : ''}"
                   value="<c:out value='${FORM_DATA.firstName}'/>"/>
            <c:if test="${not empty ERRORS.firstName}">
              <div class="invalid-feedback">${ERRORS.firstName}</div>
            </c:if>
          </div>

          <div class="mb-3">
            <label for="lastName" class="form-label">Last Name</label>
            <input id="lastName" name="lastName"
                   class="form-control ${not empty ERRORS.lastName ? 'is-invalid' : ''}"
                   value="<c:out value='${FORM_DATA.lastName}'/>"/>
            <c:if test="${not empty ERRORS.lastName}">
              <div class="invalid-feedback">${ERRORS.lastName}</div>
            </c:if>
          </div>

          <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input id="email" name="email"
                   class="form-control ${not empty ERRORS.email ? 'is-invalid' : ''}"
                   value="<c:out value='${FORM_DATA.email}'/>"/>
            <c:if test="${not empty ERRORS.email}">
              <div class="invalid-feedback">${ERRORS.email}</div>
            </c:if>
          </div>

          <div class="d-flex gap-2">
            <button class="btn btn-primary" type="submit">Add Student</button>
            <a class="btn btn-outline-secondary"
               href="${pageContext.request.contextPath}/StudentControllerServlet?command=LIST">Cancel</a>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script>
    // focus first invalid field (or firstName)
    (function () {
      const errs = {
        firstName: ${empty ERRORS.firstName ? 'false' : 'true'},
        lastName:  ${empty ERRORS.lastName  ? 'false' : 'true'},
        email:     ${empty ERRORS.email     ? 'false' : 'true'}
      };
      if (errs.firstName)      document.getElementById('firstName').focus();
      else if (errs.lastName)  document.getElementById('lastName').focus();
      else if (errs.email)     document.getElementById('email').focus();
      else                     document.getElementById('firstName').focus();
    })();
  </script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
