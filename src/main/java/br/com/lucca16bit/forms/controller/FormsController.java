package br.com.lucca16bit.forms.controller;

import br.com.lucca16bit.forms.dto.forms.CreateFormDTO;
import br.com.lucca16bit.forms.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forms")
public class FormsController {

    @Autowired
    private EmailService emailService;

    @PostMapping("contact")
    public ResponseEntity<String> create(@RequestBody @Valid CreateFormDTO data) {
        try {
            String result = emailService.sendEmail(data);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao enviar a solicitação de contato: " + e.getMessage());
        }
    }
}
