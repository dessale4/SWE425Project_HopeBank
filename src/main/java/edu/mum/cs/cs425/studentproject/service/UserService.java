package edu.mum.cs.cs425.studentproject.service;

import java.util.List;

import edu.mum.cs.cs425.studentproject.model.User;

public interface UserService {
	public void addUser(User user);
	public List<User> findAllUsers();
	public User findUserById(Long id);
}
