package com.example.demo.Controller;

import com.example.demo.DTOs.AttendanceDTO;
import com.example.demo.model.CreateAttendanceRequest;
import com.example.demo.model.Response;
import com.example.demo.service.attendance.CreateAttendanceService;
import com.example.demo.service.attendance.DeleteAttendanceService;
import com.example.demo.service.attendance.GetAttendanceByMemberIdService;
import com.example.demo.service.attendance.UpdateAttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@Tag(name = "Attendance", description = "สําหรับจัดการการเข้าใช้บริการของสมาชิก")
public class AttendanceController {

    private final CreateAttendanceService createAttendanceService;
    private final UpdateAttendanceService updateAttendanceService;
    private final GetAttendanceByMemberIdService getAttendanceByMemberIdService;
    private final DeleteAttendanceService deleteAttendanceService;

    public AttendanceController(CreateAttendanceService createAttendanceService, UpdateAttendanceService updateAttendanceService, GetAttendanceByMemberIdService getAttendanceByMemberIdService, DeleteAttendanceService deleteAttendanceService) {
        this.createAttendanceService = createAttendanceService;
        this.updateAttendanceService = updateAttendanceService;
        this.getAttendanceByMemberIdService = getAttendanceByMemberIdService;
        this.deleteAttendanceService = deleteAttendanceService;
    }


    @Operation(
            summary = "ดึงข้อมูลการเข้าใช้บริการของสมาชิก",
            description = "ใช้ memberId เพื่อค้นหาประวัติการเข้าใช้บริการทั้งหมดของสมาชิกคนนั้น"
    )
    @ApiResponse(responseCode = "200", description = "เรียกดูข้อมูลสำเร็จ")
    @GetMapping("/memberId/{memberId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByMemberId(@PathVariable Long memberId) {
        return getAttendanceByMemberIdService.execute(memberId);
    }

    @Operation(
            summary = "บันทึกการเข้าใช้บริการ",
            description = "สร้างข้อมูลการเข้าใช้บริการใหม่ของสมาชิก"
    )
    @ApiResponse(responseCode = "201", description = "สร้างข้อมูลสำเร็จ")
    @PostMapping
    public ResponseEntity<AttendanceDTO> createAttendance(@RequestBody @Valid CreateAttendanceRequest request) {
        return createAttendanceService.execute(request);
    }


    @Operation(
            summary = "อัปเดตการเข้าใช้บริการ",
            description = "แก้ไขข้อมูลของการเข้าใช้บริการที่มีอยู่"
    )
    @ApiResponse(responseCode = "200", description = "อัปเดตข้อมูลสำเร็จ")
    @PutMapping("{attendanceId}")
    public ResponseEntity<Response> updateAttendance(@PathVariable Long attendanceId){
        return updateAttendanceService.execute(attendanceId);
    }

    @Operation(
            summary = "ลบข้อมูลการเข้าใช้บริการ",
            description = "ลบข้อมูลการเข้าใช้บริการโดยใช้ attendanceId"
    )
    @ApiResponse(responseCode = "200", description = "ลบข้อมูลสำเร็จ")
    @DeleteMapping("/{attendanceId}")
    public ResponseEntity<Response> deleteAttendance(@PathVariable Long attendanceId) {
        return deleteAttendanceService.execute(attendanceId);
    }

}
