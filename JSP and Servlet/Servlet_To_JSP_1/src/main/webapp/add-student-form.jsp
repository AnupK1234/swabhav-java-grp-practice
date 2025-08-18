<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg rounded-3">
                <div class="card-header bg-success text-white text-center">
                    <h3 class="mb-0">Add Student</h3>
                </div>
                <div class="card-body">
                    <form method="post" action="${pageContext.request.contextPath}/StudentControllerServlet" novalidate>
                        <input type="hidden" name="command" value="ADD"/>

                        <!-- First Name -->
                        <div class="mb-3">
                            <label class="form-label">First Name</label>
                            <input type="text" name="firstName"
                                   class="form-control ${not empty ERRORS.firstName ? 'is-invalid' : ''}"
                                   value="<c:out value='${FORM_DATA.firstName}'/>"/>
                            <c:if test="${not empty ERRORS.firstName}">
                                <div class="invalid-feedback">${ERRORS.firstName}</div>
                            </c:if>
                        </div>

                        <!-- Last Name -->
                        <div class="mb-3">
                            <label class="form-label">Last Name</label>
                            <input type="text" name="lastName"
                                   class="form-control ${not empty ERRORS.lastName ? 'is-invalid' : ''}"
                                   value="<c:out value='${FORM_DATA.lastName}'/>"/>
                            <c:if test="${not empty ERRORS.lastName}">
                                <div class="invalid-feedback">${ERRORS.lastName}</div>
                            </c:if>
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email"
                                   class="form-control ${not empty ERRORS.email ? 'is-invalid' : ''}"
                                   value="<c:out value='${FORM_DATA.email}'/>"/>
                            <c:if test="${not empty ERRORS.email}">
                                <div class="invalid-feedback">${ERRORS.email}</div>
                            </c:if>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-success">Save</button>
                        </div>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <a href="${pageContext.request.contextPath}/StudentControllerServlet?command=LIST"
                       class="btn btn-outline-secondary btn-sm">Back to List</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
