package com.example.demo.Controller;

import com.example.demo.DTOs.BranchDTO;
import com.example.demo.DTOs.BranchSummaryDTO;
import com.example.demo.model.Response;
import com.example.demo.service.branch.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
@Tag(name = "Branch", description = "สำหรับจัดการสาขา")
public class BranchController {

    private final GetAllBranchService getAllBranchService;
    private final GetBranchByIdService getBranchByIdService;
    private final CreateBranchService createBranchService;
    private final DeleteBranchService deleteBranchService;
    private final UpdateBranchService updateBranchService;

    public BranchController(GetAllBranchService getAllBranchService, GetBranchByIdService getBranchByIdService, CreateBranchService createBranchService, DeleteBranchService deleteBranchService, UpdateBranchService updateBranchService) {
        this.getAllBranchService = getAllBranchService;
        this.getBranchByIdService = getBranchByIdService;
        this.createBranchService = createBranchService;
        this.deleteBranchService = deleteBranchService;
        this.updateBranchService = updateBranchService;
    }

    @Operation(summary = "ดึงข้อมูลทั้งหมดของสาขา", description = "สําหรับดึงข้อมูลของทุกสาขา")
    @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายการสาขาทั้งหมด")
    @GetMapping
    public ResponseEntity<List<BranchSummaryDTO>> getAllBranches() {
        return getAllBranchService.execute(null);
    }

    @Operation(summary = "ดึงข้อมูลของสาขาตาม id" ,description = "สําหรับดึงข้อมูลของสาขาตาม ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่าข้อมูลสาขา"),
            @ApiResponse(responseCode = "404", description = "ไม่พบข้อมูลสาขา")
    })
    @GetMapping("{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) {
        return getBranchByIdService.execute(id);
    }

    @Operation(summary = "สร้างสาขาใหม่", description = "สําหรับสร้างสาขาใหม่ NOTE: managerID สามารถเว้นไว้ได้ ในกรณีที่ยังไม่อยากให้สาขานั้นมีผู้จัดการ")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "สำเร็จ: สาขาถูกสร้างขึ้น"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody @Valid BranchSummaryDTO dto) {
        return createBranchService.execute(dto);
    }

    @Operation(summary = "ลบสาขาตาม id", description = "สําหรับลบสาขาตาม ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ: สาขาถูกลบ"),
            @ApiResponse(responseCode = "404", description = "ไม่พบข้อมูลสาขา")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteBranch(@PathVariable Long id) {
        return deleteBranchService.execute(id);
    }

    @Operation(summary = "อัปเดตข้อมูลสาขาตาม id", description = "สําหรับอัพเดตข้อมูลสาขาตาม ID NOTE: managerID สามารถเว้นไว้ได้ ในกรณีที่ยังไม่อยากให้สาขานั้นมีผู้จัดการ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ: สาขาถูกอัปเดต"),
            @ApiResponse(responseCode = "404", description = "ไม่พบข้อมูลสาขา"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PutMapping("{id}")
    public ResponseEntity<Response> updateBranch(@PathVariable Long id, @RequestBody @Valid BranchSummaryDTO dto) {
        return updateBranchService.execute(id, dto);
    }

}
