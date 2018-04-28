package entity;

public class User {

	private Integer id;
	private String firstName;
	private String secondName;
	private Integer age;

	public static class Builder {

		User instance = new User();

		public Builder setId(Integer id) {
			instance.id = id;
			return this;
		}

		public Builder setFirstName(String name) {
			instance.firstName = name;
			return this;
		}

		public Builder setSecondName(String name) {
			instance.secondName = name;
			return this;
		}

		public Builder setAge(Integer age) {
			instance.age = age;
			return this;
		}

		public User build() {
			return instance;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", secondName='" + secondName + '\'' +
				", age=" + age +
				'}';
	}
}
