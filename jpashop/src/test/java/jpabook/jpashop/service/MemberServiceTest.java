package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception{

    //given
        Member member = new Member();
        member.setName("solee");

    //when
        Long saveId = memberService.join(member);

        em.flush();
    //then
        assertEquals(member, memberRepository.findOne(saveId));
}

    @Test
    public void 중복_회원_예외() throws Exception{

        //given
        Member member1 = new Member();
        member1.setName("solee1");

        Member member2 = new Member();
        member2.setName("solee1");

        //when
        memberService.join(member1);
        try {

            memberService.join(member2);

        }catch (IllegalStateException e){
            return;
        }
        //then


}



}