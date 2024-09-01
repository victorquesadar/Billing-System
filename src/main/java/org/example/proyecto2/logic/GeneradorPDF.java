package org.example.proyecto2.logic;
import java.io.*;
import java.util.List;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Component;

@Component("GeneradorPDF")
public class GeneradorPDF {

    public void generarFacturaPDF(Factura factura, Proveedor proveedor, Cliente cliente, List<Producto> productoList, List<Servicio> servicioList) {
        try {
            String fileName = factura.getNombre() + ".pdf";
            File file = new File(fileName);
            OutputStream outputStream = new FileOutputStream(file);
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);

            Paragraph titulo = new Paragraph("Facturador Electrónico");
            document.add(titulo);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            document.add(new Paragraph(factura.getNombre()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(factura.getFecha()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Proveedor:"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Cedula: " + proveedor.getCedula()));
            document.add(new Paragraph("Nombre: " + proveedor.getNombre()));
            document.add(new Paragraph("Correo electrónico: " + proveedor.getEmail()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Cliente: "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Cedula: " + cliente.getCedula()));
            document.add(new Paragraph("Nombre: " + cliente.getNombre()));
            document.add(new Paragraph("Correo electrónico: " + cliente.getEmail()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            if (productoList != null) {
                for (int i=0; i<productoList.size(); i++) {
                    document.add(new Paragraph("Producto: "));
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("Código: " + productoList.get(i).getCodigo()));
                    document.add(new Paragraph("Nombre: " + productoList.get(i).getNombre()));
                    document.add(new Paragraph("Precio Unitario: " + productoList.get(i).getPrecio()));
                    document.add(new Paragraph("Cantidad: " + productoList.get(i).getCantidad()));
                    document.add(new Paragraph("Total Línea: " + productoList.get(i).getTotallinea()));
                }
            }
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            if (servicioList != null) {
                for (int i=0; i<servicioList.size(); i++) {
                    document.add(new Paragraph("Servicio: "));
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("Código: " + servicioList.get(i).getCodigo()));
                    document.add(new Paragraph("Nombre: " + servicioList.get(i).getNombre()));
                    document.add(new Paragraph("Precio: " + servicioList.get(i).getPrecio()));
                }
            }
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Subtotal: " + factura.getSubtotal()));
            document.add(new Paragraph("I.V.A (13%): " + factura.getImpuesto()));
            document.add(new Paragraph("Total: " + factura.getTotal()));

            document.close();

            System.out.println("La factura PDF se ha generado exitosamente.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}