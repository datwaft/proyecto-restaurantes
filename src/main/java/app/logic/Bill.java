package app.logic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "bill")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
  @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id"),
  @NamedQuery(name = "Bill.findByName", query = "SELECT b FROM Bill b WHERE b.name = :name"),
  @NamedQuery(name = "Bill.findByOrderType", query = "SELECT b FROM Bill b WHERE b.orderType = :orderType"),
  @NamedQuery(name = "Bill.findByOrderTime", query = "SELECT b FROM Bill b WHERE b.orderTime = :orderTime"),
  @NamedQuery(name = "Bill.findByStatus", query = "SELECT b FROM Bill b WHERE b.status = :status")})
public class Bill implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Size(max = 45)
  @Column(name = "name")
  private String name;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "orderType")
  private String orderType;
  @Basic(optional = false)
  @NotNull
  @Column(name = "orderTime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date orderTime;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "status")
  private String status;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
  @JsonbTransient
  private List<SelectedDish> selectedDishList;
  @JoinColumn(name = "address", referencedColumnName = "id")
  @ManyToOne
  private Address address;
  @JoinColumn(name = "user", referencedColumnName = "email")
  @ManyToOne
  private User user;

  public Bill() {
  }

  public Bill(Integer id) {
    this.id = id;
  }

  public Bill(Integer id, String orderType, Date orderTime, String status) {
    this.id = id;
    this.orderType = orderType;
    this.orderTime = orderTime;
    this.status = status;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public Date getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(Date orderTime) {
    this.orderTime = orderTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @XmlTransient
  public List<SelectedDish> getSelectedDishList() {
    return selectedDishList;
  }

  public void setSelectedDishList(List<SelectedDish> selectedDishList) {
    this.selectedDishList = selectedDishList;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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
    if (!(object instanceof Bill)) {
      return false;
    }
    Bill other = (Bill) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.Bill[ id=" + id + " ]";
  }

}
