// package city.olooe.plogging_project.service;

// import city.olooe.plogging_project.dto.community.ReplyDTO;
// import city.olooe.plogging_project.model.community.BoardEntity;
// import city.olooe.plogging_project.service.community.ReplyService;
// import lombok.extern.slf4j.Slf4j;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;

// import javax.transaction.Transactional;

// @Transactional
// @SpringBootTest
// @Slf4j
// public class ReplyServiceTest {

//     @Autowired
//     ReplyService replyService;

//     /**
//      * @Author 천은경
//      * @Date 23.06.20
//      * @Brief 모든 댓글 조회 테스트
//      */
//     @Test
//     public void getAllTest() {
//         for (ReplyDTO replyDTO : replyService.getAll()) {
//             log.warn("{}", replyDTO.getReply());
//         }

//     }

//     /**
//      * @Author 천은경
//      * @Date 23.06.20
//      * @Brief 해당 글 댓글 조회 테스트
//      */
//     @Test
//     public void getReplyByBoardTest() {
//         Pageable page = PageRequest.of(0, 5);
//         Page<ReplyDTO> replysOfBoard = replyService.getReplysOfBoard(ReplyDTO.builder().bno(42L).build(), page);
//         for (ReplyDTO replyDTO : replysOfBoard) {
//             log.warn("{}", replyDTO.getReply());
//         }
//     }

//     /**
//      * @Author 천은경
//      * @Date 23.06.20
//      * @Brief 댓글 작성 테스트
//      */
//     @Test
//     public void writeReplyTest() {
//         Pageable page = PageRequest.of(0,5);
//         ReplyDTO replyDTO = ReplyDTO.builder().reply("잠온다구").replyerNo(62L).bno(42L).build();
//         Page<ReplyDTO> replyDTOS = replyService.writeReply(replyDTO, page);
//         for (ReplyDTO dto : replyDTOS) {
//             log.warn("{}", replyDTO.getReply());
//         }

//     }

//     /**
//      * @Author 천은경
//      * @Date 23.06.20
//      * @Brief 댓글 삭제 테스트
//      */
//     @Test
//     public void deleteReplyTest() {
//         replyService.deleteReply(ReplyDTO.builder().rno(1L).build());
//     }
// }
