import java.sql.*;
import java.io.*;

public class TransactionMySQL {

   Connection conn = null;
   Statement stmt = null;
   BufferedReader in = null;

   static final String URL = "jdbc:mysql://localhost/";
   static final String BD = "PF";
   static final String USER = "root";
   static final String PASSWD = "pulzze";

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
                     "insert into SIMULACION (nombreUsuario, fechaSalida, fechaLlegada, nbPersonas, pais)" +
                     "values (?, ?, ?, ?, ?);"
                  statement = conn.prepareStatement( query );

                  System.out.println( "\nNombre?" );
                  statement.setString( 1, in.readLine() );

                  System.out.println( "Fecha de Salida (año-mes-dia)?" );
                  String fechaSalida = in.readLine();
                  statement.setString( 2, fechaSalida );

                  System.out.println( "Fecha de Llegada (año-mes-dia)?" );
                  statement.setString( 3, in.readLine() );

                  System.out.println( "Numero de Personas?" );	//hacer que no sea
                  statement.setInt( 4, Integer.parseInt( in.readLine() ) );

                  System.out.println( "Pais?" );
                  String pais = in.readLine();
                  statement.setString( 5, pais );

                  query( statement );

                  System.out.println( "(1) Agregar Lugar\n" ); //pais, ciudad
                  System.out.println( "(2) Agregar Hotel\n" );
                  System.out.println( "(3) Agregar Circuito\n" );
                  System.out.println( "(4) Guardar\n" );
      
                  int option = Integer.parseInt("0" + in.readLine();
                  while
                  switch(option != 4) ) {
                     case 1:
                        // TODO Recuperar y Guardar Lugar a Simulacion con Tabla
                        // QUIEREVISITAR usando max(idSimulacion)
                        break;
                     case 2:
                        // TODO Recuperar y Guardar Hotel a Simulacion con Tabla
                        // QUIEREDORMIR usando max(idSimulacion)
                        break;
                     case 3:
                        // TODO Recuperar y Guardar Circuito a Simulacion con Tabla
                        // QUIEREPARTICIPAR usando max(idSimulacion)
                        break;
                  }

                  // TODO Regresar numero de simulacion usando max(idSimulacion)
                  // TODO Calcular, agregar y mostrar costo total
                  break;

               case 2:
                  System.out.println( "Numero de simulacion o nombre de usuario a consultar\n" );
                  String x;
                  int id = 0;
                  x = in.readLine();
                  try {
                     id = Integer.readLine(x)
                  } catch (NumerFormatException e) {}

                  query = "select * from SIMULACION where nombreUsuario = ? or identificadorSimulacion = ?;";
                  statement = conn.prepareStatement( query );
                  statement.setString(1, x);
                  statement.setInt(2, id);
                  query(statement);
                  break;

               case 3:
                  System.out.println( "Numero de simulacion o nombre de usuario a consultar\n" );
                  String x;
                  int id = 0;
                  x = in.readLine();
                  try {
                     id = Integer.readLine(x)
                  } catch (NumerFormatException e) {}

                  System.out.println( "(1) nombre\n" ); //pais, ciudad
                  System.out.println( "(2) fechaSalida\n" );
                  System.out.println( "(3) fecha de llegada\n" );
                  System.out.println( "(4) numero personas\n" );
                  System.out.println( "(5) nombre ciudad\n" );
                  System.out.println( "(6) lugar\n" );
                  System.out.println( "(7) hotel\n" );
                  System.out.println( "(8) circuito\n" );
                  switch(Integer.parseInt("0" + in.readLine()) ) {
                     case 1:
                        System.out.println( "Modificar nombre\n" );
                        query = "update SIMULACION set nombreUsiario = ? where nombreUsuario = ? or identificadorSimulacion = ?;";
                        statement = conn.prepareStatement( query );
                        statement.setString(1, in.readLine());
                        statement.setString(2, x);
                        statement.setInt(3, id);
                        query(statement);
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

               case 4:
                  System.out.println( "Numero de simulacion o nombre a eliminar\n" ); //pais, ciudad
                  String r;
                  r = "'" + in.readLine() + "'";
                  statement = ( "delete from SIMULACION where nombre= " + r + " or identificadorSimulacion= " + r);
                  stmt.executeUpdate( statement );
                  System.out.println( "Eliminacion exitosa\n" );
                  break;
            }
            break;

         case 3:	//query( "select * from HORARIO" );
            System.out.println( "(1) Crear Reservacion con Simulacion\n" ); //pais, ciudad
            System.out.println( "(2) Crear Reservacion nueva\n" );
            System.out.println( "(3) Consultar Reservacion\n" );
            System.out.println( "(4) Modificar Reservacion\n" );
            System.out.println( "(5) Eliminar Reservacion\n" );

            switch(Integer.parseInt("0" + in.readLine()) ) {
               case 1:
                  //numero y nombre Simulacion
                  //validamos datos y se hace Simulacion

                  //regresa numero reservacion
                  break;

               case 2:
                  //nombre
                  //fechaSalida
                  //fecha de llegada
                  //numero personas
                  //pais
                  //nombre ciudad
                  //lugar
                  //Hotel
                  //circuito

                  //regresar costo (desayuno, habitacion y circuito)
                  //regresa numero simulacion
                  break;

               case 3:
                  //numero de reservacion y Nombre

                  //regresas reservacion
                  break;

               case 4:
                  System.out.println( "(1) nombre\n" ); //pais, ciudad
                  System.out.println( "(2) fechaSalida\n" );
                  System.out.println( "(3) fecha de llegada\n" );
                  System.out.println( "(4) numero personas\n" );
                  System.out.println( "(5) nombre ciudad\n" );
                  System.out.println( "(6) lugar\n" );
                  System.out.println( "(7) hotel\n" );
                  System.out.println( "(8) circuito\n" );
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
                  //numero reservacion o
                  //nombre

                  //regresar print eliminacion exitosa
                  break;
            }
            break;


         case 4:	conn.commit();      // fin de la transacción e inicio de la siguiente
                  break;

         case 5:	conn.rollback();    // fin de la transacción e inicio de la siguiente
                  break;

         case 6:	System.out.println();

                  System.out.println( conn.TRANSACTION_NONE + " = TRANSACTION_NONE" );
                  System.out.println( conn.TRANSACTION_READ_UNCOMMITTED + " = TRANSACTION_READ_UNCOMMITTED" );
                  System.out.println( conn.TRANSACTION_READ_COMMITTED + " = TRANSACTION_READ_COMMITTED" );
                  System.out.println( conn.TRANSACTION_REPEATABLE_READ + " = TRANSACTION_REPEATABLE_READ" );
                  System.out.println( conn.TRANSACTION_SERIALIZABLE + " = TRANSACTION_SERIALIZABLE\n\n" );

                  System.out.println( "Nivel?" );
                  conn.setTransactionIsolation( Integer.parseInt( in.readLine() ) );
                  break;

         case 7:	return false;
      }
      return true;
   }

   public static void main( String arg[] ) throws SQLException, Exception {

      if( arg.length != 0 ) {

         System.err.println( "Use: java TransactionMySQL" );
         System.exit( 1 );
      }

      TransactionMySQL transaction = new TransactionMySQL();

      while( true )

         try {
            if( ! transaction.menu() )
               break;

         } catch( Exception e ) {

            System.err.println( "failed" );
            e.printStackTrace( System.err );
         }

      transaction.close();
   }
}
