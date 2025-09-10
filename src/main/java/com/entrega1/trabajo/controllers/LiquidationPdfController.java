package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Liquidation;
import com.entrega1.trabajo.service.LiquidationService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
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

        // Título grande y centrado
        Paragraph title = new Paragraph("LIQUIDACIÓN DE ÁRBITRO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" "));

        // Datos del árbitro
        document.add(new Paragraph("Árbitro: " + liquidation.getReferee().getName(), FontFactory.getFont(FontFactory.HELVETICA, 14)));
        document.add(new Paragraph("Liga: " + liquidation.getReferee().getLeague(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
        document.add(new Paragraph("Fecha de creación: " + liquidation.getCreatedAt(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
        document.add(new Paragraph(" "));

        // Tabla de partidos
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.addCell(new PdfPCell(new Phrase("Partido", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
        table.addCell(new PdfPCell(new Phrase("Valor", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
        for (var game : liquidation.getGames()) {
            table.addCell(new PdfPCell(new Phrase(game.getName(), FontFactory.getFont(FontFactory.HELVETICA, 12))));
            table.addCell(new PdfPCell(new Phrase("$100.000", FontFactory.getFont(FontFactory.HELVETICA, 12))));
        }
        document.add(table);
        document.add(new Paragraph(" "));

        // Monto total destacado
        Paragraph total = new Paragraph("MONTO TOTAL: $" + liquidation.getTotalAmount(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);
        document.add(new Paragraph(" "));

        // Espacio para firma
        document.add(new Paragraph("Firma: ____________________________", FontFactory.getFont(FontFactory.HELVETICA, 12)));

        document.close();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=liquidacion_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(baos.toByteArray());
    }
}
