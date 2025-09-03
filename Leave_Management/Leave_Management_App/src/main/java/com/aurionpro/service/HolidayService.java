package com.aurionpro.service;

import java.util.List;
import com.aurionpro.dao.HolidayDao;
import com.aurionpro.model.Holiday;

public class HolidayService {

    private HolidayDao holidayDao;

    public HolidayService() {
        this.holidayDao = new HolidayDao();
    }

   
    public List<Holiday> getAllHolidays() {
        return holidayDao.findAllHolidays();
    }
}