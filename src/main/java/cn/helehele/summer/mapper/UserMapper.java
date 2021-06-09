package cn.helehele.summer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.helehele.summer.bean.User;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM user WHERE id = #{id}")
	User getById(@Param("id") long id);

	@Select("SELECT * FROM user WHERE email = #{email}")
	User getByEmail(@Param("email") String email);

	@Select("SELECT * FROM user LIMIT #{offset}, #{maxResults}")
	List<User> getAll(@Param("offset") int offset, @Param("maxResults") int maxResults);

	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	@Insert("INSERT INTO user (email, password, name, updatedAt) VALUES (#{user.email}, #{user.password}, #{user.name}, #{user.updatedAt})")
	void insert(@Param("user") User user);

	@Update("UPDATE user SET name = #{user.name}, password = #{user.password}, updatedAt = #{user.updatedAt} WHERE id = #{user.id}")
	void update(@Param("user") User user);

	@Delete("DELETE FROM user WHERE id = #{id}")
	void deleteById(@Param("id") long id);
}
