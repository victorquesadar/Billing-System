document.addEventListener("DOMContentLoaded", function() {
    if(window.location.pathname ==="/registroServicio"){
        head();
        header();
        footer();
        llenarTablaServicios();
        const guardarServicio = document.getElementById("formRegistrarServicio");
        if(guardarServicio){
            guardarServicio.addEventListener("submit", function (event){
                event.preventDefault();
                const codigo=document.getElementById("codigo").value;
                const nombre = document.getElementById("nombre").value;
                guardarS({codigo, nombre});
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

function popUp() {
    const popUp = document.getElementById("popupRegistrarServicio");
    const overlay = document.getElementById("overlayRegistrarServicio");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupRegistrarServicio");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function popUpError() {
    const popUp = document.getElementById("popupErrorRegistrarServicio");
    const overlay = document.getElementById("overlayErrorRegistrarServicio");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupErrorRegistrarServicio");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function guardarS(servicio){
    fetch("/api/servicios/registrarServicio",{
        method: "POST",
        headers:{
            "Content-Type": "application/json"
        },
        body: JSON.stringify(servicio)
    })
        .then(response=>{
            if(response.ok){
                popUp();
                setTimeout(() => {
                    window.location.href = "/registroServicio";
                },3000);
            }
            else{
                popUpError();
                throw new Error("Error al registrar el servicio");
            }
        })
        .catch(error => console.error("Error al registrar el servicio", error));
}

function llenarTablaServicios() {
    fetch("/api/servicios")
        .then(response => {
            if(response.ok) {
                return response.json();
            } else {
                throw new Error("Error al obtener los servicios");
            }
        })
        .then(servicios => {
            const cuerpoTabla = document.getElementById("cuerpo-tabla-servicios");
            cuerpoTabla.innerHTML = "";

            servicios.forEach(servicio => {
                const fila = document.createElement("tr");
                const celdaCodigo = document.createElement("td");
                celdaCodigo.textContent = servicio.codigo;
                celdaCodigo.classList.add("trPaddingLeft");
                const celdaNombre = document.createElement("td");
                celdaNombre.textContent = servicio.nombre;
                celdaNombre.classList.add("trPaddingLeft");

                fila.appendChild(celdaCodigo);
                fila.appendChild(celdaNombre);

                cuerpoTabla.appendChild(fila);
            });
        })
        .catch(error => console.error("Error al obtener los servicios", error));
}