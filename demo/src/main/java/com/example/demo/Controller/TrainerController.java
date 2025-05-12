package com.example.demo.Controller;

import com.example.demo.DTOs.TrainerDTO;
import com.example.demo.model.Intendant;
import com.example.demo.model.Response;
import com.example.demo.model.Trainer;
import com.example.demo.service.trainer.*;
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
@RequestMapping("trainer")
@Tag(name = "Trainer", description = "สำหรับจัดการเทรนเนอร์")
public class TrainerController {

    private final AddToClassesService addToClassesService;
    private final GetTrainersService getTrainersService;
    private final GetTrainerByIdService getTrainerByIdService;
    private final UpdateTrainerService updateTrainerService;
    private final DeleteTrainerByIdService deleteTrainerService;
    private final DeleteFromClassService deleteFromClassService;
    private final CreateTrainerService createTrainerService;


    public TrainerController(AddToClassesService addToClassesService, GetTrainersService getTrainersService, GetTrainerByIdService getTrainerByIdService, UpdateTrainerService updateTrainerService, DeleteTrainerByIdService deleteTrainerService, DeleteFromClassService deleteFromClassService, CreateTrainerService createTrainerService) {
        this.addToClassesService = addToClassesService;
        this.getTrainersService = getTrainersService;
        this.getTrainerByIdService = getTrainerByIdService;
        this.updateTrainerService = updateTrainerService;
        this.deleteTrainerService = deleteTrainerService;
        this.deleteFromClassService = deleteFromClassService;
        this.createTrainerService = createTrainerService;
    }

    @Operation(summary = "ดึงรายชื่อเทรนเนอร์ทั้งหมด", description = "ใช้เพื่อดึงข้อมูลของเทรนเนอร์ทั้งหมด")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ", content = @Content(array =
                    @ArraySchema(schema = @Schema(implementation = TrainerDTO.class)))),
            @ApiResponse(responseCode = "400", description = "คำขอไม่ถูกต้อง", content = @Content(
                    examples = @ExampleObject(
                            name = "ตัวอย่างการตอบกลับ Bad Request",
                            summary = "Response Example",
                            value = """
                                    {
                                        message : :ErrorMessage,
                                        status : 400
                                    }
                                    """
                    )
            )),
            @ApiResponse(responseCode = "404", description = "หาเทรนเนอร์ไม่เจอ", content = @Content(
                    examples = @ExampleObject(
                            name = "ตัวอย่างการตอบกลับ Not Found",
                            summary = "Response Example",
                            value = """
                                    {
                                        message : Trainer not found with an ID : 1,
                                        status : 404
                                    }
                                    """
                    )
            ))
    })
    @GetMapping
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        return getTrainersService.execute(null);
    }


    @Operation(summary = "ดึงเทรนเนอร์ตาม ID", description = "ใช้เพื่อดึงข้อมูลเทรนเนอร์โดยระบุ ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สำเร็จ", content = @Content(schema = @Schema(implementation = TrainerDTO.class))),
            @ApiResponse(responseCode = "404", description = "ไม่พบเทรนเนอร์", content = @Content(
                    examples = @ExampleObject(
                            name = "ตัวอย่างการตอบกลับ Not Found",
                            summary = "Response Example",
                            value = """
                                    {
                                        message : Trainer not found with an ID : 1,
                                        status : 404
                                    }
                                    """
                    )
            ))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable Long id) {
        return getTrainerByIdService.execute(id);
    }

    @Operation(summary = "สร้างเทรนเนอร์ใหม่", description = "ใช้เพื่อเพิ่มเทรนเนอร์ลงในระบบ branch : {id : #id} ไม่ต้องใส่ก็ได้สําหรับกรณียังไม่อยากให้ trainer มีสาขาประจํา" )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "สร้างเทรนเนอร์สำเร็จ", content = @Content(schema = @Schema(implementation = TrainerDTO.class))),
            @ApiResponse(responseCode = "400", description = "ข้อมูลไม่ถูกต้อง")
    })
    @PostMapping
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody @Valid Trainer t) {
        return createTrainerService.execute(t);
    }

    @Operation(summary = "เพิ่มเทรนเนอร์ลงในคลาส", description = "ใช้เพื่อจัดเทรนเนอร์เข้าคลาส")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "เพิ่มเทรนเนอร์เข้าคลาสสำเร็จ", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "คำขอไม่ถูกต้อง")
    })
    @PostMapping("/addClass")
    public ResponseEntity<Response> addToClasses(@RequestBody Intendant intendance) {
        return addToClassesService.execute(intendance);
    }

    @Operation(summary = "อัปเดตข้อมูลเทรนเนอร์", description = "ใช้เพื่อแก้ไขข้อมูลเทรนเนอร์ที่มีอยู่")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "อัปเดตสำเร็จ", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "ไม่พบเทรนเนอร์", content = @Content(
                    examples = @ExampleObject(
                            name = "ตัวอย่างการตอบกลับ Not Found",
                            summary = "Response Example",
                            value = """
                                    {
                                        message : Trainer not found with an ID : 1,
                                        status : 404
                                    }
                                    """
                    )
            ))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateTrainer(@PathVariable Long id, @RequestBody Trainer t) {
        return updateTrainerService.execute(id, t);
    }

    @Operation(summary = "ลบเทรนเนอร์", description = "ใช้เพื่อลบเทรนเนอร์ออกจากระบบ branch : {id : #id} ไม่ต้องใส่ก็ได้สําหรับกรณียังไม่อยากให้ trainer มีสาขาประจํา")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ลบสำเร็จ", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "ไม่พบเทรนเนอร์", content = @Content(
                    examples = @ExampleObject(
                            name = "ตัวอย่างการตอบกลับ Not Found",
                            summary = "Response Example",
                            value = """
                                    {
                                        message : Trainer not found with an ID : 1,
                                        status : 404
                                    }
                                    """
                    )
            ))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTrainer(@PathVariable Long id) {
        return deleteTrainerService.execute(id);
    }

    @Operation(summary = "ลบเทรนเนอร์ออกจากคลาส", description = "ใช้เพื่อลบความสัมพันธ์ระหว่างเทรนเนอร์กับคลาส")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ลบสำเร็จ", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "ไม่พบเทรนเนอร์หรือคลาส", content = @Content(
                    examples = @ExampleObject(
                            name = "ตัวอย่างการตอบกลับ Not Found",
                            summary = "Response Example",
                            value = """
                                    {
                                        message : Trainer not found with an ID : 1,
                                        status : 404
                                    }
                                    """
                    )
            ))
    })
    @DeleteMapping("/{trainerId}/class/{classId}")
    public ResponseEntity<Response> removeTrainerFromClass(@PathVariable Long trainerId, @PathVariable Long classId) {
        return deleteFromClassService.execute(new Intendant(trainerId, classId));
    }

}
