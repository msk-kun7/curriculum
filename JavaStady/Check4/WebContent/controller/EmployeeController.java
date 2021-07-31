package controller;
 
/**
 * 社員情報管理コントローラー  
 */
 
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.EmployeeBean;
import service.EmployeeService;
 
public class EmployeeController extends HttpServlet {
 public void doPost(HttpServletRequest request, HttpServletResponse response) //ServletRequest:すべての既知の実装クラス
 throws ServletException, IOException {
//doPost:postからアクセスがあったときサーバーとの情報と照合するためにpostを使う。
//HttpServlet：クライアントからサーブレットに送信される情報、request:要求、response:応答
 //throw:例外を発生させる。
 try {
  // 問① index.htmlから送信されたIDとPassWordの値を取得できるように修正しましょう。
 String id = request.getParameter("id");
 String password = request.getParameter("password");
 //getParameter:データを取得するもの
 /*
 * IDとPassWordと元に、社員情報を検索する関数の呼び出し、結果をJSPに渡す処理
 * ※ EmployeeBeanとEmployeeServiceをimportするのを忘れないでください。
 * get:データベースを経由しない。post:DBの中にデータを入れる
 */
 
  // 問② EmployeeServiceクラスをインスタンス化する。
 EmployeeService p1 = new EmployeeService();
  // 問③ EmployeeBeanに、EmployeeServiceよvりsearch関数を呼び出し、返り値を格納する。
  EmployeeBean ed = p1.search(id,password);
  // 問④ nullの部分に適切な引数をセットする。
 request.setAttribute("EmployeeBean", ed);
 //検索が終わったものをjspに送る。(requestを経由しないとjspに送ることはできない)
 //setAttribute(,):指定の要素に新しい属性を追加
 
 } catch (Exception e) {
 e.printStackTrace();
 } finally {
 ServletContext context = this.getServletContext();
 RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");//ServletからJSPを表示するためのインターフェイス
 dispatcher.forward(request, response);//RequestDispatcherインターフェースを使うことで使用可能になるメソッド
 }//forwardを使うことで指定のJSPへ飛ばすことができる
 //"forward"メソッドの引数も、呼び出し元のサーブレットの"doGet"メソッドや"doPost"メソッドが呼び出された時に引数に指定されている値をそのまま渡す。
 }
}