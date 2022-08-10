package studyCafeKIOSK.order;

import java.util.Scanner;

import studyCafeKIOSK.member.MemberDAO;
import studyCafeKIOSK.member.MemberService;
import studyCafeKIOSK.pay.PayDAO;
import studyCafeKIOSK.seat.SeatDAO;
import studyCafeKIOSK.seat.SeatService;
import studyCafeKIOSK.ticket.Ticket;
import studyCafeKIOSK.ticket.TicketService;

public class OrderService {

	Scanner sc = new Scanner(System.in);
	SeatDAO sDAO = SeatDAO.getInstance();
	OrderDAO oDAO = OrderDAO.getInstance();
	PayDAO pDAO = PayDAO.getInstance();
	TicketService ts = new TicketService();
	SeatService ss = new SeatService();

	public void makeOrder() {
		while (true) {
			System.out.println("1. 1회권 이용 | 2. 정액권 이용 | 3. 정액권 충전");
			System.out.print("상품 유형을 선택하세요.>");

			int ticketMenuNo = Integer.parseInt(sc.nextLine());

			if (ticketMenuNo == 1) {

				// 이용할 1회권 티겟(정보) pick up
				Ticket choosenTicket = ts.chooseTicket(ticketMenuNo);

				// 이용할 좌석번호 pick up
				int seatNo = ss.chooseSeatNo();

				// 이용기간 계산
				// 좌석 / 이용시간 / 이용기간 / 결제할 금액
				Order order = oDAO.getOrderDetail(seatNo, choosenTicket);

				// payment 선택
				
				order.setPayment(p);
				if (payConfirm.equalsIgnoreCase("y")) {
					order.setPayment(1);
					// order 등록하기
					int OrderResult = oDAO.insertOrder(order);

					if (OrderResult == 1) {
						int seatResult = sDAO.updateSeat(order);
						int memberResult = MemberDAO.getInstance().updateTimes(order);

					
					int result = (payment == 1)
							? pDAO.orderUpdateCard(MemberService.memberInfo.getMemberId(), choosenTicket.getTicketPrice(), order)
							: 0;

					if (result == 1) {
						System.out.println("결제에 성공했습니다.");

//						Order order = new Order();
						

						
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

			} else if (ticketMenuNo == 2) {
				// ★★★★★★★★★★★ 먼저 테이블들 만들고, 시간 메뉴랑 좌석 10개 넣고,
				// ★★★★★★★★★★★관리자랑 회원 만들고, 둘의 계좌도 만들고
				
				
				// 정액권 티켓정보 보여주기
				Ticket choosenTicket = ts.chooseTicket(ticketMenuNo);
				
				
				
				
			} else if (ticketMenuNo == 3) {

			}

		}
	}
}
