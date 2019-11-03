package pl.coderslab.service;

import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.entity.UserGroup;

public class UserGroupService {

    private final UserGroupDao userGroupDao;


    public UserGroupService(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }


    public UserGroup create(UserGroup userGroup){
        return userGroupDao.create(userGroup);
    }

    public UserGroup readById(Integer id){
        return userGroupDao.read(id);
    }

    public void update(UserGroup userGroup){
        userGroupDao.update(userGroup);
    }

    public void delete(Integer id){
        userGroupDao.delete(id);
    }

    public UserGroup[] findAll(){
        return userGroupDao.findAll();
    }

}
