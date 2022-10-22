package com.peaqock.aml.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commentaire {

    private Comment adminComment;
    private Comment agentComment;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Comment {
        private String createdAt;
        private String comment;
        private String username;
    }

}
