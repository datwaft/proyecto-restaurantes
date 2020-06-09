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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario
 */
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
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Category categoryId;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "dishId")
  private List<Dishtoadditionaltype> dishtoadditionaltypeList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "dishId")
  private List<Bill> billList;

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

  public Category getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Category categoryId) {
    this.categoryId = categoryId;
  }

  @XmlTransient
  public List<Dishtoadditionaltype> getDishtoadditionaltypeList() {
    return dishtoadditionaltypeList;
  }

  public void setDishtoadditionaltypeList(List<Dishtoadditionaltype> dishtoadditionaltypeList) {
    this.dishtoadditionaltypeList = dishtoadditionaltypeList;
  }

  @XmlTransient
  public List<Bill> getBillList() {
    return billList;
  }

  public void setBillList(List<Bill> billList) {
    this.billList = billList;
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
