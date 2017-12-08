import java.sql.*;
import java.io.*;

public class TransactionMySQL {

   Connection conn = null;
   Statement stmt = null;
   BufferedReader in = null;

   static final String URL = "jdbc:mysql://localhost/";
   static final String BD = "bd_final";
   static final String USER = "root";
   static final String PASSWD = null;

   int idenSim;

   public TransactionMySQL() throws SQLException, Exception {
      Class.forName( "com.mysql.jdbc.Driver" );
      System.out.print( "Connecting to the database... " );

      conn = DriverManager.getConnection( URL+BD, USER, PASSWD );
      System.out.println( "connected\n\n" );

      conn.setAutoCommit( false );
      stmt = conn.createStatement();
      in = new BufferedReader( new InputStreamReader(System.in) );
   }

   private void dumpResultSet( ResultSet rset ) throws SQLException {
      ResultSetMetaData rsetmd = rset.getMetaData();
      int i = rsetmd.getColumnCount();
      while( rset.next() ) {
         for( int j = 1; j <= i; j++ ) {
            System.out.print( rset.getString(j) + "\t" );
         }
         System.out.println();
      }
   }

   private void query( String statement ) throws SQLException {
      ResultSet rset = stmt.executeQuery( statement );
      System.out.println( "Results:" );
      dumpResultSet( rset );
      System.out.println();
      rset.close();
   }

   private void query( PreparedStatement statement ) throws SQLException {
      ResultSet rset = statement.executeQuery();
      System.out.println( "Results:" );
      dumpResultSet( rset );
      System.out.println();
      rset.close();
      statement.close();
   }

   private void update( PreparedStatement statement ) throws SQLException {
      statement.executeUpdate();
      System.out.println("");
      statement.close();
   }

   private void close() throws SQLException {
      stmt.close();
      conn.close();
   }

   private boolean menu() throws SQLException, IOException {

      String query;

      System.out.println( "\nNivel de aislamiento = " + conn.getTransactionIsolation() + "\n" );
      System.out.println( "(1) Consulta del folleto\n" );
      System.out.println( "(2) Simulacion de una Reservacion de viaje\n" );
      System.out.println( "(3) Reservacion de un viaje\n" );

      System.out.println( "(4) Validar todas operaciones\n" );
      System.out.println( "(5) Abortar todas las operaciones\n" );
      System.out.println( "(6) Cambiar nivel de aislamiento\n" );
      System.out.println( "(7) Salir\n\n" );

      System.out.print( "Option: " );

      PreparedStatement statement;

      switch( Integer.parseInt( "0" + in.readLine() ) ) {

         case 1:	//falta libre
            System.out.println( "(1) Paises que puedes visitar\n" ); //pais, ciudad
            System.out.println( "(2) Ciudades que puedes visitar\n" );
            System.out.println( "(3) Lugares que puedes visitar en una ciudad\n" );
            System.out.println( "(4) Lugares que puedes visitar en un pais\n" );
            System.out.println( "(5) Ciruitos que puedes hacer en un pais\n" );
            System.out.println( "(6) Ciruitos que salen de una ciudad especifica\n" );
            System.out.println( "(7) Ciruitos que llegan de una ciudad especifica\n" );
            System.out.println( "(8) Ciruitos que salen de un pais especifico\n" );
            System.out.println( "(9) Ciruitos que llegan de una pais especifico\n" );
            System.out.println( "(10) Hoteles que hay en una ciudad especifica\n" );
            System.out.println( "(11) Hoteles que hay en un  pais especifico\n" );
            System.out.println( "(12) Libre\n" );

            switch(Integer.parseInt("0" + in.readLine()) ) {

               case 1:
                  System.out.println( "Paises dispinibles\n" );
                  query( "select distinct(pais) from CIUDAD;" );
                  break;

               case 2:
                  System.out.println( "Ciudades dispinibles\n" );
                  query( "select nombreCiudad from CIUDAD;" );
                  break;

               case 3:
                  query( "select distinct(nombreCiudad) from LUGARAVISITAR;" );
                  System.out.println( "Lugaresque puedes visitar en un pais especifico?\n" );
                  query = "select nombreLugar, descripcion, precio from LUGARAVISITAR where nombreCiudad = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, in.readLine());
                  query(statement);
                  break;

               case 4:
                  query( "select distinct(pais) from CIRCUITO;" );
                  System.out.println( "Lugares que puedes visitar en un pais especifico?\n" );
                  query = "select nombreLugar, descripcion, precio from LUGARAVISITAR where pais = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, in.readLine());
                  query(statement);
                  break;

               case 5:
                  query( "select distinct(descripcion) from CIRCUITO;" );
                  System.out.println( "Circuitos que pasan por un pais o paises?\n" );
                  query = "select * from CIRCUITO where descripcion = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, in.readLine());
                  query(statement);
                  break;

               case 6:
                  query( "select distinct(ciudadSalida) from CIRCUITO;" );
                  System.out.println( "Ciruitos que salen de una ciudad especifica?\n" );
                  query = "select * from CIRCUITO where ciudadSalida = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, in.readLine());
                  query(statement);
                  break;

               case 7:
                  query( "select distinct(ciudadLlegada) from CIRCUITO;" );
                  System.out.println( "Ciruitos que salen de una ciudad especifica?\n" );
                  query = "select * from CIRCUITO where ciudadLlegada = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, in.readLine());
                  query(statement);
                  break;

               case 8:
                  query( "select distinct(paisSalida) from CIRCUITO;" );
                  System.out.println( "Ciruitos que salen de una pais especifica?\n" );
                  query = "select * from CIRCUITO where paisSalida = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, in.readLine());
                  query(statement);
                  break;

               case 9:
                  query( "select distinct(paisLlegada) from CIRCUITO;" );
                  System.out.println( "Ciruitos que salen de una pais especifica?\n" );
                  query = "select * from CIRCUITO where paisLlegada = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, in.readLine());
                  query(statement);
                  break;

               case 10:
                  query( "select distinct(nombreCiudad) from HOTEL;");
                  System.out.println( "Hoteles que se encuantran una ciudad especifica?\n" );
                  query = "elect * from HOTEL where nombreCiudad = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, in.readLine());
                  query(statement);
                  break;

               case 11:
                  query( "select distinct(pais) from HOTEL;" );
                  System.out.println( "Hoteles que se encuantran un pais espacfico?\n" );
                  query = "select * from HOTEL where pais = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, in.readLine());
                  query(statement);
                  break;

               case 12://Libre
                  //query( "select * from CURSO where " + in.readLine() );
                  break;
            }
            break;

         case 2:
            System.out.println( "(1) Crear Simulacion\n" ); //pais, ciudad
            System.out.println( "(2) Consultar Simulacion\n" );
            System.out.println( "(3) Modificar Simulacion\n" );
            System.out.println( "(4) Eliminar Simulacion\n" );

            switch(Integer.parseInt("0" + in.readLine()) ) {
               case 1:
                  query =
                     "insert into SIMULACION (nombreUsuario, fechaSalida, fechaLlegada, nbPersonas, pais, costo) values (?, ?, ?, ?, ?, 0);";
                  statement = conn.prepareStatement( query );

                  System.out.println( "\nNombre?" );
                  statement.setString( 1, in.readLine() );

                  query( "select fechaSalida from FECHACIRCUITO");

                  System.out.println( "Fecha de Salida (año-mes-dia)?" );
                  String fechaSalida = in.readLine();
                  statement.setString( 2, fechaSalida );

                  System.out.println( "Fecha de Llegada (año-mes-dia)?" );
                  statement.setString( 3, in.readLine() );

                  System.out.println( "Numero de Personas?" );	//hacer que no sea
                  statement.setInt( 4, Integer.parseInt( in.readLine() ) );

                  query( "select distinct(pais) from CIUDAD");

                  System.out.println( "Pais?" );
                  String pais = in.readLine();
                  statement.setString( 5, pais );

                  update( statement );

                  System.out.println( "(1) Agregar Lugar\n" ); //pais, ciudad
                  System.out.println( "(2) Agregar Hotel\n" );
                  System.out.println( "(3) Agregar Circuito\n" );
                  System.out.println( "(4) Guardar y Calcular Precio\n" );
      
                  int option = Integer.parseInt("0" + in.readLine() );
                  while(option != 4) {
                     switch(option) {
                        case 1:
                           query = "select nombreLugar, descripcion from LUGARAVISITAR where pais = ?;";
                           statement = conn.prepareStatement( query );
                           statement.setString( 1, pais );
                           query(statement);

                           query =
                              "insert into QUIEREVISITAR " + 
                              "select max(identificadorSimulacion), ?, ? " +
                              "from SIMULACION;";
                           statement = conn.prepareStatement( query );

                           System.out.println( "\nLugar" );
                           statement.setString( 1, in.readLine() );

                           System.out.println( "Descripcion" );
                           statement.setString( 2, in.readLine() );
                           
                           update( statement );
                           break;
                        case 2:
                           query = "select nombreHotel, direccion, nombreCiudad, pais from HOTEL where pais = ?;";
                           statement = conn.prepareStatement( query );
                           statement.setString( 1, pais );
                           query(statement);
                        
                           query =
                              "insert into QUIEREDORMIR select max(identificadorSimulacion), ?, ?, ?, ? from SIMULACION;";
                           statement = conn.prepareStatement( query );

                           System.out.println( "\nHotel" );
                           statement.setString( 1, in.readLine() );

                           System.out.println( "Direccion" );
                           statement.setString( 2, in.readLine() );

                           System.out.println( "Ciudad" );
                           statement.setString( 3, in.readLine() );

                           System.out.println( "Pais" );
                           statement.setString( 4, in.readLine() );
                           
                           update( statement );
                           break;
                        case 3:
                           query = "select identificadorCircuito from CIRCUITO where pais = ?;";
                           statement = conn.prepareStatement( query );
                           statement.setString( 1, pais );
                           query(statement);

                           query =
                              "insert into QUIEREPARTICIPAR " + 
                              "select max(identificadorSimulacion), ? " +
                              "from SIMULACION;";
                           statement = conn.prepareStatement( query );

                           System.out.println( "\nCircuito" );
                           statement.setString( 1, pais );

                           update( statement );
                           break;
                     }
                  }

                  query( "update SIMULACION S left join (select sum(C.precio) as costo, Q.identificadorSimulacion from CIRCUITO C, QUIEREPARTICIPAR Q where C.identificadorCircuito = Q.identificadorCircuito and Q.identificadorSimulacion = (select max(identificadorSimulacion) from SIMULACION)) P on S.identificadorSimulacion = P.identificadorSimulacion left join (select (sum(H.precioCuarto) + sum(H.precioDesayuno)) as costo, Q.identificadorSimulacion from HOTEL H, QUIEREDORMIR Q where H.nombreHotel = Q.nombreHotel and H.direccion= Q.direccion and H.nombreCiudad = Q.nombreCiudad and H.pais = Q.pais and Q.identificadorSimulacion = (select max(identificadorSimulacion) from SIMULACION)) D on S.identificadorSimulacion = D.identificadorSimulacion left join (select sum(L.precio) as costo, Q.identificadorSimulacion from LUGARAVISITAR L, QUIEREVISITAR Q where L.nombreLugar = Q.nombreLugar and L.descripcion = L.descripcion and Q.identificadorSimulacion = (select max(identificadorSimulacion) from SIMULACION)) V on S.identificadorSimulacion = V.identificadorSimulacion set S.costo = P.costo + D.costo + V.costo;" );
                  query( "select costo, identificadorSimulacion from SIMULACION where identificadorSimulacion = (select max(identificadorSimulacion) from SIMULACION)");
                  break;

               case 2:
                  System.out.println( "Numero de simulacion o nombre de usuario a consultar\n" );
                  String x2;
                  int id2 = 0;
                  x2 = in.readLine();
                  try {
                     id2 = Integer.parseInt( x2 );
                  } catch (NumberFormatException e) {}

                  query = "select * from SIMULACION where nombreUsuario = ? or identificadorSimulacion = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, x2);
                  statement.setInt(2, id2);
                  query(statement);
                  break;

               case 3:
                  System.out.println( "Numero de simulacion o nombre de usuario a modificar\n" );
                  String x3;
                  int id3 = 0;
                  x3 = in.readLine();
                  try {
                     id3 = Integer.parseInt( x3 );
                  } catch (NumberFormatException e) {}

                  System.out.println( "(1) nombre\n" ); //pais, ciudad
                  System.out.println( "(2) fechaSalida\n" );
                  System.out.println( "(3) fecha de llegada\n" );
                  System.out.println( "(4) numero personas\n" );
                  //System.out.println( "(5) pais\n" );
                  //System.out.println( "(6) lugar\n" );
                  //System.out.println( "(7) hotel\n" );
                  //System.out.println( "(8) circuito\n" );
                  switch(Integer.parseInt("0" + in.readLine()) ) {
                     case 1:
                        System.out.println( "Modificar nombre\n" );
                        query = "update SIMULACION set nombreUsuario = ? where nombreUsuario = ? or identificadorSimulacion = ?;";
                        statement = conn.prepareStatement( query );
                        statement.setString(1, in.readLine());
                        statement.setString(2, x3);
                        statement.setInt(3, id3);
                        query(statement);
                        break;

                     case 2:
                        System.out.println( "Modificar Fecha de Salida (año-mes-dia)\n" );
                        query = "update SIMULACION set fechaSalida = ? where nombreUsuario = ? or identificadorSimulacion = ?;";
                        statement = conn.prepareStatement( query );
                        statement.setString(1, in.readLine());
                        statement.setString(2, x3);
                        statement.setInt(3, id3);
                        update(statement);
                        break;

                     case 3:
                        System.out.println( "Modificar Fecha de LLegada (año-mes-dia)\n" );
                        query = "update SIMULACION set fechaLLegada = ? where nombreUsuario = ? or identificadorSimulacion = ?;";
                        statement = conn.prepareStatement( query );
                        statement.setString(1, in.readLine());
                        statement.setString(2, x3);
                        statement.setInt(3, id3);
                        update(statement);
                        break;

                     case 4:
                        System.out.println( "Modificar Numero de Personas\n" );
                        query = "update SIMULACION set nbPersonas = ? where nombreUsuario = ? or identificadorSimulacion = ?;";
                        statement = conn.prepareStatement( query );
                        statement.setInt(1, Integer.parseInt( in.readLine() ) );
                        statement.setString(2, x3);
                        statement.setInt(3, id3);
                        update(statement);
                        break;

                     case 5:
                        System.out.println( "Modificar pais\n" );
                        query = "update SIMULACION set pais = ? where nombreUsuario = ? or identificadorSimulacion = ?;";
                        statement = conn.prepareStatement( query );
                        statement.setString(1, in.readLine());
                        statement.setString(2, x3);
                        statement.setInt(3, id3);
                        update(statement);
                        break;

                     case 6:
                        // TODO Cambiar Lugar
                        break;

                     case 7:
                        // TODO Cambiar Hotel
                        break;

                     case 8:
                        // TODO Cambiar Circuito
                        break;
                  }

                  break;

               case 4:

                  System.out.println( "Numero de simulacion o nombre (de usuario) para eliminar\n" );
                  String x4;
                  int id4 = 0;
                  x4 = in.readLine();
                  try {
                     id4 = Integer.parseInt( x4 );
                  } catch (NumberFormatException e) {}

                  query = "delete from SIMULACION where nombreUsuario = ? or identificadorSimulacion = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, x4);
                  statement.setInt(2, id4);
                  update(statement);
                  System.out.println( "Eliminacion exitosa\n" );
                  break;
            }
            break;

         case 3:
            System.out.println( "(1) Crear Reservacion con Simulacion\n" ); //pais, ciudad
            System.out.println( "(2) Crear Reservacion nueva\n" );
            System.out.println( "(3) Consultar Reservacion\n" );
            System.out.println( "(4) Modificar Reservacion\n" );
            System.out.println( "(5) Eliminar Reservacion\n" );

            switch(Integer.parseInt("0" + in.readLine()) ) {
               case 1:
                  System.out.println( "Numero de simulacion o nombre de usuario para utilizar\n" );
                  String x1;
                  int id1 = 0;
                  x1 = in.readLine();
                  try {
                     id1 = Integer.parseInt( x1 );
                  } catch (NumberFormatException e) {}
                  // TODO VALIDAR RESERVACION
                  break;

               case 2:
                  System.out.println( "(1) Agregar usuario\n" ); //pais, ciudad
                  System.out.println( "(2) Continuar\n" );
                  if(Integer.parseInt("0" + in.readLine()) == 1) {
                     query = "insert into USUARIO values (?, ?, ?, ?, ?);";

                     statement = conn.prepareStatement( query );

                     System.out.println( "\nNombre?" );
                     statement.setString( 1, in.readLine() );

                     System.out.println( "Tipo: (C) Compañia, (G) Grupo, (I) Individual?" );
                     statement.setString( 2, in.readLine() );

                     System.out.println( "Categoria: (E) Empleado, (C) Cliente?" );
                     statement.setString( 3, in.readLine() );

                     System.out.println( "Direccion?" );
                     statement.setString( 4, in.readLine() );

                     System.out.println( "Forma de pago: (E) Efectivo, (T) Tarjeta?" );
                     statement.setString( 5, in.readLine() );

                     update(statement);
                  }

                  query =
                     "insert into RESERVACION (nombreUsuario, fechaSalida, fechaLlegada, nbPersonas, pais, costo)" +
                     "values (?, ?, ?, ?, ?, 0);";
                  statement = conn.prepareStatement( query );

                  System.out.println( "\nNombre?" );
                  statement.setString( 1, in.readLine() );

                  System.out.println( "Fecha de Salida (año-mes-dia)?" );
                  String fechaSalida = in.readLine();
                  statement.setString( 2, fechaSalida );

                  System.out.println( "Fecha de Llegada (año-mes-dia)?" );
                  statement.setString( 3, in.readLine() );

                  System.out.println( "Numero de Personas?" );	//hacer que no sea
                  int numP = Integer.parseInt( in.readLine() );
                  statement.setInt( 4,  numP );

                  System.out.println( "Pais?" );
                  String pais = in.readLine();
                  statement.setString( 5, pais );

                  update( statement );

                  System.out.println( "(1) Agregar Lugar\n" ); //pais, ciudad
                  System.out.println( "(2) Agregar Hotel\n" );
                  System.out.println( "(3) Agregar Circuito\n" );
                  System.out.println( "(4) Guardar y Calcular Precio\n" );
      
                  int option = Integer.parseInt("0" + in.readLine() );
                  while(option != 4) {
                     switch(option) {
                        case 1:
                           query = "select nombreLugar, descripcion from LUGARAVISITAR where pais = ?;";
                           statement = conn.prepareStatement( query );
                           statement.setString( 1, pais );
                           query(statement);

                           query =
                              "insert into VISITARA " + 
                              "select max(identificadorSimulacion), ?, ? " +
                              "from SIMULACION;";
                           statement = conn.prepareStatement( query );

                           System.out.println( "\nLugar" );
                           statement.setString( 1, in.readLine() );

                           System.out.println( "Descripcion" );
                           statement.setString( 2, in.readLine() );
                           
                           update( statement );
                           break;
                        case 2:
                           query = "select nombreHotel, direccion, nombreCiudad, pais from HOTEL where pais = ? and numCuartos > 0;";
                           statement = conn.prepareStatement( query );
                           statement.setString( 1, pais );
                           query(statement);
                        
                           query =
                              "select case when ( (select numCuartos from HOTEL where nombreHotel = ? and direccion = ? and nombreCiudad = ? and pais = ?) - ? > 0) then insert into DORMIRA select max(identificadorSimulacion), ?, ?, ?, ? from SIMULACION else select \"Faltan cuartos\" from HOTEL end;";
                           statement = conn.prepareStatement( query );

                           System.out.println( "\nHotel" );
                           String hotel = in.readLine();
                           statement.setString( 1, hotel );
                           statement.setString( 6, hotel );

                           System.out.println( "Direccion" );
                           String direccion = in.readLine();
                           statement.setString( 2, direccion );
                           statement.setString( 7, direccion );

                           System.out.println( "Ciudad" );
                           String ciudad = in.readLine();
                           statement.setString( 3, ciudad );
                           statement.setString( 8, ciudad );

                           System.out.println( "Pais" );
                           statement.setString( 4, in.readLine() );
                           statement.setString( 9, in.readLine() );

                           statement.setInt( 5, numP );
                           
                           update( statement );
                           break;
                        case 3:
                           query = "select C.identificadorCircuito, F.fechaSalida from CIRCUITO C, FECHACIRCUITO F where C.identificadorCircuito = F.identificadorCircuito and pais = ?;";
                           statement = conn.prepareStatement( query );
                           statement.setString( 1, pais );
                           query(statement);

                           query =
                              "select case when ( (select nbPersonas from FECHACIRCUITO where identificadorCircuito = ? and fechaSalida = ?) - ? > 0) then insert into PARTICIPARA select max(identificadorSimulacion), ? from SIMULACION else select \"Faltan lugares\" from CIRCUITO end;";
                           statement = conn.prepareStatement( query );

                           System.out.println( "\nCircuito" );
                           
                           update( statement );
                           break;
                     }
                  }

                  query( "update SIMULACION S left join (select sum(C.precio) as costo, Q.identificadorSimulacion from CIRCUITO C, QUIEREPARTICIPAR Q where C.identificadorCircuito = Q.identificadorCircuito and Q.identificadorSimulacion = (select max(identificadorSimulacion) from SIMULACION)) P on S.identificadorSimulacion = P.identificadorSimulacion left join (select (sum(H.precioCuarto) + sum(H.precioDesayuno)) as costo, Q.identificadorSimulacion from HOTEL H, QUIEREDORMIR Q where H.nombreHotel = Q.nombreHotel and H.direccion= Q.direccion and H.nombreCiudad = Q.nombreCiudad and H.pais = Q.pais and Q.identificadorSimulacion = (select max(identificadorSimulacion) from SIMULACION)) D on S.identificadorSimulacion = D.identificadorSimulacion left join (select sum(L.precio) as costo, Q.identificadorSimulacion from LUGARAVISITAR L, QUIEREVISITAR Q where L.nombreLugar = Q.nombreLugar and L.descripcion = L.descripcion and Q.identificadorSimulacion = (select max(identificadorSimulacion) from SIMULACION)) V on S.identificadorSimulacion = V.identificadorSimulacion set S.costo = P.costo + D.costo + V.costo;" );
                  query( "select costo, identificadorSimulacion from SIMULACION where identificadorSimulacion = (select max(identificadorSimulacion) from SIMULACION)");
                  break;

               case 3:
                  System.out.println( "Numero de reservacion o nombre de usuario a consultar\n" );
                  String x3;
                  int id3 = 0;
                  x3 = in.readLine();
                  try {
                     id3 = Integer.parseInt( x3 );
                  } catch (NumberFormatException e) {}

                  query = "select * from RESERVACION where nombreUsuario = ? or identificadorSimulacion = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, x3);
                  statement.setInt(2, id3);
                  query(statement);
                  break;

               case 10:
                  System.out.println( "(1) nombre\n" );
                  System.out.println( "(2) fechaSalida\n" );
                  System.out.println( "(3) fecha de llegada\n" );
                  System.out.println( "(4) numero personas\n" );
                  System.out.println( "(5) pais\n" );
                  //System.out.println( "(6) lugar\n" );
                  //System.out.println( "(7) hotel\n" );
                  //System.out.println( "(8) circuito\n" );
                  switch(Integer.parseInt("0" + in.readLine()) ) {
                     case 1:
                        break;

                     case 2:
                        break;

                     case 3:
                        break;

                     case 4:
                        break;

                     case 5:
                        break;

                     case 6:
                        break;

                     case 7:
                        break;

                     case 8:
                        break;
                  }
                  break;

               case 5:
                  System.out.println( "Numero de reservacion o nombre (de usuario) para eliminar\n" );
                  String x5;
                  int id5 = 0;
                  x5 = in.readLine();
                  try {
                     id5 = Integer.parseInt( x5 );
                  } catch (NumberFormatException e) {}

                  query = "delete from RESERVACION where nombreUsuario = ? or identificadorSimulacion = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, x5);
                  statement.setInt(2, id5);
                  update(statement);
                  System.out.println( "Eliminacion exitosa\n" );
                  break;
            }
            break;


         case 4:
            conn.commit();      // fin de la transacción e inicio de la siguiente
            break;

         case 5:
            conn.rollback();    // fin de la transacción e inicio de la siguiente
            break;

         case 6:
            System.out.println();
            System.out.println( conn.TRANSACTION_NONE + " = TRANSACTION_NONE" );
            System.out.println( conn.TRANSACTION_READ_UNCOMMITTED + " = TRANSACTION_READ_UNCOMMITTED" );
            System.out.println( conn.TRANSACTION_READ_COMMITTED + " = TRANSACTION_READ_COMMITTED" );
            System.out.println( conn.TRANSACTION_REPEATABLE_READ + " = TRANSACTION_REPEATABLE_READ" );
            System.out.println( conn.TRANSACTION_SERIALIZABLE + " = TRANSACTION_SERIALIZABLE\n\n" );

            System.out.println( "Nivel?" );
            conn.setTransactionIsolation( Integer.parseInt( in.readLine() ) );
            break;

         case 7:	
         return false;
      }
      return true;
   }

   public static void main( String arg[] ) throws SQLException, Exception {

      if( arg.length != 0 ) {

         System.err.println( "Use: java TransactionMySQL" );
         System.exit( 1 );
      }

      TransactionMySQL transaction = new TransactionMySQL();

      boolean cont = true;
      while( cont )

         try {
            cont = transaction.menu();

         } catch( Exception e ) {

            System.err.println( "failed" );
            e.printStackTrace( System.err );
            cont = false;
         }

      transaction.close();
   }
}
