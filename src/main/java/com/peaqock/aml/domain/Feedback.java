package com.peaqock.aml.domain;

import com.peaqock.aml.domain.common.AbstractEntity;
import com.peaqock.aml.dto.enums.FeedbackStatus;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedbacks")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class Feedback extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "script_id")
    private Script script;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prospect_id")
    private UserEntity prospect;

    @Column(columnDefinition = "varchar(255) default 'INITIAL'")
    private FeedbackStatus status;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object feedback;

    private Long score;
}
