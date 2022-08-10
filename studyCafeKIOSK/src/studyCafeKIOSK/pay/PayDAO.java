package studyCafeKIOSK.pay;

import studyCafeKIOSK.common.DAO;
import studyCafeKIOSK.order.Order;

public class PayDAO extends DAO {
	private static PayDAO payDAO = null;

	private PayDAO() {

	}

	public static PayDAO getInstance() {
		return (payDAO == null) ? payDAO = new PayDAO() : payDAO;
	}

	int sequence = 0;

	// 티켓 카드 결제(회원돈 -> 사장)
	public int orderUpdateCard(String memberId, int payPrice, Order order) {
		int fromResult = 0;
		int toResult = 0;

		try {
			conn();
			String balanceSql = "select balance from member where member_id = ?";
			pstmt = conn.prepareStatement(balanceSql);
			pstmt.setString(1, memberId);

			rs = pstmt.executeQuery();

			int balance = 0;
			if (rs.next()) {
				balance = rs.getInt("balance");

				if (balance >= payPrice) {
					String paySql = "update member set balance = balance - ? where member_id = ?";
					pstmt = conn.prepareStatement(paySql);
					pstmt.setInt(1, payPrice);
					pstmt.setString(2, memberId);

					fromResult = pstmt.executeUpdate();

					if (fromResult == 1) {
						System.out.println();
						String recieveSql = "update member set balance = balance + ? where member_id = '01036497455'";
						pstmt = conn.prepareStatement(recieveSql);
						pstmt.setInt(1, payPrice);

						toResult = pstmt.executeUpdate();

						// 결제 기록(승인번호 생성)
						if (toResult == 1) {
							insertPayLog(memberId, payPrice, order);
							
						}
					} else {
						System.out.println("입금 실패");
					}
				} else {
					System.out.println("잔고 부족");
				}
			} else {
				System.out.println("해당 회원이 없거나 로그인 안 함.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return 0;
	}

	// 결제취소
	public int orderUpdateCardCancel(String memberId, int payPrice, Order order) {
		int fromResult = 0;
		int toResult = 0;

		try {
			conn();
			String balanceSql = "select balance from member where member_id = ?";
			pstmt = conn.prepareStatement(balanceSql);
			pstmt.setString(1, memberId);

			rs = pstmt.executeQuery();

			int balance = 0;
			if (rs.next()) {
				balance = rs.getInt("balance");

				if (balance >= payPrice) {
					String paySql = "update member set balance = balance - ? where member_id = ?";
					pstmt = conn.prepareStatement(paySql);
					pstmt.setInt(1, payPrice);
					pstmt.setString(2, memberId);

					fromResult = pstmt.executeUpdate();

					if (fromResult == 1) {
						System.out.println();
						String recieveSql = "update member set balance = balance + ? where member_id = '01036497455'";
						pstmt = conn.prepareStatement(recieveSql);
						pstmt.setInt(1, payPrice);

						toResult = pstmt.executeUpdate();

						if (toResult == 1) {
							insertPayLog(memberId, payPrice, order);
							
						}
					} else {
						System.out.println("입금 실패");
					}
				} else {
					System.out.println("잔고 부족");
				}
			} else {
				System.out.println("해당 회원이 없거나 로그인 안 함.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return 0;
	}
	
	// 임의 결제(회원돈 -> 사장)
	public int tempUpdateCard(String memberId, int payPrice) {
		int fromResult = 0;
		int toResult = 0;

		try {
			conn();
			String balanceSql = "select balance from member where member_id = ?";
			pstmt = conn.prepareStatement(balanceSql);
			pstmt.setString(1, memberId);

			rs = pstmt.executeQuery();

			int balance = 0;
			if (rs.next()) {
				balance = rs.getInt("balance");

				if (balance >= payPrice) {
					String paySql = "update member set balance = balance - ? where member_id = ?";
					pstmt = conn.prepareStatement(paySql);
					pstmt.setInt(1, payPrice);
					pstmt.setString(2, memberId);

					fromResult = pstmt.executeUpdate();

					if (fromResult == 1) {
						System.out.println();
						String recieveSql = "update member set balance = balance + ? where member_id = '01036497455'";
						pstmt = conn.prepareStatement(recieveSql);
						pstmt.setInt(1, payPrice);

						toResult = pstmt.executeUpdate();

						// 결제 기록(승인번호 생성)
						if (toResult == 1) {
							Order order = new Order();
							order.setOrderId("");
							order.setPayment(1);
							insertPayLog(memberId, payPrice, order);
						}

					} else {
						System.out.println("입금 실패");
					}
				} else {
					System.out.println("잔고 부족");
				}
			} else {
				System.out.println("해당 회원이 없거나 로그인 안 함.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return 0;
	}

	public void insertPayLog(String memberId, int payPrice, Order order) {
		Pay pay = new Pay();

		pay.setPayId(++sequence);
		pay.setOrderId(order.getOrderId());
		pay.setPayment(order.getPayment());
		pay.setPayPrice(payPrice);
		pay.setFromMemberId(memberId);
		pay.setToMemberId("01036497455");

		int result = 0;

		try {
			conn();
			String sql = "insert into pay values (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pay.getPayId());
			pstmt.setString(2, pay.getOrderId());
			pstmt.setInt(3, pay.getPayment());
			pstmt.setInt(4, pay.getPayPrice());
			pstmt.setString(5, pay.getFromMemberId());
			pstmt.setString(6, pay.getToMemberId());

			result = pstmt.executeUpdate();
			
			if (result == 1) {
				System.out.println("승인");
			} else {
				System.out.println("승인 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}
	
	public String paymentToString(int payment) {
		return payment == 1 ? "카드" : "시간차감";
	}
	
	public Order choosePayment(Order order) {
		System.out.println("1. 카드 | 2. 카카오페이(개발예정)");
		System.out.print("결제 수단을 선택하세요.> ");
		int payment = Integer.parseInt(sc.nextLine());

		System.out.println("------- 결제할 내역 --------");
		System.out.println("결제할 수단: " + paymentToString(payment));
		System.out.println("결제할 금액: " + order.getTicketPrice());

		System.out.println("결제하시겠습니까?");
		System.out.print(" Y / N 선택> ");
		String payConfirm = sc.nextLine();
		
		if (payConfirm.equalsIgnoreCase("y")) {
			order.setPayment(1);
		}
		
		return order;
		
	}

}
