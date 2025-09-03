package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.aurionpro.model.Holiday; 
import com.aurionpro.util.DatabaseUtil;

public class HolidayDao {

    public List<Holiday> findAllHolidays() {
        List<Holiday> holidays = new ArrayList<>();
        String sql = "SELECT * FROM holidays ORDER BY holiday_date";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
        	while (rs.next()) {
        	    Holiday holiday = new Holiday();
        	    holiday.setId(rs.getInt("id"));
        	    holiday.setHolidayDate(rs.getDate("holiday_date").toLocalDate()); 
        	    holiday.setTitle(rs.getString("title"));
        	    holidays.add(holiday);
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holidays;
    }
}