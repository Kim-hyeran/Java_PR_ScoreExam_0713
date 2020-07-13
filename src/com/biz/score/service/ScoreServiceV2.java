package com.biz.score.service;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import com.biz.score.domain.ScoreVO;

public class ScoreServiceV2 extends ScoreServiceV1 {
	
	public ScoreServiceV2() {
		String listFile="src/com/biz/score/data/scoreList.txt";
		
		try {
			outPut=new PrintStream(listFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void scoreList() {
		this.calcSum();
		this.calcAvg();
		
		outPut.println("<성적일람표>");
		outPut.println("================================================================");
		outPut.println("학번\t\t국어\t영어\t수학\t음악\t총점\t평균");
		outPut.println("----------------------------------------------------------------");
		
		for (ScoreVO scoreVO:scoreList) {
			outPut.print(scoreVO.getNum()+"\t\t");
			outPut.print(scoreVO.getKor()+"\t");
			outPut.print(scoreVO.getEng()+"\t");
			outPut.print(scoreVO.getMath()+"\t");
			outPut.print(scoreVO.getMusic()+"\t");
			outPut.print(scoreVO.getSum()+"\t");
			outPut.printf("%5.2f\n", scoreVO.getAvg());
		}
		
		outPut.println("================================================================");
		
		outPut.close();
	}

}
