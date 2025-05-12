package com.example.demo.Controller;

import com.example.demo.DTOs.TrainerReviewDTO;
import com.example.demo.DTOs.TrainerScheduleDTO;
import com.example.demo.model.Response;
import com.example.demo.service.trainerSchedule.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final DeleteTrainerScheduleService deleteTrainerScheduleService;

    public TrainerScheduleController(CreateTrainerScheduleService createTrainerScheduleService, UpdateTrainerScheduleService updateTrainerScheduleService, GetTrainerScheduleByIdService getTrainerScheduleByIdService, GetTrainerScheduleByTrainerIdService getTrainerScheduleByTrainerIdService, DeleteTrainerScheduleService deleteTrainerScheduleService) {
        this.createTrainerScheduleService = createTrainerScheduleService;
        this.updateTrainerScheduleService = updateTrainerScheduleService;
        this.getTrainerScheduleByIdService = getTrainerScheduleByIdService;
        this.getTrainerScheduleByTrainerIdService = getTrainerScheduleByTrainerIdService;
        this.deleteTrainerScheduleService = deleteTrainerScheduleService;
    }

    @Operation(
            summary = "สร้างตารางการทำงานของเทรนเนอร์",
            description = "เพิ่มข้อมูลเวลาทำงานของเทรนเนอร์ลงในระบบ"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "สร้างตารางงานสําเร็จ", content = @Content(
                    examples = @ExampleObject(
                            name = "ตัวอย่างการตอบกลับ",
                            summary = "Response Example",
                            value = """
                                        {
                                          "id": 1,
                                          "trainerId": 1,
                                          "workDate": "2025-05-12",
                                          "startTime": "15:55:06.1733423",
                                          "endTime": null
                                        }
                                    """
                    )
            )),
            @ApiResponse(responseCode = "404", description = "ไม่พบเทรนเนอร์ที่มี id ตรงกับคําขอ", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "คําขอผิดพลาด", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping
    public ResponseEntity<TrainerScheduleDTO> createTrainerSchedule(@RequestBody @Valid TrainerScheduleDTO request) {
        return createTrainerScheduleService.execute(request);
    }

    @Operation(
            summary = "อัปเดตตารางของเทรนเนอร์",
            description = "อัปเดตข้อมูลตารางการทำงานของเทรนเนอร์โดยใช้ scheduleId"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "อัพเดทสําเร็จ", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "ไม่พบข้อมูลตารางงาน", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "คําขอผิดพลาด", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping("{scheduleId}")
    public ResponseEntity<Response> updateTrainerSchedule(@PathVariable Long scheduleId) {
        return updateTrainerScheduleService.execute(scheduleId);
    }

    @Operation(
            summary = "ดึงข้อมูลตารางการทำงานตาม scheduleId",
            description = "ค้นหาข้อมูลตารางของเทรนเนอร์โดยใช้ scheduleId"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สําเร็จ: คืนตารางงาน", content = @Content(
                    examples = @ExampleObject(
                            name = "ตัวอย่างการตอบกลับ",
                            summary = "Response Example",
                            value = """
                                        {
                                          "id": 1,
                                          "trainerId": 1,
                                          "workDate": "2025-05-12",
                                          "startTime": "15:55:06.1733423",
                                          "endTime": null
                                        }
                                    """
                    )
            )),
            @ApiResponse(responseCode = "404", description = "ไม่พบข้อมูลตารางงาน"),
            @ApiResponse(responseCode = "400", description = "คําขอผิดพลาด")
    })
    @GetMapping("{scheduleId}")
    public ResponseEntity<TrainerScheduleDTO> getTrainerScheduleById(@PathVariable Long scheduleId) {
        return getTrainerScheduleByIdService.execute(scheduleId);
    }

    @Operation(
            summary = "ดึงข้อมูลตารางการทำงานของเทรนเนอร์",
            description = "ค้นหาข้อมูลตารางของเทรนเนอร์โดยใช้ trainerId"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "สําเร็จ: คืนตารางงานทั้งหมดของเทรนเนอร์",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "ตัวอย่างการตอบกลับ",
                                    summary = "Response Example",
                                    value = """
                                        [
                                            {
                                              "id": 1,
                                              "trainerId": 1,
                                              "workDate": "2025-05-12",
                                              "startTime": "15:55:06.1733423",
                                              "endTime": null
                                            },
                                            {
                                              "id": 2,
                                              "trainerId": 1,
                                              "workDate": "2025-05-12",
                                              "startTime": "15:55:06.1733423",
                                              "endTime": null
                                            }
                                         ]
                                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "คําขอผิดพลาด", content = @Content(
                    schema = @Schema(implementation = Response.class)
            )),
            @ApiResponse(responseCode = "404", description = "ไม่พบเทรนเนอร์ที่มี id ตรงกับคําขอ", content = @Content(
                    schema = @Schema(implementation = Response.class)
            ))
    })
    @GetMapping("/get-by-trainerId/{trainerId}")
    public ResponseEntity<List<TrainerScheduleDTO>> getTrainerScheduleByTrainerId(@PathVariable Long trainerId) {
        return getTrainerScheduleByTrainerIdService.execute(trainerId);
    }

    @Operation(
            summary = "ลบข้อมูลตารางงานของเทรนเนอร์",
            description = "ลบข้อมูลตารางของเทรนเนอร์โดยใช้ scheduleId"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "อัพเดทสําเร็จ",
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "ไม่พบข้อมูลตารางงาน",
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(responseCode = "400", description = "คําขอผิดพลาด", content = @Content(
                    schema = @Schema(implementation = Response.class)
            ))
    })
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Response> deleteTrainerSchedule(@PathVariable Long scheduleId) {
        return deleteTrainerScheduleService.execute(scheduleId);
    }
}
