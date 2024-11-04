package service;

import java.util.List;

import dao.MemberDao;
import vo.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao memberDao = MemberDao.getInstance();
	@Override
	public int register(Member member) {
		return memberDao.insert(member);
	}

	@Override
	public Member findBy(String id) {
		return memberDao.selectOne(id);
	}

	@Override
	public boolean login(String id, String pw) {
		Member m = findBy(id);
		return m != null && m.getPw().equals(pw);
	}

	@Override
	public List<Member> list() {
		return null;
	}

	@Override
	public boolean remove(String id) {
		return false;
	}

	@Override
	public boolean modify(Member member) {
		return false;
	}
	
}
