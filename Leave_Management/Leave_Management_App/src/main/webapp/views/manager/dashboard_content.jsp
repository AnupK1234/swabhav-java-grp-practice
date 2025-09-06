<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard Overview</title>
    <!-- Poppins Font & Bootstrap Icons -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <style>
        body, .card, .alert, h1, h2, h3, h4, h5, h6, .btn {
            font-family: 'Poppins', Arial, sans-serif !important;
        }
        h3 {
            font-weight: 700; font-size: 2.2rem; margin-bottom: 2rem; color: #232323;
        }
        .modern-card-row {
            display: flex; flex-wrap: wrap; gap: 1.5rem; margin-bottom: 2rem;
        }
        .modern-stat-card {
            flex: 1 1 200px; min-width: 220px; background: #fff; border-radius: 1.5rem;
            box-shadow: 0 4px 24px rgba(44,62,80,0.1), 0 1.5px 7px rgba(44,62,80,0.06);
            display: flex; align-items: center; padding: 1.2rem 1.6rem;
            position: relative; transition: transform 0.2s, box-shadow 0.2s; border: 1px solid #f2f3f7;
        }
        .modern-stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 12px 36px rgba(44,62,80,0.15), 0 3px 12px rgba(44,62,80,0.08);
        }
        .modern-stat-icon {
            width: 46px; height: 46px; border-radius: 50%; background: #f7fafd;
            display: flex; align-items: center; justify-content: center;
            margin-left: 1.1rem; font-size: 1.65rem; color: #adb5bd;
        }
        .modern-stat-content { flex: 1; }
        .modern-stat-label { font-size: 0.97rem; font-weight: 600; margin-bottom: 0.18rem; }
        .modern-stat-value { font-size: 1.8rem; font-weight: 700; color: #232323; }
        .text-warning { color: #f6b402 !important; }
        .text-primary { color: #1c62f6 !important; }
        .text-success { color: #18794e !important; }

        /* Styles for the new components */
        .action-card, .holiday-card {
            background: #fff; border-radius: 1.5rem;
            box-shadow: 0 4px 24px rgba(44,62,80,0.1), 0 1.5px 7px rgba(44,62,80,0.06);
            padding: 1.5rem; border: 1px solid #f2f3f7; height: 100%;
        }
        .holiday-list { list-style: none; padding: 0; }
        .holiday-item { display: flex; align-items: center; padding: 0.8rem 0; border-bottom: 1px solid #e9ecef; }
        .holiday-item:last-child { border-bottom: none; }
        .holiday-date {
            background: #1976D2; color: #fff; border-radius: 0.6rem; padding: 0.5rem;
            text-align: center; min-width: 60px; margin-right: 1rem;
        }
        .holiday-date .day { font-size: 1.5rem; font-weight: 700; line-height: 1; }
        .holiday-date .month { font-size: 0.8rem; text-transform: uppercase; }
    </style>
</head>
<body>
    <h3>Dashboard Overview</h3>
    
    <!-- Stat Cards Row -->
    <div class="modern-card-row">
        <!-- Stat Card: Pending Requests -->
        <div class="modern-stat-card">
            <div class="modern-stat-content">
                <div class="text-warning modern-stat-label">Pending Requests</div>
                <div class="modern-stat-value">${pendingRequestsCount}</div>
            </div>
            <div class="modern-stat-icon text-warning"><i class="bi bi-card-list"></i></div>
            <a href="${pageContext.request.contextPath}/manager?action=showPendingRequests" class="stretched-link"></a>
        </div>
        <!-- Stat Card: Team Members -->
        <div class="modern-stat-card">
            <div class="modern-stat-content">
                <div class="text-primary modern-stat-label">Team Members</div>
                <div class="modern-stat-value">${teamMembersCount}</div>
            </div>
            <div class="modern-stat-icon text-primary"><i class="bi bi-people"></i></div>
            <a href="${pageContext.request.contextPath}/manager?action=manageEmployees" class="stretched-link"></a>
        </div>
        <!-- Stat Card: My Leave Balance -->
        <div class="modern-stat-card">
            <div class="modern-stat-content">
                <div class="text-success modern-stat-label">My Leave Balance</div>
                <div class="modern-stat-value">${myLeaveBalance} Days</div>
            </div>
            <div class="modern-stat-icon text-success"><i class="bi bi-calendar2-check"></i></div>
            <a href="${pageContext.request.contextPath}/manager?action=showAttendanceLeave" class="stretched-link"></a>
        </div>
    </div>

    <!-- Second Row for Holidays and Actions -->
    <div class="row g-4 mt-2">
       

        <!-- Mark Attendance Card (now smaller and on the right) -->
        <div class="col-lg-4">
            <div class="action-card d-flex flex-column justify-content-center text-center">
                <h5 class="mb-3">Daily Attendance</h5>
                <c:choose>
                    <c:when test="${isAttendanceMarkedToday}">
                        <button type="button" class="btn btn-success btn-lg" disabled>
                            <i class="bi bi-check-all me-2"></i>Attendance Marked
                        </button>
                    </c:when>
                    <c:when test="${not canMarkAttendance}">
                        <button type="button" class="btn btn-secondary btn-lg" disabled>
                            <i class="bi bi-x-circle me-2"></i><c:out value="${disableReason}" />
                        </button>
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.request.contextPath}/manager" method="post" class="mb-0">
                            <input type="hidden" name="action" value="markAttendance">
                            <button type="submit" class="btn btn-primary btn-lg">
                                <i class="bi bi-check-circle me-2"></i>Punch In for Today
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
         <!-- Upcoming Holidays Card (now on the left) -->
       <div class="col-lg-8">
    <div class="holiday-card">
        <h5 class="mb-3"><i class="bi bi-calendar-event me-2"></i>Upcoming Holidays</h5>
        <ul class="holiday-list">
            <%-- *** THIS IS THE FIX: Corrected "upcomingHylidays" to "upcomingHolidays" *** --%>
           <c:forEach var="holiday" items="${upcomingHolidays}" varStatus="loop" end="2">
    <li class="holiday-item">
        <c:set var="dateString" value="${holiday.holidayDate}" />
        <c:set var="day" value="${fn:substring(dateString, 8, 10)}" />
        <c:set var="monthNum" value="${fn:substring(dateString, 5, 7)}" />
        <c:set var="months" value="Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec" />
        <c:set var="monthName" value="${fn:split(months, ',')[monthNum - 1]}" />

        <div class="holiday-date">
            <div class="day">${day}</div>
            <div class="month">${monthName}</div>
        </div>
        <div>
            <div class="fw-bold"><c:out value="${holiday.title}" /></div>
            <div class="text-muted small">
                <% 
                    com.aurionpro.model.Holiday currentHoliday = 
                        (com.aurionpro.model.Holiday) pageContext.getAttribute("holiday"); 
                %>
                <%= java.time.format.DateTimeFormatter.ofPattern("EEEE")
                        .format(currentHoliday.getHolidayDate()) %>
            </div>
        </div>
    </li>
</c:forEach>

            <c:if test="${empty upcomingHolidays}">
                <li class="list-group-item text-muted">No upcoming holidays found.</li>
            </c:if>
        </ul>
    </div>
</div>
    </div>
</body>
</html>