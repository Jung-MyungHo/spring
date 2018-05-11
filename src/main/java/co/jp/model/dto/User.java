package co.jp.model.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

@Component
public class User {
  
  private int userid = 0;
  
  /**
   * 名前
   */
  @NotBlank //未入力はダメ！
  private String myname = "";
  
  /**
   * 年齢
   */
  @Range(min = 0, max = 150, message = "最大最小は、{myForm.minmax}") //範囲チェック
  private int age = 0;
  
  /**
   * 電話番号
   */
  
  @NotBlank //未入力はダメ！
  @Pattern(regexp = "^[-0-9]*$", message = "数字と-だけょ。") //数字とハイフン
  private String phone = "";
  
  /**
   * email
   */
  @NotBlank //未入力はダメ！
  @Email //emailフォーマットチェック
  private String email = "";
  
  /**
   * CheckBox選択　必須チェック
   */
  private String checkVal = "";
  
  public String getMyname() {
    return myname;
  }
  
  public void setMyname(String myname) {
    this.myname = myname;
  }
  
  public int getAge() {
    return age;
  }
  
  public void setAge(int age) {
    this.age = age;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getCheckVal() {
    return checkVal;
  }
  
  public void setCheckVal(String checkVal) {
    this.checkVal = checkVal;
  }
  
  public int getUserid() {
    return userid;
  }
  
  public void setUserid(int userid) {
    this.userid = userid;
  }
  
}
