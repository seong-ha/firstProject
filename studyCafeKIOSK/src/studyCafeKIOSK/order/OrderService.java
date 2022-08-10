package studyCafeKIOSK.order;

import java.util.List;
import java.util.Scanner;

import studyCafeKIOSK.member.MemberDAO;
import studyCafeKIOSK.member.MemberService;
import studyCafeKIOSK.pay.PayDAO;
import studyCafeKIOSK.seat.Seat;
import studyCafeKIOSK.seat.SeatDAO;
import studyCafeKIOSK.ticket.Ticket;
import studyCafeKIOSK.ticket.TicketDAO;

public class OrderService {

	Scanner sc = new Scanner(System.in);
	TicketDAO tDAO = TicketDAO.getInstance();
	SeatDAO sDAO = SeatDAO.getInstance();
	OrderDAO oDAO = OrderDAO.getInstance();
	PayDAO pDAO = PayDAO.getInstance();
	int sequence = 0;

	public void makeOrder() {
		while (true) {
			System.out.println("1. 1회권 이용 | 2. 정액권 이용 | 3. 정액권 충전");
			System.out.print("상품 유형을 선택하세요.>");

			int ticketNo = Integer.parseInt(sc.nextLine());

			if (ticketNo == 1) {

				// 1회권 티켓정보 보여주기
				List<Ticket> ticketList = tDAO.getTicketInfo(ticketNo);
				System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
				for (Ticket ticket : ticketList) {
					System.out.println(tDAO.ticketTypeToString(ticket.getTicketType()) + " | " + ticket.getTicketHour()
							+ "시간 | " + ticket.getTicketPrice() + "원");
				}
				System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");

				// 티켓 시간 선택
				System.out.println("2시간 | 4시간 | 8시간 | 12시간");
				System.out.print("이용 시간을 선택하세요.>");
				int ticketTime = Integer.parseInt(sc.nextLine());

				// 선택한 정보를 바탕으로 티켓id(티켓유형+시간)로 티켓정보 받아오기.
				Ticket choosenTicket = tDAO.getChoosenTicketInfo(ticketNo + ticketTime + "");

				// 빈좌석 보여주기
				List<Seat> seatList = sDAO.getSeatList();
				for (Seat seat : seatList) {
					System.out.println(seat.getSeatNo());
				}

				// 좌석 선택하게하기
				System.out.print("좌석을 선택하세요.> ");
				int seatNo = Integer.parseInt(sc.nextLine());

				// 이용기간 계산
				// 좌석 / 이용시간 / 이용기간 / 결제할 금액
				String startTime = oDAO.getStartTime();
				String finishTime = oDAO.getFinishTime(choosenTicket.getTicketHour());

				System.out.println("------- 주문할 내역 --------");
				System.out.println(" - 이용 좌석: " + seatNo + "번\n" + " - 이용시간: " + choosenTicket.getTicketHour() + "시간\n"
						+ " - 이용기간: " + oDAO.getStartTime() + "부터\n" + "           "
						+ oDAO.getFinishTime(choosenTicket.getTicketHour()) + "까지\n" + " - 결제할 금액: "
						+ choosenTicket.getTicketPrice());

				// payment 선택
				System.out.println("1. 카드 | 2. 카카오페이(개발예정)");
				System.out.print("결제 수단을 선택하세요.> ");
				int payment = Integer.parseInt(sc.nextLine());

				System.out.println("------- 결제할 내역 --------");
				System.out.println("결제할 수단: " + oDAO.paymentToString(payment));
				System.out.println("결제할 금액: " + choosenTicket.getTicketPrice());

				System.out.println("결제하시겠습니까?");
				System.out.print(" Y / N 선택> ");
				String payConfirm = sc.nextLine();

				if (payConfirm.equalsIgnoreCase("y")) {
					int result = (payment == 1)
							? pDAO.updateCard(MemberService.memberInfo.getMemberId(), choosenTicket.getTicketPrice())
							: 0;

					if (result == 1) {
						System.out.println("결제에 성공했습니다.");

						Order order = new Order();
						order.setOrderId(startTime + ++sequence);
						order.setMemberId(MemberService.memberInfo.getMemberId());
						order.setSeatNo(seatNo);
						order.setOrderTime(startTime);
						order.setPayment(payment);
						order.setTicketType(choosenTicket.getTicketType());
						order.setTicketHour(choosenTicket.getTicketHour());
						order.setTicketPrice(choosenTicket.getTicketPrice());

						// order 등록하기
						int OrderResult = oDAO.insertOrder(order);

						if (OrderResult == 1) {
							int seatResult = sDAO.updateSeat(order, finishTime);
							int memberResult = MemberDAO.getInstance().updateTimes(order);

							if (seatResult == 1 && memberResult == 1) {
								System.out.println("주문 구매 완료\n");

								System.out.println("1. 종이 + 전자영수증 | 2. 전자 영수증만 발급");
								System.out.print("영수증(출입키) 발급 유형 선택> ");

							} else if (seatResult == 1 && memberResult == 0) {
								System.out.println("좌석은 업데이트완료, 회원시간적립은 실패");
							} else if (seatResult == 0 && memberResult == 1) {
								System.out.println("좌석은 실패, 회원시간적립은 업데이트완료");
							} else {
								System.out.println("모두 실패");
							}
						} else {
							System.out.println("주문 등록 실패");
						}
					} else {
						System.out.println("결제에 실패했습니다.");
					}
				} else {
					System.out.println("결제 수단 잘못 선택");
				}
				
				break;

			} else if (ticketNo == 2) {
				// ★★★★★★★★★★★ 먼저 테이블들 만들고, 시간 메뉴랑 좌석 10개 넣고,
				// ★★★★★★★★★★★관리자랑 회원 만들고, 둘의 계좌도 만들고
				
				
				// 정액권 티켓정보 보여주기
				List<Ticket> ticketList = tDAO.getTicketInfo(ticketNo);
				System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
				for (Ticket ticket : ticketList) {
					System.out.println(tDAO.ticketTypeToString(ticket.getTicketType()) + " | " + ticket.getTicketHour() + "시간");
				}
				System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
				
				
				
				
			} else if (ticketNo == 3) {

			}

		}
	}
}
