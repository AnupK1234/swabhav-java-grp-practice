package com.aurionpro.service;

import com.aurionpro.dao.HolidayDAO;
import com.aurionpro.model.Holiday;
import java.time.LocalDate;
import java.util.List;

public class HolidayService {
	private final HolidayDAO dao = new HolidayDAO();

	public List<Holiday> getAllHolidays() {
		return dao.findAll();
	}

	public void createHoliday(LocalDate date, String title) {
		dao.create(date, title);
	}

	public void deleteHoliday(int id) {
		dao.delete(id);
	}
	public List<Holiday> getUpcomingHolidays() {
        List<Holiday> allHolidays = dao.findAll();
        LocalDate today = LocalDate.now();
        
        return allHolidays.stream()
                .filter(holiday -> !holiday.getHolidayDate().isBefore(today)) // Keep holidays that are today or in the future
                .collect(java.util.stream.Collectors.toList());
    }
}
