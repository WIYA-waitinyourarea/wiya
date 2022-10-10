package com.teamwiya.wiya.config;

/*

@Configuration //스프링 빈에 등록하기 위한 설정파일 표시 어노테이션
public class SpringConfig {
    private DataSource dataSource;

    @PersistenceContext //EntityManager 의 경우 필요
    EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpamemberRepository(em);
    }

}
*/
