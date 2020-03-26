package com.signup11.be.model;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(value = "users")
public class User {

  @Id
  private Long seq;

  private final String id;

  @JsonIgnore
  private final String password;

  private final String name;

  private final String birth;

  private final String gender;

  private final String email;

  private final String phone;

  @Column(value = "create_at")
  private final LocalDateTime createdDate;

  public User(String id, String password, String name, String birth, String gender,
      String email, String phone, LocalDateTime createdDate) {
    this.id = id;
    this.password = password;
    this.name = name;
    this.birth = birth;
    this.gender = gender;
    this.email = email;
    this.phone = phone;
    this.createdDate = defaultIfNull(createdDate, now());
  }

  public Long getSeq() {
    return seq;
  }

  public String getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public String getBirth() {
    return birth;
  }

  public String getGender() {
    return gender;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void login(String password) {
    if (!this.password.equals(password)) {
      throw new IllegalArgumentException("잘못된 패스워드 입력입니다");
    }
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equal(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(seq);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("seq", seq)
        .append("id", id)
        .append("password", password)
        .append("name", name)
        .append("createdDate", createdDate)
        .toString();
  }


  static public class Builder {

    private String id;
    private String password;
    private String name;
    private String birth;
    private String gender;
    private String email;
    private String phone;
    private LocalDateTime createdDate;

    public Builder() {
    }

    public Builder(User user) {
      this.id = user.id;
      this.password = user.password;
      this.name = user.name;
      this.birth = user.birth;
      this.gender = user.gender;
      this.email = user.email;
      this.phone = user.phone;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder birth(String birth) {
      this.birth = birth;
      return this;
    }

    public Builder gender(String gender) {
      this.gender = gender;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public Builder createdDate(LocalDateTime createdDate) {
      this.createdDate = createdDate;
      return this;
    }


    public User build() {
      return new User(id, password, name, birth, gender, email, phone, createdDate);
    }
  }
}
