package my.project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import my.project.shop.entity.Member;
import my.project.shop.repository.MemberRepository;

@Service
public class RegisterMemberService {
	private final PasswordEncoder passwordEncoder;
	private final MemberRepository repository;

	@Autowired
	public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository repository) {
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
	}

	public Long join(String email, String password, String name, String memberType, String phone, String birth,
			String role, String provider, String introduce, String career,String profile

	) {

		Member member = Member.createMember(email, password, name, memberType, phone, birth, introduce, career,profile,
				passwordEncoder);
		validateDuplicateMember(member);
		repository.save(member);

		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		repository.findByEmail(member.getEmail()).ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
}
