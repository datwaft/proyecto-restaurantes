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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "selectedadditionalcategory")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "SelectedAdditionalCategory.findAll", query = "SELECT s FROM SelectedAdditionalCategory s"),
  @NamedQuery(name = "SelectedAdditionalCategory.findById", query = "SELECT s FROM SelectedAdditionalCategory s WHERE s.id = :id")})
public class SelectedAdditionalCategory implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "selectedAdditionalCategory")
  @JsonbTransient
  private List<SelectedAdditional> selectedAdditionalList;
  @JoinColumn(name = "additionalCategory", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private AdditionalCategory additionalCategory;
  @JoinColumn(name = "selectedDish", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private SelectedDish selectedDish;

  public SelectedAdditionalCategory() {
  }

  public SelectedAdditionalCategory(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @XmlTransient
  public List<SelectedAdditional> getSelectedAdditionalList() {
    return selectedAdditionalList;
  }

  public void setSelectedAdditionalList(List<SelectedAdditional> selectedAdditionalList) {
    this.selectedAdditionalList = selectedAdditionalList;
  }

  public AdditionalCategory getAdditionalCategory() {
    return additionalCategory;
  }

  public void setAdditionalCategory(AdditionalCategory additionalCategory) {
    this.additionalCategory = additionalCategory;
  }

  public SelectedDish getSelectedDish() {
    return selectedDish;
  }

  public void setSelectedDish(SelectedDish selectedDish) {
    this.selectedDish = selectedDish;
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
    if (!(object instanceof SelectedAdditionalCategory)) {
      return false;
    }
    SelectedAdditionalCategory other = (SelectedAdditionalCategory) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.SelectedAdditionalCategory[ id=" + id + " ]";
  }

}
