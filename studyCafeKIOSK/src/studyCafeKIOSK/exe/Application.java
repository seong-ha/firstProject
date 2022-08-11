package studyCafeKIOSK.exe;

import java.util.Scanner;

import studyCafeKIOSK.member.MemberService;
import studyCafeKIOSK.order.OrderService;
import studyCafeKIOSK.seat.SeatService;

public class Application {

	OrderService os = new OrderService();
	MemberService ms = new MemberService();
	SeatService ss = new SeatService();
	Scanner sc = new Scanner(System.in);

	public Application() {
		run();
	}

	private void run() {

		beforeLogin: while (true) {

			if (MemberService.memberInfo != null) { // 로그인 되어있을 때

				afterLogin: while (true) {
					System.out.println("__________________________________________________________________________");
					System.out.println("1. 1회권 이용 / 정액권 이용 / 정액권 충전 | 2. 이용 정보 확인 | 3. 출입키 재발급");
					System.out.println("4. 좌석 이동 | 5. 시간 연장 | 6. 비밀번호 재설정 | 7. 퇴실 | 99. 로그아웃");
					System.out.print("메뉴를 선택해주세요.> ");

					int menuNo = Integer.parseInt(sc.nextLine());

					switch (menuNo) {
					case 1:
						os.makeOrder();
						MemberService.memberInfo = null;
						System.out.println("자동 로그아웃 합니다.");
						break afterLogin;
					case 2:
						ss.getUsingInfo();
						break;
					case 3:
						// 출입키는 나중에.
						break;
					case 4:
						ss.changeSeat();
						break;
					case 5:
						break;
					case 6:
						ms.changePw();
						MemberService.memberInfo = null;
						System.out.println("다시 로그인해주세요.");
						break afterLogin;
					case 7:
						break;
					case 99:
						MemberService.memberInfo = null;
						System.out.println("로그아웃 합니다.");
						break afterLogin;
					}
				}

			} else { // 로그인 안 되어 있을때

				while (true) {
					System.out.println("1. 로그인 | 2. 회원가입 | 3. 종료");
					System.out.print("메뉴 선택> ");
					int beforeLoginMenuNo = Integer.parseInt(sc.nextLine());

					if (beforeLoginMenuNo == 1) {
						ms.login();
						break;
					} else if (beforeLoginMenuNo == 2) {
						ms.join();
					} else if (beforeLoginMenuNo == 3) {
						System.out.println("프로그램을 종료합니다.");
						break beforeLogin;
					} else {
						System.out.println("메뉴를 다시 입력해주세요.");
					}
				}
			}
		}

	}

}
