package com.luna.user.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Luna@win10
 * @date 2020/3/21 14:42
 */
public class UserDO {

	private String name;
	private int id;
	private int gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	private DepartmentDO department;

	public DepartmentDO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDO department) {
		this.department = department;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public UserDO(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public UserDO() {
	}

	public UserDO(int id, String name, int gender, Date birth, DepartmentDO department) {
		this.name = name;
		this.id = id;
		this.gender = gender;
		this.birth = birth;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserDO{" +
				"name='" + name + '\'' +
				", id=" + id +
				", gender=" + gender +
				", birth=" + birth +
				", department=" + department +
				'}';
	}
}
