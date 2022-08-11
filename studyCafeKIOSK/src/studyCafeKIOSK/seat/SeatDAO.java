package studyCafeKIOSK.seat;

import java.util.ArrayList;
import java.util.List;

import studyCafeKIOSK.common.DAO;
import studyCafeKIOSK.order.Order;

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
	
	public int updateSeat(Order order) {
		int result = 0;
		
		try {
			conn();
			String sql = "update seat set member_id = ?, start_time = to_date(?, 'yy/mm/dd hh24:mi'),"
					+ " finish_time = to_date(?, 'yy/mm/dd hh24:mi') + 1/24 * ? where seat_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order.getMemberId());
			pstmt.setString(2, order.getOrderTime());
			pstmt.setString(3, order.getOrderTime());
			pstmt.setInt(4, order.getTicketHour());
			pstmt.setInt(5, order.getSeatNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return result;
	}
}
