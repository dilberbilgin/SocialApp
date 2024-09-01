package com.project.SocialApp.general;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
