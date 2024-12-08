package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserRole;
import com.example.demo.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    
    @Insert("INSERT INTO user_role (user_id, role_id) VALUES (#{userId}, #{roleId})")
    int insert(@Param("userId") Long userId, @Param("roleId") Long roleId);
    
    @Delete("DELETE FROM user_role WHERE user_id = #{userId} AND role_id = #{roleId}")
    int delete(@Param("userId") Long userId, @Param("roleId") Long roleId);
    
    @Select("SELECT COUNT(*) FROM user_role WHERE user_id = #{userId} AND role_id = #{roleId}")
    boolean exists(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Insert("<script>" +
            "INSERT INTO user_role (user_id, role_id) VALUES " +
            "<foreach collection='userRoles' item='item' separator=','>" +
            "(#{item.userId}, #{item.roleId})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("userRoles") List<UserRole> userRoles);

    @Select("SELECT role_id FROM user_role WHERE user_id = #{userId}")
    List<Role> selectRolesByUserId(@Param("userId") Long userId);

    // 删除用户角色
    @Delete("DELETE FROM user_role WHERE user_id = #{userId}")
    int deleteByUserId(@Param("userId") Long userId);
}
