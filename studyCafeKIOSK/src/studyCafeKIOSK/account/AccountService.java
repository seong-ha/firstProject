package studyCafeKIOSK.account;

import java.util.List;
import java.util.Scanner;

import studyCafeKIOSK.member.MemberService;

public class AccountService {
	/*
	 * 1. 계좌 개설 2. 입금 3. 출금 4. 계좌 해지 5. 계좌 이체
	 */

	Scanner sc = new Scanner(System.in);

	// 결제
	public void transferMoney() {
		
		/*
		결제에 필요한 정보
		1. 받는 사람 계좌
		2. 보내는 사람 계좌
		3. 출금 금액
		 */
		
		System.out.println("======== 계좌 이체 ========");
		
		System.out.println("보내는 사람 계좌(출금될 계좌)");
		String fromAccountId = sc.nextLine();
		
		System.out.println("받는 사람 계좌(입금될 계좌)");
		String toAccountId = sc.nextLine();
		
		System.out.println("출금 금액");
		int money = Integer.parseInt(sc.nextLine());
		
		AccountDAO.getInstance().transferMoney(toAccountId, fromAccountId, money);
	}
	
	// 계좌조회
	public void getAccount() {
		List<Account> list = AccountDAO.getInstance().getAccountList(MemberService.memberInfo.getMemberId());
		System.out.println(MemberService.memberInfo.getMemberName() + " 님의 계좌정보");
		
		for (Account account : list) {
			System.out.println("계좌 ID: " + account.getAccountId());
			System.out.println("잔고: " + account.getBalance());
		}
	}
}
