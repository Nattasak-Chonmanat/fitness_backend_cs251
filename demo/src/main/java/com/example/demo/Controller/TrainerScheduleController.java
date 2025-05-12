package com.example.demo.Controller;

import com.example.demo.DTOs.TrainerScheduleDTO;
import com.example.demo.model.Response;
import com.example.demo.service.trainerSchedule.CreateTrainerScheduleService;
import com.example.demo.service.trainerSchedule.GetTrainerScheduleByIdService;
import com.example.demo.service.trainerSchedule.GetTrainerScheduleByTrainerIdService;
import com.example.demo.service.trainerSchedule.UpdateTrainerScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trainer/schedule")
@Tag(name = "Trainer Schedule", description = "สำหรับจัดการตารางการทำงานของเทรนเนอร์")
public class TrainerScheduleController {

    private final CreateTrainerScheduleService createTrainerScheduleService;
    private final UpdateTrainerScheduleService updateTrainerScheduleService;
    private final GetTrainerScheduleByIdService getTrainerScheduleByIdService;
    private final GetTrainerScheduleByTrainerIdService getTrainerScheduleByTrainerIdService;

    public TrainerScheduleController(CreateTrainerScheduleService createTrainerScheduleService, UpdateTrainerScheduleService updateTrainerScheduleService, GetTrainerScheduleByIdService getTrainerScheduleByIdService, GetTrainerScheduleByTrainerIdService getTrainerScheduleByTrainerIdService) {
        this.createTrainerScheduleService = createTrainerScheduleService;
        this.updateTrainerScheduleService = updateTrainerScheduleService;
        this.getTrainerScheduleByIdService = getTrainerScheduleByIdService;
        this.getTrainerScheduleByTrainerIdService = getTrainerScheduleByTrainerIdService;
    }

    @Operation(
            summary = "สร้างตารางการทำงานของเทรนเนอร์",
            description = "เพิ่มข้อมูลเวลาทำงานของเทรนเนอร์ลงในระบบ"
    )
    @ApiResponse(responseCode = "201", description = "สร้างตารางสำเร็จ")
    @PostMapping
    public ResponseEntity<TrainerScheduleDTO> createTrainerSchedule(@RequestBody @Valid TrainerScheduleDTO request) {
        return createTrainerScheduleService.execute(request);
    }

    @Operation(
            summary = "อัปเดตตารางของเทรนเนอร์",
            description = "อัปเดตข้อมูลตารางการทำงานของเทรนเนอร์โดยใช้ scheduleId"
    )
    @ApiResponse(responseCode = "200", description = "อัปเดตข้อมูลสำเร็จ")
    @PutMapping("{scheduleId}")
    public ResponseEntity<Response> updateTrainerSchedule(@PathVariable Long scheduleId) {
        return updateTrainerScheduleService.execute(scheduleId);
    }

    @Operation(
            summary = "ดึงข้อมูลตารางการทำงานตาม scheduleId",
            description = "ค้นหาข้อมูลตารางของเทรนเนอร์โดยใช้ scheduleId"
    )
    @ApiResponse(responseCode = "200", description = "ดึงข้อมูลสำเร็จ")
    @GetMapping("{scheduleId}")
    public ResponseEntity<TrainerScheduleDTO> getTrainerScheduleById(@PathVariable Long scheduleId) {
        return getTrainerScheduleByIdService.execute(scheduleId);
    }

    @Operation(
            summary = "ดึงข้อมูลตารางการทำงานของเทรนเนอร์",
            description = "ค้นหาข้อมูลตารางของเทรนเนอร์โดยใช้ scheduleId"
    )
    @ApiResponse(responseCode = "200", description = "trainerId")
    @GetMapping("/get-by-trainerId/{trainerId}")
    public ResponseEntity<List<TrainerScheduleDTO>> getTrainerScheduleByTrainerId(@PathVariable Long trainerId) {
        return getTrainerScheduleByTrainerIdService.execute(trainerId);
    }
}
