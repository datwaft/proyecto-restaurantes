package app.logic;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "selecteddish")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "SelectedDish.findAll", query = "SELECT s FROM SelectedDish s"),
  @NamedQuery(name = "SelectedDish.findById", query = "SELECT s FROM SelectedDish s WHERE s.id = :id"),
  @NamedQuery(name = "SelectedDish.findByQuantity", query = "SELECT s FROM SelectedDish s WHERE s.quantity = :quantity")})
public class SelectedDish implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "quantity")
  private int quantity;
  @JoinColumn(name = "bill", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Bill bill;
  @JoinColumn(name = "dish", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Dish dish;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "selectedDish")
  @JsonbTransient
  private List<SelectedAdditionalCategory> selectedAdditionalCategoryList;

  public SelectedDish() {
  }

  public SelectedDish(Integer id) {
    this.id = id;
  }

  public SelectedDish(Integer id, int quantity) {
    this.id = id;
    this.quantity = quantity;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Bill getBill() {
    return bill;
  }

  public void setBill(Bill bill) {
    this.bill = bill;
  }

  public Dish getDish() {
    return dish;
  }

  public void setDish(Dish dish) {
    this.dish = dish;
  }

  @XmlTransient
  public List<SelectedAdditionalCategory> getSelectedAdditionalCategoryList() {
    return selectedAdditionalCategoryList;
  }

  public void setSelectedAdditionalCategoryList(List<SelectedAdditionalCategory> selectedAdditionalCategoryList) {
    this.selectedAdditionalCategoryList = selectedAdditionalCategoryList;
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
    if (!(object instanceof SelectedDish)) {
      return false;
    }
    SelectedDish other = (SelectedDish) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.SelectedDish[ id=" + id + " ]";
  }

}
