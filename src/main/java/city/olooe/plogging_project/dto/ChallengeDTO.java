package city.olooe.plogging_project.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeStatus;
import city.olooe.plogging_project.model.MemberEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.beans.PropertyAccessor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
public class ChallengeDTO {
    /*
     * @author : 김민수
     * 
     * @date: 23.06.02
     * 
     * @brief: ChallengDTO
     */
    private Long chNo;
    private Boolean blind;
    private Long memberNo;
    private String title;
    private String content;
    private Long personnel;
    @JsonFormat
    private Date regDate;
    @JsonFormat
    private Date startDate;
    @JsonFormat
    private Date endDate;
    private String status;
    private List<Long> challengers;
    private Integer challengeMemberCnt;
    /*
     * @author : 김민수
     * 
     * @date: 23.06.02
     * 
     * @param: ChallengeEntity
     * 
     * @return: void
     * 
     * @brief: Entity를 DTO로 변환
     */
    public ChallengeDTO(final ChallengeEntity chEntity) {
        this.chNo = chEntity.getChNo();
        this.blind = chEntity.getBlind();
        this.memberNo = chEntity.getHost().getMemberNo();
        this.title = chEntity.getTitle();
        this.content = chEntity.getContent();
        this.personnel = chEntity.getPersonnel();
        this.regDate = chEntity.getRegDate();
        this.startDate = chEntity.getStartDate();
        this.endDate = chEntity.getEndDate();
        this.challengeMemberCnt = chEntity.getChallengeMembers().size();
    }

    /*
     * @author : 김민수
     * 
     * @date: 23.06.02
     * 
     * @param: ChallengeDTO
     * 
     * @return: ChallengeEntity
     * 
     * @brief: ChallengeDTO를 ChallengeEntity 변환
     */
    public static ChallengeEntity chToEntity(final ChallengeDTO challengeDTO) {
        return ChallengeEntity.builder()
                .chNo(challengeDTO.getChNo())
                .blind(challengeDTO.getBlind())
                .host(MemberEntity.builder().memberNo(challengeDTO.memberNo).build())
                .title(challengeDTO.getTitle())
                .content(challengeDTO.getContent())
                .personnel(challengeDTO.getPersonnel())
                .regDate(challengeDTO.getRegDate())
                .startDate(challengeDTO.getStartDate())
                .endDate(challengeDTO.endDate)
                .build();
    }

    /**
     * @author : 김민수
     * @date: 23.06.08
     * 
     * @param: -
     * @return: entity
     * 
     * @brief: DTO -> entity
     */
    public ChallengeEntity toChallengeEntity() {
        return ChallengeEntity.builder().chNo(chNo).host(MemberEntity.builder().memberNo(memberNo).build()).blind(blind).title(title).content(content)
                .personnel(personnel).startDate(startDate).endDate(endDate).status(ChallengeStatus.CHALLENGEBEFORE).build();
    }

}