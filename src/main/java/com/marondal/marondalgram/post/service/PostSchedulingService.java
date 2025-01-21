package com.marondal.marondalgram.post.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PostSchedulingService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
//	@Scheduled(fixedDelay=5000)
//  crontab
//  초  분 시  일 월 요일
	@Scheduled(cron="*/5 * * * * *")
	public void scheduleTest() {
		
		logger.info("스케쥴링 실행!@!!!!!!!!!!!!!");
	}

}
