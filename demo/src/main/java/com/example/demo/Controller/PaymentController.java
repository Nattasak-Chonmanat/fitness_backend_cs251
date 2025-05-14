package com.example.demo.Controller;

import com.example.demo.DTOs.PaymentDTO;
import com.example.demo.model.CreatePaymentRequest;
import com.example.demo.model.Response;
import com.example.demo.model.UpdatePaymentRequest;
import com.example.demo.service.payment.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@Tag(name = "Payment", description = "สำหรับจัดการข้อมูลการชำระเงินของสมาชิก")
public class PaymentController {

    private final CreatePaymentService createPaymentService;
    private final UpdatePaymentService updatePaymentService;
    private final GetPaymentByMemberIdService getPaymentByMemberIdService;
    private final DeletePaymentService deletePaymentService;
    private final GetPaymentsService getPaymentsService;
    private final GetPaymentByIdService getPaymentByIdService;

    public PaymentController(CreatePaymentService createPaymentService, UpdatePaymentService updatePaymentService, GetPaymentByMemberIdService getPaymentByMemberIdService, DeletePaymentService deletePaymentService, GetPaymentsService getPaymentsService, GetPaymentByIdService getPaymentByIdService) {
        this.createPaymentService = createPaymentService;
        this.updatePaymentService = updatePaymentService;
        this.getPaymentByMemberIdService = getPaymentByMemberIdService;
        this.deletePaymentService = deletePaymentService;
        this.getPaymentsService = getPaymentsService;
        this.getPaymentByIdService = getPaymentByIdService;
    }


    @Operation(
            summary = "เรียกดูข้อมูลการชำระเงินทั้งหมดของสมาชิก",
            description = "ใช้ memberId เพื่อดึงข้อมูลการชำระเงินของสมาชิกที่เกี่ยวข้อง"
    )
    @ApiResponse(responseCode = "200", description = "เรียกข้อมูลสำเร็จ")
    @GetMapping("get-by-member/{memberId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentByMemberId(@PathVariable Long memberId) {
        return  getPaymentByMemberIdService.execute(memberId);
    }

    @Operation(
            summary = "เรียกดูข้อมูลการชำระเงินตาม paymentId",
            description = "ใช้ paymentId เพื่อดึงข้อมูลการชำระเงิน"
    )
    @ApiResponse(responseCode = "200", description = "เรียกข้อมูลสำเร็จ")
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long paymentId) {
        return getPaymentByIdService.execute(paymentId);
    }


    @Operation(
            summary = "เรียกดูข้อมูลการชำระเงินทั้งหมด",
            description = "ใช้สำหรับผู้ดูแลเพื่อดูข้อมูลการชำระเงินทั้งหมด"
    )
    @ApiResponse(responseCode = "200", description = "เรียกข้อมูลทั้งหมดสำเร็จ")
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return getPaymentsService.execute(null);
    }

    @Operation(
            summary = "สร้างข้อมูลการชำระเงิน",
            description = "บันทึกข้อมูลการชำระเงินใหม่ของสมาชิก"
    )
    @ApiResponse(responseCode = "201", description = "สร้างข้อมูลสำเร็จ")
    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody @Valid CreatePaymentRequest request) {
        return createPaymentService.execute(request);
    }


    @Operation(
            summary = "อัปเดตข้อมูลการชำระเงิน",
            description = "แก้ไขรายละเอียดการชำระเงินที่มีอยู่โดยระบุ paymentId"
    )
    @ApiResponse(responseCode = "200", description = "อัปเดตข้อมูลสำเร็จ")
    @PutMapping("/{paymentId}")
    public ResponseEntity<Response> updatePayment(@PathVariable Long paymentId, @RequestBody @Valid UpdatePaymentRequest request) {
        return updatePaymentService.execute(paymentId, request);
    }


    @Operation(
            summary = "ลบข้อมูลการชำระเงิน",
            description = "ลบข้อมูลการชำระเงินที่ระบุด้วย paymentId"
    )
    @ApiResponse(responseCode = "200", description = "ลบข้อมูลสำเร็จ")
    @DeleteMapping("{paymentId}")
    public ResponseEntity<Response> deletePayment(@PathVariable Long paymentId) {
        return deletePaymentService.execute(paymentId);
    }
}
