package studyCafeKIOSK.order;

import studyCafeKIOSK.common.DAO;
import studyCafeKIOSK.member.MemberService;
import studyCafeKIOSK.pay.PayDAO;
import studyCafeKIOSK.seat.Seat;
import studyCafeKIOSK.ticket.Ticket;

public class OrderDAO extends DAO {

	private static OrderDAO orderDAO = null;

	private OrderDAO() {

	}

	public static OrderDAO getInstance() {
		return (orderDAO == null) ? orderDAO = new OrderDAO() : orderDAO;
	}

	PayDAO pDAO = PayDAO.getInstance();
	static int sequence = 0;

	// 시작시간 알아내오기
	public String getStartTime() {
		String sysdate = "";
		
		try {
			conn();
			String sql = "select to_char(sysdate, 'yy/mm/dd hh24:mi') \"start\" from dual";
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
	
	// 시작시간(주문시간이자 sysdate) + 시간 = 마감시간
	public String getFinishTime(int time) {
		String finishTime = "";
		
		try {
			conn();
			String sql = "select to_char(sysdate + 1/24 * ?, 'yy/mm/dd hh24:mi') \"finish\" from dual";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, time);
			
			rs = pstmt.executeQuery();
			
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
	
	// 시간 연장 전용 마감시간 가져오기. (이용중인 좌석 마감시간 + 시간)
	public String getExtensionFinishTime(Seat seat, int time) {
		String finishTime = "";
		
		try {
			conn();
			String sql = "select to_char(to_date(?, 'yy/mm/dd hh24:mi')  + 1/24 * ?, 'yy/mm/dd hh24:mi') \"finish\" from dual";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seat.getFinishTime());
			pstmt.setInt(2, time);
			
			rs = pstmt.executeQuery();
			
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
			String sql = "insert into orders values (?, ?, ?, to_date(?, 'yy/mm/dd hh24:mi'), ?, ?, ?, ?)";
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
		
		if (ticket.getTicketType() == 1) {
			System.out.println("------- 주문할 내역 --------");
			System.out.println(" - 이용 좌석: " + order.getSeatNo() + "번\n"
					+ " - 이용시간: " + order.getTicketHour() + "시간\n"
					+ " - 이용기간: " + order.getOrderTime() + "부터\n"
					+ "           " + getFinishTime(order.getTicketHour()) + "까지\n"
					+ " - 결제할 금액: " + ticket.getTicketPrice());
			
		} else if (ticket.getTicketType() == 2) {
			System.out.println("------- 주문할 내역 --------");
			System.out.println(" - 이용 좌석: " + order.getSeatNo() + "번\n"
					+ " - 이용시간: " + order.getTicketHour() + "시간\n"
					+ " - 이용기간: " + order.getOrderTime() + "부터\n"
					+ "           " + getFinishTime(order.getTicketHour()) + "까지\n"
					+ " - 차감될 시간: " + ticket.getTicketPrice());
			
		} else if (ticket.getTicketType() == 3) {
			System.out.println("------- 주문할 내역 --------");
			System.out.println(" - 구매시간: " + order.getTicketHour() + "시간\n"
					+ " - 구매일시: " + order.getOrderTime() + "\n"
					+ " - 결제할 금액: " + ticket.getTicketPrice());
		} else if (ticket.getTicketType() == 4) {
			
		}
		
		return order;
	}
	
	public Order getExtensionOrderDetail(Seat seat, Ticket ticket) {
		Order order = new Order();
		
		order.setOrderId(getStartTime() + ++sequence);
		order.setMemberId(seat.getMemberId());
		order.setSeatNo(seat.getSeatNo());
		order.setOrderTime(getStartTime());
		order.setTicketType(ticket.getTicketType());
		order.setTicketHour(ticket.getTicketHour());
		order.setTicketPrice(ticket.getTicketPrice());
		
		if (ticket.getTicketPrice() > 12) {
			System.out.println("------- 주문할 내역 --------");
			System.out.println(" - 이용 좌석: " + order.getSeatNo() + "번\n"
					+ " - 추가시간: " + order.getTicketHour() + "시간\n"
					+ " - 이용기간: " + seat.getFinishTime() + "부터\n"
					+ "           " + getExtensionFinishTime(seat, ticket.getTicketHour()) + "까지\n"
					+ " - 결제할 금액: " + ticket.getTicketPrice());
		} else if (ticket.getTicketPrice() < 13) {
			System.out.println("------- 주문할 내역 --------");
			System.out.println(" - 이용 좌석: " + order.getSeatNo() + "번\n"
					+ " - 추가시간: " + order.getTicketHour() + "시간\n"
					+ " - 이용기간: " + seat.getFinishTime() + "부터\n"
					+ "           " + getExtensionFinishTime(seat, ticket.getTicketHour()) + "까지\n"
					+ " - 차감될 시간: " + ticket.getTicketPrice());
		}
		
		return order;
	}
	
	public void printReceipt(Order order, int payment) {

		System.out.println("------------- 영수증 -------------");

		if (order.getTicketType() == 1) {
			System.out.println(" - 이용 좌석: " + order.getSeatNo() + "번\n"
					+ " - 이용시간: " + order.getTicketHour() + "시간\n"
					+ " - 이용기간: " + order.getOrderTime() + "부터\n"
					+ "           " + getFinishTime(order.getTicketHour()) + "까지\n"
					+ " - 결제 수단: " + pDAO.paymentToString(payment) + "\n"
					+ " - 결제 금액: " + order.getTicketPrice()
					+ "\n출입키:  ▤▥");
			
		} else if (order.getTicketType() == 2) {
			System.out.println(" - 이용 좌석: " + order.getSeatNo() + "번\n"
					+ " - 이용시간: " + order.getTicketHour() + "시간\n"
					+ " - 이용기간: " + order.getOrderTime() + "부터\n"
					+ "           " + getFinishTime(order.getTicketHour()) + "까지\n"
					+ " - 결제 수단: " + pDAO.paymentToString(payment) + "\n"
					+ " - 차감 시간: " + order.getTicketPrice()
					+ "\n출입키:  ▨▧");
			
		} else if (order.getTicketType() == 3) {
			System.out.println(" - 구매시간: " + order.getTicketHour() + "시간\n"
					+ " - 구매일시: " + order.getOrderTime() + "\n"
					+ " - 결제 수단: " + pDAO.paymentToString(payment) + "\n"
					+ " - 결제 금액: " + order.getTicketPrice());
		} else if (order.getTicketType() == 4) {
			
			if (order.getTicketPrice() > 12) {
				System.out.println(" - 이용 좌석: " + order.getSeatNo() + "번\n"
						+ " - 연장시간: " + order.getTicketHour() + "시간\n"
						+ " - 결제 수단: " + pDAO.paymentToString(payment) + "\n"
						+ " - 결제 금액: " + order.getTicketPrice());
			} else if (order.getTicketPrice() < 13) {
				System.out.println(" - 이용 좌석: " + order.getSeatNo() + "번\n"
						+ " - 연장시간: " + order.getTicketHour() + "시간\n"
						+ " - 결제 수단: " + pDAO.paymentToString(payment) + "\n"
						+ " - 차감 시간: " + order.getTicketPrice());
			}
		}
		
		System.out.println("--------------------------------");
	}
}
