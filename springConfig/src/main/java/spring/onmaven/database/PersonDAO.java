package spring.onmaven.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import spring.onmaven.Models.Comments;
import spring.onmaven.Models.Person;

@Component 
public class PersonDAO {
	 
	public static int count;
	
	private static final String database = "jdbc:mysql://localhost:3306/people";
	private static final String username = "root";
	private static final String password = "root";
	private static Connection connection;
	
	static {
		try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            // handle the error
        }
		try {
			connection = DriverManager.getConnection(database, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Person> index() throws SQLException{
		List<Person> list = new ArrayList();
		Statement statement = connection.createStatement();
		String query = "select id, name, age from person";
		
		ResultSet result = statement.executeQuery(query);
		while(result.next()) {
			int id = result.getInt("id");
			String name = result.getString("name");
			int age = result.getInt("age");
			Person p = new Person(id, name, age);
			list.add(p);
		}
		
		return list;
	}
	
	public Person show_id(int id) throws SQLException {
		//return people.stream().filter(person->person.get_id() == id).findAny().orElse(null);
		Statement statement = connection.createStatement();
		String query = "select id, name, age from person where id="+id+"";
		
		ResultSet r = statement.executeQuery(query);
		Person person = new Person();
		while(r.next()) {
			person.setId(id);
			person.setName(r.getString("name"));
			person.setAge(r.getInt(3)); 
		}
		
		return person;
	}
	
	// take for one user
	public List<Comments> show_comment(int id) throws SQLException{
		List<Comments> list = new ArrayList();
		Statement statement = connection.createStatement();
		String query = "select c.id, c.title, c.body, p.name as author, c.date from comments as c join person as p on p.id = c.person_id where p.id="+id+" order by date desc";
		
		ResultSet r = statement.executeQuery(query);
		 
		while(r.next()) {
			Comments comment = new Comments();
			comment.setId(r.getInt("id"));
			comment.setTitle(r.getString("title"));
			comment.setText(r.getString("body"));
			comment.setAuth(r.getString("author"));
			comment.setDate(r.getDate("date").toString());
			list.add(comment);
		}
		
		return list;
	}
	 

	public void save(Person personObject) throws SQLException {
		// TODO Auto-generated method stub
		/*
		Statement statement = connection.createStatement();
		String query = "insert into person(name, age) values ('"+ personObject.getName()+"', '"+personObject.getAge()+"')";
		statement.executeUpdate(query);
		*/
		String query = "insert into person(name, age) values (?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, personObject.getName());
		statement.setInt(2, personObject.getAge());
		statement.executeUpdate();
		
	}

	public void update(int id, Person person) throws SQLException {
		 
		/*Statement statement = connection.createStatement();
		String query = "update person set name='"+person.getName()+"',age='"+
		person.getAge()+"' where id="+ id +"";
		statement.executeUpdate(query);*/
		String query = "update person set name=?, age=? where id=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, person.getName());
		statement.setInt(2, person.getAge());
		statement.setInt(3, id);
		statement.executeUpdate();
	}

	public void delete( int id) throws SQLException {
		 
		//people.removeIf(p -> p.get_id() == id);
		Statement statement = connection.createStatement();
		String query = "delete from person where id="+ id +"";
		statement.executeUpdate(query);
	}
	 
}
 