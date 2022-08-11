package studyCafeKIOSK.order;

import java.util.Scanner;

import studyCafeKIOSK.member.MemberService;
import studyCafeKIOSK.pay.PayService;
import studyCafeKIOSK.seat.SeatService;
import studyCafeKIOSK.ticket.Ticket;
import studyCafeKIOSK.ticket.TicketService;

public class OrderService {

	Scanner sc = new Scanner(System.in);
	OrderDAO oDAO = OrderDAO.getInstance();
	TicketService ts = new TicketService();
	SeatService ss = new SeatService();
	PayService ps = new PayService();
	MemberService ms = new MemberService();

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
				int payment = ps.choosePayment(choosenTicket);
				order.setPayment(payment);
				if (order.getPayment() == 1) {

					int payResult = ps.orderCardPay(order);

					if (payResult == 1) {

						// order 등록하기
						int orderResult = oDAO.insertOrder(order);

						// order 등록 되면 이에 따라 seat이랑 회원정보 update.
						if (orderResult == 1) {
							System.out.println("주문 등록 완료");

							int seatResult = ss.registerSeat(order);
							int memberResult = ms.timePlus(order);

							if (seatResult == 1 && memberResult == 1) {
								System.out.println("영수증의 출입키를 이용하여 출입하시면 됩니다.");

								System.out.println("1. 종이 + 전자영수증 | 2. 전자 영수증만 발급");
								System.out.print("영수증(출입키) 발급 유형 선택> ");

								int receipt = Integer.parseInt(sc.nextLine());
								if (receipt == 1) {
									System.out.println("영수증(출입키)이 출력됩니다.(종이 + 전자영수증)");
								} else {
									System.out.println("영수증(출입키)이 출력됩니다.(전자영수증)");
								}

								break; // 로그아웃으로 가는길.

							} else if (seatResult == 1 && memberResult == 0) {
								System.out.println("좌석은 배정 완료, 회원시간 등록 및 추가는 실패");
							} else if (seatResult == 0 && memberResult == 1) {
								System.out.println("좌석은 배정 실패, 회원시간 등록 및 추가는 업데이트완료");
							} else {
								System.out.println("좌석 배정, 회원시간 등록 및 추가: 모두 실패");
							}
						} else {
							System.out.println("주문 등록 실패");
						}
					}

				} else {
					System.out.println("결제 수단 잘못 선택");
				}

			} else if (ticketMenuNo == 2) {

				// 이용할 정액권 티겟(정보) pick up
				Ticket choosenTicket = ts.chooseTicket(ticketMenuNo);

				// 이용할 좌석번호 pick up
				int seatNo = ss.chooseSeatNo();

				// 이용기간 계산
				// 좌석 / 이용시간 / 이용기간 / 차감될 시간.
				Order order = oDAO.getOrderDetail(seatNo, choosenTicket);

				int payment = ps.choosePayment(choosenTicket);
				order.setPayment(payment);
				if (order.getPayment() == 2) {

					int payResult = ms.timeMinus(order);

					if (payResult == 1) {

						// order 등록하기
						int orderResult = oDAO.insertOrder(order);

						// order 등록 되면 이에 따라 seat이랑 회원정보 update.
						if (orderResult == 1) {
							System.out.println("주문 등록 완료");

							int seatResult = ss.registerSeat(order);

							if (seatResult == 1) {
								System.out.println("영수증의 출입키를 이용하여 출입하시면 됩니다.");

								System.out.println("1. 종이 + 전자영수증 | 2. 전자 영수증만 발급");
								System.out.print("영수증(출입키) 발급 유형 선택> ");

								int receipt = Integer.parseInt(sc.nextLine());
								if (receipt == 1) {
									System.out.println("영수증(출입키)이 출력됩니다.(종이 + 전자영수증)");
								} else {
									System.out.println("영수증(출입키)이 출력됩니다.(전자영수증)");
								}

								break; // 로그아웃으로 가는길.

							} else {
								System.out.println("좌석 배정 실패");
							}
						} else {
							System.out.println("주문 등록 실패");
						}
					}

				} else {
					System.out.println("결제 수단 잘못 선택");
				}

			} else if (ticketMenuNo == 3) {

				// 구매할 정액권 티겟(정보) pick up
				Ticket choosenTicket = ts.chooseTicket(ticketMenuNo);

				Order order = oDAO.getOrderDetail(0, choosenTicket);

				int payment = ps.choosePayment(choosenTicket);
				order.setPayment(payment);
				if (order.getPayment() == 1) {

					int payResult = ps.orderCardPay(order);

					if (payResult == 1) {

						// order 등록하기
						int orderResult = oDAO.insertOrder(order);

						// order 등록 되면 이에 따라 seat이랑 회원정보 update.
						if (orderResult == 1) {
							System.out.println("주문 등록 완료");

							int memberResult = ms.timePlus(order);

							if (memberResult == 1) {
								System.out.println("1. 종이 + 전자영수증 | 2. 전자 영수증만 발급");
								System.out.print("영수증 발급 유형 선택> ");

								int receipt = Integer.parseInt(sc.nextLine());
								if (receipt == 1) {
									System.out.println("영수증이 출력됩니다.(종이 + 전자영수증)");
								} else {
									System.out.println("영수증이 출력됩니다.(전자영수증)");
								}

							} else {
								System.out.println("정액시간 등록 실패");
							}
						} else {
							System.out.println("주문 등록 실패");
						}
					}

				} else {
					System.out.println("결제 수단 잘못 선택");
				}

			}

		}
	}
}
