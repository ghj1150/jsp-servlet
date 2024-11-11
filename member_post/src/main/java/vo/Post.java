package vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
	private final Long pno;
	private final String title;
	private final String writer;
	private final String content;
	private final Long viewCount;
	private final Date regdate;
	private final Date updatedate;
	private final int cno;
}
