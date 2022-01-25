package kr.ac.hs.selab.board.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import kr.ac.hs.selab.common.utils.Constants;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_title", unique = true)
    private String title;

    @Column(name = "board_description")
    private String description;

    @Column(name = "board_delete_flag")
    private boolean deleteFlag;

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
        this.title = this.title + Constants.TITLE_SEPARATOR + UUID.randomUUID();
        this.deleteFlag = true;
        return this;
    }
}
