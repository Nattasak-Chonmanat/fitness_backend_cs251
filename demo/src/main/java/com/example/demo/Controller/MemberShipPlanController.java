package com.example.demo.Controller;

import com.example.demo.DTOs.MemberShipplanDTO;
import com.example.demo.model.MembershipPlan;
import com.example.demo.model.Response;
import com.example.demo.service.membership.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membership")
@Tag(name = "Membership Plan", description = "สำหรับจัดการแพ็คเกจสมาชิก")
public class MemberShipPlanController {

    private final GetAllMembershipPlanService getAllMembershipPlanService;
    private final CreateMembershipPlanService createMembershipPlanService;
    private final UpdateMembershipPlanService updateMembershipPlanService;
    private final GetMembershipPlanByIdService getMembershipPlanByIdService;
    private final DeleteMembershipPlan deleteMembershipPlan;

    public MemberShipPlanController(GetAllMembershipPlanService getAllMembershipPlanService, CreateMembershipPlanService createMembershipPlanService, UpdateMembershipPlanService updateMembershipPlanService, GetMembershipPlanByIdService getMembershipPlanByIdService, DeleteMembershipPlan deleteMembershipPlan) {
        this.getAllMembershipPlanService = getAllMembershipPlanService;
        this.createMembershipPlanService = createMembershipPlanService;
        this.updateMembershipPlanService = updateMembershipPlanService;
        this.getMembershipPlanByIdService = getMembershipPlanByIdService;
        this.deleteMembershipPlan = deleteMembershipPlan;
    }

    @Operation(summary = "ดึงรายการแพ็คเกจสมาชิกทั้งหมด")
    @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายการแพ็คเกจสมาชิกทั้งหมด")
    @GetMapping
    public ResponseEntity<List<MemberShipplanDTO>> getAllMembershipPlan() {
        return getAllMembershipPlanService.execute(null);
    }

    @Operation(summary = "ดึงแพ็คเกจสมาชิกตาม ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่าข้อมูลแพ็คเกจ"),
            @ApiResponse(responseCode = "404", description = "ไม่พบแพ็คเกจสมาชิกที่ระบุ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MemberShipplanDTO> getMembershipPlanById(@PathVariable Long id) {
        return getMembershipPlanByIdService.execute(id);
    }

    @Operation(summary = "สร้างแพ็คเกจสมาชิกใหม่")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "สร้างแพ็คเกจสมาชิกสำเร็จ"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PostMapping
    public ResponseEntity<MemberShipplanDTO> createMembershipPlan(@RequestBody MembershipPlan m) {
        return createMembershipPlanService.execute(m);
    }

    @Operation(summary = "แก้ไขข้อมูลแพ็คเกจสมาชิก")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "อัปเดตสำเร็จ"),
            @ApiResponse(responseCode = "404", description = "ไม่พบแพ็คเกจสมาชิก"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PutMapping("{id}")
    public ResponseEntity<Response> updateMembershipPlan(@PathVariable Long id, @RequestBody @Valid MembershipPlan m) {
        return updateMembershipPlanService.execute(id, m);
    }

    @Operation(summary = "ลบแพ็คเกจสมาชิก")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ลบสำเร็จ"),
            @ApiResponse(responseCode = "404", description = "ไม่พบแพ็คเกจสมาชิก")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteMembershipPlan(@PathVariable Long id) {
        return deleteMembershipPlan.execute(id);
    }
}
