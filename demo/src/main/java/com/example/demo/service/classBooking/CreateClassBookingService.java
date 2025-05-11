package com.example.demo.service.classBooking;

import com.example.demo.Command;
import com.example.demo.DTOs.ClassBookingDTO;
import com.example.demo.model.Class;
import com.example.demo.model.ClassBooking;
import com.example.demo.model.CreateBookingRequest;
import com.example.demo.model.Member;
import com.example.demo.repository.ClassBookingRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.exception.BookingAlreadyExistException;
import com.example.demo.exception.ClassNotFoundException;
import com.example.demo.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CreateClassBookingService implements Command<CreateBookingRequest, ClassBookingDTO> {

    private final ClassBookingRepository classBookingRepository;
    private final MemberRepository memberRepository;
    private final ClassRepository classRepository;

    public CreateClassBookingService(ClassBookingRepository classBookingRepository, MemberRepository memberRepository, ClassRepository classRepository) {
        this.classBookingRepository = classBookingRepository;
        this.memberRepository = memberRepository;
        this.classRepository = classRepository;
    }

    @Override
    public ResponseEntity<ClassBookingDTO> execute(CreateBookingRequest booking) {

        Member member = memberRepository.findById(booking.getMemberId()).orElseThrow(() -> new MemberNotFoundException(booking.getMemberId()));
        Class clazz = classRepository.findById(booking.getClassId()).orElseThrow(() -> new ClassNotFoundException(booking.getClassId()));

        Optional<ClassBooking> existingBooking = classBookingRepository
                .findByMemberIdAndAClassIdAndStatus(
                        booking.getMemberId(),
                        booking.getClassId(),
                        "Booked");

        if (existingBooking.isPresent()) {
            throw new BookingAlreadyExistException();
        }

        ClassBooking classBooking = new ClassBooking();
        classBooking.setStatus("Booked");
        classBooking.setaClass(clazz);
        classBooking.setMember(member);
        classBooking.setBookingDate(LocalDateTime.now());
        classBookingRepository.save(classBooking);

        clazz.setCapacity(clazz.getCapacity() - 1);
        classRepository.save(clazz);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ClassBookingDTO(classBooking));
    }

}
