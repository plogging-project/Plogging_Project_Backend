package city.olooe.plogging_project.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import city.olooe.plogging_project.model.map.MapEntity;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_challengeschedule")
@DynamicInsert
public class ChallengeScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleNo; // 플로깅 스케쥴 번호

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ChallengeEntity.class )
    @JoinColumn(name = "chNo")
    @Setter
    private ChallengeEntity chNo; // 챌린지의 번호

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MapEntity.class )
    @JoinColumn(name = "mapNo")
    private MapEntity mapNo; // 추천경로 맵의 번호

    private Date startDate; // 플로깅 시작날짜 시간
    private Date endDate; // 플로깅 끝나는 날짜 시간
    private Date regDate; // 생성날짜


    public ChallengeScheduleEntity(Long scheduleNo) {
        this.scheduleNo = scheduleNo;
    }
}