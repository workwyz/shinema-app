package cn.shinema.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "cn.shinema")
@EntityScan(basePackages = { "cn.shinema" })
@EnableJpaRepositories(basePackages = { "cn.shinema" })
public class App extends SpringBootServletInitializer implements CommandLineRunner {
//	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		System.setProperty("org.jboss.logging.provider", "slf4j");
		System.setProperty("dubbo.application.logger", "slf4j");

		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		LOGGER.info("Your application started with option names : {}", args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.context().registerShutdownHook();

		return application.sources(App.class);
	}

}