package com.amazaar.LocalDatabase;

import com.amazaar.LocalDatabase.Enity.CartEntity;
import com.amazaar.LocalDatabase.Enity.ItemEntity;
import com.amazaar.LocalDatabase.Enity.LoginEntity;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

public class DaoSession extends AbstractDaoSession {
    private final DaoConfig loginEntityDaoConfig;
    private final DaoConfig itemEntityDaoConfig;
    private final DaoConfig cartEntityDaoConfig;
    private final LoginEntityDao loginEntityDao;
    private final ItemEntityDao itemEntityDao;
    private final CartEntityDao cartEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);
        loginEntityDaoConfig = daoConfigMap.get(LoginEntityDao.class).clone();
        itemEntityDaoConfig = daoConfigMap.get(ItemEntityDao.class).clone();
        cartEntityDaoConfig = daoConfigMap.get(CartEntityDao.class).clone();
        loginEntityDaoConfig.initIdentityScope(type);

        loginEntityDao = new LoginEntityDao(loginEntityDaoConfig, this);
        itemEntityDao = new ItemEntityDao(itemEntityDaoConfig, this);
        cartEntityDao = new CartEntityDao(cartEntityDaoConfig, this);
        registerDao(LoginEntity.class, loginEntityDao);
        registerDao(ItemEntity.class, itemEntityDao);
        registerDao(CartEntity.class, cartEntityDao);
    }

    public void clear() {
        loginEntityDaoConfig.clearIdentityScope();
        itemEntityDaoConfig.clearIdentityScope();
        cartEntityDaoConfig.clearIdentityScope();
    }

    public LoginEntityDao getLoginEntityDao() {
        return loginEntityDao;
    }

    public ItemEntityDao getItemEntityDao() {
        return itemEntityDao;
    }

    public CartEntityDao getCartEntityDao() {
        return cartEntityDao;
    }
}
