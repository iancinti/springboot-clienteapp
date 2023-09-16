package clienteapp.springbootclienteapp.controller;

import clienteapp.springbootclienteapp.models.entity.Ciudad;
import clienteapp.springbootclienteapp.models.entity.Cliente;
import clienteapp.springbootclienteapp.models.service.ICiudadService;
import clienteapp.springbootclienteapp.models.service.IClienteService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

    private final Logger logger = LogManager.getLogger(ClienteController.class);
    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ICiudadService ciudadService;

    @GetMapping
    public String listarClientes(Model model,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(required = false) String filtro) {
        Page<Cliente> clientePage;

        if (filtro != null && !filtro.isEmpty()) {
            clientePage = clienteService.buscarClientesPorFiltro(filtro, page, size);
        } else {
            clientePage = clienteService.getClientesPaginados(page, size);
            filtro = "";
        }
        model.addAttribute("titulo", "Lista de Clientes");
        model.addAttribute("clientes", clientePage);
        model.addAttribute("filtro", filtro);

        return "/views/clientes/listar";
    }
    @GetMapping("/create")
    public String crear(Model model) {

        Cliente cliente = new Cliente();
        List<Ciudad> listCiudades = ciudadService.listaCiudades();

        model.addAttribute("titulo", "Formulario: Nuevo Cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("ciudades", listCiudades);
        return "/views/clientes/frmCrear";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Cliente cliente) {
        logger.info("Guardando cliente");
        clienteService.guardar(cliente);
        return "redirect:/views/clientes";
    }

    @GetMapping("/search")
    public String buscarClientesFiltrados(Model model, @RequestParam String filtro,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Page<Cliente> clientePage = clienteService.buscarClientesPorFiltro(filtro, page, size);

        model.addAttribute("titulo", "Lista de Clientes (Filtrada)");
        model.addAttribute("clientes", clientePage);
        model.addAttribute("filtro", filtro);

        return "/views/clientes/resultados";
    }

    @GetMapping("/seleccionados")
    public String verSeleccionados(@RequestParam(name = "ids", required = false) List<Long> ids, Model model) {
        if (ids != null && !ids.isEmpty()) {
            List<Cliente> selectedClientes = clienteService.getClientesPorIds(ids);
            model.addAttribute("selectedClientes", selectedClientes);
        }
        return "/views/clientes/seleccionados";
    }



    @GetMapping("/download-pdf")
    public void downloadPdf(@RequestParam("ids") List<Long> selectedIds, HttpServletResponse response) {
        logger.info("Identificadores seleccionados: " + selectedIds);
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=clientes_seleccionados.pdf");

            OutputStream outputStream = response.getOutputStream();
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

            for (Long clientId : selectedIds) {
                Cliente cliente = clienteService.getClienteDetailsById(clientId);
                document.add(new Paragraph("ID: " + cliente.getId()));
                document.add(new Paragraph("Nombres: " + cliente.getNombres()));
                document.add(new Paragraph("Apellidos: " + cliente.getApellidos()));
                document.add(new Paragraph("\n"));
            }

            document.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public String handleException(Exception ex, Model model) {
            String errorMessage = "Se ha producido un error en la aplicación.";
            model.addAttribute("errorMessage", errorMessage);
            return "/views/clientes/error";
        }
    }

}

