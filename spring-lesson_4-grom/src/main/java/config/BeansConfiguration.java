package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.Controller;
import com.FileDAO;
import com.FileService;
import com.StorageDAO;
import com.StorageService;

import entity.File;
import entity.Storage;

@Configuration
@ComponentScan(basePackages = {"com"})
public class BeansConfiguration {
	
	@Bean
	public FileDAO fileDao() {
		return new FileDAO(File.class);
	}
	@Bean
	public StorageDAO storageDao() {
		return new StorageDAO(Storage.class);
	}
	@Bean
	public FileService fileService() {
		return new FileService();
	}
	@Bean
	public StorageService storageService() {
		return new StorageService();
	}
	@Bean
	public Controller controller() {
		return new Controller();
	}
		
}
