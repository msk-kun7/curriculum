package study;

// ① TaskクラスにCalculatorクラスを継承させなさい。
public class Task extends Calculator{
	
	private int a = 10;
	private int b = 20;
	private int c = 30;
	
	
    /**
     * タスクの実行
     */
    public void doTask() {

        // ② Calculator.javaのすべてのオーバーロードメソッド「plus」に適当な引数を与え、下記画像のよう出力されるようコーディングしなさい。
        // 尚、「どのクラスから呼び出しているか」を明確にするため、plus()には呼び出し元のキーワードを付与すること。
    	System.out.println("plusメソッドの引数が一つの場合:"+plus(a));
    	System.out.println("plusメソッドの引数が二つの場合:"+plus(a,b));
    	System.out.println("plusメソッドの引数が三つの場合:"+plus(a,b,c));
    	
    }
}