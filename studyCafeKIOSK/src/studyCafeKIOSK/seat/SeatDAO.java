package studyCafeKIOSK.seat;

import java.util.ArrayList;
import java.util.List;

import studyCafeKIOSK.common.DAO;

public class SeatDAO extends DAO{
	private static SeatDAO seatDAO = null;

	private SeatDAO() {

	}

	public static SeatDAO getInstance() {
		return (seatDAO == null) ? seatDAO = new SeatDAO() : seatDAO;
	}
	
	public List<Seat> getSeatList() {
		List<Seat> list = new ArrayList<>();
		Seat seat = null;
		
		try {
			conn();
			String sql = "select seat_no from seat where member_id is null";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				seat = new Seat();
				seat.setSeatNo(rs.getInt("seat_no"));
				list.add(seat);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
}
