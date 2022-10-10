package com.teamwiya.wiya.hospital.model;

import javax.persistence.Embeddable;
import java.sql.Date;

@Embeddable
public class OpenHour {
    private Date monOpen;
    private Date monClosed;
}
