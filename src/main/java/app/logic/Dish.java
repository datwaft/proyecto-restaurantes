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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "dish")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Dish.findAll", query = "SELECT d FROM Dish d"),
  @NamedQuery(name = "Dish.findById", query = "SELECT d FROM Dish d WHERE d.id = :id"),
  @NamedQuery(name = "Dish.findByPrice", query = "SELECT d FROM Dish d WHERE d.price = :price"),
  @NamedQuery(name = "Dish.findByName", query = "SELECT d FROM Dish d WHERE d.name = :name"),
  @NamedQuery(name = "Dish.findByDescription", query = "SELECT d FROM Dish d WHERE d.description = :description")})
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
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "name")
  private String name;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "description")
  private String description;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "dish")
  @JsonbTransient
  private List<SelectedDish> selectedDishList;
  @JoinColumn(name = "category", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Category category;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "dish")
  @JsonbTransient
  private List<AdditionalCategory> additionalCategoryList;

  public Dish() {
  }

  public Dish(Integer id) {
    this.id = id;
  }

  public Dish(Integer id, int price, String name, String description) {
    this.id = id;
    this.price = price;
    this.name = name;
    this.description = description;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @XmlTransient
  public List<SelectedDish> getSelectedDishList() {
    return selectedDishList;
  }

  public void setSelectedDishList(List<SelectedDish> selectedDishList) {
    this.selectedDishList = selectedDishList;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @XmlTransient
  public List<AdditionalCategory> getAdditionalCategoryList() {
    return additionalCategoryList;
  }

  public void setAdditionalCategoryList(List<AdditionalCategory> additionalCategoryList) {
    this.additionalCategoryList = additionalCategoryList;
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
