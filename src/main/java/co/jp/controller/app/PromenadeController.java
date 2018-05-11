package co.jp.controller.app;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.jp.blogic.UserAccessIf;
import co.jp.common.MyLog;
import co.jp.model.PromenadeForm;
import co.jp.model.dto.User;

@Controller
public class PromenadeController {
  
  @Autowired
  private UserAccessIf userAccessIf;
  
  @RequestMapping(value = "/promenaderes", method = RequestMethod.POST)
  public String promenaderes(@Valid @ModelAttribute("PromenadeFormData") PromenadeForm formbean, BindingResult result)
      throws Exception {
    
    if (result.hasErrors()) {
      return "promenade";
    }
    
    formbean.getUser().setCheckVal(formbean.getCheckVals()[0]);
    formbean.setDbcommandType(2);
    
    this.userAccessIf.setForm(formbean);
    this.userAccessIf.dbProc();
    
    return "promenaderes";
  }
  
  @RequestMapping(value = "/promenade", method = RequestMethod.GET)
  public String promenade(@ModelAttribute("PromenadeFormData") PromenadeForm formbean,
      @RequestParam(name = "key", required = false) String key) throws Exception {
    
    if (key == null) {
      return "promenade";
    }
    MyLog log = MyLog.getInstance();
    log.log_info("Key===={}");
    log.log_dbg("debug");
    User user = new User();
    user.setUserid(Integer.parseInt(key));
    formbean.setUser(user);
    formbean.setDbcommandType(1);
    this.userAccessIf.setForm(formbean);
    this.userAccessIf.dbProc();
    
    return "promenade";
  }
}
