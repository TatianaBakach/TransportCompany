package by.itacademy.tatabakach.transportcompany.service.test.spring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.itacademy.tatabakach.transportcompany.service.ICarService;
import by.itacademy.tatabakach.transportcompany.service.IDriverService;

public class ServiceSpringContextExample {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceSpringContextExample.class);

	
	public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
        LOGGER.info("ICarService: {}", context.getBean(ICarService.class));
        LOGGER.info("IDriverService: {}", context.getBean(IDriverService.class));
        LOGGER.info("all beans: {}", context.getBeanDefinitionNames());

    }
}