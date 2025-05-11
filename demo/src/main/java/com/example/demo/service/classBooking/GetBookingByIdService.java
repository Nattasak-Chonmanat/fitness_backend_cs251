package com.example.demo.service.classBooking;

import com.example.demo.DTOs.ClassBookingDTO;
import com.example.demo.Query;
import com.example.demo.exception.BookingNotFoundException;
import com.example.demo.model.ClassBooking;
import com.example.demo.repository.ClassBookingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetBookingByIdService implements Query<Long, ClassBookingDTO> {

    private final ClassBookingRepository classBookingRepository;

    public GetBookingByIdService(ClassBookingRepository classBookingRepository) {
        this.classBookingRepository = classBookingRepository;
    }

    @Override
    public ResponseEntity<ClassBookingDTO> execute(Long id) {
        ClassBooking booking = classBookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        return ResponseEntity.status(HttpStatus.OK).body(new ClassBookingDTO(booking));
    }
}
