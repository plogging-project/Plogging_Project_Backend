package city.olooe.plogging_project.controller;

import java.util.List;
import java.util.Objects;

import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.community.BoardCategory;
import city.olooe.plogging_project.model.community.BoardEntity;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import city.olooe.plogging_project.dto.community.BoardDTO;

import city.olooe.plogging_project.service.community.BoardService;
import lombok.RequiredArgsConstructor;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.02
 * 
 * @brief : 게시글 관련 controller
 */

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("community")
public class BoardController {

  private final BoardService boardService;

  /**
   * @Author 천은경
   * @Date 23.06.21
   * @param pageable
   * @return 페이징 게시글
   * @Brief 모든 게시글 페이징 조회
   */
  @GetMapping("/boards")
  public ResponseEntity<?> readBoard(@PageableDefault(sort = "bno", size = 12
          , direction = Sort.Direction.DESC) Pageable pageable) {
    Page<BoardDTO> boardDTOS = boardService.searchAllBoard(pageable);
    return ResponseEntity.ok().body(ResponseDTO.builder().data(boardDTOS).build());
  }

  /**
   * @Author 천은경
   * @Date 23.06.21
   * @param category
   * @param pageable
   * @return 카테고리별 게시글
   * @Brief 카테고리별 게시글 페이징 조회
   */
  @GetMapping("/boards/{category}")
  public ResponseEntity<?> boardsOfCategory(@PathVariable String category,
                                            @PageableDefault(sort = "bno", size = 12
                                                    , direction = Sort.Direction.DESC) Pageable pageable) {
    Page<BoardDTO> boardDTOS = boardService.BoardOfCategory(category, pageable);
    return ResponseEntity.ok().body(ResponseDTO.builder().data(boardDTOS).build());
  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: boardCreateDTO
   * @return: Long
   * 
   * @brief: 게시물 작성
   */
  @PostMapping("/register")
  public ResponseEntity<?> create(@AuthenticationPrincipal ApplicationUserPrincipal user,
                                  @RequestBody BoardDTO boardCreateDTO) {

    if(boardCreateDTO.getPloggingNo() == null) {
      log.warn("플로깅 null 값 체크");
    }
    log.warn("board DTO {}", boardCreateDTO.getAttach().getPath());
    log.warn("board DTO {}", boardCreateDTO.getAttach().getUuid());
    log.warn("board DTO {}", boardCreateDTO.getAttach().getFile());

    BoardDTO boardDTO = boardService.create(user, boardCreateDTO);
    return ResponseEntity.ok().body(ResponseDTO.builder().data(boardDTO).build());
  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: boardUpdateDTO
   * @return: Long
   * 
   * @brief: 게시물 수정
   */
  @PutMapping("/update")
  public ResponseEntity<?> update(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestBody BoardDTO boardUpateDTO) {

    // 본인의 게시물이 아닌 경우
    if(!Objects.equals(boardUpateDTO.getMemberNo(), user.getMember().getMemberNo())) {
      ResponseEntity.ok().body(ResponseDTO.builder().error("500").build());
    }
    
    BoardDTO boardDTO = boardService.update(boardUpateDTO);
    return ResponseEntity.ok().body(ResponseDTO.builder().data(boardDTO).build());
  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: bno
   * @return: Long
   * 
   * @brief: 게시물 개별 조회
   */
  @GetMapping("/board/{bno}")
  public ResponseEntity<?> searchByBno(@PathVariable Long bno) {
    return ResponseEntity.ok().body(boardService.searchByBno(bno));
  }


  /**
   * @author : 김성진
   * @date: '23.06.05
   *
   * @param: bno
   * @return: -
   *
   * @brief: 게시물 삭제
   */
  @DeleteMapping("/delete/{bno}")
  public ResponseEntity<?> delete(@PathVariable Long bno) {

    boardService.delete(bno);
    return ResponseEntity.ok().body(ResponseDTO.builder().data(bno).build());
  }

}
