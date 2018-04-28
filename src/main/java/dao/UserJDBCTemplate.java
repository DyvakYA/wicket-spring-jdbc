package dao;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJDBCTemplate implements UserRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void create(User user) {
		String SQL = "insert into User (firstname, secondname, age) values (?, ?, ?)";
        jdbcTemplate.update(SQL, user.getFirstName(),user.getSecondName(), user.getAge());
		System.out.println("Created Record Name = " + user.getFirstName() + " Age = " + user.getAge());
	}

	public User getUser(Integer id) {
		String SQL = "select * from User where id = ?";
		User user = jdbcTemplate.queryForObject(SQL, new Object[] { id }, new UserMapper());
		return user;
	}

	public List<User> listUsers() {
		String SQL = "select * from User";
		List<User> users = jdbcTemplate.query(SQL, new UserMapper());
		return users;
	}

	public void delete(Integer id) {
		String SQL = "delete from User where id = ?";
        jdbcTemplate.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
	}

	public void update(Integer id, User user) {
		String SQL = "update User set firstname = ?, secondname = ?, age = ? where id = ?";
		jdbcTemplate.update(SQL, new Object[] { user.getFirstName(), user.getSecondName(), user.getAge(), String.valueOf(id)});
		System.out.println("Updated Record with ID = " + id);
	}

	@Override
	public boolean findByLogin(String name) {
		String SQL = "select * from User where firstname = ?";
		List<User> users = jdbcTemplate.query(SQL, new Object[] { name },  new UserMapper());
		System.out.println("Checking user exist");
		return users.size()>0 ? true : false;
	}

	@Override
	public List<User> findByName(String value) {
		String tmp = String.valueOf(new StringBuilder("%").append(value).append("%"));
		String SQL = "select * from User where firstname Like ? OR secondname LIKE ?";
		List<User> users = jdbcTemplate.query(SQL, new Object[] { tmp , tmp },new UserMapper());
		System.out.println("Find Record with parameter = " + value);
		return users;
	}

}
