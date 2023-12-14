package com.finalproject.hohoho.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.hohoho.dto.Event;
import com.finalproject.hohoho.dto.Hotel;

public interface IEventDAO extends JpaRepository <Event, Integer> {
public List <Event> findByIsPublicFalseAndHotel(Hotel hotel);
public List <Event> findByIsPublicTrueAndHotel(Hotel hotel);
public List <Event> findByHotel(Hotel hotel);
}
