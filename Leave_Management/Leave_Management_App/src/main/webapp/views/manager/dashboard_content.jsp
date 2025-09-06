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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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

        /* Uniform Card Styling for both Daily Attendance and Holidays */
        .uniform-card {
            background: #fff; border-radius: 1.2rem;
            box-shadow: 0 4px 20px rgba(44,62,80,0.08), 0 1px 5px rgba(44,62,80,0.05);
            padding: 1.5rem; border: 1px solid #f2f3f7;
            transition: transform 0.2s, box-shadow 0.2s;
            margin-bottom: 1.5rem; min-height: 180px;
        }
        .uniform-card:hover {
            transform: translateY(-3px);
            box-shadow: 0 8px 28px rgba(44,62,80,0.12), 0 2px 8px rgba(44,62,80,0.08);
        }
        .uniform-card h5 {
            font-size: 1.1rem; font-weight: 600; margin-bottom: 1.2rem; color: #232323;
        }

        /* Daily Attendance Specific Styling */
        .attendance-card {
            text-align: center;
            display: flex; flex-direction: column; justify-content: center;
        }
        .attendance-card .btn {
            border-radius: 0.8rem; padding: 0.7rem 1.4rem; font-weight: 500;
            border: none; transition: all 0.2s; margin-top: 0.5rem;
        }

        /* Holiday Card Specific Styling */
        .holiday-list { 
            list-style: none; padding: 0; margin: 0;
        }
        .holiday-item { 
            display: flex; align-items: center; padding: 0.8rem 0; 
            border-bottom: 1px solid #f0f0f0;
        }
        .holiday-item:last-child { border-bottom: none; }
        .holiday-date {
            background: linear-gradient(135deg, #1976D2, #1565C0); 
            color: #fff; border-radius: 0.8rem; padding: 0.6rem;
            text-align: center; min-width: 65px; margin-right: 1rem;
            box-shadow: 0 2px 8px rgba(25,118,210,0.3);
        }
        .holiday-date .day { 
            font-size: 1.4rem; font-weight: 700; line-height: 1; 
        }
        .holiday-date .month { 
            font-size: 0.75rem; text-transform: uppercase; opacity: 0.9;
        }
        .holiday-info .holiday-name {
            font-weight: 600; color: #232323; margin-bottom: 0.2rem;
        }
        .holiday-info .holiday-day {
            color: #6c757d; font-size: 0.85rem;
        }
        .requests-card {
            background: #fff; border-radius: 1.2rem;
            box-shadow: 0 4px 20px rgba(44,62,80,0.08), 0 1px 5px rgba(44,62,80,0.05);
            padding: 1.5rem; border: 1px solid #f2f3f7;
            transition: transform 0.2s, box-shadow 0.2s;
            height: fit-content;
        }
        .requests-card:hover {
            transform: translateY(-3px);
            box-shadow: 0 8px 28px rgba(44,62,80,0.12), 0 2px 8px rgba(44,62,80,0.08);
        }
        .requests-card h5 {
            font-size: 1.1rem; font-weight: 600; margin-bottom: 1rem; color: #232323;
        }
        .requests-card hr {
            margin: 0.5rem 0 1rem 0; border-color: #e9ecef;
        }
        .request-list {
            list-style: none; padding: 0; margin: 0;
        }
        .request-item {
            display: flex; align-items: center; padding: 1rem 0;
            border-bottom: 1px solid #f0f0f0;
        }
        .request-item:last-child { border-bottom: none; }
        .request-avatar {
            width: 40px; height: 40px; border-radius: 50%;
            background: linear-gradient(135deg, #1976D2, #1565C0);
            color: #fff; display: flex; align-items: center; justify-content: center;
            font-weight: 600; font-size: 0.9rem; margin-right: 1rem;
            box-shadow: 0 2px 8px rgba(25,118,210,0.3);
        }
        .request-info {
            flex: 1;
        }
        .employee-name {
            font-weight: 600; color: #232323; margin-bottom: 0.2rem;
            font-size: 0.95rem;
        }
        .leave-dates {
            color: #6c757d; font-size: 0.8rem;
        }
        .request-item .btn {
            border-radius: 0.6rem; padding: 0.4rem 0.8rem;
            font-size: 0.8rem; font-weight: 500;
        }
        

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .modern-card-row {
                flex-direction: column;
            }
        }
    </style>
</head>
<body class="bg-light">
    <div class="container-fluid px-4 py-3">
        <h3>Dashboard Overview</h3>
        
        <!-- Top 3 Stat Cards Row (unchanged) -->
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

        <!-- Vertical Stack: Daily Attendance + Upcoming Holidays -->
        <div class="row">
            <!-- Left Column: Daily Attendance and Holidays Stacked -->
            <div class="col-lg-4 col-md-6">
                <!-- Daily Attendance Card -->
                <div class="uniform-card attendance-card">
                    <h5><i class="bi bi-clock me-2"></i>Daily Attendance</h5>
                    <c:choose>
                        <c:when test="${isAttendanceMarkedToday}">
                            <button type="button" class="btn btn-success" disabled>
                                <i class="bi bi-check-all me-2"></i>Attendance Marked
                            </button>
                        </c:when>
                        <c:when test="${not canMarkAttendance}">
                            <button type="button" class="btn btn-secondary" disabled>
                                <i class="bi bi-x-circle me-2"></i><c:out value="${disableReason}" />
                            </button>
                        </c:when>
                        <c:otherwise>
                            <form action="${pageContext.request.contextPath}/manager" method="post" class="mb-0">
                                <input type="hidden" name="action" value="markAttendance">
                                <button type="submit" class="btn btn-primary">
                                    <i class="bi bi-check-circle me-2"></i>Punch In Today
                                </button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>

                <!-- Upcoming Holidays Card -->
                <div class="uniform-card">
                    <h5><i class="bi bi-calendar-event me-2"></i>Upcoming Holidays</h5>
                    <ul class="holiday-list">
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
                                <div class="holiday-info">
                                    <div class="holiday-name"><c:out value="${holiday.title}" /></div>
                                    <div class="holiday-day">
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
                            <li class="holiday-item">
                                <div class="text-muted text-center w-100">
                                    <i class="bi bi-calendar-x me-2"></i>No upcoming holidays found
                                </div>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>

            <!-- Right Column: Blank space for future content -->
            <div class="col-lg-8 col-md-6">
                <!-- This space is intentionally left blank as requested -->
                <div class="requests-card">
                    <h5><i class="bi bi-hourglass-split me-2"></i>Recent Pending Requests</h5>
                    <hr>
                    <ul class="request-list">
                        <c:forEach var="req" items="${recentPendingRequests}">
                            <li class="request-item">
                                <div class="request-avatar">
                                    ${fn:substring(req.employeeFirstName, 0, 1)}${fn:substring(req.employeeLastName, 0, 1)}
                                </div>
                                <div class="request-info">
                                    <div class="employee-name">${req.employeeFirstName} ${req.employeeLastName}</div>
                                    <div class="leave-dates">${req.startDate} to ${req.endDate}</div>
                                </div>
                                <a href="${pageContext.request.contextPath}/manager?action=showPendingRequests" class="btn btn-outline-primary btn-sm ms-auto">
                                    View
                                </a>
                            </li>
                        </c:forEach>
                        <c:if test="${empty recentPendingRequests}">
                            <li class="request-item text-muted justify-content-center">
                                <i class="bi bi-check2-circle me-2"></i>No pending requests right now.
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>