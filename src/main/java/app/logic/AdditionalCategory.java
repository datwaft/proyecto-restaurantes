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
@Table(name = "additionalcategory")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "AdditionalCategory.findAll", query = "SELECT a FROM AdditionalCategory a"),
  @NamedQuery(name = "AdditionalCategory.findById", query = "SELECT a FROM AdditionalCategory a WHERE a.id = :id"),
  @NamedQuery(name = "AdditionalCategory.findByDescription", query = "SELECT a FROM AdditionalCategory a WHERE a.description = :description"),
  @NamedQuery(name = "AdditionalCategory.findByMultiple", query = "SELECT a FROM AdditionalCategory a WHERE a.multiple = :multiple"),
  @NamedQuery(name = "AdditionalCategory.findByRequired", query = "SELECT a FROM AdditionalCategory a WHERE a.required = :required")})
public class AdditionalCategory implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 25)
  @Column(name = "description")
  private String description;
  @Basic(optional = false)
  @NotNull
  @Column(name = "multiple")
  private boolean multiple;
  @Basic(optional = false)
  @NotNull
  @Column(name = "required")
  private boolean required;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "additionalCategory")
  @JsonbTransient
  private List<Additional> additionalList;
  @JoinColumn(name = "dish", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Dish dish;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "additionalCategory")
  @JsonbTransient
  private List<SelectedAdditionalCategory> selectedAdditionalCategoryList;

  public AdditionalCategory() {
  }

  public AdditionalCategory(Integer id) {
    this.id = id;
  }

  public AdditionalCategory(Integer id, String description, boolean multiple, boolean required) {
    this.id = id;
    this.description = description;
    this.multiple = multiple;
    this.required = required;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean getMultiple() {
    return multiple;
  }

  public void setMultiple(boolean multiple) {
    this.multiple = multiple;
  }

  public boolean getRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  @XmlTransient
  public List<Additional> getAdditionalList() {
    return additionalList;
  }

  public void setAdditionalList(List<Additional> additionalList) {
    this.additionalList = additionalList;
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
    if (!(object instanceof AdditionalCategory)) {
      return false;
    }
    AdditionalCategory other = (AdditionalCategory) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.AdditionalCategory[ id=" + id + " ]";
  }

}
