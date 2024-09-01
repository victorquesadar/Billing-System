document.addEventListener("DOMContentLoaded", function () {

    if (window.location.pathname === "/" || window.location.pathname === "/index" || window.location.pathname === "/registroProveedor") {
        head();
        imagenSifac();
        footerBaseIndex();
    }

    if (window.location.pathname === "/menuAdmin" || window.location.pathname === "/menuProv") {
        head();
        headerMenu();
        footer();
    }

    if(window.location.pathname === "/perfil"){
        head();
        headerPerfil();
        footer();
        llenarPerfilProveedor();
        const editPerfil = document.getElementById("formPerfil");
        if(editPerfil){
            editPerfil.addEventListener("submit", function(event){
                event.preventDefault();
                const cedula = document.getElementById("cedula").value;
                const nombre = document.getElementById("nombre").value;
                const email = document.getElementById("email").value;
                const contrasena = document.getElementById("password").value;
                editarPerfil(cedula,nombre,email,contrasena);
            })
        }
    }

    if(window.location.pathname === "/listaProveedores"){
        head();
        headerAdmin();
        footer();
        listaProvAcept();
    }

    if(window.location.pathname === "/solicitudesRegistroProv"){
        head();
        headerAdmin();
        footer();
        listaSolicitudesProv();
    }

    const inicioSesion = document.getElementById("formInicioSesion");
    if(inicioSesion){
        inicioSesion.addEventListener("submit", function (event){
            event.preventDefault();
            const email = document.getElementById("email").value;
            const password =  document.getElementById("password").value;
            loginUser(email, password);
        })
    }
    const guardarProveedor = document.getElementById("formRegistrarProveedor");
    if(guardarProveedor){
        guardarProveedor.addEventListener("submit", function (event){
            event.preventDefault();
            const cedula = document.getElementById("cedula").value;
            const nombre =  document.getElementById("nombre").value;
            const email = document.getElementById("email").value;
            const contrasena =  document.getElementById("contrasena").value;
            guardarProv({cedula,nombre,email,contrasena});
        })
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

function imagenSifac() {
    const imagenSifac = document.getElementsByClassName("imagenSifac");
    for (let i=0; i<imagenSifac.length; i++) {
        imagenSifac[i].innerHTML = `
             <div id="sifacImg" th:fragment="sifacImg" >
                <img id="sifacIndex" src="/sifac3.png" height="350px" width="350px">
                <h1 id="sifacLabel">SIFAC</h1>
            </div>
        `;
    }
}

function headerMenu() {
    const headerBaseMenu = document.getElementsByClassName("headerBaseMenu");
    for (let i=0; i<headerBaseMenu.length; i++) {
        headerBaseMenu[i].innerHTML = `
            <header class="headerBase">
                <div id="sifacHeader">
                    <img id="sifac" src="\sifac.png" height="60px" width="60px">
                    <h4>SIFAC</h4>
                </div>
    
                <div id="cerrarSesion">
                    <a href="/index" target="_self">
                        <img id="cerrarSesionH" src="\cerrar_sesion.png" height="32px" width="32px">
                    </a>
                </div>
            </header>
        `;
    }
}

function headerAdmin() {
    const headerBaseAdmin = document.getElementsByClassName("headerBaseAdmin");
    for (let i=0; i<headerBaseAdmin.length; i++) {
        headerBaseAdmin[i].innerHTML = `
            <header class="headerNav">
            <a id="btLinkMenuAdmin" href="/menuAdmin">
                <img class="sifacH" src="\sifac.png" height="60px" width="60px">
            </a>

            <nav id="navAdmin">
                <ul>
                    <li> <a href="/solicitudesRegistroProv" target="_blank"> Solicitudes de Registro </a> </li>
                    <li> <a href="/listaProveedores" target="_blank"> Gestionar Proveedores </a> </li>
                    <li> <a href="/index" target="_self"> Cerrar Sesión </a> </li>
                </ul>
            </nav>
        </header>
        `;
    }
}

function footerBaseIndex() {
    const footerBaseIndex = document.getElementsByClassName("footerBaseIndex");
    for (let i=0; i<footerBaseIndex.length; i++) {
        footerBaseIndex[i].innerHTML = `
            <footer id="footerIndex">
                <p>
                    2024 - SIFAC - Derechos Reservados
                </p>
            </footer>
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
    const popUp = document.getElementsByClassName("popup");
    const overlay = document.getElementsByClassName("overlay");
    for (let i=0; i<popUp.length; i++) {
        popUp[i].classList.add("activePopUp");
    }
    for (let i=0; i<overlay.length; i++) {
        overlay[i].classList.add("activeOverlay");
    }

    const close = document.getElementsByClassName("btClose");

    for (let i=0; i<close.length; i++) {
        close[i].addEventListener('click', () => {
            for (let i=0; i<popUp.length; i++) {
                popUp[i].classList.remove("activePopUp");
            }
            for (let i=0; i<overlay.length; i++) {
                overlay[i].classList.remove("activeOverlay");
            }
        });
    }
}

function popUpError() {
    const popUpError = document.getElementsByClassName("popupError");
    const overlayError = document.getElementsByClassName("overlayError");
    for (let i=0; i<popUpError.length; i++) {
        popUpError[i].classList.add("activePopUp");
    }
    for (let i=0; i<overlayError.length; i++) {
        overlayError[i].classList.add("activeOverlay");
    }

    const close = document.getElementsByClassName("btClose");

    for (let i=0; i<close.length; i++) {
        close[i].addEventListener('click', () => {
            for (let i=0; i<popUpError.length; i++) {
                popUpError[i].classList.remove("activePopUp");
            }
            for (let i=0; i<overlayError.length; i++) {
                overlayError[i].classList.remove("activeOverlay");
            }
        });
    }
}

/* --- INDEX.HTML ---- */

function loginUser(email, password) {
    fetch('/api/proveedores/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`
    })
        .then(response => response.text())
        .then(url => {
            if (url) {
                window.location.href = url;
            } else {
                alert('Error al iniciar sesión. Por favor verifica tus datos.');
            }
        })
        .catch(error => {
            console.error('Error al iniciar sesión:', error);
            alert('Error al iniciar sesión. Por favor intenta de nuevo más tarde.');
        });
}

/* ------------------- */

/* --- PERFIL.HTML ---- */

function headerPerfil() {
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

function llenarPerfilProveedor() {
    fetch('/api/proveedores/perfilProv')
        .then(response => response.json())
        .then(data => {
            document.getElementById("cedula").value = data.cedula;
            document.getElementById("nombre").value = data.nombre;
            document.getElementById("email").value = data.email;
            document.getElementById("password").value = data.contrasena;
        })
        .catch(error => {
            console.error('Error al obtener el perfil del proveedor:', error);
        });
}

function editarPerfil(cedula, nombre, email, contrasena) {
    const data = {
        cedula: cedula,
        nombre: nombre,
        email: email,
        contrasena: contrasena
    };

    fetch('/api/proveedores/editPerfil', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                console.log('Perfil editado con éxito');
                popUp();
            } else {
                console.error('Error al editar el perfil');
                popUpError();
            }
        })
        .catch(error => {
            console.error('Error al editar el perfil:', error);
            popUpError();
        });
}

/* -------------------- */

/* --- REGISTROPROVEEDOR.HTML ---- */

function guardarProv(proveedor){
    fetch("/api/proveedores/registrarProveedor",{
        method: "POST",
        headers:{
            "Content-Type": "application/json"
        },
        body: JSON.stringify(proveedor)
    })
        .then(response=>{
            if(response.ok){
                popUp();
                setTimeout(() => {
                    window.location.href = "/index";
                },4000);
            }
            else{
                popUpError();
            }
        })
        .catch(error => console.error("Error al registrar el Proveedor", error));
}

/* ------------------------------- */

/* --- LISTAPROVEEDORES.HTML ---- */

function listaProvAcept(){
    fetch("/api/proveedores/listaAceptados")
        .then(response => response.json())
        .then(proveedores =>{
            console.log(proveedores);
            const cuerpoTablaProveedores = document.getElementById("cuerpo-tabla-proveedores");
            cuerpoTablaProveedores.innerHTML = "";
            proveedores.forEach(proveedor => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${proveedor.cedula}</td>
                    <td>${proveedor.nombre}</td>
                    <td>${proveedor.email}</td>
                    <td><button class="btEliminar" onclick="eliminarUsuario('${proveedor.cedula}')">Eliminar</button></td>
                `;
                cuerpoTablaProveedores.appendChild(tr);
            });
        })
        .catch(error => console.error("Error al obtener la lista de proveedores aceptados:", error));
}

function eliminarUsuario(cedula){
    console.log('Cédula a eliminar:', cedula);
    fetch(`/api/proveedores/${cedula}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                popUp();
                listaProvAcept();
            } else {
                popUpError();
                console.error('Error al eliminar proveedor');
            }
        })
        .catch(error => console.error('Error al eliminar proveedor:', error));
}

/* ------------------------------ */

/* --- SOLICITUDESREGISTROPROV.HTML ---- */

function listaSolicitudesProv(){
    fetch("/api/proveedores/listaSolicitudes")
        .then(response => response.json())
        .then(proveedores =>{
            console.log(proveedores);
            const cuerpoTablaProveedores = document.getElementById("cuerpo-tabla-solicitudes");
            cuerpoTablaProveedores.innerHTML = "";
            proveedores.forEach(proveedor => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${proveedor.cedula}</td>
                    <td>${proveedor.nombre}</td>
                    <td>${proveedor.email}</td>
                    <td><button class="btAceptar" onclick="aceptarSolicitud('${proveedor.cedula}')">Aceptar</button></td>
                    <td><button class="btRechazar" onclick="rechazarSolicitud('${proveedor.cedula}')">Rechazar</button></td>
                `;
                cuerpoTablaProveedores.appendChild(tr);
            });
        })
        .catch(error => console.error("Error al obtener la lista de solicitud de proveedores:", error));
}

function aceptarSolicitud(cedula){
    console.log('Cédula a Aceptar:', cedula);
    fetch(`/api/proveedores/${cedula}`, {
        method: 'POST'
    })
        .then(response => {
            if (response.ok) {
                popUp();
                listaSolicitudesProv();
            } else {
                popUpError();
                console.error('Error al aceptar proveedor');
            }
        })
        .catch(error => console.error('Error al aceptar proveedor:', error));
}

function rechazarSolicitud(cedula){
    console.log('Cédula a rechazar:', cedula);
    fetch(`/api/proveedores/${cedula}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                popUp();
                listaSolicitudesProv();
            } else {
                popUpError();
                console.error('Error al eliminar proveedor');
            }
        })
        .catch(error => console.error('Error al rechazar proveedor:', error));
}

/* ------------------------------------- */