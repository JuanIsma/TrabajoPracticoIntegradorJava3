create database pronosticos;
 
 use pronosticos;
 
 create table pronostico( Id int , Participante varchar(100)  not null, EquipoL varchar(100)  not null , 
  GanaL varchar(10)  not null , Empata varchar(10)  not null ,GanaV varchar(100)  not null ,EquipoV varchar(100)  not null );
 

   
insert into pronostico values('1','Mariana','Argentina','X','','','Arabia Saudita' ),
('1','Mariana','Polonia','','X','','México'),
('1','Mariana','Argentina','X','','','México'),
('1','Mariana','Arabia Saudita','','','X','Polonia'   ),
('1','Mariana','Polonia','','','X','Argentina'  ),
('1','Mariana','Arabia Saudita','','','X','México'),
('2','Pedro','Argentina','X','','','Arabia Saudita'   ),
('2','Pedro','Polonia','','','X','México'  ),
('2','Pedro','Argentina','X','','','México'),
('2','Pedro','Arabia Saudita','','X','','Polonia' ),
('2','Pedro','Polonia','','','X','Argentina' ),
('2','Pedro','Arabia Saudita','','','X','México'),
('2','Pedro','Dinamarca','','','X','Tunez' ),
('2','Pedro','Francia','X','','','Dinamarca' ),
('2','Pedro','Tunez','','','X','Australia' ),
('2','Pedro','Tunez','X','','','Francia'   ),
('2','Pedro','Australia','','X','','Dinamarca'  ),
('2','Pedro','Francia','X','','','Australia' ),
('3','Ricardo','Argentina','','','X','Arabia Saudita' ),
('3','Ricardo','Polonia','','X','','México'),
('3','Ricardo','Argentina','X','','','México'),
('3','Ricardo','Arabia Saudita','','','X','Polonia'   ),
('3','Ricardo','Polonia','','','X','Argentina'  ),
('3','Ricardo','Arabia Saudita','','','X','México'),
('3','Ricardo','Dinamarca','','X','','Tunez' ),
('3','Ricardo','Francia','X','','','Dinamarca'  ),
('3','Ricardo','Tunez','','','X','Australia' ),
('3','Ricardo','Tunez','X','','','Francia' ),
('3','Ricardo','Australia','X','','','Dinamarca'),
('3','Ricardo','Francia','X','','','Australia'  ),
('3','Ricardo','Marruecos','','X','','Croacia' ),
('3','Ricardo','Bélgica','X','','','Canadá'),
('3','Ricardo','Bélgica','','','X','Marruecos' ),
('3','Ricardo','Croacia','X','','','Canadá'),
('3','Ricardo','Croacia','','X','','Bélgica'   ),
('3','Ricardo','Canadá','','','X','Marruecos' );

  
  
  
   -- DROP database  pronostico;
  
  