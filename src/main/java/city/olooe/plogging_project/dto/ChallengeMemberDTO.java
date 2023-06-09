package city.olooe.plogging_project.dto;

import java.util.Date;

import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeMemberEntity;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeMemberDTO {
    private Long cmemberNo;
    private ChallengeEntity chNo;
    private MemberEntity challenger;
    private Date regDate;

    /*
     * @author : 김민수
     * 
     * @date: 23.06.09
     * 
     * @param: ChallengeDTO
     * 
     * @return: ChallengeEntity
     * 
     * @brief: ChallengeDTO를 ChallengeEntity 변환
     */
    public static ChallengeMemberEntity chToEntity(final ChallengeMemberDTO challengeMemberDTO) {
        return ChallengeMemberEntity.builder()
                .cmemberNo(challengeMemberDTO.getCmemberNo())
                .chNo(challengeMemberDTO.getChNo())
                .challenger(challengeMemberDTO.getChallenger())
                .regDate(challengeMemberDTO.regDate)
                .build();
    }

    /**
     * @author : 김민수
     * @date: 23.06.09
     * 
     * @param: -
     * @return: entity
     * 
     * @brief: DTO -> entity
     */
    public ChallengeMemberEntity toChallengeMemberEntity() {
        return ChallengeMemberEntity.builder().cmemberNo(cmemberNo).chNo(chNo).challenger(challenger).regDate(regDate)
                .build();
    }

}
