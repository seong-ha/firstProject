package studyCafeKIOSK.member;

import java.util.Scanner;

import studyCafeKIOSK.order.Order;
import studyCafeKIOSK.pay.PayDAO;

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
	MemberDAO mDAO = MemberDAO.getInstance();
	PayDAO pDAO = PayDAO.getInstance();
	Scanner sc = new Scanner(System.in);
	
	public void join() {
		System.out.print("폰번호를 입력하세요.> ");
		String phone = sc.nextLine();
		System.out.print("이름을 입력하세요.");
		String name = sc.nextLine();
		System.out.print("비밀번호를 입력하세요.");
		String pw = sc.nextLine();
		
		Member member = new Member();
		member.setMemberId(phone);
		member.setMemberName(name);
		member.setMemberPw(pw);
		member.setPhone(phone);
		
		int result = mDAO.insertMember(member);
		
		if (result == 1) {
			System.out.println("회원 가입이 완료 되었습니다.");
		} else {
			System.out.println("회원 가입에 실패했습니다.");
		}
		
	}
	
	public void login() {
		System.out.print("폰번호를 입력하세요.>");
		String memberId = sc.nextLine();
		
		System.out.print("비밀번호를 입력하세요.>");
		String memberPw = sc.nextLine();
		
		Member member = MemberDAO.getInstance().login(memberId, memberPw);
		
		if (member != null) {
			memberInfo = member;
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
		
	}
	
	public void changePw() {
		System.out.println("현재 비밀 번호와 바꿀 비밀번호를 입력해주세요.");
		
		System.out.print("현재 비밀번호> ");
		String memberNowPw = sc.nextLine();
		System.out.print("바꿀 비밀번호> ");
		String memberNextPw = sc.nextLine();
		
		int result = mDAO.updateMemberPw(memberInfo.getMemberId(), memberNowPw, memberNextPw);
		
		if (result == 1) {
			System.out.println("비밀변호 변경 완료");
		} else {
			System.out.println("비밀번호 변경 실패");
		}
		
	}
	
	public int timePlus(Order order) {
		int result = MemberDAO.getInstance().updateTimesPlus(order);
		
		String resultPrint = "";
		
		if (result == 1) {
			if (order.getTicketType() == 1) {
				resultPrint = "1회권 시간 등록 완료";
			} else if(order.getTicketType() == 3) {
				resultPrint = "정액 시간 충전 완료";
			} else if(order.getTicketType() == 4) {
				resultPrint = "시간 연장 완료";
			}
		} else {
			resultPrint = "시간 등록 및 추가 실패";
		}
		
		System.out.println(resultPrint);
		
		return result;
	}
	
	public int timeMinus(Order order) {
		int result = mDAO.updateTimesMinus(order);
		
		if (result == 1) {
			System.out.println("정액 시간 차감 완료");
		} else {
			System.out.println("정액 시간 차감 실패");
		}
		
		return result;
	}
	
}
