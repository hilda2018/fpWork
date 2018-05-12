package com.fresh.model;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
import com.nova.frame.annotation.Transient;

@Table("FCT_MEMBER")
@SuppressWarnings("unused")
public class Member {

	/**
	 * 会员id
	 */
	@Id(auto = false)
	private Integer memberId;
	/**
	 * 会员登陆名
	 */
	private String memberName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 积分
	 */
	private Integer totalPoint;
	/**
	 * 商铺id
	 */
	private Integer shopId;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * qq
	 */
	private String qq;
	/**
	 * email
	 */
	private String email;
	/**
	 * 职务
	 */
	private Integer positionId;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 会员状态 0正常 1 禁用
	 */
	private Integer state;
	/**
	 * 部门
	 */
	private String department;
	/**
	 * 登陆次数
	 */
	private Integer loginTimes = 0;
	/**
	 * 是否认证商铺
	 */
	private Integer isAuthMember;
	/**
	 * 是否跨境卖家认证
	 */
	private Integer isRuauthMember;
	/**
	 * 手机、邮箱是否认证
	 */
	private Integer authNum;
	@Transient
	private boolean isMobileAuth;
	@Transient
	private boolean isMailAuth;
	@Transient
	private Integer shopStatus;

	public void setMobileAuth(boolean isMobileAuth) {
		this.isMobileAuth = isMobileAuth;
	}

	public void setMailAuth(boolean isMailAuth) {
		this.isMailAuth = isMailAuth;
	}

	public boolean getIsMobileAuth() {
		if (this.authNum == null)
			this.authNum = 10;
		return !((this.authNum & 4) == 0);
	}

	public boolean getIsMailAuth() {
		if (this.authNum == null)
			this.authNum = 10;
		return !((this.authNum & 16) == 0);
	}

	public Integer getAuthNum() {
		return authNum;
	}

	public void setAuthNum(Integer authNum) {
		this.authNum = authNum;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getIsRuauthMember() {
		return isRuauthMember;
	}

	public void setIsRuauthMember(Integer isRuauthMember) {
		this.isRuauthMember = isRuauthMember;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		if (this.phone == null) {
			this.phone = "";
		}
		return phone;
	}

	public void setPhone(String phone) {

		this.phone = phone;
	}

	public String getMobile() {
		if (this.mobile == null) {
			this.mobile = "";
		}
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setIsAuthMember(Integer isAuthMember) {
		this.isAuthMember = isAuthMember;
	}

	public Integer getIsAuthMember() {
		return isAuthMember;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public Integer getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(Integer shopStatus) {
		this.shopStatus = shopStatus;
	}

	public Integer getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}
}