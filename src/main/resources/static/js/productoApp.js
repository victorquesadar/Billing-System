document.addEventListener("DOMContentLoaded", function() {
    if(window.location.pathname ==="/registroProducto"){
        head();
        header();
        footer();
        listaP();
        const guardarProducto = document.getElementById("formRegistrarProducto");
        if(guardarProducto){
            guardarProducto.addEventListener("submit", function (event){
                event.preventDefault();
                const codigo=document.getElementById("codigo").value;
                const nombre = document.getElementById("nombre").value;
                const precio=document.getElementById("precio").value;
                guardarP({codigo, nombre, precio});
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
    const popUp = document.getElementById("popupRegistrarProducto");
    const overlay = document.getElementById("overlayRegistrarProducto");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupRegistrarProducto");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function popUpError() {
    const popUp = document.getElementById("popupErrorRegistrarProducto");
    const overlay = document.getElementById("overlayErrorRegistrarProducto");
    popUp.classList.add("activePopUp");
    overlay.classList.add("activeOverlay");

    const close = document.getElementById("closePopupErrorRegistrarProducto");
    close.addEventListener('click', () => {
        popUp.classList.remove("activePopUp");
        overlay.classList.remove("activeOverlay");
    });
}

function guardarP(producto){
    fetch("/api/productos/registrarProducto",{
        method: "POST",
        headers:{
            "Content-Type": "application/json"
        },
        body: JSON.stringify(producto) //convertimos el usuario que obtuvimos desde el formulario
    })
        .then(response=>{
            if(response.ok){
                popUp();
                setTimeout(() => {
                    window.location.href = "/registroProducto";
                },3000);
            }
            else{
                popUpError();
                throw new Error("Error al registrar el producto");
            }
        })
        .catch(error => console.error("Error al registrar el producto", error));
}

function listaP(){
    fetch("/api/productos")
        .then(response=> response.json())
        .then(productos => {
            console.log("Lista de productos recibida del servidor:",productos);
            const cuerpoTablaProductos=document.getElementById("cuerpo-tabla-productos");
            cuerpoTablaProductos.innerHTML = "";
            productos.forEach(producto => {
                const tr= document.createElement("tr");
                tr.innerHTML = `  
                    <td>${producto.codigo}</td>
                    <td>${producto.nombre}</td>
                    <td>${producto.precio}</td>
                `;
                cuerpoTablaProductos.appendChild(tr);
            });
        })
        .catch(error => console.error("Error al obtener la lista de productos:", error));
}