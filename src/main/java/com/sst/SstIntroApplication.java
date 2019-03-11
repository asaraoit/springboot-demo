package com.sst;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SstIntroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SstIntroApplication.class, args);
		log.debug("11111");
	}
}
