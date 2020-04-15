package edu.yctc.user.entity;

/**
 * @author luna@win10
 * @date 2020/3/22 9:59
 */
public class DepartmentDO {

	private int id;

	private String name;

	public DepartmentDO() {
	}

	public DepartmentDO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DepartmentDO{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
