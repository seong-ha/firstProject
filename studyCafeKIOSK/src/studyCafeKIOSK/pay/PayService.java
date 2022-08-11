package studyCafeKIOSK.pay;

import java.util.Scanner;

import studyCafeKIOSK.member.MemberService;
import studyCafeKIOSK.order.Order;
import studyCafeKIOSK.ticket.Ticket;

public class PayService {
	
	Scanner sc = new Scanner(System.in);
	PayDAO pDAO = PayDAO.getInstance();
	
	public int choosePayment(Ticket ticket) {
		System.out.println("1. 카드 | 2. 카카오페이(개발예정)");
		System.out.print("결제 수단을 선택하세요.> ");
		int payment = Integer.parseInt(sc.nextLine());

		System.out.println("------- 결제할 내역 --------");
		System.out.println("결제할 수단: " + pDAO.paymentToString(payment));
		System.out.println("결제할 금액: " + ticket.getTicketPrice());

		System.out.println("결제하시겠습니까?");
		System.out.print(" Y / N 선택> ");
		String payConfirm = sc.nextLine();
		
		if (payConfirm.equalsIgnoreCase("y")) {
			return payment;
		}
		
		return 0;
	}
	
	// 주문인데 카드로 결제경우 (정액권 이용은 회원서비스나 여기서나에서 따로만들기)
	public int orderCardPay(Order order) {
		int result = pDAO.orderUpdateCard(MemberService.memberInfo.getMemberId(), order.getTicketPrice(), order);
		
		if (result == 1) {
			System.out.println("결제 완료");
		} else {
			System.out.println("결제 실패");
		}
		return result;
	}
	
	// 정액권 구매나 임의 결제 (카드)
	public void cardPay(Ticket ticket) {
		pDAO.orderUpdateCard(MemberService.memberInfo.getMemberId(), ticket.getTicketPrice(), null);
	}
}
