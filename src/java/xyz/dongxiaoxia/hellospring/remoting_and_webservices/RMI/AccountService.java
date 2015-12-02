package xyz.dongxiaoxia.hellospring.remoting_and_webservices.RMI;

import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public interface AccountService {
    void insertAccount(Account account);

    List<Account> getAccounts(String name);
}
