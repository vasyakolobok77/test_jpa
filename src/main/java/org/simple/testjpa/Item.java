package org.simple.testjpa;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_items")
@Entity
public class Item {
    @Id
    private Integer id;

    private Instant ts;

    private Instant tstz;

}
