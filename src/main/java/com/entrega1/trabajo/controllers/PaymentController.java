package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Referee;
import com.entrega1.trabajo.service.payments.PaymentService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@Controller
@RequestMapping("/pagos")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Muestra el formulario de simulación de pago.
    @GetMapping("/simular")
    public String mostrarFormularioPago(
            @RequestParam(name = "nombre", required = false) String nombre,
            Model model) {

        model.addAttribute("nombre", nombre);
        model.addAttribute("resultado", null);
        model.addAttribute("monto", null);
        return "payments/simular-pago";
    }

    // Procesa el formulario (simulación de pago con la inversión de dependencias).
    @PostMapping("/simular")
    public String procesarPago(@RequestParam String nombre,
                               @RequestParam double saldo,
                               @RequestParam double monto,
                               Model model) {

        Referee referee = new Referee();
        referee.setName(nombre);
        referee.setSaldoDisponible(saldo);

        String resultado = paymentService.pagarReferee(referee, monto);

        model.addAttribute("nombre", nombre);
        model.addAttribute("resultado", resultado);
        model.addAttribute("monto", monto);
        return "payments/simular-pago";
    }

    // 🔹 NUEVO: Generar PDF del cheque
    @GetMapping("/cheque/pdf")
    public ResponseEntity<byte[]> generarChequePdf(@RequestParam String nombre,
                                                   @RequestParam double monto) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();

            // Título centrado
            Paragraph title = new Paragraph(
                    "CHEQUE DE PAGO",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)
            );
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // línea en blanco

            // Contenido del cheque
            Paragraph contenido = new Paragraph(
                    """
                    Referee: %s
                    Monto: $%.2f
                    Banco: Banco Simulado EAFIT
                    """.formatted(nombre, monto),
                    FontFactory.getFont(FontFactory.HELVETICA, 14)
            );
            document.add(contenido);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(
                    "Firma autorizada: __________________________",
                    FontFactory.getFont(FontFactory.HELVETICA, 12)
            ));

            document.close();

            // Headers HTTP para que el navegador lo trate como PDF descargable
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData(
                    "filename",
                    "cheque-" + nombre.replace(" ", "_") + ".pdf"
            );

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(baos.toByteArray());

        } catch (Exception e) {
            // En caso de error, podrías redirigir a una página de error o similar.
            return ResponseEntity.status(500).build();
        }
    }
}
