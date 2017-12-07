package simplequery;

public interface SimpleQuery {
  
   public void connect(String url, String user, String passwd, String driver);
   public void runQuery(String statement);
}