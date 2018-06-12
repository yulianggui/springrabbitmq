package org.zhegui.test.springbootrabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zhegui.test.springbootrabbitmq.init.QueueInitializer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class RabbitMQApplication implements CommandLineRunner{
	
	@Autowired
	private QueueInitializer queueInitializer;
	
    public static void main( String[] args )
    {
    	SpringApplication.run(RabbitMQApplication.class, args);
    }

	public void run(String... arg0) throws Exception {
		
		queueInitializer.init();
	}
}
