package hu.nagyantal.springblog.gateway.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Table("authority")
@Data
public class Authority implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(max = 50)
    private String name;

    @Override
    public String getId() {
        return name;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
