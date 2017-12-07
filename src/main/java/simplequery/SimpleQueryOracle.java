package simplequery;

import java.sql.*;

public class SimpleQueryOracle {

   static final String CONN_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
   static final String USER = "system";
   static final String PASSWD = "???";          /* Modificar el PASSWORD */
   static final String PRE_STMT = "select * from emp";

   public SimpleQueryOracle() {

      try {

         // Registro del "driver"
         System.out.print( "Loading driver... " );
         DriverManager.registerDriver(
               new oracle.jdbc.driver.OracleDriver() );
         System.out.println( "loaded" );

         // Establece la connecci�n con el servidor del SGBD
         System.out.print( "Connecting to the database... " );

         Connection conn = DriverManager.getConnection( CONN_URL,
               USER,
               PASSWD);
         System.out.println( "connected" );

         // Creaci�n de la consulta
         PreparedStatement stmt = conn.prepareStatement( PRE_STMT );

         // Ejecuci�n de la consulta
         ResultSet rset = stmt.executeQuery();

         // Presentaci�n del resultado
         System.out.println( "Results:" );
         dumpResultSet( rset );
         System.out.println( "" );

         // Cerrar y liberaci�n de la memoria
         rset.close();
         stmt.close();
         conn.close();

      } catch( SQLException e ) {

         System.err.println( "failed" );
         e.printStackTrace( System.err );
      }
   }

   private void dumpResultSet(ResultSet rset) throws SQLException {

      ResultSetMetaData rsetmd = rset.getMetaData();
      int i = rsetmd.getColumnCount();

      while (rset.next()) {
         for (int j = 1; j <= i; j++) {
            System.out.print(rset.getString(j) + "\t");
         }
         System.out.println();
      }
   }
}
