package studyCafeKIOSK.ticket;

import java.util.List;
import java.util.Scanner;

public class TicketService {

	TicketDAO tDAO = TicketDAO.getInstance();
	Scanner sc = new Scanner(System.in);

	// 선택한 티켓타입에 따라 티켓정보 보여주고 구매할 티켓 선택
	public Ticket chooseTicket(int ticketType) {
		List<Ticket> ticketList = tDAO.getTicketInfo(ticketType);
		System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");

		for (Ticket ticket : ticketList) {
			
			if (ticketType == 1 | ticketType == 3) {
				System.out.println(tDAO.ticketTypeToString(ticket.getTicketType()) + " | "
								+ ticket.getTicketHour() + "시간 | "
								+ ticket.getTicketPrice() + "원");
			} else if (ticketType == 2) {
				System.out.println(tDAO.ticketTypeToString(ticket.getTicketType()) + " | "
								+ ticket.getTicketHour() + "시간 | "
								+ ticket.getTicketPrice() + "시간");
			}
			
		}
		System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");

		// 티켓 시간 선택
		if (ticketType == 1 || ticketType == 2) {
			System.out.println("2시간 | 4시간 | 8시간 | 12시간");
		} else if (ticketType == 3) {
			System.out.println("50시간 | 100시간");
		} else if (ticketType == 4) {
			System.out.println("1시간 | 2시간 | 3시간 | 4시간");
		}

		System.out.print("이용 시간을 선택하세요.>");
		int ticketTime = Integer.parseInt(sc.nextLine());

		// 선택한 정보를 바탕으로 티켓id(티켓유형+시간)로 티켓정보 받아오기.
		Ticket choosenTicket = tDAO.getChoosenTicketInfo("" + ticketType + ticketTime);
		return choosenTicket;
	}
}
