package org.exampleProjectOne.factory;

import org.exampleProjectOne.dao.UserDao;
import org.exampleProjectOne.service.Service;

public interface Factory {
    UserDao getDao();
}
