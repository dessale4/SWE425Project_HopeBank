package edu.mum.cs.cs425.studentproject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.studentproject.model.User;
import edu.mum.cs.cs425.studentproject.repository.UserRepository;
import edu.mum.cs.cs425.studentproject.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}
	@Override
	public List<User> findAllUsers() {
		
		return userRepository.findAll();
	}
	@Override
	public User findUserById(Long id) {
		
		return userRepository.getOne(id);
	}
	
}
