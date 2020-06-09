/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "additionaltype")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Additionaltype.findAll", query = "SELECT a FROM Additionaltype a"),
  @NamedQuery(name = "Additionaltype.findById", query = "SELECT a FROM Additionaltype a WHERE a.id = :id"),
  @NamedQuery(name = "Additionaltype.findByDescription", query = "SELECT a FROM Additionaltype a WHERE a.description = :description")})
public class Additionaltype implements Serializable {

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
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "additionalTypeId")
  private List<Additional> additionalList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "additionalTypeId")
  private List<Dishtoadditionaltype> dishtoadditionaltypeList;

  public Additionaltype() {
  }

  public Additionaltype(Integer id) {
    this.id = id;
  }

  public Additionaltype(Integer id, String description) {
    this.id = id;
    this.description = description;
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

  @XmlTransient
  public List<Additional> getAdditionalList() {
    return additionalList;
  }

  public void setAdditionalList(List<Additional> additionalList) {
    this.additionalList = additionalList;
  }

  @XmlTransient
  public List<Dishtoadditionaltype> getDishtoadditionaltypeList() {
    return dishtoadditionaltypeList;
  }

  public void setDishtoadditionaltypeList(List<Dishtoadditionaltype> dishtoadditionaltypeList) {
    this.dishtoadditionaltypeList = dishtoadditionaltypeList;
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
    if (!(object instanceof Additionaltype)) {
      return false;
    }
    Additionaltype other = (Additionaltype) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.Additionaltype[ id=" + id + " ]";
  }
  
}
