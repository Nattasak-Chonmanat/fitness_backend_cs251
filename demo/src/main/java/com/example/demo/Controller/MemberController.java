package com.example.demo.Controller;

import com.example.demo.DTOs.MemberDTO;
import com.example.demo.model.*;
import com.example.demo.service.member.*;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("member")
@Tag(name = "Member", description = "สำหรับจัดการข้อมูลสมาชิก")
public class MemberController {

    private final RegisterMemberService createMemberService;
    private final GetAllMembersService getAllMembersService;
    private final DeleteMemberService deleteMemberService;
    private final GetMemberByIdService getMemberByIdService;
    private final UpdateMemberService updateMemberService;
    private final LoginMemberService loginMemberService;
    private final UpdateMemberMembershipService updateMemberMembershipService;

    public MemberController(
            RegisterMemberService createMemberService,
            GetAllMembersService getAllMembersService,
            DeleteMemberService deleteMemberService,
            GetMemberByIdService getMemberByIdService,
            UpdateMemberService updateMemberService,
            LoginMemberService loginMemberService,
            UpdateMemberMembershipService updateMemberMembershipService) {
        this.createMemberService = createMemberService;
        this.getAllMembersService = getAllMembersService;
        this.deleteMemberService = deleteMemberService;
        this.getMemberByIdService = getMemberByIdService;
        this.updateMemberService = updateMemberService;
        this.loginMemberService = loginMemberService;
        this.updateMemberMembershipService = updateMemberMembershipService;
    }

    @Operation(summary = "ดึงสมาชิกทั้งหมด", description = "ใช้สำหรับเรียกดูรายชื่อสมาชิกทั้งหมดในระบบ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ")
    })
    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return getAllMembersService.execute(null);
    }

    @Operation(summary = "ดึงข้อมูลสมาชิกตาม ID", description = "ใช้สำหรับดึงข้อมูลของสมาชิกที่มี ID ระบุ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ"),
            @ApiResponse(responseCode = "404", description = "ไม่พบสมาชิก")
    })
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> getMemberByID(@PathVariable Long memberId) {
        return getMemberByIdService.execute(memberId);
    }

    @Operation(summary = "สมัครสมาชิกใหม่", description = "ใช้สำหรับลงทะเบียนสมาชิกใหม่")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ลงทะเบียนสำเร็จ"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PostMapping("/register")
    public ResponseEntity<MemberDTO> createMembers(@RequestBody @Valid Member m) {
        return createMemberService.execute(m);
    }

    @Operation(summary = "เข้าสู่ระบบสมาชิก", description = "ใช้สำหรับเข้าสู่ระบบสมาชิกโดยตรวจสอบข้อมูลบัญชีผู้ใช้")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "เข้าสู่ระบบสำเร็จ", content = @Content(schema = @Schema(implementation = MemberLoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง", content = @Content(
                    examples = @ExampleObject(
                            name = "ตัวอย่างการตอบกลับ",
                            summary = "Response Example",
                            value = """
                                        {
                                          "message": "'Operation' success/failed",
                                          "status": "Http status code(200, 201, 400, 404, 500)"
                                        }
                                    """
                    )
            ))
    })
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> LoginMember(@RequestBody MemberLoginRequest loginRequest) {
        return loginMemberService.execute(loginRequest);
    }

    @Operation(summary = "ลบสมาชิก", description = "ใช้สำหรับลบสมาชิกออกจากระบบ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ลบสำเร็จ"),
            @ApiResponse(responseCode = "404", description = "ไม่พบสมาชิก")
    })
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Response> deleteMember(@PathVariable Long memberId) {
        return deleteMemberService.execute(memberId);
    }

    @Operation(summary = "อัปเดตข้อมูลสมาชิก", description = "ใช้สำหรับอัปเดตข้อมูลของสมาชิกที่มีอยู่")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "อัปเดตสำเร็จ"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง"),
            @ApiResponse(responseCode = "404", description = "ไม่พบสมาชิก")
    })
    @PutMapping("/{memberId}")
    public ResponseEntity<Response> updateMember(@PathVariable Long memberId, @Valid @RequestBody UpdateMemberRequest member) {
        return updateMemberService.execute(memberId, member);
    }

    @Operation(summary = "อัปเดตแผนสมาชิก", description = "ใช้สำหรับเปลี่ยนแผนสมาชิกของสมาชิกที่ระบุ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "เปลี่ยนแผนสมาชิกสำเร็จ"),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง"),
            @ApiResponse(responseCode = "404", description = "ไม่พบสมาชิก")
    })
    @PutMapping("/update-membership/{memberId}")
    public ResponseEntity<Response> updateMemberMembershipPlan(
            @PathVariable Long memberId,
            @RequestBody @Valid UpdateMemberMembershipRequest request) {
        return updateMemberMembershipService.execute(memberId, request);
    }

}
