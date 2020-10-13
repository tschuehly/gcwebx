package de.cschillingtschuehly.gcwebx;

import de.cschillingtschuehly.gcwebx.modell.*;
import de.cschillingtschuehly.gcwebx.repositories.*;
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
	@Override
	public void run(String... args){
		contentRepository.findById(1).ifPresentOrElse((content -> System.out.println("Welcometitle already asigned")),
				() -> {contentRepository.save(new Content(1, "Welcometitle", "<p>Place the welcometext here in the editor</p>", false, LocalDateTime.now(), LocalDateTime.now()));
					System.out.println("welcome does not exist");});
		contentRepository.findById(2).ifPresentOrElse((content -> System.out.println("Imprint already asigned")),
				() -> contentRepository.save(new Content(2, "Imprint", "<p>Place the imprint here in the editor</p>", false, LocalDateTime.now(),LocalDateTime.now())));

	}

}
