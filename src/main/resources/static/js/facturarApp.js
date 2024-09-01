document.addEventListener("DOMContentLoaded", function(){
    if(window.location.pathname === "/listaFacturas"){
        head();
        header();
        footer();
        listaFact();
    }

    if(window.location.pathname ==="/facturar"){
        head();
        header();
        footer();
        clienteLista();
        productoLista();
        servicioLista();

        const guardarDetalle = document.getElementById("formDetallesFactura");
        if(guardarDetalle){
            guardarDetalle.addEventListener("submit", function (event){
                event.preventDefault();
                const nombre = document.getElementById("nombre").value;
                const fecha = document.getElementById("fecha").value;
                const cliente = document.getElementById("selCliente").value;
                console.log("Nombre:", nombre);
                console.log("Fecha:", fecha);
                console.log("Cliente:", cliente);
                guardarDetalleFac(nombre,fecha,cliente);
            });
        }

        const guardarProducto = document.getElementById("formAñadirProducto");
        if(guardarProducto){
            guardarProducto.addEventListener("submit", function (event){
                event.preventDefault();
                const producto = document.getElementById("selProducto").value;
                const cantidad = document.getElementById("cantidad").value;

                console.log("cantidad:", cantidad);
                console.log("producto:", producto);
                guardarProductoFac(producto,cantidad);
            });
        }

        const guardarServicio = document.getElementById("formAñadirServicio");
        if(guardarServicio){
            guardarServicio.addEventListener("submit", function (event){
                event.preventDefault();
                const servicio = document.getElementById("selServicio").value;
                const precio = document.getElementById("precio").value;

                console.log("servicio:", servicio);
                console.log("precio:", precio);
                guardarServicioFac(servicio,precio);
            });
        }

        const formGenerarFactura = document.getElementById("formGenerarFactura");
        if (formGenerarFactura) {
            formGenerarFactura.addEventListener("submit", function(event) {
                event.preventDefault();
                generarFactura();
            });
        }
    }
});

function head() {
    const head = document.getElementsByClassName("head");
    for (let i=0; i<head.length; i++) {
        head[i].innerHTML = `
            <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" type="text/css" href="/styles.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
                <title>SIFAC</title>
            </head>
        `;
    }
}

function header() {
    const headerProv = document.getElementsByClassName("headerProv");
    for (let i=0; i<headerProv.length; i++) {
        headerProv[i].innerHTML = `
            <header class="headerNav">
                <a id="btLinkMenuProv" href="/menuProv">
                    <img class="sifacH" src="\sifac.png" height="60px" width="60px">
                </a>
    
                <nav id="navProv">
                    <ul>
                        <li> <a href="/facturar" target="_blank"> Facturar </a> </li>
                        <li> <a href="/registroCliente" target="_blank"> Registrar Cliente </a> </li>
                        <li> <a href="/registroProducto" target="_blank"> Registrar Producto </a> </li>
                        <li> <a href="/registroServicio" target="_blank"> Registrar Servicio </a> </li>
                        <li> <a href="/listaFacturas" target="_blank"> Ver Facturas </a> </li>
                        <li> <a href="/perfil" target="_blank"> Perfil </a> </li>
                        <li> <a href="/index" target="_self"> Cerrar Sesión </a> </li>
                    </ul>
                </nav>
            </header>
        `;
    }
}

function footer() {
    const footerBase = document.getElementsByClassName("footerBase");
    for (let i=0; i<footerBase.length; i++) {
        footerBase[i].innerHTML = `
            <footer id="footerBase">
                <p>
                    2024 - SIFAC - Derechos Reservados
                </p>
            </footer>
         `;
    }
}

function popUpDetallesFactura() {
    const popUp = document.getElementById("popupDetallesFactura");
    const overlay = document.getElementById("overlayDetallesFactura");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupDetallesFactura");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function popUpErrorDetallesFactura() {
    const popUp = document.getElementById("popupErrorDetallesFactura");
    const overlay = document.getElementById("overlayErrorDetallesFactura");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupErrorDetallesFactura");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function popUpAnadirProducto() {
    const popUp = document.getElementById("popupAñadirProducto");
    const overlay = document.getElementById("overlayAñadirProducto");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupAñadirProducto");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function popUpErrorAnadirProducto() {
    const popUp = document.getElementById("popupErrorAñadirProducto");
    const overlay = document.getElementById("overlayErrorAñadirProducto");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupErrorAñadirProducto");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function popUpAnadirServicio() {
    const popUp = document.getElementById("popupAñadirServicio");
    const overlay = document.getElementById("overlayAñadirServicio");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupAñadirServicio");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function popUpErrorAnadirServicio() {
    const popUp = document.getElementById("popupErrorAñadirServicio");
    const overlay = document.getElementById("overlayErrorAñadirServicio");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupErrorAñadirServicio");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function popUpGenerarFactura() {
    const popUp = document.getElementById("popupGenerarFactura");
    const overlay = document.getElementById("overlayGenerarFactura");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupGenerarFactura");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function popUpErrorGenerarFactura() {
    const popUp = document.getElementById("popupErrorGenerarFactura");
    const overlay = document.getElementById("overlayErrorGenerarFactura");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupErrorGenerarFactura");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function clienteLista() {
    fetch("/api/facturas/listaClientes")
        .then(response => response.json())
        .then(data => {
            const selectCliente = document.getElementById("selCliente");
            selectCliente.innerHTML = '<option value="" style="display: none;">Selecciona una opción</option>';
            data.forEach(cliente => {
                const option = document.createElement("option");
                option.value = cliente.cedula;
                option.textContent = cliente.cedula + '|' + cliente.nombre;
                selectCliente.appendChild(option);
            });
        })
        .catch(error => console.error("Error al obtener la lista de clientes:", error));
}

function productoLista(){
    fetch("/api/facturas/listaProductos")
        .then(response => response.json())
        .then(data => {
            const selectProducto = document.getElementById("selProducto");
            selectProducto.innerHTML = '<option value="" style="display: none;">Selecciona una opción</option>';
            data.forEach(producto => {
                const option = document.createElement("option");
                option.value = producto.codigo;
                option.textContent = producto.codigo + '|' + producto.nombre;
                selectProducto.appendChild(option);
            });
        })
        .catch(error => console.error("Error al obtener la lista de productos:", error));
}

function servicioLista(){
    fetch("/api/facturas/listaServicios")
        .then(response => response.json())
        .then(data => {
            const selectServicio = document.getElementById("selServicio");
            selectServicio.innerHTML = '<option value="" style="display: none;">Selecciona una opción</option>';
            data.forEach(servicio => {
                const option = document.createElement("option");
                option.value = servicio.codigo;
                option.textContent = servicio.codigo + '|' + servicio.nombre;
                selectServicio.appendChild(option);
            });
        })
        .catch(error => console.error("Error al obtener la lista de servicios:", error));
}

function guardarDetalleFac(nombre, fecha, cliente) {
    const datos = new URLSearchParams();
    datos.append('nombre', nombre);
    datos.append('fecha', fecha);
    datos.append('cliente', cliente);

    fetch('api/facturas/guardarDetallesFactura', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: datos
    })
        .then(response => {
            if (!response.ok) {
                popUpErrorDetallesFactura();
                throw new Error('Error al guardar detalles de factura');
            }
            else{
                popUpDetallesFactura();
                setTimeout(() => {
                    window.location.href = "/facturar";
                },5000);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function guardarProductoFac(producto,cantidad){
    const datos = new URLSearchParams();
    datos.append('producto', producto);
    datos.append('cantidad', cantidad);

    fetch('api/facturas/guardarProductoFactura', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: datos
    })
        .then(response => {
            if (!response.ok) {
                popUpErrorAnadirProducto();
                throw new Error('Error al guardar detalles de producto');
            }
            else{
                popUpAnadirProducto();
                setTimeout(() => {
                    window.location.href = "/facturar";
                },3000);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function guardarServicioFac(servicio, precio){
    const datos = new URLSearchParams();
    datos.append('servicio', servicio);
    datos.append('precio', precio);

    fetch('api/facturas/guardarServicioFactura', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: datos
    })
        .then(response => {
            if (!response.ok) {
                popUpErrorAnadirServicio();
                throw new Error('Error al guardar detalles de servicio');
            }
            else{
                popUpAnadirServicio();
                setTimeout(() => {
                    window.location.href = "/facturar";
                },3000);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function generarFactura() {
    fetch('api/facturas/generarFactura', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                popUpErrorGenerarFactura();
                throw new Error('Error al generar la factura');
            }
            else{
                popUpGenerarFactura();
                setTimeout(() => {
                    window.location.href = "/facturar";
                },7000);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function listaFact(){
    fetch("/api/facturas/listaFac")
        .then(response => response.json())
        .then(facturas =>{
            console.log(facturas);
            const cuerpoTablaFacturas = document.getElementById("cuerpo-tabla-facturas");
            cuerpoTablaFacturas.innerHTML = "";
            facturas.forEach(factura => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td class="paddingNombreFactura">
                        ${factura.nombre}
                    </td>
                    
                    <td class="paddingBtGenerarPDF">
                        <div class="btGenerar btGenerarPDF">
                            <a id="btLinkPDF" href="${factura.nombre}.pdf" target="_blank">PDF</a>
                        </div>    
                    </td>
                    
                    <td class="paddingBtGenerarXML">
                        <div class="btGenerar btGenerarXML">
                            <a id="btLinkXML" href="${factura.nombre}.xml" target="_blank">XML</a>
                        </div>
                    </td>
                `;
                cuerpoTablaFacturas.appendChild(tr);
            });
        })
        .catch(error => console.error("Error al obtener la lista de facturas:", error));
}