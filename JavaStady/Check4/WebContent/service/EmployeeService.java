package service;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;//何回もSQL文を生成しなくても済むように改良されたクラス
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;//SQLの実行
import java.text.SimpleDateFormat;
import java.util.Calendar;

import bean.EmployeeBean;


 
/* ・社員情報検索サービス
 */
 
public class EmployeeService {
 
  // 問① 接続情報を記述してください
 /** ドライバーのクラス名 */
 private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
 /** ・JDMC接続先情報 */
//jdbc:mysql://(サーバ名)/(データベース名)　データベースへの接続
 private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/Employee";
 /** ・ユーザー名 */
 private static final String USER = "postgres";
 /** ・パスワード */
 private static final String PASS = "masaki";
 /** ・タイムフォーマット */
 private static final String TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
 
  // 問② 入力された値で、UPDATEする文
 /** ・SQL UPDATE文 */
 private static final String SQL_UPDATE = "UPDATE Employee_table SET login_time = ? WHERE id = ?";
 
  // 問③ 入力されたIDとPassWordをキーにして、検索するSELECT文
 /** ・SQL SELECT文 */
 private static final String SQL_SELECT = "SELECT * FROM Employee_table WHERE id = ? AND password = ?";
 
 EmployeeBean employeeDate = null;
 
  // 送信されたIDとPassWordを元に社員情報を検索するためのメソッド
 public EmployeeBean search(String id, String password) {
 
 Connection connection = null;
 Statement statement = null;
 ResultSet resultSet = null;
 PreparedStatement preparedStatement = null;
 
 try {	
  // データベースに接続
 Class.forName(POSTGRES_DRIVER);
 connection = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS); //Connection オブジェクトは、データベースとの接続
 statement = connection.createStatement();//SQL文をデータベースに接続する
 
  // 処理が流れた時間をフォーマットに合わせて生成
 Calendar cal = Calendar.getInstance();
 SimpleDateFormat sdFormat = new SimpleDateFormat(TIME_FORMAT);
 
  // PreparedStatementで使用するため、String型に変換
 String login_time = sdFormat.format(cal.getTime());
 
 /*
 * 任意のユーザーのログインタイムを更新できるように、プリペアドステートメントを記述。
 */
 
  // preparedStatementに実行したいSQLを格納
 preparedStatement = connection.prepareStatement(SQL_UPDATE);
  // 問④ preparedStatementを使って、一番目のindexに今の時間をセットしてください。2番目のindexにIDをセットしてください。
 preparedStatement.setString(1,login_time);//１つ目の？にlogin_timeに格納する。
 preparedStatement.setString(2,id);//2つ目の？にidに格納する。
  // 問⑤ UPDATEを実行する文を記述¥
 preparedStatement.executeUpdate();//格納したデータを確定させるためのコード
 /*
 * UPDATEが成功したものを即座に表示
 * 任意のユーザーを検索できるように、プリペアドステートメントを記述。
 * 
 */
 // preparedStatementとは、更新されたデータでも入れられるもの
 preparedStatement = connection.prepareStatement(SQL_SELECT);
  //問⑥ 一番目のindexにIDをセットしてください。2番目のindexにPASSWORDをセット。
 preparedStatement.setString(1,id);//１つ目の？にidに格納する。
 preparedStatement.setString(2,password);//2つ目の？にpasswordに格納する。
  // SQLを実行。実行した結果をresultSetに格納。
 resultSet = preparedStatement.executeQuery();//そのSQLを検索を実行する。executeQuery：問い合わせ文を送信する場合に使用。resultSetに格納。
 //resultSet:データの位置を指し示す。
 
 //繰り返し処理
 //nextメソッドとは、データの１行目が空白になっているため、次のデータから処理するために用いるメソッド
 while (resultSet.next()) {
  // 問⑦ tmpName,tmpComment,tmpLoginTimeに適当な値を入れてください。
 //該当する行の任意の列の取得には、データのタイプに応じて「getXXX()」メソッドを使用
 String tmpName = resultSet.getString("name");//id.passwordで呼び出したsplの行にある'name'を呼び出しtmpNameに格納してください。
 String tmpComment = resultSet.getString("comment");//id.passwordで呼び出したsplの行にある'comment'を呼び出しtmpCommentに格納してください。
 String tmpLoginTime = resultSet.getString("login_time");//id.passwordで呼び出したsplの行にある'logintime'を呼び出しtmpLoginTimeに格納してください。
 
  // 問⑧ EmployeeBeanに取得したデータを入れてください。
 employeeDate = new EmployeeBean();
 employeeDate.setName(tmpName);
 employeeDate.setComment(tmpComment);
 employeeDate.setLogin_Time(tmpLoginTime);
 }
 
  // forName()で例外発生
 } catch (ClassNotFoundException e) {
 e.printStackTrace();
 
  // getConnection()、createStatement()、executeQuery()で例外発生
 } catch (SQLException e) {
 e.printStackTrace();
 
 } finally {
 try {
 //closeメソッドは、JavaとSQLの接続を一時的に切断
 if (resultSet != null) {
 resultSet.close();
 }
 if (statement != null) {
 statement.close();
 }
 if (connection != null) {
 connection.close();
 }
 
 } catch (SQLException e) {//SQLが見つからなかった場合
 e.printStackTrace();
 }
 //『スタックトレース』：そのエラーが発生するまでの過程
 }
 return employeeDate;
 }
}