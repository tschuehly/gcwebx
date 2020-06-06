package de.cschillingtschuehly.gcwebx;

import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.modell.User;
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
	@Autowired MemberRepository deletedMemberRepository;
	public static void main(String[] args) {
		SpringApplication.run(GcwebxApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		memberRepository.deleteAll();
		memberRepository.save(new Member("Apu","d9ujps1UeZNwOaHFbo=",LocalDate.of(1970, Month.JANUARY,1),LocalDate.of(2018, Month.SEPTEMBER,15)));
		memberRepository.save(new Member("Dave","ucJeEsgVuJgimq0=", LocalDate.of(1980, Month.JANUARY,15),LocalDate.of(2018, Month.DECEMBER,15)));
		memberRepository.save(new Member("Cytex","hierstehteinecooleTeamspeakID", LocalDate.of(1990, Month.JANUARY,30),LocalDate.of(2019, Month.JANUARY,1)));
		userRepository.deleteAll();
		userRepository.save(new User("user","$2y$12$g.0oBcNDKnbNzJRD16a/ZeQFAERFRb3Wv2mNISLiRf7KBQmNJXR36","ROLE_USER"));
		userRepository.save(new User("admin","$2y$12$D2XjQoR1K/b.1nPdjenPzezMMlQ69l6kDkSkb12l5M3S.RTER.ozC","ROLE_ADMIN"));
	}

}
