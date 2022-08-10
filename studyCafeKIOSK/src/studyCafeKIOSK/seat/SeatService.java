package studyCafeKIOSK.seat;

import java.util.List;
import java.util.Scanner;

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
}
