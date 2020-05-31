package app.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class UserPK implements Serializable {

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 32)
  @Column(name = "User")
  private String user;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 15)
  @Column(name = "id")
  private String id;

  public UserPK() {
  }

  public UserPK(String user, String id) {
    this.user = user;
    this.id = id;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (user != null ? user.hashCode() : 0);
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof UserPK)) {
      return false;
    }
    UserPK other = (UserPK) object;
    if ((this.user == null && other.user != null) || (this.user != null && !this.user.equals(other.user))) {
      return false;
    }
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.UserPK[ user=" + user + ", id=" + id + " ]";
  }

}
