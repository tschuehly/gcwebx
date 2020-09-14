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

		contentRepository.save(new Content(1, "Willkommen bei Xperience Gaming", "<p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</p>", false, LocalDateTime.parse("2020-07-02T10:51:48.283"),  LocalDateTime.parse("2020-07-09T11:55:56.135")));
		contentRepository.save(new Content(2, "Aufstieg für Silentpeak ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.", true, LocalDateTime.parse("2020-07-02T10:53:48.283"),  LocalDateTime.parse("2020-07-02T11:46:56.135")));
		contentRepository.save(new Content(4, "Welcome Zero21by9", "<p>Ein neuer <em>Streamer</em> wagt sich an die Xperience Community ran!!</p>", true, LocalDateTime.parse("2020-07-02T10:44:38.283"),  LocalDateTime.parse("2020-07-02T11:22:54.135")));
		contentRepository.save(new Content(5, "News5", "<p>test5</p>", true, LocalDateTime.parse("2020-07-02T10:44:37.283"),  LocalDateTime.parse("2020-07-02T10:13:24.135")));
		contentRepository.save(new Content(6, "News6", "<p>test6</p>", true, LocalDateTime.parse("2020-07-02T10:42:36.283"),  LocalDateTime.parse("2020-07-02T09:12:34.135")));

		teamRepository.deleteAll();

		userRepository.save(new WebsiteUser("user","$2y$12$g.0oBcNDKnbNzJRD16a/ZeQFAERFRb3Wv2mNISLiRf7KBQmNJXR36",true,false,false,false,false));
		userRepository.save(new WebsiteUser("admin","$2y$12$D2XjQoR1K/b.1nPdjenPzezMMlQ69l6kDkSkb12l5M3S.RTER.ozC",true,false,false,false,true));
		userRepository.save(new WebsiteUser("editor","$2y$12$U1m4vvqWq6zyyK/1zT7QoOmM9ha9wQ06SoASuuypfkElMKksubDJu",true,true,false,false,false));
		userRepository.save(new WebsiteUser("support","$2y$12$f3mE0G1qwzxGpqmQLVqUMuwmDfePU0d4wHFRqmp2hXxR.ckm/Ic.u",true,false,true,false,false));
		userRepository.save(new WebsiteUser("moderator","$2y$12$4zdceAF0Q95ot.TeaXqEzubqITfpIgvXNS02qKylWrDkIHzE0nTQm",true,false,false,true,false));
		memberRepository.deleteAll();

		memberRepository.save(new Member("Professor","twitter.com","youtube.com","ash.jpg"));
		memberRepository.save(new Member("Klatschko","twitter.com","youtube.com","iq.jpg"));
		memberRepository.save(new Member("Cytex","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Fellow","twitter.com","youtube.com","rook.jpg"));
		memberRepository.save(new Member("Name5","twitter.com","youtube.com","sage.png"));
		memberRepository.save(new Member("Name6","twitter.com","youtube.com","raze.png"));
		memberRepository.save(new Member("Name7","twitter.com","youtube.com","sage.png"));
		memberRepository.save(new Member("Name8","twitter.com","youtube.com","sage.png"));
		memberRepository.save(new Member("Name9","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Name10","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Name11","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Name12","twitter.com","youtube.com","mute.jpg"));
		memberRepository.save(new Member("Thaiminater",  "https://twitter.com/Thaiminater" , "https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg" ,"zofia.jpg" ));
		memberRepository.save(new Member("T0XIC_BABE",  "https://twitter.com/Thaiminater" , "https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg" ,"sledge.jpg") );
		memberRepository.save(new Member("Trypled",  "https://twitter.com/Thaiminater", "https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg","ash.jpg"));
		memberRepository.save(new Member("SimpleElite",  "https://twitter.com/Thaiminater", "https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg","iq.jpg"));
		memberRepository.save(new Member("Sucuk",  "https://twitter.com/Thaiminater", "https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg","jackal.jpg"));
		memberRepository.save(new Member("ORO",  "https://twitter.com/Thaiminater", "https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg","ace.jpg"));

		final List<Member> members = new ArrayList<>();
		memberRepository.findById(213L).ifPresent(member -> members.add(member));
		memberRepository.findById(214L).ifPresent(member -> members.add(member));
		memberRepository.findById(215L).ifPresent(member -> members.add(member));
		memberRepository.findById(216L).ifPresent(member -> members.add(member));
		memberRepository.findById(217L).ifPresent(member -> members.add(member));
		teamRepository.save(new Team(2L,"Silentpeak","rainbowsix","Das Team spielt in der DESBL Advanced League",members));
		members.clear();

		memberRepository.findById(201L).ifPresent(member -> members.add(member));
		memberRepository.findById(202L).ifPresent(member -> members.add(member));
		memberRepository.findById(203L).ifPresent(member -> members.add(member));
		memberRepository.findById(204L).ifPresent(member -> members.add(member));

		teamRepository.save(new Team(1L,"Xperience","rainbowsix","Das Team spielt in der Ascencion League Div.2",members));memberRepository.findById(201L).ifPresent(member -> members.add(member));
		members.clear();
		memberRepository.findById(205L).ifPresent(member -> members.add(member));
		memberRepository.findById(206L).ifPresent(member -> members.add(member));
		memberRepository.findById(207L).ifPresent(member -> members.add(member));
		memberRepository.findById(208L).ifPresent(member -> members.add(member));
		memberRepository.findById(209L).ifPresent(member -> members.add(member));
		memberRepository.findById(210L).ifPresent(member -> members.add(member));
		teamRepository.save(new Team(3L,"xp.exe","valorant","",members));
		/*System.out.println(teamRepository.findAll());*/



	}

}
