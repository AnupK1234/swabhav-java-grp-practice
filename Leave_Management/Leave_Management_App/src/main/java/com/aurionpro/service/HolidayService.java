package com.aurionpro.service;

import java.time.LocalDate;
import java.util.List;

import com.aurionpro.dao.HolidayDAO;
import com.aurionpro.model.Holiday;

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
}
