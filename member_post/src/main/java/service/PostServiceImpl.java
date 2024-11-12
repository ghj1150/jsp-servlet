package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.Criteria;
import mapper.PostMapper;
import utils.MybatisInit;
import vo.Post;

public class PostServiceImpl implements PostService {
	@Override
	public int write(Post post) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)) {
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.insert(post);
		}
	}

	@Override
	public int modify(Post post) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)) {
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.update(post);
		}
	}

	@Override
	public int remove(Long pno) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)) {
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.delete(pno);
		}
	}

	@Override
	public Post findBy(Long pno) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)) {
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.selectOne(pno);
		}
	}

	@Override
	public List<Post> list(Criteria cri) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)) {
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.selectList(cri);
		}
	}
	
	@Override
	public int count(Criteria cri) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)) {
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.getCount(cri);
		}
	}

	@Override
	public Post view(Long pno) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)) {
			PostMapper mapper = session.getMapper(PostMapper.class);
			mapper.increaseViewCount(pno);
			return mapper.selectOne(pno);
		}
	}
	
	
	
}
