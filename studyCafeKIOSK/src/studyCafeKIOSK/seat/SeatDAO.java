package studyCafeKIOSK.seat;

import java.util.ArrayList;
import java.util.List;

import studyCafeKIOSK.common.DAO;
import studyCafeKIOSK.order.Order;
import studyCafeKIOSK.order.OrderDAO;

public class SeatDAO extends DAO{
	private static SeatDAO seatDAO = null;

	private SeatDAO() {

	}

	public static SeatDAO getInstance() {
		return (seatDAO == null) ? seatDAO = new SeatDAO() : seatDAO;
	}
	
	OrderDAO oDAO = OrderDAO.getInstance();
	
	public List<Seat> getSeatList() {
		List<Seat> list = new ArrayList<>();
		Seat seat = null;
		
		try {
			conn();
			String sql = "select * from seat where member_id is null";
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
	
	public Seat getSeatedInfo(String memberId) {
		Seat seat = null;
		try {
			conn();
			String sql = "select seat_no, member_id, to_char(start_time, 'yy/mm/dd hh24:mi') \"start_time\", "
					+ "to_char(finish_time, 'yy/mm/dd hh24:mi') \"finish_time\" "
					+ "from Seat where member_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				seat = new Seat();
				seat.setSeatNo(rs.getInt("seat_no"));
				seat.setMemberId(rs.getString("member_id"));
				seat.setStartTime(rs.getString("start_time"));
				seat.setFinishTime(rs.getString("finish_time"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return seat;
	}
	
	public int transferSeat(Seat fromSeat, int seatNo) {
		int fromResult = 0;
		int toResult = 0;
		
		try {
			conn();
			String fromSeatSql = "update seat set member_id = null, start_time = null, finish_time = null "
					+ "where member_id = ?";
			pstmt = conn.prepareStatement(fromSeatSql);
			pstmt.setString(1, fromSeat.getMemberId());
			
			fromResult = pstmt.executeUpdate();
			
			if (fromResult == 1) {
				System.out.println("기존 좌석 정보 삭제완료");
				
				String toSeatSql = "update seat set member_id = ?, start_time = to_date(to_char(sysdate, 'yy/mm/dd hh24:mi'), 'yy/mm/dd hh24:mi'), "
						+ "finish_time = to_date(?, 'yy/mm/dd hh24:mi') where seat_no = ?";
				pstmt = conn.prepareStatement(toSeatSql);
				pstmt.setString(1, fromSeat.getMemberId());
				pstmt.setString(2, fromSeat.getFinishTime());
				pstmt.setInt(3, seatNo);
				
				toResult = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return toResult;
		
	}
}
