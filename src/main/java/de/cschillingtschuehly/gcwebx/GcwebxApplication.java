package de.cschillingtschuehly.gcwebx;

import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.repositories.ContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
@SpringBootApplication
public class GcwebxApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(GcwebxApplication.class, args);
	}
	@Autowired
	private ContentRepository contentRepository;

	private final Logger logger = LoggerFactory.getLogger(GcwebxApplication.class);

	@Override
	public void run(String... args){
		contentRepository.findById(1L).ifPresentOrElse((content -> logger.info("Welcometitle already asigned")),
				() -> {
					contentRepository.save(new Content(1L, "Welcometitle", "<p>Place the welcometext here in the editor</p>", false, LocalDateTime.now(), LocalDateTime.now()));
					logger.info("welcome does not exist");});
		contentRepository.findById(2L).ifPresentOrElse((content -> logger.info("Imprint already asigned")),
				() -> contentRepository.save(new Content(2L, "Imprint", "<p>Place the imprint here in the editor</p>", false, LocalDateTime.now(),LocalDateTime.now())));

	}

}
