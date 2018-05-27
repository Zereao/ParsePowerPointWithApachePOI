package com.parse.ppt.poi.dao;

import com.parse.ppt.poi.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * @author Jupiter
 */
@Repository
public interface UserDao {
    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return User
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM user"
            + "     WHERE 1=1 "
            + "	    AND id = #{id,jdbcType=INTEGER}"
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "username", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_num", property = "phoneNum", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "main_page_essay_title", property = "mainPageEssayTitle", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "main_page_essay_content", property = "mainPageEssayContent", javaType = String.class, jdbcType = JdbcType.LONGNVARCHAR)})
    User getUserById(@Param("id") Integer id);


    /**
     * 通过email获取用户信息
     *
     * @param email 用户电子邮箱
     * @return User
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM user"
            + "     WHERE 1=1 "
            + "	    AND email = #{email,jdbcType=VARCHAR}"
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "username", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_num", property = "phoneNum", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "main_page_essay_title", property = "mainPageEssayTitle", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "main_page_essay_content", property = "mainPageEssayContent", javaType = String.class, jdbcType = JdbcType.LONGNVARCHAR)})
    User getUserByEmail(@Param("email") String email);

    /**
     * 通过手机号码获取用户信息
     *
     * @param phoneNum 用户手机号码
     * @return User
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM user"
            + "     WHERE 1=1 "
            + "	      AND phone_num = #{phoneNum,jdbcType=VARCHAR}"
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "username", property = "username", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_num", property = "phoneNum", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "main_page_essay_title", property = "mainPageEssayTitle", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "main_page_essay_content", property = "mainPageEssayContent", javaType = String.class, jdbcType = JdbcType.LONGNVARCHAR)})
    User getUserByPhoneNum(@Param("phoneNum") String phoneNum);

    /**
     * 增加用户
     *
     * @param user 增加的用户对象
     */
    @Insert(value = "<script>"
            + "     INSERT INTO user (username, email, phone_num, password, main_page_essay_title, main_page_essay_content)"
            + "     VALUES (#{username,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR},"
            + "             #{phoneNum,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR},"
            + "             #{mainPageEssayTitle,jdbcType=VARCHAR}, #{mainPageEssayContent,jdbcType=LONGNVARCHAR})"
            + "</script>")
    void addUser(User user);

    /**
     * 更新用户首页文章
     *
     * @param email        用户email
     * @param essayTitle   用户首页-文章标题
     * @param essayContent 用户首页-文章内容
     */
    @Update(value = "<script>"
            + "     UPDATE user"
            + "        <set>"
            + "           <if test=\"essayTitle != null\">"
            + "              main_page_essay_title = #{essayTitle,jdbcType=VARCHAR},"
            + "           </if>"
            + "           <if test=\"essayContent != null\">"
            + "              main_page_essay_content = #{essayContent,jdbcType=VARCHAR},"
            + "           </if>"
            + "        </set>"
            + "      WHERE email = #{email,jdbcType=VARCHAR}"
            + "</script>")
    void updateUserEssay(@Param("email") String email,
                         @Param("essayTitle") String essayTitle,
                         @Param("essayContent") String essayContent);
}
