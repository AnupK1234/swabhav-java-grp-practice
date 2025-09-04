<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Your Team Members</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body, .card, .table, .form-control, .badge {
            font-family: 'Poppins', Arial, sans-serif !important;
        }
        h3 {
            font-weight: 700;
            font-size: 2rem;
            color: #232323;
            margin-bottom: 1.8rem;
        }
        .modern-card {
            background: #fff;
            border-radius: 1.25rem;
            box-shadow: 0 6px 28px rgba(44,62,80,0.17), 0 1.5px 7px rgba(44,62,80,0.08);
            padding: 2rem 2.1rem 2.2rem 2.1rem;
            margin-bottom: 2rem;
            border: 1px solid #f1f4f6;
        }
        .search-bar {
            margin-bottom: 1.2rem;
        }
        .form-control {
            height: 2.5rem;
            font-size: 1rem;
            border-radius: 0.7rem;
            border: 1px solid #e3e6ea;
            padding: 0.6rem 1rem;
            box-shadow: none;
            transition: border 0.2s;
        }
        .form-control:focus {
            border-color: #1c62f6;
            outline: none;
        }
        .table {
            margin-bottom: 0;
            background: transparent;
        }
        .table thead th {
            background: #f8fafb !important;
            border-bottom: 2px solid #e8ecef;
            font-weight: 600;
            font-size: 1.04rem;
            color: #495057;
            letter-spacing: 0.01em;
        }
        .table-striped > tbody > tr:nth-of-type(odd) {
            background-color: #f6fafd;
        }
        .table-hover tbody tr:hover {
            background-color: #e8f0fa;
        }
        .badge.bg-info {
            background: linear-gradient(90deg, #1dcaff 0%, #40e0d0 100%);
            color: #fff;
            font-size: 1rem;
            padding: 0.5em 0.9em;
            border-radius: 1em;
            font-weight: 600;
        }
        .text-muted {
            color: #8d99ae !important;
        }
        @media (max-width: 767.98px) {
            .modern-card {
                padding: 1rem 0.6rem;
            }
            .table thead { display: none; }
            .table, .table tbody, .table tr, .table td {
                display: block;
                width: 100%;
            }
            .table tr { margin-bottom: 1rem; }
            .table td {
                text-align: right;
                position: relative;
                padding-left: 50%;
                border: none;
                border-bottom: 1px solid #e8ecef;
            }
            .table td:before {
                content: attr(data-label);
                position: absolute;
                left: 1rem;
                top: 0.6rem;
                width: 48%;
                text-align: left;
                font-weight: 600;
                color: #495057;
                font-size: 1rem;
            }
        }
    </style>
</head>
<body>
    <h3>Your Team Members</h3>
    <div class="modern-card">
        <!-- Search Bar -->
        <div class="search-bar">
            <input type="text" id="employeeSearch" class="form-control" placeholder="Search by first or last name...">
        </div>
        <!-- Team Table -->
        <table class="table table-striped table-hover" id="employeeTable">
            <thead class="table-light">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Leaves Remaining</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="employee" items="${employeeList}">
                    <tr>
                        <td data-label="First Name"><c:out value="${employee.firstName}"/></td>
                        <td data-label="Last Name"><c:out value="${employee.lastName}"/></td>
                        <td data-label="Email"><c:out value="${employee.email}"/></td>
                        <td data-label="Leaves Remaining">
                            <span class="badge bg-info"><c:out value="${employee.leaveBalance}"/></span>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty employeeList}">
                    <tr>
                        <td colspan="4" class="text-center text-muted">You have no employees assigned to you.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
    <!-- Simple JS for search functionality -->
    <script>
      document.getElementById('employeeSearch').addEventListener('keyup', function() {
        var searchValue = this.value.toLowerCase();
        var rows = document.querySelectorAll('#employeeTable tbody tr');
        rows.forEach(function(row) {
          var firstName = row.cells[0]?.innerText?.toLowerCase() || '';
          var lastName = row.cells[1]?.innerText?.toLowerCase() || '';
          // Only filter real rows, not the "no employees" row
          if(row.cells.length === 4) {
            if(firstName.includes(searchValue) || lastName.includes(searchValue)) {
              row.style.display = '';
            } else {
              row.style.display = 'none';
            }
          }
        });
        // Show "no employees" message if all rows are hidden
        var anyVisible = Array.from(rows).some(r => r.style.display !== 'none' && r.cells.length === 4);
        var noEmpRow = document.querySelector('#employeeTable tbody tr td.text-center');
        if(noEmpRow) {
          noEmpRow.parentElement.style.display = anyVisible ? 'none' : '';
        }
      });
    </script>
</body>
</html>