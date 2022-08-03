package com.facades;

import com.models.SysPayment;

public interface SysPaymentFacadeLocal {

    void create(SysPayment payment);

    void edit(SysPayment payment);

    void remove(SysPayment payment);
}
