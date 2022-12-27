package andrey.shalya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SoaTestCloudAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoaTestCloudAppApplication.class, args);
	}

}
