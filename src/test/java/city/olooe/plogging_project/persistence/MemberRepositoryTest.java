package city.olooe.plogging_project.persistence;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

// import city.olooe.plogging_project.model.AuthEntity;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 박연재
 * @date: 2023.06.01
 * @brief: MemberEntity와 jpa 구현 인터페이스를 활용한 테스트 ( 로그인, 회원가입)
 */
@SpringBootTest
@Slf4j
public class MemberRepositoryTest {

  @Autowired
  MemberRepository memberRepository;

  @DisplayName("회원 생성 테스트")
  @Test
  public void createMember() {
    MemberEntity member = MemberEntity.builder().userId("root2").password("12345").userName("김연재")
        .email("root1234@gmail.com").build();
    log.info("{}", memberRepository.save(member));

  }

  @DisplayName("회원 목록 조회 테스트")
  @Test
  public void testFindAllMember() {

    log.info("{}", memberRepository.findAll());
  }

  @DisplayName("회원 수정 테스트")
  @Test
  public void testModifyMember() {
    // log.info("{}")
  }

  @DisplayName("회원 단일 조회 테스트")
  @Test
  public void checkMember() {

    log.info("{}", memberRepository.findByUserIdAndPassword("root2", "12345"));
  }

  @DisplayName("클래스 확인 용도")
  @Test
  public void testClass() {
    log.info("{}", memberRepository.getClass().getName());
  }
}
