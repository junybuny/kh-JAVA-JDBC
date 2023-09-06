package com.kh.view;

import java.util.Scanner;
import com.kh.controller.MemberController;

// View : 사용자가 보게 될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {
	
	// Scanner 객체 생성(전역적으로 다 입력받을 수 있도록)
	private Scanner sc = new Scanner(System.in);
	
	// MemberController 객체 생성(전역에서 바로 요청할 수 있도록)
	private MemberController mc = new MemberController();
	
	/**
	 * 사용자가 보게될 첫 화면(메인화면)
	 */
	public void mainMenu() {
		
		while(true) {
	
			System.out.println("\n== 회원관리 프로그램 ==");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디 검색");
			System.out.println("4. 회원 이름으로 키워드 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴"); 
			System.out.println("0. 프로그램 종료");
			
			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: 
				inputMember();
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
			case 0:
				System.out.println("이용해주셔서 감사합니다. 프로그램을 종료합니다.");
				return;
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
			
			
		}
		
	}

	/**
	 * 회원 추가 창(서브화면) 죽, 추가하고자 하는 회원의 정보를 입력받아 회원을 추가요청하는 창
	 */
	private void inputMember() {
		System.out.print("\n=== 회원 추가 ===");

		System.out.print("\n아이디 : ");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();

		System.out.print("성별(M/F) : ");
		String gender = sc.nextLine().toUpperCase();	
		
		System.out.print("나이 : ");
		String age = sc.nextLine(); // "20"
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호(-빼고입력) : ");
		String phone = sc.nextLine();
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		System.out.print("취미 : ");
		String hobby = sc.nextLine();

		// 회원 추가 요청 == Controller메서드 요청
		mc.insertMember(userId, userPwd, userName, gender,
						age, email, phone, address, hobby);
		
	}

	//-------------------- 응답화면 --------------------
	/**
	 * 서비스 요청 처리 후 성공했을 경우 사용자가 보게될 응답 화면
	 * @param message : 객체별 성공메세지
	 */
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	/**
	 * 서비스 요청 처리 후 실패했을 경우 사용자가 보게될 응답화면
	 * @param message : 객체별 실패메세지
	 */
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}

	
	
	
	
	
	
	
	
	
}
