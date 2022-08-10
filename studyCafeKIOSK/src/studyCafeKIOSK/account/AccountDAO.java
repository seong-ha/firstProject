package studyCafeKIOSK.account;

import java.util.ArrayList;
import java.util.List;

import studyCafeKIOSK.common.DAO;

public class AccountDAO extends DAO {

	// 싱글톤
	private static AccountDAO am = new AccountDAO();

	// 생성자 막기
	private AccountDAO() {

	}

	public static AccountDAO getInstance() {
		return am;
	}
	
	public List<Account> getAccountList(String memberId) {
		List<Account> list = new ArrayList<>();
		Account account = null;
		
		try {
			conn();
			String sql = "select * from account where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				account = new Account();
				account.setAccountId(rs.getString("account_id"));
				account.setMemberId(rs.getString("member_id"));
				account.setBalance(rs.getInt("balance"));
				list.add(account);
			}
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
				disconnect();
		}
			
		return list;
	}

	// 카드 결제  - toAccount: 받는 사람, - fromAccount: 보내는 사람, - balance: 금액
	public void transferMoney(String toAccount, String fromAccount, int balance) {
		int fromResult = 0;
		int toResult = 0;
		int rollbackResult = 0;
		
		try {
			conn();
			// 출금 전 잔고 확인
			String balanceSql = "select balance from account where account_id =?";
			pstmt = conn.prepareStatement(balanceSql);
			pstmt.setString(1, fromAccount);
			
			rs = pstmt.executeQuery();
			
			int fromBalance = 0;
			if (rs.next()) {
				fromBalance = rs.getInt("balacne");
			}
			
			  // 잔고 부족 시 결제 도로마무
			if (fromBalance >= balance) {
				
				// 보내는 사람의 계좌에 돈을 출금해주는 sql
				String fromSql = "update account set balance = balance - ? where account_id = ?";
				pstmt = conn.prepareStatement(fromSql);
				pstmt.setInt(1, balance);
				pstmt.setString(2, fromAccount);
				
				fromResult = pstmt.executeUpdate();
				
				if (fromResult == 1) {
					System.out.println("정상 출금");
					
					// 받는 사람의 계좌에 돈을 입금해주는 sql
					String toSql = "update account set balance = balance + ? where account_id = ?";
					pstmt = conn.prepareStatement(toSql);
					pstmt.setInt(1, balance);
					pstmt.setString(2, toAccount);
					
					toResult = pstmt.executeUpdate();
					
					if (toResult == 1) {
						System.out.println("결제 완료");
					} else {	// 결제 실패에 대한 복구 및 상세한 안내. 8월5일 10시31분
						String rollbackFromSql = "update account set balance = balance + ? where account_id = ?";
						pstmt = conn.prepareStatement(rollbackFromSql);
						pstmt.setInt(1, balance);
						pstmt.setString(2, fromAccount);
						
						rollbackResult = pstmt.executeUpdate();
						
						if (rollbackResult == 1) {
							System.out.println("결제 실패: 입금 실패. 출금 복구");
						} else {
							System.out.println("결제 실패: 입금 실패. 출금 복구 실패. 카드사에 연락바랍니다.");
						}
					}
					
				} else {
					System.out.println("결제 실패");
				}
				
			} else {
				System.out.println("잔고가 부족합니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
}
