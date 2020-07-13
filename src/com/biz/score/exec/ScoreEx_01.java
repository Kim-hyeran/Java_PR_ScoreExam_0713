package com.biz.score.exec;

import java.util.Scanner;

import com.biz.score.config.Position;
import com.biz.score.service.ScoreService;
import com.biz.score.service.ScoreServiceV1;
import com.biz.score.service.ScoreServiceV2;

public class ScoreEx_01 {
	
	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		ScoreService scoreServiceV1=new ScoreServiceV1();
		ScoreService scoreServiceV2=new ScoreServiceV2();
		
		while(true) {
			System.out.println("==============================");
			System.out.println("<성적 관리 시스템 v1>");
			System.out.println("==============================");
			System.out.println("1. 성적 입력");
			System.out.println("2. 성적일람표 출력");
			System.out.println("3. 성적일람표 파일 저장");
			System.out.println("------------------------------");
			System.out.println("END. 업무 종료");
			System.out.println("==============================");
			
			System.out.print("업무 선택>> ");
			String stMenu=scan.nextLine();
			if(stMenu.equals("END")) {
				break;
			}
			
			int intMenu=0;
			try {
				intMenu=Integer.valueOf(stMenu);
			} catch (Exception e) {
				System.out.println("메뉴는 숫자로만 선택할 수 있습니다.");
				continue;
			}
			
			if(intMenu==Position.성적입력) {
				while(true) {
					if(!scoreServiceV1.scoreInput()) {
						break;
					}
				}
			} else if (intMenu==Position.성적출력) {
				scoreServiceV1.scoreList();
			} else if (intMenu==Position.성적저장) {
				scoreServiceV2.scoreList();
			}
		}
		System.out.println("업무 종료");
		
	}

}
