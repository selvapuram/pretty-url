package com.stylight.prettyurl;

import com.stylight.prettyurl.util.DbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PrettyUrlApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(PrettyUrlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DbUtils dbUtils = applicationContext.getBean(DbUtils.class);
		dbUtils.loadDataFromClassPath();
	}
}
