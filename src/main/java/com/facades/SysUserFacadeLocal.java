package com.facades;

import com.models.SysUser;

public interface SysUserFacadeLocal {

    void create(SysUser user);

    void edit(SysUser user);

    void remove(SysUser user);

}
