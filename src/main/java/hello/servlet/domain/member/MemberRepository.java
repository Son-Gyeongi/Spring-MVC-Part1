package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
// 회원(Member) 저장소
public class MemberRepository {
    // static으로 해서 MemberRepository가 new로 많아도 하나만 생성된다. 딱 하나만 사용된다.

    // 데이터 저장 / 키는 id 값은 Member
    private static Map<Long, Member> store = new HashMap<>();
    // id가 하나씩 증가하는 sequence
    private static long sequence = 0L;

    // 싱글톤으로 만들거다.
    // 스프링 쓰면 싱글톤 안 만들어도 된다. 스프링 자체가 싱글톤 보장해주기 때문이다.
    private static final MemberRepository instance = new MemberRepository();

    // 무조건 getInstance()로 조회해야 한다.
    public static MemberRepository getInstance() {
        return instance;
    }

    // 싱글톤 만들 떄는 private으로 생성자를 막아야한다. 아무나 생성하지 못하게 해야 한다.
    private MemberRepository() {}

    // save 만들기
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // Member 찾기
    public Member findById(Long id) {
        // 회원을 id로 빠르게 찾아보자
        return store.get(id);
    }

    // Member 모두 찾기
    public List<Member> findAll() {
        // store에 있는 모든 값들을 꺼내서 새로운 ArrayList에 담아서 넘겨준다.
        // store.values()에 저장된 값들은 건드리고 싶지 않다.
        // new ArrayList에 값을 넣거나 조작해도 된다. store 자체 보호하기 위해서
        return new ArrayList<>(store.values());
    }

    // 테스트할 때 쓰인다.
    public void clearStore() {
        // store 다 날려버린다.
        store.clear();
    } // 잘 동작하는 지 테스트 코드 작성해보자.
}
