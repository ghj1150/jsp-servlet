package service;

import java.util.List;

import vo.Post;

public interface PostService {
	// 게시글 작성
	int write(Post post);
	// 게시글 수정
	int modify(Post post);
	// 게시글 글번호 삭제
	int remove(Long pno);
	// 단일 조회
	Post findBy(Long pno);
	// 게시글 목록
	List<Post> list();
}
