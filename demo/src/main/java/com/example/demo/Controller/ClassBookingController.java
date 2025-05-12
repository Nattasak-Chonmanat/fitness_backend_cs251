package com.example.demo.Controller;

import com.example.demo.DTOs.ClassBookingDTO;
import com.example.demo.model.ClassBooking;
import com.example.demo.model.CreateBookingRequest;
import com.example.demo.model.Response;
import com.example.demo.model.UpdateBookingRequest;
import com.example.demo.repository.ClassBookingRepository;
import com.example.demo.service.classBooking.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@Tag(name = "Booking", description = "สําหรับจัดการการจองคลาส")
public class ClassBookingController {

    private final ClassBookingRepository classBookingRepository;
    private final CreateClassBookingService createClassBookingService;
    private final GetBookingByMemberIdService getBookingByMemberIdService;
    private final UpdateBookingService updateBookingService;
    private final GetBookingByIdService getBookingByIdService;
    private final DeleteBookingService deleteBookingService;

    public ClassBookingController(ClassBookingRepository classBookingRepository, CreateClassBookingService createClassBookingService, GetBookingByMemberIdService getBookingByMemberIdService, UpdateBookingService updateBookingService, GetBookingByIdService getBookingByIdService, DeleteBookingService deleteBookingService) {
        this.classBookingRepository = classBookingRepository;
        this.createClassBookingService = createClassBookingService;
        this.getBookingByMemberIdService = getBookingByMemberIdService;
        this.updateBookingService = updateBookingService;
        this.getBookingByIdService = getBookingByIdService;
        this.deleteBookingService = deleteBookingService;
    }

    @Operation(summary = "ดึงข้อมูลการจองทั้งหมด")
    @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายการการจองทั้งหมด")
    @GetMapping
    public ResponseEntity<List<ClassBookingDTO>> getAllBookings() {
        List<ClassBookingDTO> bookingDTOS = classBookingRepository.findAll().stream().map(ClassBookingDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(bookingDTOS);
    }

    @Operation(summary = "ดึงข้อมูลการจองของสมาชิกตาม memberId")
    @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายการการจองตาม memberId")
    @GetMapping("/memberId/{memberId}")
    public ResponseEntity<List<ClassBookingDTO>> getBookingsByMemberId(@PathVariable Long memberId) {
        return getBookingByMemberIdService.execute(memberId);
    }

    @Operation(summary = "ดึงข้อมูลการจองตาม bookingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายละเอียดการจอง"),
            @ApiResponse(responseCode = "404", description = "ไม่พบข้อมูลการจอง")
    })
    @GetMapping("/{bookingId}")
    public ResponseEntity<ClassBookingDTO> getBookingById(@PathVariable Long bookingId) {
        return  getBookingByIdService.execute(bookingId);
    }

    @Operation(summary = "สร้างการจองคลาสใหม่")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "สำเร็จ: การจองคลาสใหม่ถูกสร้างขึ้น"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PostMapping
    public ResponseEntity<ClassBookingDTO> createBooking(@RequestBody @Valid CreateBookingRequest classBooking) {
        return createClassBookingService.execute(classBooking);
    }

    @Operation(summary = "อัปเดตการจองตาม bookingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ: การจองถูกอัปเดต"),
            @ApiResponse(responseCode = "404", description = "ไม่พบข้อมูลการจอง"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PutMapping("/{bookingId}")
    public ResponseEntity<Response> updateBooking(@PathVariable Long bookingId, @RequestBody @Valid UpdateBookingRequest request) {
        return updateBookingService.execute(bookingId, request);
    }


    @Operation(summary = "ลบการจองตาม bookingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ: การจองถูกลบแล้ว"),
            @ApiResponse(responseCode = "404", description = "ไม่พบข้อมูลการจอง"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Response> deleteBooking(@PathVariable Long bookingId) {
        return deleteBookingService.execute(bookingId);
    }


}
