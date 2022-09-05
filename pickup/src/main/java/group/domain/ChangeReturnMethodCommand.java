package group.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
public class ChangeReturnMethodCommand {

        private String pickupMethod;

        @JsonCreator
        @Builder
        public ChangeReturnMethodCommand(@JsonProperty("returnMethod") String pickupMethod)
        {
                this.pickupMethod = pickupMethod;
        }

}
