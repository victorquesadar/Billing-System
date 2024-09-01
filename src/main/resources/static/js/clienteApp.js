document.addEventListener("DOMContentLoaded", function(){
    if(window.location.pathname ==="/registroCliente"){
        head();
        header();
        footer();
        lista();
        const guardarCliente = document.getElementById("formRegistrarCliente");
        if(guardarCliente){
            guardarCliente.addEventListener("submit", function (event){
                event.preventDefault();
                const cedula=document.getElementById("cedula").value;
                const nombre = document.getElementById("nombre").value;
                const email=document.getElementById("email").value;
                guardar({cedula, nombre, email});
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
    const popUp = document.getElementById("popupRegistrarCliente");
    const overlay = document.getElementById("overlayRegistrarCliente");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupRegistrarCliente");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function popUpError() {
    const popUp = document.getElementById("popupErrorRegistrarCliente");
    const overlay = document.getElementById("overlayErrorRegistrarCliente");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupErrorRegistrarCliente");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function lista() {
    fetch("/api/clientes/listaClientes")
        .then(response => response.json())
        .then(clientes => {
            console.log("Lista de clientes recibida del servidor:", clientes);
            const cuerpoTablaClientes = document.getElementById("cuerpo-tabla-clientes");
            cuerpoTablaClientes.innerHTML = "";  // Limpia el cuerpo de la tabla antes de agregar nuevas filas.

            clientes.forEach((cliente, registroCliente) => {
                console.log("Procesando cliente:", cliente);  // Agrega más salida de depuración aquí
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${cliente.cedula}</td>
                    <td>${cliente.nombre}</td>
                    <td>${cliente.email}</td>
                `;
                cuerpoTablaClientes.appendChild(tr);
            });
        })
        .catch(error => {
            console.error("Error al obtener la lista de usuarios:", error);
            alert("Error al cargar clientes. Ver consola para más detalles.");
        });
}

function guardar(cliente){
    fetch("/api/clientes/registarCliente",{
        method: "POST",
        headers:{
            "Content-Type": "application/json"
        },
        body: JSON.stringify(cliente) //convertimos el usuario que obtuvimos desde el formulario
    })
        .then(response=>{
            if(response.ok){
                popUp();
                setTimeout(() => {
                    window.location.href = "/registroCliente";
                },3000);
        }
            else{
                popUpError();
                throw new Error("Error al registrar el cliente");
            }
        })
        .catch(error => console.error("Error al registrar el cliente", error));
}