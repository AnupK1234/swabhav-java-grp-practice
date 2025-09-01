<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="/login.jsp"/>
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Your Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body, .card, .form-control, .btn, label, h3, .text-muted {
            font-family: 'Poppins', Arial, sans-serif !important;
        }
        h3 {
            font-weight: 700;
            font-size: 2rem;
            color: #232323;
            margin-bottom: 1.5rem;
        }
        .modern-card {
            background: #fff;
            border-radius: 1.5rem;
            box-shadow: 0 6px 28px rgba(44,62,80,0.18), 0 1.5px 7px rgba(44,62,80,0.09);
            padding: 2.2rem 2.3rem;
            margin-bottom: 2rem;
            border: 1px solid #f1f4f6;
            max-width: 520px;
        }
        .text-muted {
            color: #7b8994 !important;
            font-size: 1.08rem;
            margin-bottom: 2.2rem;
        }
        .modern-form {
            display: flex;
            flex-direction: column;
            gap: 1.3rem;
        }
        .form-label {
            font-weight: 600;
            font-size: 1rem;
            color: #232323;
            margin-bottom: 0.4rem;
        }
        .form-control {
            border-radius: 0.8rem;
            border: 1px solid #e3e6ea;
            background: #f7fbfc;
            font-size: 1.08rem;
            padding: 0.7rem 1.1rem;
            transition: border 0.2s;
            box-shadow: none;
        }
        .form-control:focus {
            border-color: #1c62f6;
            background: #fff;
        }
        .form-control[readonly] {
            background-color: #f0f2f5;
            cursor: not-allowed;
        }
        .btn-primary {
            background: linear-gradient(90deg, #1c62f6 0%, #40e0d0 100%);
            border: none;
            border-radius: 0.8rem;
            font-weight: 600;
            font-size: 1.1rem;
            padding: 0.7rem 1.9rem;
            transition: all 0.2s;
            box-shadow: 0 3px 18px rgba(44,62,80,0.09);
        }
        .btn-primary:hover {
            box-shadow: 0 8px 32px rgba(44,62,80,0.13);
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
    <div class="modern-card">
    <h3>Edit Your Profile</h3>
        <p class="text-muted">Update your personal information below. Your username cannot be changed.</p>
        
        <form action="${pageContext.request.contextPath}/employee" method="post" class="modern-form needs-validation" novalidate>
            <input type="hidden" name="action" value="updateProfile">
            <input type="hidden" name="id" value="<c:out value='${sessionScope.user.id}'/>">

            <div>
                <label for="firstName" class="form-label">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" 
                       value="<c:out value='${sessionScope.user.firstName}'/>" required>
                <div class="invalid-feedback">
                    Please provide your first name.
                </div>
            </div>
            
            <div>
                <label for="lastName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" 
                       value="<c:out value='${sessionScope.user.lastName}'/>" required>
                <div class="invalid-feedback">
                    Please provide your last name.
                </div>
            </div>
            
            <div>
                <label for="email" class="form-label">Email Address</label>
                <input type="email" class="form-control" id="email" name="email" 
                       value="<c:out value='${sessionScope.user.email}'/>" required>
                <div class="invalid-feedback">
                    Please provide a valid email address.
                </div>
            </div>
            
            <div>
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" 
                       value="<c:out value='${sessionScope.user.username}'/>" readonly>
            </div>
            
            <button type="submit" class="btn btn-primary mt-2">
                <i class="bi bi-save me-2"></i>Save Changes
            </button>
        </form>
    </div>

    <script>
        // Standard Bootstrap 5 validation script
        (function () {
          'use strict'
          var forms = document.querySelectorAll('.needs-validation');
          Array.prototype.slice.call(forms)
            .forEach(function (form) {
              form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                  event.preventDefault();
                  event.stopPropagation();
                }
                form.classList.add('was-validated');
              }, false);
            });
        })();
    </script>
</body>
</html>