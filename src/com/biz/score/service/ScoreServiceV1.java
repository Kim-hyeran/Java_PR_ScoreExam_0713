package com.biz.score.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.score.config.Position;
import com.biz.score.domain.ScoreVO;

public class ScoreServiceV1 implements ScoreService {
	
	List<ScoreVO> scoreList;
	
	Scanner scan;
	FileReader fileReader;
	BufferedReader buffer;
	
	String scoreFile="";
	
	public ScoreServiceV1() {
		scoreList=new ArrayList<ScoreVO>();
		scan=new Scanner(System.in);		
		scoreFile="src/com/biz/score/data/score.txt";
		
		this.loadScore();
	}
	
	@Override
	public void loadScore() {
		scoreList.clear();
		
		try {
			fileReader=new FileReader(scoreFile);
			buffer=new BufferedReader(fileReader);
			
			String reader="";
			
			while(true) {
				reader=buffer.readLine();
				
				if(reader==null) {
					break;
				}
				
				String[] scores=reader.split(",");
				
				ScoreVO scoreVO=new ScoreVO();
				scoreVO.setNum(scores[Position.SCORE_NUM]);
				scoreVO.setKor(Integer.valueOf(scores[Position.SCORE_KOR]));
				scoreVO.setEng(Integer.valueOf(scores[Position.SCORE_ENG]));
				scoreVO.setMath(Integer.valueOf(scores[Position.SCORE_MATH]));
				scoreVO.setMusic(Integer.valueOf(scores[Position.SCORE_MUSIC]));
				
				scoreList.add(scoreVO);
			}
			buffer.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("성적 정보가 존재하지 않습니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean scoreInput() {
		System.out.print("학번(입력 종료:END)>> ");
		String strNum=scan.nextLine();
		if(strNum.equals("END")) {
			return false;
		}
		
		int intNum=0;
		try {
			intNum=Integer.valueOf(strNum);
		} catch (Exception e) {
			System.out.println("학번은 숫자로만 입력할 수 있습니다.\n");
			return true;
		}
		if(intNum<1||intNum>99999) {
			System.out.println("학번은 1부터 최대 99999까지 입력할 수 있습니다.\n");
			return true;
		}
		strNum=String.format("%05d", intNum);
		
		for(ScoreVO scoreVO:scoreList) {
			if(scoreVO.getNum().equals(strNum)) {
				System.out.println(strNum+"번 학생의 성적이 이미 등록되어 있습니다.\n");
				return true;
			}
		}
		
		System.out.print("국어(입력 종료:END)>> ");
		String strKor=scan.nextLine();
		int intKor=0;
		try {
			intKor=Integer.valueOf(strKor);
		} catch (Exception e) {
			System.out.println("점수는 숫자로만 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하십시오.");
			return true;
		}
		if(intKor<0||intKor>100) {
			System.out.println("점수는 0점부터 100점까지 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하십시오.");
			return true;
		}
		
		System.out.print("영어(입력 종료:END)>> ");
		String strEng=scan.nextLine();
		int intEng=0;
		try {
			intEng=Integer.valueOf(strEng);
		} catch (Exception e) {
			System.out.println("점수는 숫자로만 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하십시오.");
			return true;
		}
		if(intEng<0||intEng>100) {
			System.out.println("점수는 0점부터 100점까지 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하십시오.");
			return true;
		}
		
		System.out.print("수학(입력 종료:END)>> ");
		String strMath=scan.nextLine();
		int intMath=0;
		try {
			intMath=Integer.valueOf(strMath);
		} catch (Exception e) {
			System.out.println("점수는 숫자로만 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하십시오.");
			return true;
		}
		if(intMath<0||intMath>100) {
			System.out.println("점수는 0점부터 100점까지 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하십시오.");
			return true;
		}
		
		System.out.print("음악(입력 종료:END)>> ");
		String strMusic=scan.nextLine();
		int intMusic=0;
		try {
			intMusic=Integer.valueOf(strMusic);
		} catch (Exception e) {
			System.out.println("점수는 숫자로만 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하십시오.");
			return true;
		}
		if(intMusic<0||intMusic>100) {
			System.out.println("점수는 0점부터 100점까지 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하십시오.");
			return true;
		}
		
		ScoreVO scoreVO=new ScoreVO();
		scoreVO.setNum(strNum);
		scoreVO.setKor(intKor);
		scoreVO.setEng(intEng);
		scoreVO.setMath(intMath);
		scoreVO.setMusic(intMusic);
		
		scoreList.add(scoreVO);
		
		this.scoreSave();
		
		return true;
	}

	@Override
	public void scoreSave() {
		String scoreFile="src/com/biz/score/data/score.txt";
		
		PrintStream outPut=null;
		
		try {
			outPut=new PrintStream(scoreFile);
			
			for (ScoreVO scoreVO:scoreList) {
				outPut.printf("%s,%d,%d,%d,%d\n",
						scoreVO.getNum(), scoreVO.getKor(), scoreVO.getEng(), scoreVO.getMath(), scoreVO.getMusic());
			}
			outPut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일을 찾을 수 없습니다.");
		}
		
	}
	
	@Override
	public void calcSum() {
		int sum=0;
		
		for(ScoreVO scoreVO:scoreList) {
			sum=scoreVO.getKor()+scoreVO.getEng()+scoreVO.getMath()+scoreVO.getMusic();
			
			scoreVO.setSum(sum);
		}
	}
	
	@Override
	public void calcAvg() {
		float avg=0;
		
		for(ScoreVO scoreVO:scoreList) {
			avg=(float)scoreVO.getSum()/4;
			
			scoreVO.setAvg(avg);
		}
	}

	@Override
	public void scoreList() {
		this.calcSum();
		this.calcAvg();
		
		int korSum=0;
		int engSum=0;
		int mathSum=0;
		int musicSum=0;
		int sumSum=0;
		float avgAvg=0;
		
		int size=scoreList.size();
		
		System.out.println("\t\t\t<성적일람표>");
		System.out.println("================================================================");
		System.out.println("학번\t\t국어\t영어\t수학\t음악\t총점\t평균");
		System.out.println("----------------------------------------------------------------");
		
		
		for (ScoreVO scoreVO:scoreList) {
			System.out.print(scoreVO.getNum()+"\t\t");
			System.out.print(scoreVO.getKor()+"\t");
			System.out.print(scoreVO.getEng()+"\t");
			System.out.print(scoreVO.getMath()+"\t");
			System.out.print(scoreVO.getMusic()+"\t");
			System.out.print(scoreVO.getSum()+"\t");
			System.out.printf("%5.2f\n", scoreVO.getAvg());
		}
		
		for (ScoreVO scoreVO:scoreList) {
			korSum+=scoreVO.getKor();
			engSum+=scoreVO.getEng();
			mathSum+=scoreVO.getMath();
			musicSum+=scoreVO.getMusic();
			sumSum+=scoreVO.getSum();
			avgAvg+=scoreVO.getAvg();
		}
		
		System.out.println("----------------------------------------------------------------");
		System.out.print("과목총점\t");
		System.out.print(korSum+"\t");
		System.out.print(engSum+"\t");
		System.out.print(mathSum+"\t");
		System.out.print(musicSum+"\t");
		System.out.println(sumSum);
		
		System.out.print("과목평균\t");
		System.out.printf("%5.2f\t",(float)korSum/size);
		System.out.printf("%5.2f\t",(float)engSum/size);
		System.out.printf("%5.2f\t",(float)mathSum/size);
		System.out.printf("%5.2f\t\t",(float)musicSum/size);
		System.out.printf("%5.2f\n",avgAvg/size);
		System.out.println("================================================================");

	}

}
