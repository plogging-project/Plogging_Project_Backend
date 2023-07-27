package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.PointHistoryEntity;

import java.util.List;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistoryEntity, Long> {
    /**
     * @author 이재원
     * @date 23.06.12
     * @param pointNo
     * @return List<PointHistoryEntity>
     * @Brief 포인트 내역 중 상태별 조회를 위한 메서드
     */
    List<PointHistoryEntity> findByPointNoAndType(Long pointNo, RewardTypeStatus type);

    /**
     * @author 이재원
     * @date 23.06.12
     * @param memberNo
     * @return List<PointHistoryEntity>
     * @Brief 멤버 번호를 조회하여 상태별 조회 메서드
     */
    List<PointHistoryEntity> findByTypeAndMemberNo(RewardTypeStatus type , MemberEntity memberNo);

    /**
     * @author 이재원
     * @date 23.06.12
     * @param
     * @return List<PointHistoryEntity>
     * @Brief 멤버 번호를 조회하여 상태별 조회 메서드
     */
    List<PointHistoryEntity> findByType(RewardTypeStatus type);
    /**
     * @author 이재원
     * @date 23.06.12
     * @param
     * @return List<PointHistoryEntity>
     * @Brief Status값을 조회하여 List 출력
     */
    List<PointHistoryEntity> findByStatus(boolean status);

    @Query(value = "SELECT sum(point) FROM PointHistoryEntity")
    public Long totalPoint();

    @Query(value = "SELECT sum(point) FROM PointHistoryEntity WHERE memberNo = :memberNo AND type = :type")
    public Long DonationPoint(@Param("memberNo") MemberEntity memberNo,@Param("type") RewardTypeStatus type);

    @Query(value = "SELECT sum(point) FROM PointHistoryEntity WHERE memberNo = :memberNo")
    public Long sumPoint(@Param("memberNo") MemberEntity memberNo);

    List<PointHistoryEntity> findByMemberNo(MemberEntity memberEntity);

//    @Query(value = "SELECT ph.memberNo, ph.type, sum(ph.point) FROM MemberEntity m LEFT JOIN PointHistoryEntity ph on m.memberNo = ph.memberNo WHERE m.memberNo = :memberNo")
//    public List<PointHistoryEntity> findByPointList(@Param("memberNo") MemberEntity memberNo);

//    @Query(value = "select tp.memberNo, sum(tp.point), RANK() OVER (ORDER BY sum (tp.point) DESC) RANK from PointHistoryEntity tp")
//    public List<PointHistoryEntity> findByPointList(MemberEntity memberEntity);
//
//    @Query(value = "select tp.memberNo, sum(tp.point), RANK() OVER (order by sum (tp.point) DESC) rank from PointHistoryEntity tp")
//    public List<PointHistoryEntity> findByPointList2(Long memberNo);
}
//    select tp.memberNo, sum(tp.point),
//        RANK() OVER (ORDER BY sum(tp.point) DESC) RANK2
//        from tbl_pointhistory tp
//        group by tp.memberNo
//        order by 2 desc limit 5;
