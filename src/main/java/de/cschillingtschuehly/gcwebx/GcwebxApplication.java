package de.cschillingtschuehly.gcwebx;

import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.modell.Team;
import de.cschillingtschuehly.gcwebx.modell.User;
import de.cschillingtschuehly.gcwebx.repositories.ContentRepository;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import de.cschillingtschuehly.gcwebx.repositories.TeamRepository;
import de.cschillingtschuehly.gcwebx.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class GcwebxApplication implements CommandLineRunner{
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContentRepository contentRepository;
	@Autowired MemberRepository deletedMemberRepository;
	@Autowired
	TeamRepository teamRepository;
	public static void main(String[] args) {
		SpringApplication.run(GcwebxApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		userRepository.deleteAll();



		contentRepository.deleteAll();
		contentRepository.save(new Content(1, "Willkommen bei Xperience Gaming", "<h3>Herzlich Willkommen bei Xperience!</h3><p>Unser Clan hat sich entschlossen eine Community zugründen die nicht ist wie jede andere. Bei uns hast <strong>DU </strong>das sagen.</p><p>Weder gibt es einen Diktator der alles tut was er für richtig hält, noch eine Clanführung die nur ihren Willen durchsetzten möchte.</p><p>Hier hat die Community die Macht um etwas zu bewegen und zu ändern. Xperience´s Maingame ist zurzeit Rainbow Six Siege.</p><p>Dennoch sind wir auch für andere Spiele zuhaben und können uns in anderen Kategorien weiter entwickeln.</p><p>Jeder Gamer der sich älter als 18 Jahre bezeichnen kann, darf sich jeder Zeit anschließen um etwas großes zu bewirken. Elo und Skill sind Nebensache.</p><p>Sobald du dich angesprochen fühlst, besuch uns doch einfach auf unserem Teamspeak um genaueres zu erfahren.</p><p><br></p><p>Wir warten auf dich!!</p>", false, LocalDateTime.parse("2020-07-02T10:51:48.283"),  LocalDateTime.parse("2020-07-09T11:55:56.135")));
		contentRepository.save(new Content(2, "Aufstieg für Silentpeak ", "<h2><em>Silentpeak steigt in die Advanced league auf!!</em></h2>", true, LocalDateTime.parse("2020-07-02T10:53:48.283"),  LocalDateTime.parse("2020-07-02T11:46:56.135")));
		contentRepository.save(new Content(4, "Welcome Zero21by9", "<p>Ein neuer <em>Streamer</em> wagt sich an die Xperience Community ran!!</p>", true, LocalDateTime.parse("2020-07-02T10:44:38.283"),  LocalDateTime.parse("2020-07-02T11:22:54.135")));
		contentRepository.save(new Content(5, "News5", "<p>test5</p>", true, LocalDateTime.parse("2020-07-02T10:44:37.283"),  LocalDateTime.parse("2020-07-02T10:13:24.135")));
		contentRepository.save(new Content(6, "News6", "<p>test6</p>", true, LocalDateTime.parse("2020-07-02T10:42:36.283"),  LocalDateTime.parse("2020-07-02T09:12:34.135")));


		memberRepository.deleteAll();
		teamRepository.deleteAll();


		memberRepository.save(new Member(1L,"Thaiminater","test1",LocalDate.of(2000,06,21),LocalDate.of(2019,11,02), new URL("https://twitter.com/Thaiminater"),new URL("https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg"),"zofia.jpg"));
		memberRepository.save(new Member(2L,"T0XIC_BABE","test1",LocalDate.of(2000,06,21),LocalDate.of(2019,11,02), new URL("https://twitter.com/Thaiminater"),new URL("https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg"),"sledge.jpg"));
		memberRepository.save(new Member(3L,"Trypled","test1",LocalDate.of(2000,06,21),LocalDate.of(2019,11,02), new URL("https://twitter.com/Thaiminater"),new URL("https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg"),"ash.jpg"));
		memberRepository.save(new Member(4L,"SimpleElite","test1",LocalDate.of(2000,06,21),LocalDate.of(2019,11,02), new URL("https://twitter.com/Thaiminater"),new URL("https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg"),"iq.jpg"));
		memberRepository.save(new Member(5L,"Sucuk","test1",LocalDate.of(2000,06,21),LocalDate.of(2019,11,02), new URL("https://twitter.com/Thaiminater"),new URL("https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg"),"jackal.jpg"));
		memberRepository.save(new Member(6L,"ORO","test1",LocalDate.of(2000,06,21),LocalDate.of(2019,11,02), new URL("https://twitter.com/Thaiminater"),new URL("https://www.youtube.com/channel/UCF_1TbJprVWT6SsyqSEJ_Qg"),"ace.jpg"));

		userRepository.save(new User("user","$2y$12$g.0oBcNDKnbNzJRD16a/ZeQFAERFRb3Wv2mNISLiRf7KBQmNJXR36",true,false,false,false,false));
		userRepository.save(new User("admin","$2y$12$D2XjQoR1K/b.1nPdjenPzezMMlQ69l6kDkSkb12l5M3S.RTER.ozC",true,false,false,false,true));

		List<Member> members = new ArrayList<>();
		members.add(memberRepository.findById(1L).get());
		members.add(memberRepository.findById(2L).get());
		teamRepository.save(new Team(1L,"Xperience Main","rainbowsix",members));
		members.clear();
		members.add(memberRepository.findById(3L).get());
		members.add(memberRepository.findById(4L).get());
		teamRepository.save(new Team(2L,"Silentpeak","rainbowsix",members));
		members.clear();
		members.add(memberRepository.findById(5L).get());
		members.add(memberRepository.findById(6L).get());
		teamRepository.save(new Team(2L,"xp.exe","valorant",members));
		System.out.println(teamRepository.findAll());
	}

}
