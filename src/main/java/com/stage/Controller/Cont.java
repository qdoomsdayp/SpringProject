package com.stage.Controller;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/")
public class Cont {
    Connection conn;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String User(Model model) throws SQLException, ClassNotFoundException {
        this.conn = new Connect().getConnect();
        List<User> listUsers = allUsers();
        List<Role> listRoles = allRole();
        model.addAttribute("listRoles", new Gson().toJson(listRoles));
        model.addAttribute("listUsers", new Gson().toJson(listUsers));
        return "users";
    }

    @RequestMapping(value = "roles", method = RequestMethod.GET)
    public String Role(Model model) throws SQLException, ClassNotFoundException {
        this.conn = new Connect().getConnect();
        List<Role> listRolesR = allRole();
        model.addAttribute("listRolesR", new Gson().toJson(listRolesR));
        return "roles";
    }

    @RequestMapping(value = "addU", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<User>> addUser(@RequestBody User us) throws IOException, SQLException {

        System.out.println("ADD - OK");

        return new ResponseEntity<>(add(us), HttpStatus.OK);
    }

    @RequestMapping(value = "deleteU", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<User>> delUser(@RequestBody User us) throws IOException, SQLException {

        System.out.println("DEL - OK");
        return new ResponseEntity<>(delete(us), HttpStatus.OK);
    }

    @RequestMapping(value = "editU", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<User>> editUser(@RequestBody User us) throws IOException, SQLException {

        System.out.println("EDIR - OK");

        return new ResponseEntity<>(edit(us), HttpStatus.OK);
    }

    @RequestMapping(value = "roles/select", method = RequestMethod.POST)
    public
    @ResponseBody
    Role selectRole(@RequestBody Role role) throws IOException, SQLException {

        System.out.println("SELECT R - OK");

        return selectListR(role);
    }

    @RequestMapping(value = "roles/delete", method = RequestMethod.POST)
    public ResponseEntity<List<Role>> delRole(@RequestBody Role role) throws IOException, SQLException {

        System.out.println("DEL R - OK");

        return new ResponseEntity<>(deleteR(role), HttpStatus.OK);
    }

    @RequestMapping(value = "roles/edit", method = RequestMethod.POST)
    public ResponseEntity<List<Role>> editRole(@RequestBody Role role) throws IOException, SQLException {

        System.out.println("EDIT R - OK");

        return new ResponseEntity<>(editR(role), HttpStatus.OK);
    }

    @RequestMapping(value = "roles/addR", method = RequestMethod.POST)
    public ResponseEntity<List<Role>> addRole(@RequestBody Role role) throws IOException, SQLException {

        System.out.println("ADD R - OK");

        return new ResponseEntity<>(addR(role), HttpStatus.OK);
    }

    public List<Role> allRole() throws SQLException {
        List<Role> listRoles = new ArrayList<>();
        PreparedStatement preparedStatement1 = conn.prepareStatement("select * from usersandrolls.rolls");
        ResultSet resultSet = preparedStatement1.executeQuery();
        while (resultSet.next()) {
            listRoles.add(new Role(resultSet.getString("id_roll"),
                    resultSet.getString("name")));
        }
        return listRoles;
    }

    public List<User> allUsers() throws SQLException {
        List<User> listUsers = new ArrayList<>();

        PreparedStatement preparedStatement = conn.prepareStatement("select * from usersandrolls.users");
        ResultSet resultSet = preparedStatement.executeQuery();
        String str = "";
        while (resultSet.next()) {
            listUsers.add(new User(resultSet.getString("id_users"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("id_roll")));
        }
        return listUsers;
    }

    public List<User> add(User us) throws SQLException, IOException {
        String name = us.getName();
        String email = us.getEmail();
        int idrole = Integer.parseInt(us.getIdrole());
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO usersandrolls.users " +
                "(name,email,id_roll) VALUES (?,?,?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, idrole);
        preparedStatement.executeUpdate();

        return allUsers();
    }

    public List<User> delete(User us) throws SQLException, IOException {
        int delet = Integer.parseInt(us.getId());
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE from usersandrolls.users  WHERE id_users = ?");
        preparedStatement.setInt(1, delet);
        preparedStatement.executeUpdate();
        return allUsers();
    }

    public List<User> edit(User us) throws SQLException, IOException {
        int edit = Integer.parseInt(us.getId());
        String name = us.getName();
        String email = us.getEmail();
        int idrole = Integer.parseInt(us.getIdrole());
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE usersandrolls.users SET name =?,email = ?,id_roll = ? WHERE id_users = " + edit);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, idrole);
        preparedStatement.executeUpdate();

        return allUsers();
    }

    public Role selectListR(Role role) throws IOException, SQLException {
        String idR = role.getIdR();
        PreparedStatement preparedStatement1 = conn.prepareStatement("SELECT * FROM usersandrolls.rolls " +
                "WHERE id_roll = " + "\"" + idR + "\"");
        ResultSet resultSet = preparedStatement1.executeQuery();
        resultSet.next();

        role.setIdR(idR);
        role.setNameR(resultSet.getString("name"));
        return role;
    }

    public List<Role> deleteR(Role role) throws SQLException, IOException {
        String delet = role.getIdR();
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE from usersandrolls.rolls  WHERE id_roll = ?");
        preparedStatement.setString(1, delet);
        preparedStatement.executeUpdate();
        return allRole();
    }


    public List<Role> editR(Role role) throws SQLException, IOException {
        String edit = role.getIdR();
        String name = role.getNameR();
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE usersandrolls.rolls SET name =? WHERE id_roll = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, edit);
        preparedStatement.executeUpdate();
        return allRole();

    }

    public List<Role> addR(Role role) throws SQLException, IOException {
        String name = role.getNameR();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO usersandrolls.rolls " +
                "(name) VALUES (?)");
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();

        return allRole();
    }
}
