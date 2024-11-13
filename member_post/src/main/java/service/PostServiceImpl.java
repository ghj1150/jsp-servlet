package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.Criteria;
import mapper.AttachMapper;
import mapper.PostMapper;
import utils.MybatisInit;
import vo.Post;

public class PostServiceImpl implements PostService {
	public static void main(String[] args) {
		new PostServiceImpl().write(Post.builder().title("제목").content("aaaa").writer("aaaa").cno(2).build());
	}
	
	@Override
	public int write(Post post) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)) {
			PostMapper mapper = session.getMapper(PostMapper.class);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			System.out.println(post); // post.getPno() null
			mapper.insert(post);
			
			System.out.println(post); // post.getPno() not null
			post.getAttachs().forEach(a -> {
				a.setPno(post.getPno());
				attachMapper.insert(a);
			});
			
			
			return 0;
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
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			attachMapper.delete(pno);
			return mapper.delete(pno);
		}
	}

	@Override
	public Post findBy(Long pno) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)) {
			PostMapper mapper = session.getMapper(PostMapper.class);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			Post post = mapper.selectOne(pno);
			post.setAttachs(attachMapper.selecList(pno));
			return post;
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
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			mapper.increaseViewCount(pno);
			Post post = mapper.selectOne(pno);
			post.setAttachs(attachMapper.selecList(pno));
			return post;
		}
	}
	
	
	
}
