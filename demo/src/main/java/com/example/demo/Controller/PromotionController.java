package com.example.demo.Controller;

import com.example.demo.DTOs.PromotionDTO;
import com.example.demo.model.CreatePromotionRequest;
import com.example.demo.model.Response;
import com.example.demo.model.UpdatePromotionRequest;
import com.example.demo.service.promotion.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")
@Tag(name = "Promotion", description = "สําหรับจัดการการโปรโมชัน")
public class PromotionController {

    private final CreatePromotionService createPromotionService;
    private final GetPromotionByIdService getPromotionByIdService;
    private final GetAllActivePromotionService getAllActivePromotionService;
    private final DeletePromotionByIdService deletePromotionByIdService;
    private final UpdatePromotionService updatePromotionService;

    public PromotionController(CreatePromotionService createPromotionService, GetPromotionByIdService getPromotionByIdService, GetAllActivePromotionService getAllActivePromotionService, DeletePromotionByIdService deletePromotionByIdService, UpdatePromotionService updatePromotionService) {
        this.createPromotionService = createPromotionService;
        this.getPromotionByIdService = getPromotionByIdService;
        this.getAllActivePromotionService = getAllActivePromotionService;
        this.deletePromotionByIdService = deletePromotionByIdService;
        this.updatePromotionService = updatePromotionService;
    }


    @Operation(
            summary = "สร้างโปรโมชั่นใหม่",
            description = "ใช้เพื่อสร้างโปรโมชั่นโดยระบุ code, ประเภท, มูลค่าส่วนลด, วันที่เริ่มต้น และวันหมดอายุ"
    )
    @ApiResponse(responseCode = "200", description = "สร้างโปรโมชั่นสำเร็จ", content = @Content(schema = @Schema(implementation = PromotionDTO.class)))
    @PostMapping
    public ResponseEntity<PromotionDTO> createPromotion(@Valid @RequestBody CreatePromotionRequest request) {
        return createPromotionService.execute(request);
    }

    @Operation(
            summary = "ดึงข้อมูลโปรโมชั่นตาม ID",
            description = "ใช้สำหรับเรียกดูข้อมูลโปรโมชั่นด้วยรหัสโปรโมชั่นที่ระบุ"
    )
    @ApiResponse(responseCode = "200", description = "ดึงข้อมูลสำเร็จ")
    @ApiResponse(responseCode = "404", description = "ไม่พบโปรโมชั่นตาม ID ที่ระบุ")
    @GetMapping("{promotionId}")
    public ResponseEntity<PromotionDTO> getPromotionById(@PathVariable Long promotionId) {
        return getPromotionByIdService.execute(promotionId);
    }

    @Operation(
            summary = "ดึงรายการโปรโมชั่นที่ยังไม่หมดอายุ",
            description = "ใช้สำหรับเรียกดูโปรโมชั่นทั้งหมดที่สถานะเป็น Active โดยอิงจากวันที่ปัจจุบัน"
    )
    @ApiResponse(
            responseCode = "200",
            description = "ดึงรายการสำเร็จ",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PromotionDTO.class)))
    )
    @GetMapping("/active")
    public ResponseEntity<List<PromotionDTO>> getAllActivePromotion() {
        return getAllActivePromotionService.execute(null);
    }

    @Operation(
            summary = "ลบโปรโมชั่นตาม ID",
            description = "ลบโปรโมชั่นจากระบบตามรหัสโปรโมชั่นที่ระบุ"
    )
    @ApiResponse(responseCode = "200", description = "ลบสำเร็จ")
    @ApiResponse(responseCode = "404", description = "ไม่พบโปรโมชั่นที่ต้องการลบ")

    @DeleteMapping("{promotionId}")
    public ResponseEntity<Response> deletePromotion(@PathVariable Long promotionId) {
        return deletePromotionByIdService.execute(promotionId);
    }


    @Operation(
            summary = "อัปเดตโปรโมชั่น",
            description = "ใช้เพื่ออัปเดตข้อมูลโปรโมชั่น เช่น มูลค่าส่วนลด ช่วงเวลา หรือสถานะ"
    )
    @ApiResponse(responseCode = "200", description = "อัปเดตสำเร็จ")
    @ApiResponse(responseCode = "404", description = "ไม่พบโปรโมชั่นที่ต้องการอัปเดต")
    @PutMapping("{promotionId}")
    public ResponseEntity<Response> updatePromotion(@PathVariable Long promotionId, @RequestBody @Valid UpdatePromotionRequest request) {
        return updatePromotionService.execute(promotionId, request);
    }

}
