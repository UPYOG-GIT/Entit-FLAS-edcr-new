package org.egov.edcr.service;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.egov.common.entity.edcr.FLASRoom;
import org.egov.edcr.entity.FLASApplication;
import org.egov.edcr.repository.FLASRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FLASService {

	@PersistenceContext
	private EntityManager entityManager;

	private Session getCurrentSession() {
		return entityManager.unwrap(Session.class);
	}

	@Autowired
	private FLASRepository flasRepository;

	public void save(FLASApplication flasApplication) {
		flasRepository.save(flasApplication);
	}

	public void saveAll(List<FLASApplication> flasApplication) {
		flasRepository.save(flasApplication);
	}

	public List<FLASApplication> proccess(List<FLASRoom> flasRooms, String applcationNo) {

//		List<FLASRoom> flasRooms = pl.getFlasRooms();
//		List<FLASToilet> flasToilets = pl.getFlasToilets();

		List<FLASApplication> flasApplicationList = new ArrayList<>();

		for (FLASRoom rooms : flasRooms) {
			FLASApplication flasApplication = new FLASApplication();
			flasApplication.setApplicationNumber(applcationNo);
			flasApplication.setArea(rooms.getTotalArea().setScale(2, RoundingMode.HALF_UP).toString());
			flasApplication.setAvgHeight(rooms.getAverageHeight().setScale(2, RoundingMode.HALF_UP).toString());
			flasApplication.setBreadth(rooms.getBreadth().setScale(2, RoundingMode.HALF_UP).toString());
			flasApplication.setBreathingSpace(rooms.getBreathingSpace().setScale(2, RoundingMode.HALF_UP).toString());
			flasApplication.setLength(rooms.getLength().setScale(2, RoundingMode.HALF_UP).toString());
			flasApplication.setMaxHeight(rooms.getMaxHeight().setScale(2, RoundingMode.HALF_UP).toString());
			flasApplication.setMinHeight(rooms.getMinHeight().setScale(2, RoundingMode.HALF_UP).toString());
			flasApplication.setName(rooms.getName());
			flasApplication.setTotalArea(rooms.getTotalArea().setScale(2, RoundingMode.HALF_UP).toString());
			flasApplicationList.add(flasApplication);
		}

//		String query = "INSERT INTO state.flas_rooms (name, area, minheight, maxheight, avgheight, length, breadth, totalarea, breathingspace, createddate) "
//				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		/*
		 * jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
		 * 
		 * @Override public void setValues(PreparedStatement ps, int i) throws
		 * SQLException { FLASRoom room = flasRooms.get(i); ps.setString(1,
		 * room.getName()); ps.setBigDecimal(2, room.get); ps.setBigDecimal(3,
		 * room.getMinHeight()); ps.setBigDecimal(4, room.getMaxHeight());
		 * ps.setBigDecimal(5, room.getAverageHeight()); ps.setBigDecimal(6,
		 * room.getLength()); ps.setBigDecimal(7, room.getBreadth());
		 * ps.setBigDecimal(8, room.getTotalArea()); ps.setBigDecimal(9,
		 * room.getBreathingSpace()); ps.setTimestamp(10, new
		 * java.sql.Timestamp(System.currentTimeMillis())); }
		 * 
		 * @Override public int getBatchSize() { return flasRooms.size(); } });
		 */

		return flasApplicationList;
	}
}
