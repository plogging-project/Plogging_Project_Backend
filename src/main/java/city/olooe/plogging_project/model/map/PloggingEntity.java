package city.olooe.plogging_project.model.map;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "tbl_plogging")
/*
 * @author : 이시화
 * 
 * @date: 23.06.01
 * 
 * @brief: 플로깅 엔티티
 */
public class PloggingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ploggingNo;// pk
  // @JoinColumn
  // private Long mapNo;// fk 경로
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemberEntity.class)
  @JoinColumn(name = "memberNo")
  private MemberEntity member;// fk 회원
  private String type; // 유형 (제자리시작 / 목표지설정 / 추천경로 / 챌린지경로)
  private Integer ploggingTime; // 이동시간
  private Date regDate; // 플로깅 날짜(insert날짜)
  private Double distance; // 이동 거리
  private Boolean status; // 성공여부

  @ManyToOne
  @JoinColumn(name = "mapNo")
  private MapEntity mapEntity;// fk 경로

}
