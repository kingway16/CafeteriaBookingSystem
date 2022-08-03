package com.facades;


import com.models.SysOrder;

public interface SysOrderFacadeLocal {

    void create(SysOrder order);

    void edit(SysOrder order);

    void remove(SysOrder order);
}
