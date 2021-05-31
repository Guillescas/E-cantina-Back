package br.com.projeto.ecantina.controller;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.ecantina.config.errors.ResponseErrors;


@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    
    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Object> create(@RequestParam String qrText) {
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, 250, 250);
        } catch (WriterException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ResponseErrors(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        
        
        return ResponseEntity.ok(MatrixToImageWriter.toBufferedImage(bitMatrix));
    }
    
}
