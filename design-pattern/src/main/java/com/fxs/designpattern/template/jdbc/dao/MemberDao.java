package com.fxs.designpattern.template.jdbc.dao;

import com.fxs.designpattern.template.jdbc.JdbcTemplate;
import com.fxs.designpattern.template.jdbc.RowMapper;
import com.fxs.designpattern.template.jdbc.pojo.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class MemberDao extends JdbcTemplate {
    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<?> selectAll() {
        String sql = "select * from t_member";
        return super.executeQuery(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rumNum) throws Exception {
                Member member = new Member();
                member.setUserName(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                member.setNickName(rs.getString("nickname"));
                member.setAge(rs.getInt("age"));
                member.setAddr(rs.getString("addr"));
                return member;
            }
        }, null);
    }
}
