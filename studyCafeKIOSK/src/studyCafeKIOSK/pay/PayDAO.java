package studyCafeKIOSK.pay;

import studyCafeKIOSK.common.DAO;

public class PayDAO extends DAO{
	private static PayDAO payDAO = null;

	private PayDAO() {

	}

	public static PayDAO getInstance() {
		return (payDAO == null) ? payDAO = new PayDAO() : payDAO;
	}
	
	
	
	public int updateCard(String memberId, int payPrice) {
		int fromResult = 0;
		int toResult = 0;
		
		try {
			conn();
			String balanceSql = "select balance from account where member_id = ?";
			pstmt = conn.prepareStatement(balanceSql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			int balance = 0;
			if (rs.next()) {
				balance = rs.getInt("balance");
				
				if (balance >= payPrice) {
					String paySql = "update account set balance = balance - ? where member_id = ?";
					pstmt = conn.prepareStatement(paySql);
					pstmt.setInt(1, payPrice);
					pstmt.setString(2, memberId);
					
					fromResult = pstmt.executeUpdate();
					
					if (fromResult == 1) {
						System.out.println();
						 String recieveSql = "update account set balance = balance + ? where member_id = '01036497455'";
						 pstmt = conn.prepareStatement(recieveSql);
						 pstmt.setInt(1, payPrice);
						 
						 toResult = pstmt.executeUpdate();
					} else {
						System.out.println("입금 실패");
					}
				} else {
					System.out.println("잔고 부족");
				}
			} else {
				System.out.println("계좌없을 때 터지는 예외. 처리해야함");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return 0;
	}
}
