package xyz.dongxiaoxia.hellospring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.UserLoginList;
import xyz.dongxiaoxia.hellospring.core.repository.UserLoginListDao;
import xyz.dongxiaoxia.hellospring.service.UserLoginListService;
import xyz.dongxiaoxia.hellospring.util.PageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
@Component
@Transactional
public class UserLoginListServiceImpl implements UserLoginListService {

    @Autowired
    private UserLoginListDao userLoginListDao;

    @Override
    public void add(UserLoginList userLoginList) {
        userLoginListDao.insert(userLoginList);
    }

    @Override
    public PageView query(PageView pageView, UserLoginList userLoginList) {
        List<UserLoginList> list = userLoginListDao.query(pageView, userLoginList);
        pageView.setRecords(list);
        return pageView;
    }


    public List<UserLoginList> list() {
        return userLoginListDao.list();
    }
}
