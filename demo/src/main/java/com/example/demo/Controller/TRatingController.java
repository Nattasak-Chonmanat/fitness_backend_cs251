package com.example.demo.Controller;

import com.example.demo.DTOs.TrainerReviewDTO;
import com.example.demo.model.CreateTrainerReviewRequest;
import com.example.demo.model.Response;
import com.example.demo.model.UpdateTrainerReviewRequest;
import com.example.demo.service.trainerReview.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trainer/review")
@Tag(name = "Trainer Reviews", description = "สำหรับจัดการรีวิวของเทรนเนอร์")
public class TRatingController {

    private final CreateTrainerReviewService createTrainerReviewService;
    private final GetTrainerReviewByMemberIdService getTrainerReviewByMemberId;
    private final UpdateTrainerReviewService updateTrainerReviewService;
    private final GetTrainerReviewByTrainerIdService getTrainerReviewByTrainerIdService;
    private final GetReviewService getReviewService;
    private final DeleteTrainerReviewService deleteTrainerReviewService;

    public TRatingController(
            CreateTrainerReviewService createTrainerReviewService,
            GetTrainerReviewByMemberIdService getTrainerReviewByMemberId,
            UpdateTrainerReviewService updateTrainerReviewService,
            GetTrainerReviewByTrainerIdService getTrainerReviewByTrainerIdService,
            GetReviewService getReviewService,
            DeleteTrainerReviewService deleteTrainerReviewService
    ) {
        this.createTrainerReviewService = createTrainerReviewService;
        this.getTrainerReviewByMemberId = getTrainerReviewByMemberId;
        this.updateTrainerReviewService = updateTrainerReviewService;
        this.getTrainerReviewByTrainerIdService = getTrainerReviewByTrainerIdService;
        this.getReviewService = getReviewService;
        this.deleteTrainerReviewService = deleteTrainerReviewService;
    }

    @Operation(summary = "สร้างรีวิวเทรนเนอร์", description = "ใช้สำหรับสร้างรีวิวให้กับเทรนเนอร์")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สร้างรีวิวสำเร็จ", content = @Content(schema = @Schema(implementation = TrainerReviewDTO.class))),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PostMapping
    public ResponseEntity<TrainerReviewDTO> createTrainerReview(@RequestBody @Valid CreateTrainerReviewRequest rating) {
        return createTrainerReviewService.execute(rating);
    }

    @Operation(summary = "ดึงรีวิวจากรหัสสมาชิก", description = "ดึงรายการรีวิวทั้งหมดที่ถูกเขียนโดยสมาชิกคนหนึ่ง")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TrainerReviewDTO.class)))),
            @ApiResponse(responseCode = "404", description = "ไม่พบข้อมูลรีวิว")
    })
    @GetMapping("/memberId/{memberId}")
    public ResponseEntity<List<TrainerReviewDTO>> getTrainerReviewByMemberId(@PathVariable Long memberId) {
        return getTrainerReviewByMemberId.execute(memberId);
    }

    @Operation(summary = "ดึงรีวิวของเทรนเนอร์", description = "ใช้เพื่อดึงรายการรีวิวทั้งหมดของเทรนเนอร์")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TrainerReviewDTO.class)))),
            @ApiResponse(responseCode = "404", description = "ไม่พบเทรนเนอร์")
    })
    @GetMapping("/trainerId/{trainerId}")
    public ResponseEntity<List<TrainerReviewDTO>> getTrainerReviewByTrainerId(@PathVariable Long trainerId) {
        return getTrainerReviewByTrainerIdService.execute(trainerId);
    }

    @Operation(summary = "ดึงรีวิวทั้งหมด", description = "ใช้เพื่อดึงรีวิวทุกรีวิวในระบบ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TrainerReviewDTO.class))))
    })
    @GetMapping
    public ResponseEntity<List<TrainerReviewDTO>> getAllReviews() {
        return getReviewService.execute(null);
    }

    @Operation(summary = "อัปเดตรีวิว", description = "ใช้เพื่ออัปเดตข้อความหรือคะแนนรีวิวที่มีอยู่")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "อัปเดตสำเร็จ", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง"),
            @ApiResponse(responseCode = "404", description = "ไม่พบรีวิว")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateTrainerReview(@PathVariable Long id, @RequestBody @Valid UpdateTrainerReviewRequest request) {
        return updateTrainerReviewService.execute(id, request);
    }

    @Operation(summary = "ลบรีวิว", description = "ใช้เพื่อลบรีวิวของเทรนเนอร์ออกจากระบบ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ลบสำเร็จ", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "ไม่พบรีวิว")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTrainerReview(@PathVariable Long id) {
        return deleteTrainerReviewService.execute(id);
    }
}


