<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Attendance & Leave Management</title>
<!-- Bootstrap 5 CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Icons (Bootstrap Icons) -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
<!-- Vanilla Calendar CSS -->
<link href="https://cdn.jsdelivr.net/npm/@uvarov.frontend/vanilla-calendar/build/vanilla-calendar.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/@uvarov.frontend/vanilla-calendar/build/themes/light.min.css" rel="stylesheet">
<style>
body { background: #f3f5f7; }
.main-card { border-radius: 1.5rem; box-shadow: 0 4px 32px rgba(0,0,0,0.08); background: #fff; padding: 2rem; }
.calendar-card, .actions-card { border-radius: 1rem; background: #fcfcfc; padding: 1.5rem; box-shadow: 0 2px 12px rgba(0,0,0,0.05); }
.section-title { font-weight: 700; font-size: 1.65rem; margin-bottom: 1rem; color: #1976D2; }
.calendar-legend { margin: 1rem 0 0.5rem 0; font-size: 0.93rem; gap: 1rem; display: flex; flex-wrap: wrap; }
.legend-item { font-size: 0.93rem; gap: 0.3rem; display: flex; align-items: center; }
.legend-circle { width: 0.85em; height: 0.85em; border-radius: 50%; margin-right: 0.2em; display: inline-block; }
#calendar .vanilla-calendar-day_weekend { background: transparent !important; color: #adb5bd !important; }
#calendar .vanilla-calendar-day_holiday { background: transparent !important; color: #1976D2 !important; font-weight: 700; border-radius: 8px; }
#calendar .vanilla-calendar-day_present { background: transparent !important; color: #388e3c !important; font-weight: 700; border-radius: 8px; }
#calendar .vanilla-calendar-day_onleave { background: transparent !important; color: #FFC107 !important; font-weight: 700; border-radius: 8px; }
#calendar .vanilla-calendar-day_absent { background: transparent !important; color: #F44336 !important; font-weight: 700; border-radius: 8px; }
.legend-present { background: #388e3c; }
.legend-leave { background: #FFC107; }
.legend-holiday { background: #1976D2; }
.legend-absent { background: #F44336; }
.legend-weekend { background: #adb5bd; }
@media (max-width:991.98px) { .main-card { padding: 1rem; } .calendar-card, .actions-card { padding: 1rem; } }
.mini-stat-card { background: #fff; border-radius: 0.8rem; padding: 0.8rem 1rem; text-align: center; border: 1px solid #e9ecef; box-shadow: 0 2px 8px rgba(0,0,0,0.04); }
.mini-stat-card .value { font-size: 1.75rem; font-weight: 700; line-height: 1.2; }
.mini-stat-card .label { font-size: 0.8rem; font-weight: 600; text-transform: uppercase; color: #6c757d; }
.text-success-dark { color: #388e3c; }
.text-danger-dark { color: #F44336; }
.text-primary-dark { color: #1976D2; }
.holiday-date .day { font-size: 1.25rem; font-weight: 700; text-align: center; }
.holiday-date .month { font-size: 0.95rem; color: #1976D2; text-align: center; }
.col-lg-5 {
    display: flex;
    flex-direction: column;
}
</style>
</head>
<body>
<div class="container pt-2 pb-5">
    <div class="main-card">
        <!-- Summary Cards Row (Full Width) -->
        <div class="row g-4 mb-4">
            <div class="col-12">
                <div class="d-flex flex-wrap gap-4 justify-content-between">
                    <div class="mini-stat-card flex-fill mx-1">
                        <div class="value text-success-dark">${presentCount}</div>
                        <div class="label">Present</div>
                    </div>
                    <div class="mini-stat-card flex-fill mx-1">
                        <div class="value text-danger-dark">${absentCount}</div>
                        <div class="label">Absent</div>
                    </div>
                    <div class="mini-stat-card flex-fill mx-1">
                        <div class="value text-primary-dark">${leaveBalance}</div>
                        <div class="label">Balance</div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Main Content Row -->
        <div class="row g-4 align-items-stretch">
            <!-- Calendar Section (Left) -->
            <div class="col-lg-5 mb-4 mb-lg-0">
                <div class="calendar-card h-100 mb-4">
                    <div class="d-flex align-items-center mb-3">
                        <i class="bi bi-calendar3 display-6 me-2 text-primary"></i>
                        <h2 class="section-title mb-0">Your Attendance</h2>
                    </div>
                    <div class="calendar-legend">
                        <span class="legend-item"><span class="legend-circle legend-present"></span>Present</span>
                        <span class="legend-item"><span class="legend-circle legend-leave"></span>On Leave</span>
                        <span class="legend-item"><span class="legend-circle legend-holiday"></span>Holiday</span>
                        <span class="legend-item"><span class="legend-circle legend-absent"></span>Absent</span>
                    </div>
                    <div id="calendar"></div>
                </div>
                <!-- Upcoming Holidays (Below Calendar) -->
                 <div class="calendar-card mt-3">
            <h6 class="text-primary mb-2">
                <i class="bi bi-calendar-event me-1"></i>Upcoming Holidays
            </h6>
            <ul class="list-group list-group-flush">
                <c:forEach var="holiday" items="${upcomingHolidays}">
                    <li class="list-group-item d-flex justify-content-between align-items-center px-1 py-2">
                        <div>
                            <div class="fw-bold"><c:out value="${holiday.title}" /></div>
                            <div class="text-muted small">
                                <c:set var="date" value="${holiday.holidayDate}" />
                                <c:set var="year" value="${fn:substring(date,0,4)}"/>
                                <c:set var="month" value="${fn:substring(date,5,7)}"/>
                                <c:set var="day" value="${fn:substring(date,8,10)}"/>
                                <c:set var="months" value="Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec"/>
                                <c:set var="monthName" value="${fn:split(months, ',')[month-1]}" />
                                ${day} ${monthName} ${year}
                            </div>
                        </div>
                        <div class="holiday-date ms-3">
                            <div class="day">${day}</div>
                            <div class="month">${monthName}</div>
                        </div>
                    </li>
                </c:forEach>
                <c:if test="${empty upcomingHolidays}">
                    <li class="list-group-item text-muted">No upcoming holidays for the rest of the year.</li>
                </c:if>
            </ul>
        </div>
    </div>
            <!-- Actions Section (Right) -->
            <div class="col-lg-7">
                <div class="actions-card h-100 d-flex flex-column">
                    <div>
                        <h2 class="section-title">Attendance & Leave Actions</h2>
                        <hr>
                        <!-- Attendance Button -->
                        <div class="mb-4">
                            <c:choose>
                                <c:when test="${isAttendanceMarkedToday}">
                                    <button type="button" class="btn btn-success btn-lg w-100 mb-2" disabled>
                                        <i class="bi bi-check-all me-2"></i>Attendance Marked Today
                                    </button>
                                </c:when>
                                <c:when test="${not canMarkAttendance}">
                                    <button type="button" class="btn btn-secondary btn-lg w-100 mb-2" disabled>
                                        <i class="bi bi-x-circle me-2"></i>
                                        <c:out value="${disableReason}" />
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <form action="${pageContext.request.contextPath}/manager" method="post">
                                        <input type="hidden" name="action" value="markAttendance">
                                        <button type="submit" class="btn btn-primary btn-lg w-100 mb-2">
                                            <i class="bi bi-check-circle me-2"></i>Mark Today's Attendance
                                        </button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- Leave Application Form -->
                        <h4 class="mb-2">Apply for Leave</h4>
                        <p class="text-muted mb-3">Select your leave period from the calendar and provide a reason below.</p>
                        <form action="${pageContext.request.contextPath}/manager" method="post" class="needs-validation" novalidate>
                            <input type="hidden" name="action" value="applyLeave">
                            <div class="row g-3">
                                <div class="col-sm-6">
                                    <label for="startDate" class="form-label">Start Date</label>
                                    <input type="text" class="form-control" id="startDate" name="startDate" readonly required>
                                    <div class="invalid-feedback">Please select a start date.</div>
                                </div>
                                <div class="col-sm-6">
                                    <label for="endDate" class="form-label">End Date</label>
                                    <input type="text" class="form-control" id="endDate" name="endDate" readonly required>
                                    <div class="invalid-feedback">Please select an end date.</div>
                                </div>
                                <div class="col-12">
                                    <label for="reason" class="form-label">Reason for Leave</label>
                                    <textarea class="form-control" id="reason" name="reason" rows="3" required></textarea>
                                    <div class="invalid-feedback">Please provide a reason for your leave.</div>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-warning mt-4 w-100">
                                <i class="bi bi-send me-2"></i>Submit Leave Application
                            </button>
                        </form>
                    </div>
                   
                    
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS & Vanilla Calendar JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@uvarov.frontend/vanilla-calendar/build/vanilla-calendar.min.js" defer></script>
<script>
document.addEventListener('DOMContentLoaded', () => {
    // Prepare data from backend
    const holidays = [
        <c:forEach var="h" items="${holidays}">
            { date: '${h.holidayDate}', title: '<c:out value="${h.title}" />' },
        </c:forEach>
    ];
    const attendanceRecords = [
        <c:forEach var="a" items="${attendanceRecords}">
            { date: '${a.date}', status: '<c:out value="${a.status}" />' },
        </c:forEach>
    ];
    const missedDates = [
        <c:forEach var="d" items="${missedDates}">
            '${d}',
        </c:forEach>
    ];
    const leaveHistory = [
        <c:forEach var="lr" items="${leaveHistory}">
            { startDate: '${lr.startDate}', endDate: '${lr.endDate}', status: '${lr.status}' },
        </c:forEach>
    ];

    const holidayDateSet = new Set(holidays.map(h => h.date));

    // Prepare calendar popups
    const calendarPopups = {};
    holidays.forEach(h => {
        calendarPopups[h.date] = { modifier: 'vanilla-calendar-day_holiday', html: h.title };
    });
    missedDates.forEach(date => {
        calendarPopups[date] = { modifier: 'vanilla-calendar-day_absent', html: 'Absent' };
    });
    attendanceRecords.forEach(a => {
        if (a.status.toUpperCase() === 'PRESENT') {
            calendarPopups[a.date] = { modifier: 'vanilla-calendar-day_present', html: 'Present' };
        }
    });
    leaveHistory.forEach(lr => {
        if (lr.status.toUpperCase() === 'APPROVED') {
            let currentDate = new Date(lr.startDate);
            let endDate = new Date(lr.endDate);
            while (currentDate <= endDate) {
                let formattedDate = currentDate.toISOString().split('T')[0];
                calendarPopups[formattedDate] = {
                    modifier: 'vanilla-calendar-day_onleave',
                    html: 'On Approved Leave'
                };
                currentDate.setDate(currentDate.getDate() + 1);
            }
        }
    });

    // Initialize calendar
    const calendar = new VanillaCalendar('#calendar', {
        popups: calendarPopups,
        settings: {
            selection: { day: 'multiple-ranged' },
            visibility: { weekend: true, daysOutside: false },
        },
        actions: {
            clickDay(e, dates) {
                const startDateInput = document.getElementById('startDate');
                const endDateInput = document.getElementById('endDate');
                const submitButton = document.querySelector('form[action*="applyLeave"] button[type="submit"]');

                if (dates && dates.length > 0) {
                    dates.sort((a, b) => new Date(a) - new Date(b));
                    const startDate = dates[0];
                    const endDate = dates[dates.length - 1];
                    
                    let isInvalidDaySelected = false;
                    let invalidReason = "";
                    let currentDate = new Date(startDate);
                    
                    while (currentDate <= new Date(endDate)) {
                        const day = currentDate.getUTCDay();
                        const formattedDate = currentDate.toISOString().split('T')[0];

                        if (day === 0 || day === 6) {
                            isInvalidDaySelected = true;
                            invalidReason = "You cannot select weekends for leave.";
                            break;
                        }
                        if (holidayDateSet.has(formattedDate)) {
                            isInvalidDaySelected = true;
                            invalidReason = "You cannot select a public holiday for leave.";
                            break;
                        }
                        currentDate.setUTCDate(currentDate.getUTCDate() + 1);
                    }

                    if (isInvalidDaySelected) {
                        alert(invalidReason);
                        if (submitButton) submitButton.disabled = true;
                        startDateInput.value = '';
                        endDateInput.value = '';
                        calendar.reset();
                    } else {
                        if (submitButton) submitButton.disabled = false;
                        startDateInput.value = startDate;
                        endDateInput.value = endDate;
                    }
                } else {
                    startDateInput.value = '';
                    endDateInput.value = '';
                    if (submitButton) submitButton.disabled = false;
                }
            },
        },
    });
    calendar.init();

    // Bootstrap form validation
    (() => {
        'use strict';
        var forms = document.querySelectorAll('.needs-validation');
        Array.prototype.slice.call(forms).forEach(function(form) {
            form.addEventListener('submit', function(event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
});
</script>
</body>
</html>