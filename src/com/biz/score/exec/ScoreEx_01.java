package com.biz.score.exec;

import com.biz.score.service.ScoreService;
import com.biz.score.service.ScoreServiceV1;

public class ScoreEx_01 {
	
	public static void main(String[] args) {
		
		ScoreService scoreService=new ScoreServiceV1();
		
		while(true) {
			if (!scoreService.scoreInput()) {
				break;
			}
		}
		
		scoreService.scoreList();
		
	}

}
