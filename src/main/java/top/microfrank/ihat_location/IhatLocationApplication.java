package top.microfrank.ihat_location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.ArrayList;

@SpringBootApplication
public class IhatLocationApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(IhatLocationApplication.class, args);
	}

	@Autowired
	Myproject myproject;

	@Override
	public void run(String... strings) throws Exception {
		myproject.execute();
	}
}
