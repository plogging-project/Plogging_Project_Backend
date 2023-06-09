package city.olooe.plogging_project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.model.community.BoardEntity;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeMemberEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.map.PloggingEntity;
import city.olooe.plogging_project.persistence.community.BoardRepository;
import city.olooe.plogging_project.persistence.ChallengeMemberRepository;
import city.olooe.plogging_project.persistence.ChallengeRepository;
import city.olooe.plogging_project.persistence.ChallengeScheduleRepository;
import city.olooe.plogging_project.persistence.MemberRepository;
import city.olooe.plogging_project.persistence.PloggingRepository;
import city.olooe.plogging_project.persistence.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

  private final MemberRepository memberRepository;
  private final ChallengeMemberRepository challengeMemberRepository;

  // public MemberEntity searchChallengeDetailByMember(final MemberEntity member)
  // {
  // return memberRepository.containChallengeByMemberEntity(member);
  // }

  // public MemberEntity searchPloggingByMember(final MemberEntity member) {
  // return memberRepository.containPloggingByMemberEntity(member);
  // }

  // public MemberEntity searchPointHistoryByMember(final MemberEntity member) {
  // return memberRepository.containPointHistoryByMemberEntity(member);
  // }

  public List<BoardEntity> searchBoardByMember(final MemberEntity member) {
    return memberRepository.findByUserId(member.getUserId()).getBoardEntities();
  }

}
