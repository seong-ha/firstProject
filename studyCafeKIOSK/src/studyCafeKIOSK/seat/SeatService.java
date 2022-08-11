package studyCafeKIOSK.seat;

import java.util.List;
import java.util.Scanner;

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
}
