package de.cschillingtschuehly.gcwebx;

import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
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
	public static void main(String[] args) {
		SpringApplication.run(GcwebxApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		memberRepository.deleteAll();
		memberRepository.save(new Member("Apu","d9ujps1UeZNwOaHFbo=",LocalDate.of(1970, Month.JANUARY,1),LocalDate.of(2018, Month.SEPTEMBER,15)));
		memberRepository.save(new Member("Dave","ucJeEsgVuJgimq0=", LocalDate.of(1980, Month.JANUARY,15),LocalDate.of(2018, Month.DECEMBER,15)));
		memberRepository.save(new Member("Cytex","hierstehteinecooleTeamspeakID", LocalDate.of(1990, Month.JANUARY,30),LocalDate.of(2019, Month.JANUARY,1)));
	}

}
