package kr.ac.hs.selab.member.domain.vo;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.terms.domain.Terms;
import kr.ac.hs.selab.terms.domain.vo.Category;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Embeddable
public class Tos {

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Terms> terms = new HashSet<>();

    public void addSign(Member member) {
        Category.sign()
                .forEach(category ->
                        terms.add(
                                new Terms(category, member)
                        )
                );
    }
}