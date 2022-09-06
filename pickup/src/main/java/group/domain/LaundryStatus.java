package group.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name="LaundryStatus_table")
@Data
public class LaundryStatus {

        @Id
        @GeneratedValue(strategy=GenerationType.TABLE)
        private Long id;

        private String status;
        private String returnMethod;
        private Long pickupId;

}