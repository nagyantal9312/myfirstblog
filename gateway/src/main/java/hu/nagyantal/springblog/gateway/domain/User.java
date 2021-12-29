package hu.nagyantal.springblog.gateway.domain;

import hu.nagyantal.springblog.gateway.config.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Table("user")
@Data
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column("username")
    private String username;

    @NotNull
    @Size(min = 60, max = 60)
    @Column("password_hash")
    private String password;

    @Size(max = 50)
    @Column("first_name")
    private String firstName;

    @Size(max = 50)
    @Column("last_name")
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @NotNull
    private boolean activated = false;

    @Size(min = 2, max = 10)
    @Column("lang_key")
    private String langKey;

    @Size(max = 256)
    @Column("image_url")
    private String imageUrl;

    @Size(max = 20)
    @Column("activation_key")
    private String activationKey;

    @Size(max = 20)
    @Column("reset_key")
    private String resetKey;

    @Column("reset_date")
    private Instant resetDate;

    @Transient
    private Set<Authority> authorities = new HashSet<>();

    public void setUsername(String username) {
        this.username = StringUtils.lowerCase(username, Locale.ENGLISH);
    }











}
