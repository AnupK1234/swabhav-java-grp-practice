<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Personal Leave History</title>
    <!-- Poppins Font Import -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <!-- Bootstrap Icons (for the info icon) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body, .card, .table, .badge, .modal-title, .modal-body, .btn {
            font-family: 'Poppins', Arial, sans-serif !important;
        }
        h3 {
            font-weight: 700;
            font-size: 2.2rem;
            color: #232323;
            margin-bottom: 1.6rem;
        }
        .modern-card {
            background: #fff;
            border-radius: 1.5rem;
            box-shadow: 0 6px 28px rgba(44,62,80,0.1), 0 1.5px 7px rgba(44,62,80,0.06);
            padding: 2rem;
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
            font-size: 1rem;
            color: #495057;
            letter-spacing: 0.02em;
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
            font-size: 0.95rem;
            padding: 0.5em 1em;
            border-radius: 50rem; /* pill shape */
            font-weight: 600;
            letter-spacing: 0.01em;
        }
        .badge.bg-success {
            background-color: #d4edda !important;
            color: #155724 !important;
        }
        .badge.bg-danger {
            background-color: #f8d7da !important;
            color: #721c24 !important;
            cursor: pointer; /* Indicates it's clickable */
            transition: background-color 0.2s;
        }
        .badge.bg-danger:hover {
            background-color: #f1b0b7 !important;
        }
        .badge.bg-warning {
            background-color: #fff3cd !important;
            color: #856404 !important;
        }
        .text-muted {
            color: #8d99ae !important;
        }

        /* Responsive Table Styles */
        @media (max-width: 767.98px) {
            .modern-card { padding: 1rem 0.6rem; }
            .table thead { display: none; }
            .table, .table tbody, .table tr, .table td { display: block; width: 100%; }
            .table tr { margin-bottom: 1rem; border: 1px solid #e8ecef; border-radius: 0.8rem; padding: 0.5rem; }
            .table td { text-align: right; position: relative; padding-left: 50%; border: none; padding-bottom: 0.8rem; padding-top: 0.8rem; }
            .table td:before {
                content: attr(data-label);
                position: absolute;
                left: 1rem;
                width: 48%;
                text-align: left;
                font-weight: 600;
                color: #495057;
            }
            .text-center { text-align: right !important; }
        }
    </style>
</head>
<body>
    <h3>Your Personal Leave History</h3>
    <div class="modern-card">
        <div class="table-responsive">
            <table class="table table-striped table-hover align-middle">
                <thead>
                    <tr>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Reason</th>
                        <th class="text-center">Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="leave" items="${leaveHistory}">
                        <tr>
                            <td data-label="Start Date"><c:out value="${leave.startDate}"/></td>
                            <td data-label="End Date"><c:out value="${leave.endDate}"/></td>
                            <td data-label="Reason"><c:out value="${leave.reason}"/></td>
                            <td data-label="Status" class="text-center">
                                <c:choose>
                                    <c:when test="${leave.status eq 'APPROVED'}">
                                        <span class="badge bg-success">Approved</span>
                                    </c:when>
                                    <c:when test="${leave.status eq 'REJECTED'}">
                                        <span class="badge bg-danger" 
                                              data-bs-toggle="modal" 
                                              data-bs-target="#reasonModal" 
                                              data-rejection-reason="<c:out value='${leave.rejectionReason}'/>">
                                            Rejected <i class="bi bi-info-circle ms-1"></i>
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-warning">Pending</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty leaveHistory}">
                        <tr>
                            <td colspan="4" class="text-center text-muted p-4">You have no leave records to display.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Rejection Reason Modal -->
    <div class="modal fade" id="reasonModal" tabindex="-1" aria-labelledby="reasonModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="reasonModalLabel">
                        <i class="bi bi-chat-right-text-fill me-2 text-danger"></i>Rejection Comment
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p id="modalRejectionReasonText" class="fst-italic text-center fs-5 p-3"></p>
                </div>
            </div>
        </div>
    </div>

    <!-- JavaScript to pass the comment to the modal -->
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var reasonModal = document.getElementById('reasonModal');
            if (reasonModal) {
                reasonModal.addEventListener('show.bs.modal', function (event) {
                    var badge = event.relatedTarget;
                    var reason = badge.getAttribute('data-rejection-reason');
                    var modalBody = reasonModal.querySelector('#modalRejectionReasonText');
                    
                    if (reason && reason.trim() !== '') {
                        modalBody.textContent = '"' + reason + '"';
                    } else {
                        modalBody.textContent = 'No comment was provided by the approver.';
                    }
                });
            }
        });
    </script>
</body>
</html>