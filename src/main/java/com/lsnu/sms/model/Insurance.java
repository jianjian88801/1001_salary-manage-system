package com.lsnu.sms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Insurance {
    private Integer id;
    private Integer endowment;
    private Integer unemployment;
    private Integer employmentInjury;
    private Integer maternity;
    private Integer medical;
    private Integer accumulation;
}
