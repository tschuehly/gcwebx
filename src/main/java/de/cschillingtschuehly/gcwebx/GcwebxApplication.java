package de.cschillingtschuehly.gcwebx;

import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.modell.Team;
import de.cschillingtschuehly.gcwebx.modell.WebsiteUser;
import de.cschillingtschuehly.gcwebx.repositories.ContentRepository;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import de.cschillingtschuehly.gcwebx.repositories.TeamRepository;
import de.cschillingtschuehly.gcwebx.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GcwebxApplication implements CommandLineRunner{
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContentRepository contentRepository;
	@Autowired
	private
	TeamRepository teamRepository;
	public static void main(String[] args) {
		SpringApplication.run(GcwebxApplication.class, args);
	}
	@Override
	public void run(String... args){
		userRepository.deleteAll();
		contentRepository.deleteAll();

		contentRepository.save(new Content(1, "Willkommen bei Xperience Gaming", "<p>Unser Clan hat sich entschlossen eine Community zugründen die nicht ist wie jede andere. Bei uns hast <strong>DU </strong>das sagen.</p><p>Weder gibt es einen Diktator der alles tut was er für richtig hält, noch eine Clanführung die nur ihren Willen durchsetzten möchte.</p><p>Hier hat die Community die Macht um etwas zu bewegen und zu ändern. Xperience´s Maingame ist zurzeit Rainbow Six Siege.</p><p>Dennoch sind wir auch für andere Spiele zuhaben und können uns in anderen Kategorien weiter entwickeln.</p><p>Jeder Gamer der sich älter als 18 Jahre bezeichnen kann, darf sich jeder Zeit anschließen um etwas großes zu bewirken. Elo und Skill sind Nebensache.</p><p>Sobald du dich angesprochen fühlst, besuch uns doch einfach auf unserem Teamspeak um genaueres zu erfahren.</p><p><br></p><p>Wir warten auf dich!!</p>", false, LocalDateTime.parse("2020-07-02T10:51:48.283"),  LocalDateTime.parse("2020-07-09T11:55:56.135")));
		contentRepository.save(new Content(2, "Aufstieg für Silentpeak ", "<em>Silentpeak steigt in die Advanced league auf!!</em>", true, LocalDateTime.parse("2020-07-02T10:53:48.283"),  LocalDateTime.parse("2020-07-02T11:46:56.135")));
		contentRepository.save(new Content(4, "Welcome Zero21by9", "<p>Ein neuer <em>Streamer</em> wagt sich an die Xperience Community ran!!</p>", true, LocalDateTime.parse("2020-07-02T10:44:38.283"),  LocalDateTime.parse("2020-07-02T11:22:54.135")));
		contentRepository.save(new Content(5, "News5", "<p>test5</p>", true, LocalDateTime.parse("2020-07-02T10:44:37.283"),  LocalDateTime.parse("2020-07-02T10:13:24.135")));
		contentRepository.save(new Content(6, "News6", "<p>test6</p>", true, LocalDateTime.parse("2020-07-02T10:42:36.283"),  LocalDateTime.parse("2020-07-02T09:12:34.135")));

		teamRepository.deleteAll();

		userRepository.save(new WebsiteUser("user","$2y$12$g.0oBcNDKnbNzJRD16a/ZeQFAERFRb3Wv2mNISLiRf7KBQmNJXR36",true,false,false,false,false));
		userRepository.save(new WebsiteUser("admin","$2y$12$D2XjQoR1K/b.1nPdjenPzezMMlQ69l6kDkSkb12l5M3S.RTER.ozC",true,false,false,false,true));
		userRepository.save(new WebsiteUser("editor","$2y$12$U1m4vvqWq6zyyK/1zT7QoOmM9ha9wQ06SoASuuypfkElMKksubDJu",true,true,false,false,false));
		userRepository.save(new WebsiteUser("support","$2y$12$f3mE0G1qwzxGpqmQLVqUMuwmDfePU0d4wHFRqmp2hXxR.ckm/Ic.u",true,false,true,false,false));
		userRepository.save(new WebsiteUser("moderator","$2y$12$4zdceAF0Q95ot.TeaXqEzubqITfpIgvXNS02qKylWrDkIHzE0nTQm",true,false,false,true,false));


		memberRepository.save(new Member("Name1","twitter.com","youtube.com","ash.jpg"));
		memberRepository.save(new Member("Name2","twitter.com","youtube.com","iq.jpg"));
		memberRepository.save(new Member("Name3","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Name4","twitter.com","youtube.com","blitz.jpg"));
		memberRepository.save(new Member("Name5","twitter.com","youtube.com","blackbeard.jpg"));
		memberRepository.save(new Member("Name6","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Name7","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Name8","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Name9","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Name10","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Name11","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Name12","twitter.com","youtube.com","mute.jpg"));
		final List<Member> members = new ArrayList<>();
		memberRepository.findById(201L).ifPresent(member -> members.add(member));
		memberRepository.findById(202L).ifPresent(member -> members.add(member));
		memberRepository.findById(203L).ifPresent(member -> members.add(member));
		teamRepository.save(new Team(1L,"Silentpeak","rainbowsix","",members));
		members.clear();

		memberRepository.findById(204L).ifPresent(member -> members.add(member));
		memberRepository.findById(205L).ifPresent(member -> members.add(member));
		memberRepository.findById(206L).ifPresent(member -> members.add(member));

		teamRepository.save(new Team(2L,"xp.exe","valorant","",members));
		/*System.out.println(teamRepository.findAll());*/



	}

}
