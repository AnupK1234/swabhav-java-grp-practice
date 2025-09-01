<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Your Personal Leave History</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body, .card, .table, .badge {
            font-family: 'Poppins', Arial, sans-serif !important;
        }
        h3 {
            font-weight: 700;
            font-size: 2rem;
            color: #232323;
            margin-bottom: 1.6rem;
        }
        .modern-card {
            background: #fff;
            border-radius: 1.25rem;
            box-shadow: 0 6px 28px rgba(44,62,80,0.14), 0 1.5px 7px rgba(44,62,80,0.08);
            padding: 2rem 2.1rem 2.2rem 2.1rem;
            margin-bottom: 2rem;
            border: 1px solid #f1f4f6;
        }
        .table {
            margin-bottom: 0;
            background: transparent;
        }
        .table thead th {
            background: #f8fafb !important;
            border-bottom: 2px solid #e8ecef;
            font-weight: 600;
            font-size: 1.05rem;
            color: #495057;
            letter-spacing: 0.01em;
            text-transform: uppercase;
        }
        .table-striped > tbody > tr:nth-of-type(odd) {
            background-color: #f6fafd;
        }
        .table-hover tbody tr:hover {
            background-color: #e8f0fa;
            transition: background 0.2s;
        }
        .badge {
            font-size: 1rem;
            padding: 0.5em 0.9em;
            border-radius: 1.2em;
            font-weight: 600;
            letter-spacing: 0.01em;
        }
        .badge.bg-success {
            background: linear-gradient(90deg, #4be160 0%, #42a556 100%);
            color: #fff;
        }
        .badge.bg-danger {
            background: linear-gradient(90deg, #ff5858 0%, #fa7e1e 100%);
            color: #fff;
        }
        .badge.bg-warning {
            background: linear-gradient(90deg, #ffe259 0%, #ffa751 100%);
            color: #232323 !important;
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
    <h3>Your Personal Leave History</h3>
    <div class="modern-card">
        <table class="table table-striped table-hover">
            <thead class="table-light">
                <tr>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Reason</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="leave" items="${leaveHistory}">
                    <tr>
                        <td data-label="Start Date"><c:out value="${leave.startDate}"/></td>
                        <td data-label="End Date"><c:out value="${leave.endDate}"/></td>
                        <td data-label="Reason"><c:out value="${leave.reason}"/></td>
                        <td data-label="Status">
                            <c:choose>
                                <c:when test="${leave.status eq 'APPROVED'}"><span class="badge bg-success">Approved</span></c:when>
                                <c:when test="${leave.status eq 'REJECTED'}"><span class="badge bg-danger">Rejected</span></c:when>
                                <c:otherwise><span class="badge bg-warning text-dark">Pending</span></c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty leaveHistory}">
                    <tr>
                        <td colspan="4" class="text-center text-muted">You have not applied for any leaves yet.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</body>
</html>