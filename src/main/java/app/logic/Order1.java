package app.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "order")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o"),
  @NamedQuery(name = "Order1.findById", query = "SELECT o FROM Order1 o WHERE o.id = :id"),
  @NamedQuery(name = "Order1.findByAddress", query = "SELECT o FROM Order1 o WHERE o.address = :address"),
  @NamedQuery(name = "Order1.findByClient", query = "SELECT o FROM Order1 o WHERE o.client = :client"),
  @NamedQuery(name = "Order1.findByDeliveryType", query = "SELECT o FROM Order1 o WHERE o.deliveryType = :deliveryType")})
public class Order1 implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "address")
  private String address;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "client")
  private String client;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "deliveryType")
  private String deliveryType;
  @JoinColumn(name = "dish_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Dish dishId;
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @ManyToOne
  private User userId;

  public Order1() {
  }

  public Order1(Integer id) {
    this.id = id;
  }

  public Order1(Integer id, String address, String client, String deliveryType) {
    this.id = id;
    this.address = address;
    this.client = client;
    this.deliveryType = deliveryType;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public String getDeliveryType() {
    return deliveryType;
  }

  public void setDeliveryType(String deliveryType) {
    this.deliveryType = deliveryType;
  }

  public Dish getDishId() {
    return dishId;
  }

  public void setDishId(Dish dishId) {
    this.dishId = dishId;
  }

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Order1)) {
      return false;
    }
    Order1 other = (Order1) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.Order1[ id=" + id + " ]";
  }

}
