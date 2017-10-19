package com.codekraft.cdfipb.recruitementfrontend;

import com.codekraft.cdfipb.recruitementfrontend.domains.User;
import com.codekraft.cdfipb.recruitementfrontend.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class RecruitementFrontendApplication {

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static void main(String[] args) {
		SpringApplication.run(RecruitementFrontendApplication.class, args);
	}

	@Bean
	InitializingBean seedUser(UserRepository repository){
		return () -> {
			repository.deleteAll();
			repository.save(new User("shuaib@codekraft.ng",passwordEncoder.encode("shuaib"), "shuaib Afegbua","ROLE_USER"));
//			repository.save(new User("admin","admin", "fleet admin","ROLE_ADMIN"));
		};
	}

	@GetMapping("/")
	public String index(){
		return "index";
	}

	@GetMapping("/page/1")
	public String index2(){
		return "index";
	}
}
