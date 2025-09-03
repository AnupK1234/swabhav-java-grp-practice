package com.aurionpro.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.aurionpro.dao.AttendanceDAO;
import com.aurionpro.model.Attendance;
import com.aurionpro.model.Holiday;
import com.aurionpro.model.LeaveRequest;
import com.aurionpro.model.User;

public class AttendanceService {
    
    private AttendanceDAO attendanceDao;

    public AttendanceService() {
        this.attendanceDao = new AttendanceDAO();
    }

    public List<Attendance> getAttendanceForUser(int userId) {
        return attendanceDao.findAttendanceForUser(userId);
    }
    
    public boolean markUserAttendance(User user) {
        Attendance attendance = new Attendance();
        attendance.setUserId(user.getId());
        attendance.setDate(LocalDate.now());
        attendance.setStatus("PRESENT");
        attendance.setMarkedBy("SELF");
        
        return attendanceDao.markAttendance(attendance);
    }

   
    public boolean isAttendanceMarkedToday(List<Attendance> attendanceRecords) {
        LocalDate today = LocalDate.now();
        return attendanceRecords.stream().anyMatch(record -> record.getDate().equals(today));
    }

    
    public List<String> calculateMissedAttendanceDates(List<Holiday> holidays, List<Attendance> attendanceRecords, List<LeaveRequest> leaveHistory) {
        Set<LocalDate> accountedForDates = new HashSet<>();
        holidays.forEach(h -> accountedForDates.add(h.getHolidayDate()));
        attendanceRecords.forEach(a -> accountedForDates.add(a.getDate()));
        
        leaveHistory.stream()
            .filter(lr -> "APPROVED".equalsIgnoreCase(lr.getStatus()))
            .forEach(lr -> {
                LocalDate start = lr.getStartDate().toLocalDate();
                LocalDate end = lr.getEndDate().toLocalDate();
                start.datesUntil(end.plusDays(1)).forEach(accountedForDates::add);
            });

        List<String> missedDates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(90); 
        
        for (LocalDate date = startDate; date.isBefore(today); date = date.plusDays(1)) {
            DayOfWeek day = date.getDayOfWeek();
            if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY && !accountedForDates.contains(date)) {
                missedDates.add(date.toString());
            }
        }
        return missedDates;
    }
}