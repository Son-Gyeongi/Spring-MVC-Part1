package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 테스트는 public 없어도 된다. - Junit4는 있어야 하는데 Junit5는 안해도 된다.
class MemberRepositoryTest {

    // 테스트할 대상 가져오기
//    MemberRepository memberRepository = new MemberRepository(); // 싱글톤이라서 new 로 안된다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach // 테스트 끝날 때마다 테스트 깔끔하게 초기화 하기
    void afterEach() {
        // 각 테스트가 서로 영향 받지 않게 clearStore() 꼭 해줘야 한다.
        memberRepository.clearStore();
    }

    @Test // MemberRepository 저장 테스트
    void save() {
        // given - 이런게 주어졌을 때
        // Member 생성
        Member member = new Member("hello", 20);

        // when - 이런게 실행했을 때
        Member savedMember = memberRepository.save(member);

        // then - 결과가 이거여야 해
        // 저장된 member 찾아보기
        Member findMember = memberRepository.findById(savedMember.getId());
        // org.assertj.core.api.Assertions
        // findMember 찾아온 멤버는 savedMember 저장한 멤버랑 같아야 한다.
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test // 모든 걸 조회하는 테스트
    void findAll() {
        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        // 저장
        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        // Assertions - static import
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}
