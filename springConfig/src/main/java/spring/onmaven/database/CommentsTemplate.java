package spring.onmaven.database;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import spring.onmaven.Models.Comments;
import spring.onmaven.Models.Pages;
import spring.onmaven.Models.Person;

@Component
public class CommentsTemplate {
	private final JdbcTemplate jdbcT;
	
	@Autowired
	public CommentsTemplate(JdbcTemplate jdbcT) {
		this.jdbcT = jdbcT;
	}
	
	public List<Comments> index(){
 
		return jdbcT.query("select c.id, c.title, c.body, p.name as author, c.date from comments as c join person as p on c.person_id = p.id order by c.date desc", new CommentMapper());
	}
	//get all db size int number (+1 if rest remains )
	
	public int CountAll() {
		
		int result = jdbcT.queryForObject("select count(*) from Comments", Integer.class);

		return result;
		
	}
	
	public List<Comments> indexPagination(){
		//limit 
		return jdbcT.query("select c.id, c.title, c.body, p.name as author, c.date from comments as c join person as p on c.person_id = p.id order by c.date desc",
						 new CommentMapper());
			}
	
	public Comments show(int id) throws SQLException{
		String query = "select c.id, c.title, c.body as text, p.name as author, c.date from comments as c join person as p on c.person_id = p.id where c.id=?";
	
		return jdbcT.query(query, new Object[] {id}, new BeanPropertyRowMapper<>(Comments.class)).stream().findAny().orElse(null);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	
	public void save(Comments comments, Person p) {
		comments.setCurrentDate();
		  System.out.println(comments.getPerson_id());
		jdbcT.update("INSERT INTO Comments(title, body, person_id, date)  VALUES(?, ?, ?,?)", comments.getTitle(), comments.getText(),
                 comments.getPerson_id(), comments.getDate());
    }
	
	
	public void update(int id, Comments comments) throws SQLException{
		 
		jdbcT.update("UPDATE Comments SET title=?, body=?, person_id=?, date=? WHERE id=?", comments.getTitle(), comments.getText(),
                comments.getPerson_id(), comments.getDate(), id);
    }

    public void delete(int id) {
    	jdbcT.update("DELETE FROM Comments WHERE id=?", id);
    }
}
