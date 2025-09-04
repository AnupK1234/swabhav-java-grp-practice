package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.Holiday;
import com.aurionpro.util.DatabaseUtil;

public class HolidayDAO {
	public List<Holiday> findAll() {
		List<Holiday> list = new ArrayList<>();
		try (Connection con = DatabaseUtil.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM holidays ORDER BY holiday_date")) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Holiday h = new Holiday();
					h.setId(rs.getInt("id"));
					h.setHolidayDate(rs.getDate("holiday_date").toLocalDate());
					h.setTitle(rs.getString("title"));
					list.add(h);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public void create(LocalDate date, String title) {
		try (Connection con = DatabaseUtil.getConnection();
				PreparedStatement ps = con.prepareStatement("INSERT INTO holidays(holiday_date,title) VALUES(?,?)")) {
			ps.setDate(1, Date.valueOf(date));
			ps.setString(2, title);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		try (Connection con = DatabaseUtil.getConnection();
				PreparedStatement ps = con.prepareStatement("DELETE FROM holidays WHERE id=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addHoliday(Date holidayDate, String description) throws SQLException {
        String sql = "INSERT INTO holidays (holiday_date, title) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, holidayDate);
            ps.setString(2, description);
            ps.executeUpdate();
        }
    }
}
