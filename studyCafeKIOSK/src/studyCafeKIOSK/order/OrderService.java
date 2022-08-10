package studyCafeKIOSK.order;

import java.util.List;
import java.util.Scanner;

import studyCafeKIOSK.seat.Seat;
import studyCafeKIOSK.seat.SeatDAO;

public class OrderService {
	
	Scanner sc = new Scanner(System.in);
	
	public void makeOrder() {
		System.out.println("1. 1회권 이용 | 2. 정액권 이용 | 3. 정액권 충전");
		System.out.print("상품 유형을 선택하세요.>");
		
		int ticketNo = Integer.parseInt(sc.nextLine());
		
		if (ticketNo == 1) {
			System.out.println("2시간 | 4시간 | 8시간 | 12시간");
			System.out.print("이용 시간을 선택하세요.>");
			int ticketTime = Integer.parseInt(sc.nextLine());
			
			// 빈좌석 출력해주기
			List<Seat> list = SeatDAO.getInstance().getSeatList();
			
			for (Seat seat : list) {
				System.out.println(seat.getSeatNo());
			}
			
			System.out.print("좌석을 선택하세요.> ");
			
			
			
			
			
		} else if (ticketNo == 2) {
			
		} else if (ticketNo == 3) {
			
		}
		
		
		
	}
}
