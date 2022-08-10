package studyCafeKIOSK.order;

import studyCafeKIOSK.common.DAO;
import studyCafeKIOSK.member.MemberService;
import studyCafeKIOSK.ticket.Ticket;

public class OrderDAO extends DAO {

	private static OrderDAO orderDAO = null;

	private OrderDAO() {

	}

	public static OrderDAO getInstance() {
		return (orderDAO == null) ? orderDAO = new OrderDAO() : orderDAO;
	}
	
	int sequence = 0;

	// 시작시간 알아내오기
	public String getStartTime() {
		String sysdate = "";
		
		try {
			conn();
			String sql = "select to_char(sysdate, 'yy/mm/dd hh24:mi') start from dual";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				sysdate = rs.getString("start");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return sysdate;
	}
	
	// 시작시간 + 시간 = 마감시간
	public String getFinishTime(int time) {
		String finishTime = "";
		
		try {
			conn();
			String sql = "select to_char(sysdate + 1/24 * ?, 'yy/mm/dd hh24:mi') finish from dual";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, time);
			
			rs = pstmt.executeQuery(sql);
			
			while (rs.next()) {
				finishTime = rs.getString("finish");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return finishTime;
	}
	
	// 주문 등록 및 시행
	public int insertOrder(Order order) {
		int result = 0;
		
		try {
			conn();
			String sql = "insert into order values (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order.getOrderId());
			pstmt.setString(2, order.getMemberId());
			pstmt.setInt(3, order.getSeatNo());
			pstmt.setString(4, order.getOrderTime());
			pstmt.setInt(5, order.getPayment());
			pstmt.setInt(6, order.getTicketType());
			pstmt.setInt(7, order.getTicketHour());
			pstmt.setInt(8, order.getTicketPrice());
			
			result = pstmt.executeUpdate();
			while (rs.next()) {
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	public Order getOrderDetail(int seatNo, Ticket ticket) {
		Order order = new Order();
		
		order.setOrderId(getStartTime() + ++sequence);
		order.setMemberId(MemberService.memberInfo.getMemberId());
		order.setSeatNo(seatNo);
		order.setOrderTime(getStartTime());
		order.setTicketType(ticket.getTicketType());
		order.setTicketHour(ticket.getTicketHour());
		order.setTicketPrice(ticket.getTicketPrice());
		
		System.out.println("------- 주문할 내역 --------");
		System.out.println(" - 이용 좌석: " + order.getSeatNo() + "번\n"
				+ " - 이용시간: " + order.getTicketHour() + "시간\n"
				+ " - 이용기간: " + order.getOrderTime() + "부터\n"
				+ "           " + getFinishTime(order.getTicketHour()) + "까지\n"
				+ " - 결제할 금액: " + ticket.getTicketPrice());
		
		return order;
	}
}
