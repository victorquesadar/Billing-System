package org.example.proyecto2.logic;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
@Component("GenerarXML")
public class GenerarXML {
    public void guardarXmlFactura(Factura factura, Proveedor proveedor, Cliente cliente, List<Producto> productoList, List<Servicio> servicioList){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document documento = implementation.createDocument(null,"Sifac",null);
            documento.setXmlVersion("1.0");

            Element factur = documento.createElement("factura");
            Element prov = documento.createElement("proveedor");
            Element clie = documento.createElement("cliente");

            Element numero = documento.createElement("numero");
            Text textNumero = documento.createTextNode(String.valueOf(factura.getNumero()));
            numero.appendChild(textNumero);
            factur.appendChild(numero);

            Element codigo = documento.createElement("codigo");
            Text textCodigo = documento.createTextNode(factura.getNombre());
            codigo.appendChild(textCodigo);
            factur.appendChild(codigo);

            Element fecha = documento.createElement("fecha");
            Text textFecha = documento.createTextNode(factura.getFecha());
            fecha.appendChild(textFecha);
            factur.appendChild(fecha);

            //--- PROVEEDOR ---

            Element cedProv = documento.createElement("cedula");
            Text textCedProv = documento.createTextNode(proveedor.getCedula());
            cedProv.appendChild(textCedProv);
            prov.appendChild(cedProv);

            Element nombreProv = documento.createElement("nombre");
            Text textNombreProv = documento.createTextNode(proveedor.getNombre());
            nombreProv.appendChild(textNombreProv);
            prov.appendChild(nombreProv);

            Element correoProv = documento.createElement("correo");
            Text textCorreoProv = documento.createTextNode(proveedor.getEmail());
            correoProv.appendChild(textCorreoProv);
            prov.appendChild(correoProv);

            factur.appendChild(prov);

            //-----------------

            //--- CLIENTE ---

            Element cedCli = documento.createElement("cedula");
            Text textCedCli = documento.createTextNode(cliente.getCedula());
            cedCli.appendChild(textCedCli);
            clie.appendChild(cedCli);

            Element nombreCli = documento.createElement("nombre");
            Text textNombreCli = documento.createTextNode(cliente.getNombre());
            nombreCli.appendChild(textNombreCli);
            clie.appendChild(nombreCli);

            Element correoCli = documento.createElement("correo");
            Text textCorreoCli = documento.createTextNode(cliente.getEmail());
            correoCli.appendChild(textCorreoCli);
            clie.appendChild(correoCli);

            factur.appendChild(clie);

            //---------------

            //--- LISTAPRODUCTOS ---

            Element productos = documento.createElement("productos");
            for(int i=0;i<productoList.size();i++){
                Element producto = documento.createElement("producto");

                Element codigoProd = documento.createElement("codigo");
                Text textCodigoProd = documento.createTextNode(productoList.get(i).getCodigo());
                codigoProd.appendChild(textCodigoProd);
                producto.appendChild(codigoProd);

                Element nombreProd = documento.createElement("nombre");
                Text textnombreProd = documento.createTextNode(productoList.get(i).getNombre());
                nombreProd.appendChild(textnombreProd);
                producto.appendChild(nombreProd);

                Element precioUniProd = documento.createElement("precioUnitario");
                Text textPrecioUniProd = documento.createTextNode(String.valueOf(productoList.get(i).getPrecio()));
                precioUniProd.appendChild(textPrecioUniProd);
                producto.appendChild(precioUniProd);

                Element cantidadProd = documento.createElement("cantidad");
                Text textCantidadProd = documento.createTextNode(String.valueOf(productoList.get(i).getCantidad()));
                cantidadProd.appendChild(textCantidadProd);
                producto.appendChild(cantidadProd);

                Element totalLineaProd = documento.createElement("totalLinea");
                Text textTotalLineaProd = documento.createTextNode(String.valueOf(productoList.get(i).getTotallinea()));
                totalLineaProd.appendChild(textTotalLineaProd);
                producto.appendChild(totalLineaProd);


                productos.appendChild(producto);
            }
            factur.appendChild(productos);

            //------------------

            //--- LISTASERVICIOS ---

            Element servicios = documento.createElement("servicios");
            for(int i=0;i<servicioList.size();i++){
                Element servicio = documento.createElement("servicio");

                Element codigoSer = documento.createElement("codigo");
                Text textCodigoSer = documento.createTextNode(servicioList.get(i).getCodigo());
                codigoSer.appendChild(textCodigoSer);
                servicio.appendChild(codigoSer);

                Element nombreSer = documento.createElement("nombre");
                Text textNombreSer = documento.createTextNode(servicioList.get(i).getNombre());
                nombreSer.appendChild(textNombreSer);
                servicio.appendChild(nombreSer);

                Element precioSer = documento.createElement("precio");
                Text textPrecioSer = documento.createTextNode(String.valueOf(servicioList.get(i).getPrecio()));
                precioSer.appendChild(textPrecioSer);
                servicio.appendChild(precioSer);

                servicios.appendChild(servicio);
            }
            factur.appendChild(servicios);

            //--------------------

            //--- SUMATORIA FACTURA ---

            Element subTotal = documento.createElement("subTotal");
            Text textSubTotal = documento.createTextNode(String.valueOf(factura.getSubtotal()));
            subTotal.appendChild(textSubTotal);
            factur.appendChild(subTotal);

            Element iva = documento.createElement("iva");
            Text textIva = documento.createTextNode(String.valueOf(factura.getImpuesto()));
            iva.appendChild(textIva);
            factur.appendChild(iva);

            Element total = documento.createElement("total");
            Text textTotal = documento.createTextNode(String.valueOf(factura.getTotal()));
            total.appendChild(textTotal);
            factur.appendChild(total);

            //------------------------

            documento.getDocumentElement().appendChild(factur);

            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File(factura.getNombre()+".xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        }
        catch (ParserConfigurationException | TransformerException ex){
            System.out.println(ex.getMessage());
        }
    }
}