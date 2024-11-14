package vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Reply {

    private final Long rno;
    private final String content;
    private final Date regdate;
    private final Date updatedate;
    private final boolean hidden;
    private final int likes;
    private final String writer;
    private final Long pno;

}