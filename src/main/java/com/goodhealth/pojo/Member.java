package com.goodhealth.pojo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;


/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="member_id")
	private int memberId;

	@Column(name="member_address")
	private String memberAddress;

	@Column(name="member_birthday")
	private String memberBirthday;

	@Column(name="member_email")
	@Email(message="请输入正确的邮箱地址")
	private String memberEmail;

	@Column(name="member_gender")
	@NotNull(message="性别不可以为空")
	private int memberGender;

	@Column(name="member_integral")
	private int memberIntegral;

	@Column(name="member_name")
	@NotBlank(message="用户名不能为空")
	@Pattern(regexp="^[a-zA-Z0-9]{4,10}$",message="用户名是数字字母的4~10位组合")
	private String memberName;

	@Column(name="member_password")
	@NotBlank(message="密码不能为空")
	@Pattern(regexp="^[a-zA-Z0-9]{4,16}$",message="密码是数字字母的4~16位组合")
	private String memberPassword;

	@Column(name="member_status")
	private int memberStatus;

	@Column(name="member_tell")
	@NotNull
	@Pattern(regexp="^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",message="请输入正确的电话号码")
	private String memberTell;

	//bi-directional many-to-one association to Shoppingcar
	@OneToMany(mappedBy="member")
	private List<Shoppingcar> shoppingcars;
	


	public Member() {
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberAddress() {
		return this.memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberBirthday() {
		return this.memberBirthday;
	}

	public void setMemberBirthday(String memberBirthday) {
		this.memberBirthday = memberBirthday;
	}

	public String getMemberEmail() {
		return this.memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public int getMemberGender() {
		return this.memberGender;
	}

	public void setMemberGender(int memberGender) {
		this.memberGender = memberGender;
	}

	public int getMemberIntegral() {
		return this.memberIntegral;
	}

	public void setMemberIntegral(int memberIntegral) {
		this.memberIntegral = memberIntegral;
	}

	public String getMemberName() {
		return this.memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPassword() {
		return this.memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public int getMemberStatus() {
		return this.memberStatus;
	}

	public String getMemberTell() {
		return this.memberTell;
	}

	public void setMemberTell(String memberTell) {
		this.memberTell = memberTell;
	}

	public List<Shoppingcar> getShoppingcars() {
		return this.shoppingcars;
	}

	public void setShoppingcars(List<Shoppingcar> shoppingcars) {
		this.shoppingcars = shoppingcars;
	}

	public Shoppingcar addShoppingcar(Shoppingcar shoppingcar) {
		getShoppingcars().add(shoppingcar);
		shoppingcar.setMember(this);

		return shoppingcar;
	}

	public Shoppingcar removeShoppingcar(Shoppingcar shoppingcar) {
		getShoppingcars().remove(shoppingcar);
		shoppingcar.setMember(null);

		return shoppingcar;
	}

}