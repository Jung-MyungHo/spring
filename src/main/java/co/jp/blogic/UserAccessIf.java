package co.jp.blogic;

import co.jp.model.PromenadeForm;

public interface UserAccessIf {
  
  /**
   * FormBeanの継承
   * コントローラで取得したFormBeanを継承する
   * @param form リクエストパラメータFormBean
   */
  void setForm(PromenadeForm form);
  
  /**
   * リクエストされたdbcommandTypeにより
   * DB処理タイプを振り分ける
   */
  void dbProc() throws Exception;
}
