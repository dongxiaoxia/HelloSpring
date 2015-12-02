package xyz.dongxiaoxia.hellospring.remoting_and_webservices.RMI;

/**
 * Created by Administrator on 2015/12/2.
 */
public class SimpleObject {
    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    // additional methods using the accountService
    private void messsage() {
        accountService.getAccounts("name");
    }
}
