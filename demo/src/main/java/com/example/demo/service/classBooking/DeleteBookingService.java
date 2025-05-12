package com.example.demo.service.classBooking;

import com.example.demo.Command;
import com.example.demo.exception.BookingNotFoundException;
import com.example.demo.model.ClassBooking;
import com.example.demo.model.Response;
import com.example.demo.repository.ClassBookingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class DeleteBookingService implements Command<Long, Response> {

    private final ClassBookingRepository bookingRepository;

    public DeleteBookingService(ClassBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id) {
        ClassBooking classBooking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        bookingRepository.delete(classBooking);
        return  ResponseEntity.ok(new Response("Delete booking successful.", HttpStatus.OK));
    }
}
