package io.office360.auth.service;

import io.office360.auth.web.account.AccountDto;
import io.office360.common.persistence.service.IOperationsService;

public interface IAccountService extends IOperationsService {

    AccountDto findByUsername(String username);

}
