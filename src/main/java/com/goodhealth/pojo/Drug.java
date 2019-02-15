package com.goodhealth.pojo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import com.goodhealth.controller.ValidGroup1;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the drug database table.
 * 
 */
@Entity
@Table(name="drug")
public class Drug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="drug_id")
	private int drugId;

	@Column(name="drug_character")
	private String drugCharacter;

	@Column(name="drug_component")
	private String drugComponent;

	@Column(name="drug_function")
	private String drugFunction;

	@NotNull(message="药品价值积分不得为空",groups={ValidGroup1.class})
	@Column(name="drug_integral")
	private int drugIntegral;

	@NotBlank(message="药品名不得为空")
	@Column(name="drug_name")
	private String drugName;

	@Column(name="drug_pic")
	private String drugPic;

	@Column(name="drug_price")
	@DecimalMin(value = "0" ,message="药品价格不得为空")
	private BigDecimal drugPrice;

	@Column(name="drug_productor")
	private String drugProductor;

	@Column(name="drug_storage")
	private String drugStorage;

	@Column(name="drug_usage")
	private String drugUsage;

	//bi-directional many-to-one association to Shoppingcar
	@OneToMany(mappedBy="drug")
	private List<Shoppingcar> shoppingcars;

	public Drug() {
	}

	public int getDrugId() {
		return this.drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public String getDrugCharacter() {
		return this.drugCharacter;
	}

	public void setDrugCharacter(String drugCharacter) {
		this.drugCharacter = drugCharacter;
	}

	public String getDrugComponent() {
		return this.drugComponent;
	}

	public void setDrugComponent(String drugComponent) {
		this.drugComponent = drugComponent;
	}

	public String getDrugFunction() {
		return this.drugFunction;
	}

	public void setDrugFunction(String drugFunction) {
		this.drugFunction = drugFunction;
	}

	public int getDrugIntegral() {
		return this.drugIntegral;
	}

	public void setDrugIntegral(int drugIntegral) {
		this.drugIntegral = drugIntegral;
	}

	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugPic() {
		return this.drugPic;
	}

	public void setDrugPic(String drugPic) {
		this.drugPic = drugPic;
	}

	public BigDecimal getDrugPrice() {
		return this.drugPrice;
	}

	public void setDrugPrice(BigDecimal drugPrice) {
		this.drugPrice = drugPrice;
	}

	public String getDrugProductor() {
		return this.drugProductor;
	}

	public void setDrugProductor(String drugProductor) {
		this.drugProductor = drugProductor;
	}

	public String getDrugStorage() {
		return this.drugStorage;
	}

	public void setDrugStorage(String drugStorage) {
		this.drugStorage = drugStorage;
	}

	public String getDrugUsage() {
		return this.drugUsage;
	}

	public void setDrugUsage(String drugUsage) {
		this.drugUsage = drugUsage;
	}

	public List<Shoppingcar> getShoppingcars() {
		return this.shoppingcars;
	}

	public void setShoppingcars(List<Shoppingcar> shoppingcars) {
		this.shoppingcars = shoppingcars;
	}

	public Shoppingcar addShoppingcar(Shoppingcar shoppingcar) {
		getShoppingcars().add(shoppingcar);
		shoppingcar.setDrug(this);

		return shoppingcar;
	}

	public Shoppingcar removeShoppingcar(Shoppingcar shoppingcar) {
		getShoppingcars().remove(shoppingcar);
		shoppingcar.setDrug(null);

		return shoppingcar;
	}

}