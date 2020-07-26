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
