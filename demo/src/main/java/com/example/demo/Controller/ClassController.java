package com.example.demo.Controller;

import com.example.demo.DTOs.ClassDTO;
import com.example.demo.DTOs.ClassSummaryDTO;
import com.example.demo.model.Class;
import com.example.demo.model.Response;
import com.example.demo.service.aClass.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
@Tag(name = "Class", description = "สำหรับจัดการข้อมูลคลาสออกกําลังกาย")
public class ClassController {

    private final GetAllClassService getAllClassService;
    private final CreateClassService createClassService;
    private final GetClassByIdService getClassByIdService;
    private final SearchClassByClassName searchClassByClassName;
    private final UpdateClassService updateClassService;
    private final DeleteClassByIdService deleteClassByIdService;

    public ClassController(GetAllClassService getAllClassService, CreateClassService createClassService, GetClassByIdService getClassByIdService, SearchClassByClassName searchClassByClassName, UpdateClassService updateClassService, DeleteClassByIdService deleteClassByIdService) {
        this.getAllClassService = getAllClassService;
        this.createClassService = createClassService;
        this.getClassByIdService = getClassByIdService;
        this.searchClassByClassName = searchClassByClassName;
        this.updateClassService = updateClassService;
        this.deleteClassByIdService = deleteClassByIdService;
    }

    @Operation(summary = "ดึงคลาสออกกําลังกายทั้งหมด")
    @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายการคลาสทั้งหมด")
    @GetMapping
    public ResponseEntity<List<ClassSummaryDTO>> getAllClasses() {
        return getAllClassService.execute(null);
    }

    @Operation(summary = "ค้นหาคลาสด้วยชื่อ")
    @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่าคลาสที่ตรงกับชื่อที่ค้นหา")
    @GetMapping("/search")
    public ResponseEntity<List<ClassSummaryDTO>> searchClasses(@RequestParam(defaultValue = "") String name) {
        return searchClassByClassName.execute(name);
    }

    @Operation(summary = "สร้างคลาสใหม่")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "สร้างคลาสสำเร็จ"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PostMapping
    public ResponseEntity<ClassSummaryDTO> createClass(@RequestBody @Valid Class c) {
        return createClassService.execute(c);
    }

    @Operation(summary = "ดึงข้อมูลคลาสตาม ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายละเอียดคลาส"),
            @ApiResponse(responseCode = "404", description = "ไม่พบคลาส")
    })
    @GetMapping("/{classId}")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable Long classId) {
        return getClassByIdService.execute(classId);
    }

    @Operation(summary = "อัปเดตข้อมูลคลาส")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "อัปเดตคลาสสำเร็จ"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง"),
            @ApiResponse(responseCode = "404", description = "ไม่พบคลาส")
    })
    @PutMapping("/{classId}")
    public ResponseEntity<Response> updateClass(@PathVariable Long classId, @RequestBody @Valid Class c) {
        return updateClassService.execute(classId, c);
    }

    @Operation(summary = "ลบคลาสออกกําลังกาย")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ลบคลาสสำเร็จ"),
            @ApiResponse(responseCode = "404", description = "ไม่พบคลาส")
    })
    @DeleteMapping("/{classId}")
    public ResponseEntity<Response> deleteClass(@PathVariable Long classId) {
        return deleteClassByIdService.execute(classId);
    }

}
