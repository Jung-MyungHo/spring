package co.jp.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLog {
  
  /**
  * 唯一のMyLog4Jインスタンス
  */
  private static MyLog thisinstance = null;
  
  public synchronized static MyLog getInstance() {
    //インスタンスが無ければ作成する
    if (MyLog.thisinstance == null) {
      MyLog.thisinstance = new MyLog();
    }
    return MyLog.thisinstance;
  }
  
  /**
  * コンストラクタ
  * Singleton実装の為、コンストラクタは隠蔽します
  */
  private MyLog() {
  }
  
  private String getMessage(String msg) {
    //自分のクラスを取得(MyLog4J)
    Class<?> c = this.getClass();
    //クラス名を取得
    String thisClassName = c.getName();
    //カレントスレッドを取得
    Thread t = Thread.currentThread();
    //StackTraceElementの配列を取得
    StackTraceElement[] stackTraceElements = t.getStackTrace();
    int pos = 0;
    for (StackTraceElement stackTraceElement : stackTraceElements) {
      //クラス名比較
      if (thisClassName.equals(stackTraceElement.getClassName())) {
        break; //stackTraceElementsから自分と同じクラス名だったら終了
      }
      pos++;
    }
    pos += 2; //出力したいクラス名/メソッド名は自分(MyLog4J)の2個次の位置にいる
    StackTraceElement m = stackTraceElements[pos];
    //ログ出力対象のクラス名:[メソッド名] + log message
    String log_str = m.getClassName() + ":" + m.getMethodName() + "() " + msg;
    return log_str;
  }
  
  /**
  * MyLog4Jでデバッグレベルの情報をロギングする
  * @param obj : ログが出力される Class Object
  * @param msg : デバッグメッセージ
  */
  public void log_dbg(String msg) {
    Logger logger = LogManager.getLogger(this.getClass());
    logger.debug("{}", this.getMessage(msg));
  }
  
  /**
  * MyLog4Jでinfoレベルの情報をロギングする
  * @param msg : 出力メッセージ
  */
  public void log_info(String msg) {
    Logger logger = LogManager.getLogger(this.getClass());
    logger.info("{}", this.getMessage(msg));
  }
  
  /**
  * MyLog4Jでinfoレベルの情報をロギングする
  * @param obj : ログが出力される Class Object
  * @param msg : 出力メッセージ
  */
  public void log_info(Object obj, String msg) {
    Logger logger = LogManager.getLogger(obj.getClass());
    logger.info("{}", msg);
  }
  
  /**
  * MyLog4Jで警告レベルの情報をロギングする
  * @param obj : ログが出力される Class Object
  * @param msg : 警告メッセージ
  */
  public void log_warn(String msg) {
    Logger logger = LogManager.getLogger(this.getClass());
    logger.warn("{}", this.getMessage(msg));
  }
  
  /**
  * MyLog4Jでエラーレベル情報ををロギングする
  * @param e    : 例外情報
  */
  public void log_error(Exception e) {
    String msg = e.getMessage();
    Class<? extends Object> clss = e.getClass(); //Exceptionのクラス
    String clsname = e.getClass().getName(); //Exceptionのクラス名
    StackTraceElement[] st = e.getStackTrace();
    String log_msg = "";
    if (st != null && st.length > 0) {
      log_msg += "Class:" + clsname + "¥n";
      log_msg += "Detail:" + msg + "¥n";
      for (int i = 0; i < st.length; i++) {
        String err = st[i].toString();
        log_msg += err + "¥n";
      }
      Logger logger = LogManager.getLogger(clss);
      logger.error("{}", log_msg);
    }
  }
}
