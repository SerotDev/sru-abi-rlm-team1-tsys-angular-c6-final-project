package com.finalproject.hohoho.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.finalproject.hohoho.dto.Hotel;
import com.finalproject.hohoho.dto.Town;
import com.finalproject.hohoho.services.HotelServiceImpl;

@RestController
@RequestMapping("/api")
public class HotelController {

	@Autowired
	HotelServiceImpl hotelServiceImpl;

	// Get paginated all hotels from location
	@GetMapping("/hotels/{idTown}")
	public List<Hotel> list(@RequestParam(name = "idTown", required = false) Integer idTown,
			@RequestParam(name = "minStarRatingAvg", required = false) Integer minStarRatingAvg,
			@RequestParam(name = "numberRooms", required = false) Integer numberRooms,
			@RequestParam(name = "minPrice", required = false) Integer minPrice,
			@RequestParam(name = "maxPrice", required = false) Integer maxPrice,
			@RequestParam(name = "idService", required = false) List<Integer> idService) {

		// Data declaration
		List<Hotel> allHotels = new ArrayList<Hotel>();
		boolean isFiltered = false;

		return hotelServiceImpl.list();
	}

	// Add new hotel
	@PostMapping("/hotel/add")
	public Hotel save(@RequestBody Hotel hotel) {
		return hotelServiceImpl.save(hotel);
	}

	// Get hotel by id
	@GetMapping("/hotel/{id}")
	public Hotel byId(@PathVariable(name = "id") Integer id) {
		Hotel hotelByID = new Hotel();
		hotelByID = hotelServiceImpl.byId(id);
		return hotelByID;
	}

	// Update hotel by id
	@PutMapping("/hotel/update/{id}")
	public Hotel update(@PathVariable(name = "id") Integer id, @RequestBody Hotel hotel) {

		Hotel hotelSelected = new Hotel();
		Hotel hotelUpdated = new Hotel();

		hotelSelected = hotelServiceImpl.byId(id);
		hotelSelected.setId(id);
		hotelSelected.setDescription(hotel.getDescription());
		hotelSelected.setPhone(hotel.getPhone());
		hotelSelected.setAddress(hotel.getAddress());
		hotelSelected.setEmail(hotel.getEmail());
		hotelSelected.setWeb(hotel.getWeb());
		hotelSelected.setNumber_rooms(hotel.getNumber_rooms());
		hotelSelected.setImgs_url(hotel.getImgs_url());
		hotelSelected.setPrice(hotel.getPrice());
		hotelSelected.setLatitude(hotel.getLatitude());
		hotelSelected.setLongitude(hotel.getLongitude());
		hotelSelected.setUser(hotel.getUser());
		hotelSelected.setTown(hotel.getTown());
		hotelUpdated = hotelServiceImpl.update(hotelSelected);

		return hotelUpdated;
	}

	// Delete hotel by id
	@DeleteMapping("/hotel/delete/{id}")
	public void delete(@PathVariable Integer id) {
		hotelServiceImpl.delete(id);
	}
}
