package com.example.demo.service.classBooking;

import com.example.demo.UpdateCommand;
import com.example.demo.exception.BookingNotFoundException;
import com.example.demo.model.Class;
import com.example.demo.model.ClassBooking;
import com.example.demo.model.Response;
import com.example.demo.model.UpdateBookingRequest;
import com.example.demo.repository.ClassBookingRepository;
import com.example.demo.repository.ClassRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UpdateBookingService implements UpdateCommand<Long, UpdateBookingRequest, Response> {

    private final ClassBookingRepository classBookingRepository;
    private final ClassRepository classRepository;

    public UpdateBookingService(ClassBookingRepository classBookingRepository, ClassRepository classRepository) {
        this.classBookingRepository = classBookingRepository;
        this.classRepository = classRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, UpdateBookingRequest request) {
        ClassBooking classBooking = classBookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        String currentStatus = classBooking.getStatus();
        String newStatus = request.getStatus();

        Class clazz = classBooking.getaClass();

        if (!currentStatus.equals("Canceled") && newStatus.equals("Canceled")) {
            clazz.setCapacity(clazz.getCapacity() + 1);
            classRepository.save(clazz);
        }

        if (currentStatus.equals("Canceled") && !newStatus.equals("Canceled")) {
            clazz.setCapacity(clazz.getCapacity() - 1);
            classRepository.save(clazz);
        }

        classBooking.setStatus(newStatus);
        classBookingRepository.save(classBooking);

        return ResponseEntity.ok().body(new Response("Update booking successful.", HttpStatus.OK));
    }

}
