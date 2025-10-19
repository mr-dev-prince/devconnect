package com.devconnect.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.devconnect")
public class DevConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevConnectApplication.class, args);

//        ThreadingDemo.simulate();
	}

    // Writing controller gives 404 Not found response

    // Here’s why it happens: when you put @RestController directly on the main application class, sometimes
    // Spring doesn’t register it as part of the component scan if the package structure shifts or gets nested deeper
    // (like when you added model, service, threading packages).

    //    @GetMapping("/") // creating root GET api
    //    public String home(){
    //        return "DevConnect backend is running";
    //    }

}
