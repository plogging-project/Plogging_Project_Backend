package city.olooe.plogging_project.persistence.map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.map.LocationEntity;

@Repository
/*
 * @author : 이시화
 * 
 * @date: 23.06.01
 * 
 * @brief: LocationEntity jpa 구현체
 */
public interface LocationRepository extends JpaRepository<LocationEntity, String> {

}