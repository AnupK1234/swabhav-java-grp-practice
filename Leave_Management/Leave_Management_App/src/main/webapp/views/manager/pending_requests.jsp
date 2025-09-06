<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Pending Employee Leave Requests</title>
    <!-- Poppins Font Import -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        /* Your existing, correct CSS styles */
        body, .card, .table, .btn, .form-control { font-family: 'Poppins', Arial, sans-serif !important; }
        h3 { font-weight: 700; font-size: 2rem; color: #232323; }
        .modern-card { background: #fff; border-radius: 1.25rem; box-shadow: 0 6px 28px rgba(44,62,80,0.14), 0 1.5px 7px rgba(44,62,80,0.08); padding: 2rem 2.1rem; margin-bottom: 2rem; border: 1px solid #f1f4f6; }
        .table thead th { background: #f8fafb !important; border-bottom: 2px solid #e8ecef; font-weight: 600; color: #495057; text-transform: uppercase; }
        .table-hover tbody tr:hover { background-color: #e8f0fa; }
        .form-control { border-radius: 0.8rem; border: 2px solid #e8ecef; }
        .form-control:focus { border-color: #1c62f6; box-shadow: none; }
    </style>
</head>
<body>
    <h3 class="mb-4">Pending Employee Leave Requests</h3>
    
    <!-- Search Bar (Live Filter) -->
    <div class="modern-card">
        <div class="input-group">
            <span class="input-group-text bg-transparent border-end-0"><i class="bi bi-search"></i></span>
            <input type="text" class="form-control border-start-0" id="searchInput" onkeyup="filterTable()" placeholder="Type to search by employee name...">
        </div>
    </div>

    <div class="modern-card">
        <div class="table-responsive">
            <table class="table table-hover align-middle" id="requestsTable">
                <thead>
                    <tr>
                        <th>Employee Name</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Reason</th>
                        <th class="text-center">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="req" items="${pendingRequests}">
                        <tr>
                            <td><c:out value="${req.employeeFirstName} ${req.employeeLastName}" /></td>
                            <td><c:out value="${req.startDate}" /></td>
                            <td><c:out value="${req.endDate}" /></td>
                            <td><c:out value="${req.reason}" /></td>
                            <td class="text-center">
                                <!-- Approve Form -->
                                <form action="${pageContext.request.contextPath}/manager" method="post" class="d-inline">
                                    <input type="hidden" name="action" value="processLeave">
                                    <input type="hidden" name="leaveId" value="${req.id}">
                                    <button type="submit" name="decision" value="approve" class="btn btn-success btn-sm">Approve</button>
                                </form>
                                
                                <!-- NEW: Button to trigger the rejection modal -->
                                <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#rejectModal" data-leave-id="${req.id}">
                                    Reject
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty pendingRequests}">
                        <tr><td colspan="5" class="text-center text-muted p-4">There are no pending leave requests from your team.</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <!-- NEW: Rejection Reason Modal (for Manager) -->
    <div class="modal fade" id="rejectModal" tabindex="-1" aria-labelledby="rejectModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="rejectModalLabel">Reason for Rejection</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="${pageContext.request.contextPath}/manager" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="action" value="processLeave">
                        <input type="hidden" name="decision" value="reject">
                        <input type="hidden" name="leaveId" id="modalLeaveId">
                        
                        <div class="mb-3">
                            <label for="rejectionReason" class="form-label">Please provide a comment for the employee:</label>
                            <textarea class="form-control" id="rejectionReason" name="rejectionReason" rows="3" required></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-danger">Submit Rejection</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- JavaScript for Live Search and Modal -->
    <script>
        // Live Search Function
        function filterTable() {
            var input = document.getElementById("searchInput");
            var filter = input.value.toLowerCase();
            var table = document.getElementById("requestsTable");
            var tr = table.getElementsByTagName("tr");

            for (var i = 1; i < tr.length; i++) {
                var td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                    var txtValue = td.textContent || td.innerText;
                    if (txtValue.toLowerCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }

        // JavaScript to pass the leave ID to the modal
        document.addEventListener('DOMContentLoaded', function() {
            var rejectModal = document.getElementById('rejectModal');
            if (rejectModal) {
                rejectModal.addEventListener('show.bs.modal', function (event) {
                    var button = event.relatedTarget;
                    var leaveId = button.getAttribute('data-leave-id');
                    var modalLeaveIdInput = rejectModal.querySelector('#modalLeaveId');
                    modalLeaveIdInput.value = leaveId;
                });
            }
        });
    </script>
</body>
</html>