package kr.ac.hs.selab.terms.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.terms.domain.vo.Category;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms extends BaseEntity {

    @Id
    @Column(name = "terms_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "terms_category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Terms(Category category, Member member) {
        this.category = category;
        this.member = member;
    }
}