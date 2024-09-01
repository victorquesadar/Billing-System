use sifacp2;

create table Proveedor ( 
	cedula varchar(10) not null,
    nombre varchar(50) not null,
	aceptado tinyint(1) not null,
    email varchar(50) not null,
    contrasena varchar(50) not null,
	Primary Key (cedula)
);

insert into Proveedor(cedula, nombre, aceptado, email, contrasena)
values ('118740667', 'Julian Ramirez', 1, 'julianr@gmail.com', 'julianr123');

insert into Proveedor(cedula, nombre, aceptado, email, contrasena)
values ('123456789', 'Pablo Alvarez', 1, 'pabloa@gmail.com', 'pabloa123');

UPDATE Proveedor SET aceptado = 2 WHERE cedula = '118740667';

select * from Proveedor;

select* from Cliente;

select* from Producto;

select* from Servicio;

select* from Factura;

create table Cliente (
	cedula varchar(10) not null,
    nombre varchar(50) not null,
    email varchar(50) not null,
    proveedor varchar(10) not null,
    Primary Key (cedula)
);

create table Producto (
	codigo varchar(10) not null,
    nombre varchar(50) not null,
    precio int not null,
    cantidad int,
    totallinea int,
    proveedor varchar(10) not null,
	factura int,
    Primary Key (codigo)
);

create table Servicio (
	codigo varchar(10) not null,
    nombre varchar(50) not null,
    precio int,
    proveedor varchar(10) not null,
	factura int,
    Primary Key (codigo)
);

create table Factura (
	numero int auto_increment,
	nombre varchar(30) not null,
    fecha varchar(10) not null,
    proveedor varchar(10) not null,
    cliente varchar(10) not null,
    subtotal int not null,
    impuesto int not null,
    total int not null,
    Primary Key (numero)
);

alter table Cliente add Foreign Key(proveedor) references Proveedor(cedula) on delete cascade;
alter table Producto add Foreign Key(proveedor) references Proveedor(cedula) on delete cascade;
alter table Producto add Foreign Key(factura) references Factura(numero) on delete cascade;
alter table Servicio add Foreign Key(proveedor) references Proveedor(cedula) on delete cascade;
alter table Servicio add Foreign Key(factura) references Factura(numero) on delete cascade;
alter table Factura add Foreign Key(proveedor) references Proveedor(cedula) on delete cascade;
alter table Factura add Foreign Key(cliente) references Cliente(cedula) on delete cascade;