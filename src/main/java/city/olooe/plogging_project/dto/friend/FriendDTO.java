package city.olooe.plogging_project.dto.friend;

import city.olooe.plogging_project.model.friend.FriendEntity;
import city.olooe.plogging_project.model.friend.FriendStatusType;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
 * @author : 천은경
 * @date: 23.06.02
 * @brief: 플친 DTO
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@DynamicInsert
@DynamicUpdate
public class FriendDTO {
    private Long friendNo;
    private Long fromMemberNo;
    private String fromMemberId;
    private Long toMemberNo;
    private String toMemberId;
    private String status;

    // Entity를 Dto로 변환
    public FriendDTO(final FriendEntity friendEntity) {
        this.friendNo = friendEntity.getFriendNo();
        this.fromMemberNo = friendEntity.getFromMember().getMemberNo();
        this.fromMemberId = friendEntity.getFromMember().getUserId();
        this.toMemberNo = friendEntity.getToMember().getMemberNo();
        this.toMemberId = friendEntity.getToMember().getUserId();
        this.status = friendEntity.getStatus().getKey();
    }

    // DTO를 Entity로 변환
    public static FriendEntity toEntity(final FriendDTO friendDTO) {
        return FriendEntity.builder()
                .friendNo(MemberEntity.builder().memberNo(friendDTO.friendNo).build().getMemberNo())
                .fromMember(MemberEntity.builder().memberNo(friendDTO.fromMemberNo).userId(friendDTO.fromMemberId).build())
                .toMember(MemberEntity.builder().memberNo(friendDTO.toMemberNo).userId(friendDTO.toMemberId).build())
                .status(FriendStatusType.valueOf(friendDTO.status)).build();
    }

}

