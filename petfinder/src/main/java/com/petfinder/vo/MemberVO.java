package com.petfinder.vo;
/**
 * 회원정보를 저장하기 위한 VO클래스
 * 
 * @author  김현진
 * @since 2016.11.14
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2016.11.14        김현진             최초 생성
 * 
 * </pre>
 */
public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String region1;
	private String region2;
	private String region3;
	private String dog;
	private String size;
	private String color;
	private String gender;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
		
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegion1() {
		return region1;
	}
	public void setRegion1(String region1) {
		this.region1 = region1;
	}
	public String getRegion2() {
		return region2;
	}
	public void setRegion2(String region2) {
		this.region2 = region2;
	}
	public String getRegion3() {
		return region3;
	}
	public void setRegion3(String region3) {
		this.region3 = region3;
	}
	public String getDog() {
		return dog;
	}
	public void setDog(String dog) {
		this.dog = dog;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}



	
}
