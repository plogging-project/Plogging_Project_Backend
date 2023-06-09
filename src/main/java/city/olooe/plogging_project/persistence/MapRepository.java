package city.olooe.plogging_project.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.map.MapEntity;

import java.util.List;

import javax.persistence.EntityManager;


/**
 * @author : 이시화
 * 
 * @date: 2023.06.01
 * 
 * @brief: MapEntity jpa 인터페이스 구현체
 */
@Repository
public interface MapRepository extends JpaRepository<MapEntity, Long> {
  List<MapEntity> findByAddrContainingOrCourseNameContainingOrCourseDetailContaining(String courseDetail,String courseName,String addr);
   Slice<MapEntity> findByMapNoLessThan(Long mapNo,Pageable pageable);
}
