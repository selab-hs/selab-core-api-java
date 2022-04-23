package kr.ac.hs.selab.terms.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import kr.ac.hs.selab.terms.domain.vo.Category;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    @Column(name = "member_id")
    private Long memberId;

    public Terms(Category category, Long memberId) {
        this.category = category;
        this.memberId = memberId;
    }
}