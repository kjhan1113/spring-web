package com.springboot.webmvc.repository;

import com.mysql.cj.protocol.Resultset;
import com.springboot.webmvc.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{

    DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(this.dataSource);
    }

    private void closeConnection (Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if(rs != null){
                rs.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            if(pstmt != null){
                pstmt.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            if(conn != null){
                conn.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Member addMember(Member member) {
        String sql = "Insert into member (name) values (?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        System.out.println("----------------------+++++++++"+member.toString());

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("Fail to find id");
            }

            return member;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        } finally {
            closeConnection(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        } finally {
            closeConnection(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        } finally {
            closeConnection(conn, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAllMembers() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        } finally {
            closeConnection(conn, pstmt, rs);
        }
    }

    @Override
    public void clearData() {
    }
}
