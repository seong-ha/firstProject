package studyCafeKIOSK.seat;

import java.util.List;
import java.util.Scanner;

import studyCafeKIOSK.member.MemberService;
import studyCafeKIOSK.order.Order;

public class SeatService {

	Scanner sc = new Scanner(System.in);
	SeatDAO sDAO = SeatDAO.getInstance();
	
	// 빈 좌석 보여주고 좌석 선택
	public int chooseSeatNo() {
		List<Seat> seatList = sDAO.getSeatList();
		for (Seat seat : seatList) {
			System.out.println(seat.getSeatNo());
		}
		
		System.out.print("좌석을 선택하세요.> ");
		int seatNo = Integer.parseInt(sc.nextLine());
		
		return seatNo;
	}
	
	public int registerSeat(Order order) {
		int result = sDAO.updateSeat(order);
		
		if (result == 1) {
			System.out.println("좌석 업데이트 완료.");
		} else {
			System.out.println("좌석 업데이트 실패.");
		}
		
		return result;
	}
	
	public void getUsingInfo() {
		Seat seat = sDAO.getSeatedInfo(MemberService.memberInfo.getMemberId());
		
		if (seat != null) {
			System.out.println("----------------" + MemberService.memberInfo.getMemberName() + "님의 이용 정보 ----------------");
			System.out.println(" - 이용중인 좌석: " + seat.getSeatNo() + "번\n"
							+ " - 이용기간: " + seat.getStartTime() + "부터\n"
							+ "           " + seat.getFinishTime() + "까지\n");
		} else {
			System.out.println("현재 이용 중인 좌석이 없습니다.");
		}
		
	}
	
	public void changeSeat() {
		Seat fromSeat = sDAO.getSeatedInfo(MemberService.memberInfo.getMemberId());
		
		if (fromSeat != null) {
			List<Seat> seatList = sDAO.getSeatList();
			for (Seat seat : seatList) {
				System.out.println(seat.getSeatNo());
			}
			
			System.out.print("옮기실 좌석을 선택하세요.> ");
			int seatNo = Integer.parseInt(sc.nextLine());
			
			System.out.println("------------ 이동할 좌석 정보 ------------");
			System.out.println(fromSeat.getSeatNo() + "번 좌석에서 " + seatNo + "번 좌석으로 이동하시겠습니까?");
			System.out.print(" Y / N 선택> ");
			String confirmChange = sc.nextLine();
			
			if (confirmChange.equalsIgnoreCase("y")) {
				int changeResult = sDAO.transferSeat(fromSeat, seatNo);
				
				if (changeResult == 1) {
					Seat toSeat = sDAO.getSeatedInfo(MemberService.memberInfo.getMemberId());
					System.out.println("------------------ 좌석 이동 안내 --------------------");
					System.out.println(toSeat.getSeatNo() + "번 좌석으로 이동되었습니다.\n"
							+ " - 이용 가능 시간: " + toSeat.getStartTime() + "부터\n"
							+ "               " + toSeat.getFinishTime() + "까지");
				} else {
					System.out.println("좌석 이동 실패.");
				}
			} else {
				System.out.println("좌석 이동을 취소합니다.");
				return;
			}
			
		} else {
			System.out.println("현재 이용 중인 좌석이 없습니다. 자리 이용 중일 때 말씀하시지예");
		}
	}
}
