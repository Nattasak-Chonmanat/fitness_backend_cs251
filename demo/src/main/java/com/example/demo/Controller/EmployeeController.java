package com.example.demo.Controller;

import com.example.demo.DTOs.EmployeeDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.Response;
import com.example.demo.service.employee.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Tag(name = "Employee", description = "สำหรับจัดการข้อมูลพนักงาน")
public class EmployeeController {

    private final CreateEmployeeService createEmployeeService;
    private final GetEmployeesService getEmployeesService;
    private final UpdateEmployeeService updateEmployeeService;
    private final DeleteEmployeeService deleteEmployeeService;
    private final GetEmployeeByIdService getEmployeeByIdService;

    public EmployeeController(CreateEmployeeService createEmployeeService, GetEmployeesService getEmployeesService, UpdateEmployeeService updateEmployeeService, DeleteEmployeeService deleteEmployeeService, GetEmployeeByIdService getEmployeeByIdService) {
        this.createEmployeeService = createEmployeeService;
        this.getEmployeesService = getEmployeesService;
        this.updateEmployeeService = updateEmployeeService;
        this.deleteEmployeeService = deleteEmployeeService;
        this.getEmployeeByIdService = getEmployeeByIdService;
    }

    @Operation(summary = "ดึงรายชื่อพนักงานทั้งหมด")
    @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่ารายชื่อพนักงานทั้งหมด")
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        return getEmployeesService.execute(null);
    }

    @Operation(summary = "ดึงข้อมูลพนักงานตาม ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ: คืนค่าข้อมูลพนักงาน"),
            @ApiResponse(responseCode = "404", description = "ไม่พบพนักงานที่ระบุ")
    })
    @GetMapping("{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        return getEmployeeByIdService.execute(employeeId);
    }

    @Operation(summary = "สร้างพนักงานใหม่")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "สร้างพนักงานสำเร็จ"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid Employee employee) {
        return createEmployeeService.execute(employee);
    }

    @Operation(summary = "อัปเดตข้อมูลพนักงาน")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "อัปเดตข้อมูลสำเร็จ"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง"),
            @ApiResponse(responseCode = "404", description = "ไม่พบพนักงาน")
    })
    @PutMapping("/{employeeId}")
    public ResponseEntity<Response> updateEmployee(@PathVariable Long employeeId, @RequestBody @Valid Employee employee) {
        return updateEmployeeService.execute(employeeId, employee);
    }

    @Operation(summary = "ลบพนักงาน")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ลบพนักงานสำเร็จ"),
            @ApiResponse(responseCode = "404", description = "ไม่พบพนักงาน")
    })
    @DeleteMapping("{employeeId}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable Long employeeId) {
        return deleteEmployeeService.execute(employeeId);
    }
}
