package service;

import java.util.List;

import dao.PostDao;
import vo.Post;

public class PostServiceImpl implements PostService {
	private PostDao dao = new PostDao();

	@Override
	public int write(Post post) {
		return dao.insert(post);
	}

	@Override
	public int modify(Post post) {
		return dao.update(post);
	}

	@Override
	public int remove(Long pno) {
		return dao.delete(pno);
	}

	@Override
	public Post findBy(Long pno) {
		return dao.selectOne(pno);
	}

	@Override
	public List<Post> list() {
		return dao.selectList();
	}
	
}
