package co.jp.blogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.jp.mapper.UserMapper;
import co.jp.model.PromenadeForm;
import co.jp.model.dto.User;

@Scope("prototype")
@Service
public class UserAccess implements UserAccessIf {
  
  @Autowired
  private UserMapper usrMapper; //MapperインターフェースをDIする
  
  //リクエストパラメータFormBean(コントローラから引き継ぎ)
  private PromenadeForm formPromenade;
  
  /**
   * FormBeanの継承
   * コントローラでからリクエストパラメータFormBeanを継承する
   * @param form リクエストパラメータFormBean
   */
  public void setForm(PromenadeForm form) {
    this.formPromenade = form;
  }
  
  /**
   * リクエストされたdbcommandTypeにより
   * DB処理タイプを振り分ける
   */
  public void dbProc() throws Exception {
    //DB処理タイプを取得する
    int dbcommandType = this.formPromenade.getDbcommandType();
    //viewから渡されたDTOを取得する
    User user = this.formPromenade.getUser();
    //好きなものラジオボタン選択値を取得する
    String[] checkVals = this.formPromenade.getCheckVals();
    //好きなものが一個選択されているので、ビジネスモデルに継承する
    if (checkVals != null && checkVals.length > 0)
      user.setCheckVal(checkVals[0]);
    
    switch (dbcommandType) {
    case 1: //User情報検索
      this.getUser();
      break;
    case 2: //User情報新規追加
      this.insertUser();
      break;
    case 3: //User情報更新
      this.updateUser();
      break;
    case 4: //User情報削除
      this.deleteUser();
      break;
    default: //エラー
      new Exception("dbcommandTypeが不正です");
    }
  }
  
  /**
   * User情報を取得する
   * @exception useridが未指定の場合は、exceptionをスローする
   */
  @Transactional(readOnly = true) //DBトランザクション状態設定(read only)
  public void getUser() throws Exception {
    //viewから指定されたuserIDを取得する
    int userid = this.formPromenade.getUser().getUserid();
    if (userid == 0) {
      //useridが未指定の場合はExceptionをスローする
      new Exception("useridが未指定です");
    }
    
    //User情報をuseridでDBを検索する
    //(Mapperインターフェースメソッドを呼び出す)
    User user = usrMapper.getUser(userid);
    if (user == null)
      return; //検索結果がない
    //検索結果をFormBeanにセット
    this.formPromenade.setUser(user);
    
    //好きなもの
    String checkVal = user.getCheckVal();
    //好きなもの　結果表示用
    this.formPromenade.setFavorite(checkVal);
    //好きなものラジオボタン復元
    String checkVals[] = new String[1];
    checkVals[0] = checkVal;
    this.formPromenade.setCheckVals(checkVals);
  }
  
  /**
   * User情報を新規追加する
   */
  @Transactional(rollbackFor = Exception.class) //DBトランザクション状態設定(read/write)
  public void insertUser() throws Exception {
    //viewから渡されたDTOを取得する
    User user = this.formPromenade.getUser();
    
    //User情報をDBに新規追加する
    //(Mapperインターフェースメソッドを呼び出す)
    usrMapper.insertNewUser(user);
  }
  
  /**
   * User情報を更新する
   * @exception useridが未指定の場合は、exceptionをスローする
   */
  @Transactional(rollbackFor = Exception.class) //DBトランザクション状態設定(read/write)
  public void updateUser() throws Exception {
    //viewから指定されたuserIDを取得する
    User user = this.formPromenade.getUser();
    if (user.getUserid() == 0) {
      //useridが未指定の場合はExceptionをスローする
      new Exception("useridが未指定です");
    }
    
    //useridで決定されるUser情報でDBを更新する
    //(Mapperインターフェースメソッドを呼び出す)
    usrMapper.updateUser(user);
  }
  
  /**
   * User情報を削除する
   * @exception useridが未指定の場合は、exceptionをスローする
   */
  @Transactional(rollbackFor = Exception.class) //DBトランザクション状態設定(read/write)
  public void deleteUser() throws Exception {
    //viewから指定されたuserIDを取得する
    int userid = this.formPromenade.getUser().getUserid();
    if (userid == 0) {
      //useridが未指定の場合はExceptionをスローする
      new Exception("useridが未指定です");
    }
    
    //useridで決定されるUser情報をDBから削除する
    //(Mapperインターフェースメソッドを呼び出す)
    usrMapper.deleteUser(userid);
  }
}
