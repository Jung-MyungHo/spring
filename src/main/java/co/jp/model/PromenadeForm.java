package co.jp.model;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;

import org.springframework.stereotype.Component;

import co.jp.model.dto.User;

@Component
public class PromenadeForm {
  
  @Valid
  private User user;
  
  /**
   * DB処理タイプ
   */
  private int dbcommandType = 0;
  
  private String favorite = "";
  
  /**
   * CheckBox選択　必須チェック
   */
  private String[] checkVals = new String[0];
  
  /**
   * checkBox の選択必須チェック
   */
  @AssertTrue(message = "どれか選ばんか～ぃ！")
  public boolean isCheckValsSelect() {
    return checkVals.length == 0 ? false : true;
  }
  
  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }
  
  public int getDbcommandType() {
    return dbcommandType;
  }
  
  public void setDbcommandType(int dbcommandType) {
    this.dbcommandType = dbcommandType;
  }
  
  public String[] getCheckVals() {
    return checkVals;
  }
  
  public void setCheckVals(String[] checkVals) {
    this.checkVals = checkVals;
  }
  
  public String getFavorite() {
    return favorite;
  }
  
  public void setFavorite(String favorite) {
    this.favorite = favorite;
  }
  
}
