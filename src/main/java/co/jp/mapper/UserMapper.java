package co.jp.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.jp.model.dto.User;

@Repository
public interface UserMapper {
  User getUser(int userid);
  
  List<User> getUserList();
  
  void insertNewUser(User usr);
  
  void updateUser(User usr);
  
  void deleteUser(int userid);
}
