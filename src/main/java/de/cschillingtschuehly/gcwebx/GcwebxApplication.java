package de.cschillingtschuehly.gcwebx;

import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.modell.User;
import de.cschillingtschuehly.gcwebx.repositories.ContentRepository;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import de.cschillingtschuehly.gcwebx.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

@SpringBootApplication
public class GcwebxApplication implements CommandLineRunner{
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContentRepository contentRepository;
	@Autowired MemberRepository deletedMemberRepository;
	public static void main(String[] args) {
		SpringApplication.run(GcwebxApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		userRepository.deleteAll();
/*
		contentRepository.deleteAll();
		contentRepository.save(new Content(1, "<p>test1</p>"));
		contentRepository.save(new Content(2, "<p>test2</p>"));
*/

		userRepository.save(new User("user","$2y$12$g.0oBcNDKnbNzJRD16a/ZeQFAERFRb3Wv2mNISLiRf7KBQmNJXR36",true,false,false,false,false));
		userRepository.save(new User("admin","$2y$12$D2XjQoR1K/b.1nPdjenPzezMMlQ69l6kDkSkb12l5M3S.RTER.ozC",true,false,false,false,true));

	}

}
