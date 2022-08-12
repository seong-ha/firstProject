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
			
			if (ticketType == 1) {
				System.out.println(tDAO.ticketTypeToString(ticket.getTicketType()) + " | "
								+ ticket.getTicketHour() + "시간 | "
								+ ticket.getTicketPrice() + "원");
			} else if (ticketType == 2) {
				System.out.println(tDAO.ticketTypeToString(ticket.getTicketType()) + " | "
								+ ticket.getTicketHour() + "시간");
			} else if (ticketType == 3) {
				System.out.println(tDAO.ticketTypeToString(ticket.getTicketType()) + " | "
								+ ticket.getTicketHour() + "시간");
			} else if (ticketType == 4) {
				
				// 1회권 연장
				if (ticket.getTicketPrice() > 12) {
					System.out.println(tDAO.ticketTypeToString(ticket.getTicketType()) + " | "
								+ ticket.getTicketHour() + "시간 | "
								+ ticket.getTicketPrice() + "원  → <1회권 이용 중일 시>");
				// 정액권 연장
				} else if (ticket.getTicketPrice() < 13) {
					System.out.println(tDAO.ticketTypeToString(ticket.getTicketType()) + " | "
							+ ticket.getTicketHour() + "시간            → <정액권 이용 중일 시>");
				}
			}
			
		}
		System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");

		// 티켓타입이 연장이면 이용중인 티켓종류와 연장할 시간 받기 / 나머지는 그냥 이용할 시간만 받기.
		int usingTicket = 0;
		int ticketTime = 0;
		
		if (ticketType == 4) {
			System.out.println("1. 1회권 | 2. 정액권");
			System.out.print("이용중인 상품유형을 선택하세요.> ");
			usingTicket = Integer.parseInt(sc.nextLine());
		
			System.out.print("연장할 시간> ");
			ticketTime = Integer.parseInt(sc.nextLine());
		} else {
			System.out.print("이용 시간을 선택하세요.>");
			ticketTime = Integer.parseInt(sc.nextLine());
		}

		// 선택한 정보를 바탕으로 티켓id(티켓유형+시간 or 티켓유형+시간+시간)로 티켓정보 받아오기.
		String ticketId = "";
		// 연장유형일때
		if (ticketType == 4) {
			// 1회권 연장일때는 연장타입(4) + 시간
			if (usingTicket == 1) {
				ticketId = "" + ticketType + ticketTime;
			// 정액권 연장일때는 연장타입(4) + 시간 + 시간
			} else if (usingTicket == 2) {
				ticketId = "" + ticketType + ticketTime + ticketTime;
			}
			
		// 나머지 일때
		} else {
			ticketId = "" + ticketType + ticketTime;
		}
		
		Ticket choosenTicket = tDAO.getChoosenTicketInfo(ticketId);
		
		return choosenTicket;
	}
}
