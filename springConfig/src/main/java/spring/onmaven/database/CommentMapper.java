package spring.onmaven.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.onmaven.Models.Comments;

public class CommentMapper implements RowMapper{

	@Override
	public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Comments comment = new Comments();
		
		 
		comment.setId(rs.getInt("id"));
		 
		comment.setTitle(rs.getString("title"));
		comment.setText(rs.getString("body"));
		comment.setAuth(rs.getString("author"));
		comment.setDate(rs.getDate("date").toString());

		return comment;
	}
	
}
