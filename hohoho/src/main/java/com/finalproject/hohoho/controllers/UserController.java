package com.finalproject.hohoho.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.finalproject.hohoho.dto.Event;
import com.finalproject.hohoho.dto.Hotel;
import com.finalproject.hohoho.dto.User;
import com.finalproject.hohoho.services.EventServiceImpl;
import com.finalproject.hohoho.services.HotelServiceImpl;
import com.finalproject.hohoho.services.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	HotelServiceImpl hotelServiceImpl;
	
	@Autowired
	EventServiceImpl eventServiceImpl;

	// Get all users
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/users")
	public List<User> list() {
		return userServiceImpl.list();
	}
	
	// Add new user
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/user/add")
	public User save(@RequestBody User user) {
		return userServiceImpl.save(user);
	}
	
	// Get user by id
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/user/{id}")
	public User byId(@PathVariable(name = "id") Integer id) {
		User userByID = new User();
		userByID = userServiceImpl.byId(id);
		return userByID;
	}
	// Get hotel by user id
	@PreAuthorize("hasRole('ADMIN') or hasRole('HOTEL')")
	@GetMapping("/user/hotels/{userHotel}")
	public List <Hotel> listHotelsbyUser(@PathVariable(name = "userHotel")Integer userHotel){
		User user = userServiceImpl.byId(userHotel);
		return hotelServiceImpl.listHotelsbyUser(user);		
	}
	
	//Get events by hotel id
	@PreAuthorize("hasRole('ADMIN') or hasRole('HOTEL')")
	@GetMapping("/user/events/{userHotel}")
	public List <Event> eventsByHotelId(@PathVariable(name = "userHotel")Integer userHotel){
		Hotel hotel = hotelServiceImpl.byId(userHotel);
		return eventServiceImpl.eventsByHotelId(hotel);
		
	}
	
	// Update user by id
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/user/update/{id}")
	public User update(@PathVariable(name = "id") Integer id, @RequestBody User user) {

		User userSelected = new User();
		User userUpdated = new User();

		userSelected = userServiceImpl.byId(id);
		userSelected.setId(id);
		userSelected.setName(user.getName());
		userSelected.setSurname(user.getSurname());
		userSelected.setPhone(user.getPhone());
		userSelected.setProfImgUrl(user.getProfImgUrl());
		userSelected.setEmail(user.getEmail());
		userSelected.setPassword(user.getPassword());
		userSelected.setRegistration_date(user.getRegistrationDate());
		userSelected.setRole(user.getRole());
		userUpdated = userServiceImpl.update(userSelected);

		return userUpdated;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	// Delete user by id
	@DeleteMapping("/user/delete/{id}")
	public void delete(@PathVariable Integer id) {
		userServiceImpl.delete(id);
	}
	

}

