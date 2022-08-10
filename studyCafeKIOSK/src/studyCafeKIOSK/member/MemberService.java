package studyCafeKIOSK.member;

import java.util.List;
import java.util.Scanner;

public class MemberService {
	
//	private static MemberService ms = null;
//	
//	private MemberService() {
//		
//	}
//	
//	public static MemberService getInstance() {
//		return (ms == null) ? ms = new MemberService() : ms;
//	}

	public static Member memberInfo = null;
	Scanner sc = new Scanner(System.in);
	
	public void login() {
		System.out.print("아이디를 입력하세요.>");
		String memberId = sc.nextLine();
		
		System.out.print("비밀번호를 입력하세요.>");
		String memberPw = sc.nextLine();
		
		List<Member> list = MemberDAO.getInstance().login(memberId, memberPw);
		
		if (list.size() != 0) {
			memberInfo = list.get(0);
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
		
	}
}
