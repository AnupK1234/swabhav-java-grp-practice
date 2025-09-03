<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Attendance & Leave Management</title>
<!-- Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Icons (Bootstrap Icons) -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">
<!-- Vanilla Calendar CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/@uvarov.frontend/vanilla-calendar/build/vanilla-calendar.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/@uvarov.frontend/vanilla-calendar/build/themes/light.min.css"
	rel="stylesheet">
<style>
body {
	background: #f3f5f7;
}

.main-card {
	border-radius: 1.5rem;
	box-shadow: 0 4px 32px rgba(0, 0, 0, 0.08);
	background: #fff;
	padding: 2rem 2rem 2rem 2rem;
}

.calendar-card {
	border-radius: 1rem;
	background: #fcfcfc;
	padding: 1.5rem;
	box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.actions-card {
	border-radius: 1rem;
	background: #fcfcfc;
	padding: 1.5rem;
	box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.section-title {
	font-weight: 700;
	font-size: 1.65rem;
	margin-bottom: 1rem;
	color: #1976D2;
}

.calendar-legend {
	margin: 1rem 0 0.5rem 0;
	font-size: 0.93rem;
	gap: 1rem;
	display: flex;
	flex-wrap: wrap;
}

.legend-item {
	font-size: 0.93rem;
	gap: 0.3rem;
	display: flex;
	align-items: center;
}

.legend-circle {
	width: 0.85em;
	height: 0.85em;
	border-radius: 50%;
	margin-right: 0.2em;
	display: inline-block;
}
/* Calendar Status Colors */
#calendar .vanilla-calendar-day_weekend {
	background: transparent !important;
	color: #adb5bd !important;
	
}

#calendar .vanilla-calendar-day_holiday {
	background: transparent !important;
	color: #1976D2 !important;
	font-weight: 700;
	border-radius: 8px;
}

#calendar .vanilla-calendar-day_present {
	background: transparent !important;
	color: #388e3c !important;
	font-weight: 700;
	border-radius: 8px;
}

#calendar .vanilla-calendar-day_onleave {
	background: transparent !important;
	color: #FFC107 !important;
	font-weight: 700;
	border-radius: 8px;
}

#calendar .vanilla-calendar-day_absent {
	background: transparent !important;
	color: #F44336 !important;
	font-weight: 700;
	border-radius: 8px;
}

.legend-present {
	background: #388e3c;
}

.legend-leave {
	background: #FFC107;
}

.legend-holiday {
	background: #1976D2;
}

.legend-absent {
	background: #F44336;
}

.legend-weekend {
	background: #adb5bd;
}

@media ( max-width : 991.98px) {
	.main-card {
		padding: 1rem;
	}
	.calendar-card, .actions-card {
		padding: 1rem;
	}
}
</style>
</head>
<body>
	<div class="container pt-2 pb-5">
		<div class="main-card">
			<div class="row g-4 align-items-stretch">
				<!-- Calendar Section -->
				<div class="col-lg-5 mb-3 mb-lg-0">
					<div class="calendar-card h-100">
						<div class="d-flex align-items-center mb-3">
							<i class="bi bi-calendar3 display-6 me-2 text-primary"></i>
							<h2 class="section-title mb-0">Your Attendance</h2>
						</div>
						<div class="calendar-legend">
							<span class="legend-item"><span
								class="legend-circle legend-present"></span>Present</span> <span
								class="legend-item"><span
								class="legend-circle legend-leave"></span>On Leave</span> <span
								class="legend-item"><span
								class="legend-circle legend-holiday"></span>Holiday</span> <span
								class="legend-item"><span
								class="legend-circle legend-absent"></span>Absent</span> 
						</div>
						<div id="calendar"></div>
					</div>
				</div>
				<!-- Actions Section -->
				<div class="col-lg-7">
					<div class="actions-card h-100 d-flex flex-column">
						<div>
							<h2 class="section-title">Attendance & Leave Actions</h2>
							<hr>
							<!-- Attendance Button -->
							<div class="mb-4">
								<c:choose>
									<c:when test="${isAttendanceMarkedToday}">
										<button type="button"
											class="btn btn-success btn-lg w-100 mb-2" disabled>
											<i class="bi bi-check-all me-2"></i>Attendance Marked Today
										</button>
									</c:when>
									<c:otherwise>
										<form action="${pageContext.request.contextPath}/manager"
											method="post">
											<input type="hidden" name="action" value="markAttendance">
											<button type="submit"
												class="btn btn-primary btn-lg w-100 mb-2">
												<i class="bi bi-check-circle me-2"></i>Mark Today's
												Attendance
											</button>
										</form>
									</c:otherwise>
								</c:choose>
							</div>
							<!-- Leave Application Form -->
							<h4 class="mb-2">Apply for Leave</h4>
							<p class="text-muted mb-3">Select your leave period from the
								calendar and provide a reason below.</p>
							<form action="${pageContext.request.contextPath}/manager"
								method="post" class="needs-validation" novalidate>
								<input type="hidden" name="action" value="applyLeave">
								<div class="row g-3">
									<div class="col-sm-6">
										<label for="startDate" class="form-label">Start Date</label> <input
											type="text" class="form-control" id="startDate"
											name="startDate" readonly required>
										<div class="invalid-feedback">Please select a start
											date.</div>
									</div>
									<div class="col-sm-6">
										<label for="endDate" class="form-label">End Date</label> <input
											type="text" class="form-control" id="endDate" name="endDate"
											readonly required>
										<div class="invalid-feedback">Please select an end date.</div>
									</div>
									<div class="col-12">
										<label for="reason" class="form-label">Reason for
											Leave</label>
										<textarea class="form-control" id="reason" name="reason"
											rows="3" required></textarea>
										<div class="invalid-feedback">Please provide a reason
											for your leave.</div>
									</div>
								</div>
								<button type="submit" class="btn btn-warning mt-4 w-100">
									<i class="bi bi-send me-2"></i>Submit Leave Application
								</button>
							</form>
						</div>
						<!-- Past Leave Summary (Optional) -->
						<div class="mt-5">
							<h6 class="text-primary mb-2">
								<i class="bi bi-clock-history me-1"></i>Past Leave Summary
							</h6>
							<ul class="list-group">
								<c:forEach var="lr" items="${leaveHistory}">
									<li
										class="list-group-item d-flex justify-content-between align-items-center px-2 py-2">
										<span> <i class="bi bi-calendar-date me-1"></i>
											${lr.startDate} to ${lr.endDate}
									</span> <span
										class="badge bg-${lr.status == 'APPROVED' ? 'success' : lr.status == 'PENDING' ? 'warning' : 'danger'} text-dark fw-bold">
											${lr.status} </span>
									</li>
								</c:forEach>
								<c:if test="${empty leaveHistory}">
									<li class="list-group-item text-muted">No leave records
										found.</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS (for responsive and validation) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Vanilla Calendar JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/@uvarov.frontend/vanilla-calendar/build/vanilla-calendar.min.js"
		defer></script>
	<script>
document.addEventListener('DOMContentLoaded', () => {
    // 1. Prepare data from backend
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

    // 2. Prepare calendar popups
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

    // 3. Initialize calendar
    const calendar = new VanillaCalendar('#calendar', {
        popups: calendarPopups,
        settings: {
            selection: { day: 'multiple-ranged' },
            visibility: { weekend: true, daysOutside: false },
        },
        actions: {
            clickDay(e, dates) {
                if (dates && dates.length > 0) {
                    dates.sort((a, b) => new Date(a) - new Date(b));
                    document.getElementById('startDate').value = dates[0];
                    document.getElementById('endDate').value = dates[dates.length - 1];
                } else {
                    document.getElementById('startDate').value = '';
                    document.getElementById('endDate').value = '';
                }
            },
        },
    });
    calendar.init();

    // 4. Bootstrap form validation
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