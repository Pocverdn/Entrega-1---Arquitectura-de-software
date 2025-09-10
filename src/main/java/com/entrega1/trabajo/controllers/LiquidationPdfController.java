package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Liquidation;
import com.entrega1.trabajo.service.LiquidationService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayOutputStream;

@Controller
public class LiquidationPdfController {
    private final LiquidationService liquidationService;

    public LiquidationPdfController(LiquidationService liquidationService) {
        this.liquidationService = liquidationService;
    }

    @GetMapping("/liquidations/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Integer id) {
        Liquidation liquidation = liquidationService.findById(id).orElseThrow();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph("Liquidación de Árbitro"));
        document.add(new Paragraph("Árbitro: " + liquidation.getReferee().getName()));
        document.add(new Paragraph("Liga: " + liquidation.getReferee().getLeague()));
        document.add(new Paragraph("Fecha de creación: " + liquidation.getCreatedAt()));
        document.add(new Paragraph("Monto total: $" + liquidation.getTotalAmount()));
        document.add(new Paragraph("Partidos incluidos:"));
        for (var game : liquidation.getGames()) {
            document.add(new Paragraph("- " + game.getName() + " ($100.000)"));
        }
        document.close();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=liquidacion_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(baos.toByteArray());
    }
}
