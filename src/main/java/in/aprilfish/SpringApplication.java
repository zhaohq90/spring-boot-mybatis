package in.aprilfish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@MapperScan("in.aprilfish.mapper")
@SpringBootApplication
public class SpringApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
	}

}
