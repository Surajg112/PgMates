package com.pgmates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.pgmates.models.Admin;
import com.pgmates.services.AdminService;
import com.pgmates.utils.FileUploadProperties;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
    FileUploadProperties.class
})
public class PgMatesBackend {
	
	private static final Logger log = LoggerFactory.getLogger(PgMatesBackend.class);

	public static void main(String[] args) {
		SpringApplication.run(PgMatesBackend.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(AdminService srv) {
	    return (args) -> {
	    	if(srv.countAdmin()==0) {
	    		Admin admin=new Admin();
	    		admin.setUserid("admin");
	    		admin.setPwd(srv.encrypt("admin"));
	    		admin.setUname("Administrator");
	    		srv.saveAdmin(admin);
	    		log.info("Admin user created successfully");
	    	}
	    };
	}

}
