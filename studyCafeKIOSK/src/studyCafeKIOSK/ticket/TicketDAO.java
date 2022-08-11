package studyCafeKIOSK.ticket;

import java.util.ArrayList;
import java.util.List;

import studyCafeKIOSK.common.DAO;

public class TicketDAO extends DAO {

	private static TicketDAO ticketDAO = null;

	private TicketDAO() {

	}

	public static TicketDAO getInstance() {
		return (ticketDAO == null) ? ticketDAO = new TicketDAO() : ticketDAO;
	}

	public String ticketTypeToString(int ticketType) {
		
		if (ticketType == 1) {
			return "1회권";
		} else if (ticketType == 2) {
			return "정액권";
		} else if (ticketType == 3) {
			return "정액권 충전";
		} else if (ticketType == 4) {
			return "연장";
		} else {
			System.out.println("티켓 종류를 잘못 선택했습니다.");
			return "";
		}
		
	}
	
	// 1회권, 정액권 이용할 때 보여줄 상품정보(2,4,6,8 시간)
	public List<Ticket> getTicketInfo(int ticketType) {
		List<Ticket> list = new ArrayList<>();
		Ticket ticket = null;

		try {
			conn();
			String sql = "select * from ticket where ticket_type = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketType);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ticket = new Ticket();
				ticket.setTicketId(rs.getString("ticket_id"));
				ticket.setTicketType(rs.getInt("ticket_type"));
				ticket.setTicketHour(rs.getInt("ticket_hour"));
				ticket.setTicketPrice(rs.getInt("ticket_price"));
				list.add(ticket);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	public Ticket getChoosenTicketInfo(String ticketId) {
		Ticket ticket = null;
		try {
			conn();
			String sql = "select * from ticket where ticket_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ticketId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ticket = new Ticket();
				ticket.setTicketId(rs.getString("ticket_id"));
				ticket.setTicketType(rs.getInt("ticket_type"));
				ticket.setTicketHour(rs.getInt("ticket_hour"));
				ticket.setTicketPrice(rs.getInt("ticket_price"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return ticket;
	}
}
