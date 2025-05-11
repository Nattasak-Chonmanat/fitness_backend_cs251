package com.example.demo.Controller;

import com.example.demo.DTOs.CReviewDTO;
import com.example.demo.DTOs.CReviewSummaryDTO;
import com.example.demo.model.CReviewCreateRequest;
import com.example.demo.model.Response;
import com.example.demo.model.UpdateClassReviewRequest;
import com.example.demo.service.classReview.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("class/review")
@Tag(name = "Class Review", description = "สำหรับจัดการรีวิวของคลาสออกกําลังกาย")
public class ClassReviewController {

    private final CreateReviewService createReviewService;
    private final DeleteReviewService deleteReviewService;
    private final GetReviewByMemberId getReviewByMemberId;
    private final UpdateReviewService updateReviewService;
    private final GetReviewById getReviewById;
    private final GetReviewsByClassId getReviewsByClassId;
    private final GetAllReviewsService getAllReviewsService;

    public ClassReviewController(CreateReviewService createReviewService, DeleteReviewService deleteReviewService, GetReviewByMemberId getReviewByMemberId, UpdateReviewService updateReviewService, GetReviewById getReviewById, GetReviewsByClassId getReviewsByClassId, GetAllReviewsService getAllReviewsService) {
        this.createReviewService = createReviewService;
        this.deleteReviewService = deleteReviewService;
        this.getReviewByMemberId = getReviewByMemberId;
        this.updateReviewService = updateReviewService;
        this.getReviewById = getReviewById;
        this.getReviewsByClassId = getReviewsByClassId;
        this.getAllReviewsService = getAllReviewsService;
    }

    @Operation(summary = "ดึงรีวิวทั้งหมดของสมาชิก")
    @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายการรีวิวของสมาชิก")
    @GetMapping("/memberId/{memberId}")
    public ResponseEntity<List<CReviewSummaryDTO>> getReviewByMemberId(@PathVariable Long memberId) {
        return getReviewByMemberId.execute(memberId);
    }

    @Operation(summary = "ดึงรีวิวทั้งหมดของคลาสเรียน")
    @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายการรีวิวของคลาส")
    @GetMapping("/classId/{classId}")
    public ResponseEntity<List<CReviewSummaryDTO>> getReviewsByClassId(@PathVariable Long classId) {
        return getReviewsByClassId.execute(classId);
    }

    @Operation(summary = "ดึงรีวิวทั้งหมด")
    @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายการรีวิวทั้งหมด")
    @GetMapping
    public ResponseEntity<List<CReviewDTO>> getAllReviews() {
        return getAllReviewsService.execute(null);
    }

    @Operation(summary = "ดึงรีวิวตาม ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายละเอียดรีวิว"),
            @ApiResponse(responseCode = "404", description = "ไม่พบรีวิว")
    })
    @GetMapping("/{reviewId}")
    public ResponseEntity<CReviewDTO> getReviewById(@PathVariable Long reviewId) {
        return getReviewById.execute(reviewId);
    }

    @Operation(summary = "สร้างรีวิวใหม่")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "สร้างรีวิวสำเร็จ"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PostMapping
    public ResponseEntity<CReviewDTO> createReview(@RequestBody @Valid CReviewCreateRequest request) {
        return createReviewService.execute(request);
    }

    @Operation(summary = "ลบรีวิว")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ลบรีวิวสำเร็จ"),
            @ApiResponse(responseCode = "404", description = "ไม่พบรีวิว")
    })
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Response> deleteReview(@PathVariable Long reviewId) {
        return deleteReviewService.execute(reviewId);
    }

    @Operation(summary = "อัปเดตรีวิว")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "อัปเดตรีวิวสำเร็จ"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง"),
            @ApiResponse(responseCode = "404", description = "ไม่พบรีวิว")
    })
    @PutMapping("{reviewId}")
    public ResponseEntity<Response> updateReview(@PathVariable Long reviewId, @RequestBody UpdateClassReviewRequest request) {
        return updateReviewService.execute(reviewId, request);
    }
}
