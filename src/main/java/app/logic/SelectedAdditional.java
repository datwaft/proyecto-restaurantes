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
@Table(name = "selectedadditional")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "SelectedAdditional.findAll", query = "SELECT s FROM SelectedAdditional s"),
  @NamedQuery(name = "SelectedAdditional.findById", query = "SELECT s FROM SelectedAdditional s WHERE s.id = :id")})
public class SelectedAdditional implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @JoinColumn(name = "additional", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Additional additional;
  @JoinColumn(name = "selectedAdditionalCategory", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private SelectedAdditionalCategory selectedAdditionalCategory;

  public SelectedAdditional() {
  }

  public SelectedAdditional(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Additional getAdditional() {
    return additional;
  }

  public void setAdditional(Additional additional) {
    this.additional = additional;
  }

  public SelectedAdditionalCategory getSelectedAdditionalCategory() {
    return selectedAdditionalCategory;
  }

  public void setSelectedAdditionalCategory(SelectedAdditionalCategory selectedAdditionalCategory) {
    this.selectedAdditionalCategory = selectedAdditionalCategory;
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
    if (!(object instanceof SelectedAdditional)) {
      return false;
    }
    SelectedAdditional other = (SelectedAdditional) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.SelectedAdditional[ id=" + id + " ]";
  }

}
