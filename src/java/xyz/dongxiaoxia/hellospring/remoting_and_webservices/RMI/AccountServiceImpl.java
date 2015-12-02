package xyz.dongxiaoxia.hellospring.remoting_and_webservices.RMI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public void insertAccount(Account account) {

    }

    @Override
    public List<Account> getAccounts(String name) {
        if ("123".equals(name)) {
            Account account = new Account();
            account.setName("dongxiaoxia");
            List list = new ArrayList();
            list.add(account);
            return list;
        }
        return null;
    }
}
