package pl.niepracuj.model.dto;

import lombok.*;
import pl.niepracuj.model.enums.TechnologyEnum;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyDto {

    private Long id;

    private TechnologyEnum name;
}
