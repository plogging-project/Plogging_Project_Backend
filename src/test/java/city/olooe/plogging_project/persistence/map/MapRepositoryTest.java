package city.olooe.plogging_project.persistence.map;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;

import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.StopoverEntity;
import city.olooe.plogging_project.persistence.MapRepository;
import city.olooe.plogging_project.persistence.StopoverRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MapRepositoryTest {
  @Autowired
  MapRepository mapRepository;
  @Autowired
  StopoverRepository stopoverRepository;

  @Test
  @DisplayName("맵 생성 테스트")
  public void createMap() {
    // given
    MapEntity mapEntity = MapEntity.builder().startX(0d).startY(0d).endX(0d).endY(0d).courseName("테스트")
        .courseDetail("테스트내용").addr("테스트주소").distance(0d).build();
    // when
    // then
    log.info("{}", mapRepository.save(mapEntity));

  }
  @Test
  @DisplayName("맵 + 경유지 생성 테스트")
  public void createMapAndStopover() {
    // given
  //   StopoverEntity stopoverEntity = StopoverEntity.builder().stopoverX(0d).stopoverY(0d).stopoverIdx(1).build();
   
  //  stopoverEntity = stopoverRepository.save(stopoverEntity);
    
  //   MapEntity mapEntity = MapEntity.builder().startX(0d).startY(0d).endX(0d).endY(0d).courseName("테스트")
  //       .courseDetail("테스트내용").addr("테스트주소").distance(0d)
  //       .build();
        
  //       mapEntity = mapRepository.save(mapEntity);
        // when
        
        // then
        // stopoverRepository.save(StopoverEntity.builder().stopoverNo(stopoverRepository.findByMapNo().mapEntity(mapEntity).build());
  }
  @Test
  @DisplayName("경로 조회 테스트")
  @Transactional
  public void readRoute(){
      MapEntity mapEntity = mapRepository.findById(1L).orElse(null);
      List<StopoverEntity> stopoverEntities =  mapEntity.getStops();
      stopoverEntities.forEach(stop -> System.out.println(stop));
    
  }
  @Test
  @DisplayName("경로 검색 테스트")
  public void searchRoute(){
    Pageable pageable = PageRequest.of(0, 5);
    List<MapEntity> mapEntity = mapRepository.findByAddrContainingOrCourseNameContainingOrCourseDetailContaining("동작","동작","동작");
    mapEntity.forEach(map -> System.out.println(map));
  }

}
