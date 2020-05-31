package app.logic;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "dish")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Dish.findAll", query = "SELECT d FROM Dish d"),
  @NamedQuery(name = "Dish.findById", query = "SELECT d FROM Dish d WHERE d.id = :id"),
  @NamedQuery(name = "Dish.findByPrice", query = "SELECT d FROM Dish d WHERE d.price = :price")})
public class Dish implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "price")
  private int price;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "dishId")
  private Collection<Dishtoaditionaltype> dishtoaditionaltypeCollection;
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Category categoryId;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "dishId")
  private Collection<Order1> order1Collection;

  public Dish() {
  }

  public Dish(Integer id) {
    this.id = id;
  }

  public Dish(Integer id, int price) {
    this.id = id;
    this.price = price;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @XmlTransient
  public Collection<Dishtoaditionaltype> getDishtoaditionaltypeCollection() {
    return dishtoaditionaltypeCollection;
  }

  public void setDishtoaditionaltypeCollection(Collection<Dishtoaditionaltype> dishtoaditionaltypeCollection) {
    this.dishtoaditionaltypeCollection = dishtoaditionaltypeCollection;
  }

  public Category getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Category categoryId) {
    this.categoryId = categoryId;
  }

  @XmlTransient
  public Collection<Order1> getOrder1Collection() {
    return order1Collection;
  }

  public void setOrder1Collection(Collection<Order1> order1Collection) {
    this.order1Collection = order1Collection;
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
    if (!(object instanceof Dish)) {
      return false;
    }
    Dish other = (Dish) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.Dish[ id=" + id + " ]";
  }

}
