package edu.mum.cs.cs425.studentproject.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}
	
	//sort Users by first name 
	@Override
	public List<User> sortUsers(List<User> userList, String sortField) {
		List<User> result = userList.stream().sorted(Comparator.comparing(User::getFirstName).thenComparing(User::getLastName).reversed())
//		List<User> result = userList.stream().sorted((o1, o2)->o1.getFirstName().
//                compareTo(o2.getFirstName()))
                .collect(Collectors.toList());
		return result;
	}
	
	@Override
	public List<User> adultUsersList(List<User> adulUserList, Integer age) {
		List<User> adultUsersList = adulUserList.stream().filter(user->
									(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears() > age) && 
									Period.between(user.getDateOfBirth(), LocalDate.now()).getYears()<40).
									collect(Collectors.toList());
		return adultUsersList;
	}
	
}
