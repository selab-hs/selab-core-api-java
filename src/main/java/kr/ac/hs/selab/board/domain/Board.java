package kr.ac.hs.selab.board.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    private Long id;

    @Column(name = "board_title", unique = true, nullable = false)
    private String title;

    @Column(name = "board_description", nullable = false)
    private String description;

    @Column(name = "board_delete_flag", nullable = false)
    private boolean deleteFlag;

    @Transient
    private static final String HYPHEN = "-";

    @Builder
    private Board(String title, String description) {
        this.title = title;
        this.description = description;
        this.deleteFlag = false;
    }

    public Board update(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }

    public Board delete() {
        this.title = this.title + HYPHEN + this.id;
        this.deleteFlag = true;
        return this;
    }
}
