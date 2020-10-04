package edu.mum.cs.cs425.studentproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan
@SpringBootApplication
public class HopeBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(HopeBankApplication.class, args);
	}

}
