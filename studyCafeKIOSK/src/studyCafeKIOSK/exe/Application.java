package studyCafeKIOSK.exe;

import java.util.Scanner;

import studyCafeKIOSK.member.MemberService;

public class Application {
	
	MemberService ms = new MemberService();
	Scanner sc = new Scanner(System.in);
	
	public Application() {
		run();
	}

	private void run() {
		
		while (true) {
			
			if (MemberService.memberInfo != null) { // 로그인 되어있을 때
				
				while (true) {
					System.out.println("__________________________________________________________________________");
					System.out.println("1. 1회권 이용 | 2. 정액권 이용 | 3. 정액권 충전 | 4. 이용 정보 확인 | 5. 출입키 재발급");
					System.out.println("6. 출입키 재발급 | 7. 좌석 이동 | 8. 시간 연장 | 9. 비밀번호 재설정 | 10. 퇴실");
					System.out.print("메뉴를 선택해주세요.> ");
					
					int menuNo = Integer.parseInt(sc.nextLine());
					
					switch (menuNo) {
						case 1:
							
							break;
						case 2:
							break;
						case 3:
							break;
						case 4:
							break;
						case 5:
							break;
						case 6:
							break;
						case 7:
							break;
						case 8:
							break;
						case 9:
							break;
						case 10:
							break;
					}
				}
				
			} else { // 로그인 안 되어 있을때
				ms.login();
			}
		}
		
	}
	
}