/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "dishtoadditionaltype")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Dishtoadditionaltype.findAll", query = "SELECT d FROM Dishtoadditionaltype d"),
  @NamedQuery(name = "Dishtoadditionaltype.findById", query = "SELECT d FROM Dishtoadditionaltype d WHERE d.id = :id")})
public class Dishtoadditionaltype implements Serializable {

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

  public Dishtoadditionaltype() {
  }

  public Dishtoadditionaltype(Integer id) {
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
    if (!(object instanceof Dishtoadditionaltype)) {
      return false;
    }
    Dishtoadditionaltype other = (Dishtoadditionaltype) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.Dishtoadditionaltype[ id=" + id + " ]";
  }
  
}
