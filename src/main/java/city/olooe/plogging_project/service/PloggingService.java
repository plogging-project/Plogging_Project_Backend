package city.olooe.plogging_project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.dto.community.BoardDTO;
import city.olooe.plogging_project.dto.map.MapDTO;
import city.olooe.plogging_project.dto.map.PloggingDTO;
import city.olooe.plogging_project.dto.map.StopoverDTO;
import city.olooe.plogging_project.model.community.BoardEntity;
import city.olooe.plogging_project.dto.member.MemberSearchDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.friend.FriendStatusType;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.PloggingEntity;
import city.olooe.plogging_project.model.map.StopoverEntity;
import city.olooe.plogging_project.persistence.MapRepository;
import city.olooe.plogging_project.persistence.PloggingRepository;
import city.olooe.plogging_project.persistence.StopoverRepository;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import lombok.extern.slf4j.Slf4j;

/**
* @author : 이시화
* @date: 23.06.11
* 
* @brief: 비즈니스 계층의 플로깅 서비스
*/
@Service
@Slf4j
public class  PloggingService {
  
  @Autowired
  private MapRepository repository;// map jpa구현체
  @Autowired
  private StopoverRepository stopoverRepository;
  @Autowired
  private PloggingRepository ploggingRepository;
  /**
  * @author : 이시화
  * @date: 23.06.11
  * 
  * @param: mapNo
  * @return: MapDTO
  * 
  * @brief: 경로 단일 조회
  */
  @Transactional
  
  public MapDTO serchByMapNo(final Long mapNo) {
     MapEntity entity = repository.findById(mapNo).orElseThrow(()->new RuntimeException(""));
     MapDTO dto = new MapDTO(entity);
    return dto;
  }
  /**
  * @author : 이시화
  * @date: 23.06.12
  * 
  * @param: void
  * @return: mapEntityList
  * 
  * @brief: 전체 경로리스트를 반환
  */
  @Transactional
  public Slice<MapDTO> getMapList(Pageable pageable) {
    Slice<MapEntity> entity = repository.findByMapNoLessThan(144L,pageable);
    Slice<MapDTO> dtos = entity.map((mapEntity) -> { return new MapDTO(mapEntity);});
    
    return dtos;
  }
  @Transactional
  public PloggingDTO insertPlogging(PloggingDTO dto, Long memberNo,MapDTO mapDTO) {
    
    if (mapDTO.getMapNo() == null) {
  MapEntity mapEntity = repository.save(mapDTO.toEntityNotStops());
      dto.setMapDTO(new MapDTO(mapEntity.getMapNo(),mapEntity.getStartX(),mapEntity.getStartY(),mapEntity.getEndX(),mapEntity.getEndY()));
      dto.setMemberNo(memberNo);
      PloggingEntity ploggingEntity = ploggingRepository.save(dto.toEntityStops());
      PloggingDTO ploggingDTO = new PloggingDTO(ploggingEntity.getPloggingNo(),ploggingEntity.getMember().getMemberNo(),ploggingEntity.getType(),ploggingEntity.getPloggingTime(),ploggingEntity.getRegDate(),ploggingEntity.getDistance(),ploggingEntity.getStatus(),ploggingEntity.getMapEntity());
      return ploggingDTO;
    }else{
      dto.setMapDTO(mapDTO);
      dto.setMemberNo(memberNo);
      PloggingEntity ploggingEntity = dto.toEntity();
      PloggingDTO ploggingDTO = new PloggingDTO(ploggingRepository.save(ploggingEntity));
      return ploggingDTO;
    }
    
    
  }
 /**
   * @Author 이시화
   * @Date 23.06.22
   * @param keyword
   * @return 추천경로 리스트
   * @Brief 경로명 or 경로설명 or 자치구로 경로 검색
   */
  public List<MapDTO> searchRoute(String keyword) {

    List<MapEntity> searchMapEntity = repository.findByAddrContainingOrCourseNameContainingOrCourseDetailContaining(keyword, keyword, keyword);


    List<MapDTO> mapDTO = searchMapEntity.stream().map(MapDTO::new).collect(Collectors.toList());

    return mapDTO;
  }


  // @Transactional
  // public List<StopoverDTO> getStopoverList() {
  //   List<StopoverEntity> entity = stopoverRepository.findAll();
  //   List<StopoverDTO> dtos = entity.stream().map(StopoverDTO::new).collect(Collectors.toList());
  //   return dtos;
  // }
  // @Transactional
  // public void updateData(List<StopoverDTO> dtos) {
  //   List<StopoverEntity> entity = new ArrayList<>();
  //   for (StopoverDTO dto : dtos) {
  //     entity.add(dto.toEntity());
  //   }
  //   stopoverRepository.saveAll(entity);

  // }
}
