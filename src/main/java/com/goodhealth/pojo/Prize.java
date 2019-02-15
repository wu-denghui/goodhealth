package com.goodhealth.pojo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.goodhealth.controller.ValidGroup1;


/**
 * The persistent class for the prize database table.
 * 
 */
@Entity
@Table(name="prize")
public class Prize implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prize_id")
	private int prizeId;

	@Column(name="prize_name")
	@NotBlank(message="积分奖品名不得为空")
	private String prizeName;

	@Column(name="prize_pic")
	@NotBlank(message="积分奖品图片不得为空")
	private String prizePic;

	@Column(name="prize_value")
	@NotNull(message="积分奖品兑换积分不得为空")
	private int prizeValue;

	public Prize() {
	}

	public int getPrizeId() {
		return this.prizeId;
	}

	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}

	public String getPrizeName() {
		return this.prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPrizePic() {
		return this.prizePic;
	}

	public void setPrizePic(String prizePic) {
		this.prizePic = prizePic;
	}

	public int getPrizeValue() {
		return this.prizeValue;
	}

	public void setPrizeValue(int prizeValue) {
		this.prizeValue = prizeValue;
	}

}