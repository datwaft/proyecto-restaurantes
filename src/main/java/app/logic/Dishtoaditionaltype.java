package app.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "dishtoaditionaltype")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Dishtoaditionaltype.findAll", query = "SELECT d FROM Dishtoaditionaltype d"),
  @NamedQuery(name = "Dishtoaditionaltype.findById", query = "SELECT d FROM Dishtoaditionaltype d WHERE d.id = :id")})
public class Dishtoaditionaltype implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @JoinColumn(name = "additional_type_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Additionaltype additionalTypeId;
  @JoinColumn(name = "dish_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Dish dishId;

  public Dishtoaditionaltype() {
  }

  public Dishtoaditionaltype(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Additionaltype getAdditionalTypeId() {
    return additionalTypeId;
  }

  public void setAdditionalTypeId(Additionaltype additionalTypeId) {
    this.additionalTypeId = additionalTypeId;
  }

  public Dish getDishId() {
    return dishId;
  }

  public void setDishId(Dish dishId) {
    this.dishId = dishId;
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
    if (!(object instanceof Dishtoaditionaltype)) {
      return false;
    }
    Dishtoaditionaltype other = (Dishtoaditionaltype) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.Dishtoaditionaltype[ id=" + id + " ]";
  }

}
