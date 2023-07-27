// package city.olooe.plogging_project.service;

// import city.olooe.plogging_project.model.friend.FriendEntity;
// import city.olooe.plogging_project.model.friend.FriendStatusType;
// import city.olooe.plogging_project.persistence.FriendRepository;
// import city.olooe.plogging_project.persistence.MemberRepository;
// import lombok.extern.slf4j.Slf4j;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import java.util.List;
// import java.util.stream.Collectors;

// //
// //
// @SpringBootTest
// @Slf4j
// class FriendServiceTest {
// //
//     private final Logger logger = LoggerFactory.getLogger(this.getClass());
// //
//     @Autowired
//     FriendRepository friendRepository;

//     @Autowired
//     MemberRepository memberRepository;

//     @Autowired
//     FriendService friendService;

//     @Autowired
//     MemberService memberService;

//     @Test
//     @DisplayName("팔로워 리스트")
//     public void followerList() {
//         List<FriendEntity> friendEntities = friendService.getFriendListToMe(62L, FriendStatusType.FRIEND.getKey());
//         log.warn("팔로워 리스트 : {}", friendEntities.stream().map(FriendEntity::getFriendNo).collect(Collectors.toList()));
//     }

//     @Test
//     @DisplayName("팔로잉 리스트")
//     public void followingList() {
//         List<FriendEntity> friendEntities = friendService.GetMyFriendList(62L, FriendStatusType.FRIEND.getKey());
//         log.warn("팔로잉 리스트 : {}", friendEntities.stream().map(FriendEntity::getFriendNo).collect(Collectors.toList()));
//     }


//     @Test
//     @DisplayName("플친요청")
//     void requestFriend() {

//         List<FriendEntity> friendEntities = friendService.requestFriend(62L, 64L);

//         logger.warn("플친요청 테스트 플친대기리스트 : {}", friendEntities.size());
//     }

//     @Test
//     @DisplayName("플친 거절 및 플친 삭제")
//     void removeFriend() {
//         List<FriendEntity> beforeFriends = friendService.GetMyFriendList(62L, FriendStatusType.FRIEND.getKey());
//         logger.warn("삭제 전 플친 리스트 : {}", beforeFriends);
//         List<FriendEntity> afterFriends = friendService.removeFriend(62L, 6L);
//         logger.warn("삭제 후 플친 리스트 : {}", afterFriends);
//     }

//     @Test
//     @DisplayName("플친 요청 승인")
//     void acceptFriend() {
//         List<FriendEntity> beforeFriends = friendService.GetMyFriendList(62L, FriendStatusType.FRIEND.getKey());
//         logger.warn("승인 전 플친 리스트 : {}", beforeFriends.stream().toString());
//         List<FriendEntity> afterFriends = friendService.acceptRequest(62L, 8L);
//         logger.warn("승인 후 플친 리스트 : {}", afterFriends.stream().toString());

//     }

//     @Test
//     @DisplayName("플친 차단")
//     void blockFriend() {
//         List<FriendEntity> beforeBlocked = friendService.GetMyFriendList(62L, FriendStatusType.BLOCK.getKey());
//         logger.warn("전 차단 리스트 : {}", beforeBlocked.stream().toString());
//         List<FriendEntity> afterBlocked = friendService.blockFriend(62L, 8L);
//         logger.warn("후 차단 리스트 : {}", afterBlocked.stream().toString());
//     }

//     @Test
//     @DisplayName("차단 취소 및 요청 취소")
//     void cancelFriend() {
//         List<FriendEntity> beforeCanceled = friendService.GetMyFriendList(62L, FriendStatusType.PENDING.getKey());
//         logger.warn("전 취소 리스트 : {}", beforeCanceled.stream().toString());
//         List<FriendEntity> afterCanceled = friendService.cancelRequest(62L, 15L);
//         logger.warn("후 취소 리스트 : {}", afterCanceled);
//     }
// //
// //    @Test
// //    void 나의플친리스트() {
// //        MemberEntity member = memberRepository.findByUserId("olooe");
// //        List<FriendEntity> friends = friendService.GetFriendList(member);
// //
// //        logger.warn("플친 : {}", friends.size());
// //    }
// //
// //    @Test
// //    void 내가한요청리스트() {
// //        MemberEntity member = memberRepository.findByUserId("olooe");
// //        List<FriendEntity> requestList = friendService.getMyRequestList(member);
// //        logger.warn("요청리스트 : {}", requestList.size());
// //    }
// //
// //
// //    @Test
// //    void 받은요청리스트() {
// //        MemberEntity member = memberRepository.findByUserId("olooe");
// //        List<FriendEntity> requestList = friendService.getRequestListToMe(member);
// //        logger.warn("요청리스트 : {}", requestList.size());
// //    }
// //
// //    @Test
// //    void 나의차단리스트() {
// //        MemberEntity member = memberRepository.findByUserId("olooe");
// //        List<FriendEntity> blockedList = friendService.getBlockedList(member);
// //        logger.warn("차단리스트 : {}", blockedList.size());
// //    }
// //
// //    @Test
// //    void 친구상태조회() {
// //        MemberEntity fromMember = memberRepository.findByUserId("olooe");
// //        MemberEntity toMember = memberRepository.findByUserId("seolha");
// //        String status = friendService.getStatus(fromMember, toMember);
// //        logger.warn("친구상태 : {}", status);
// //    }
// }