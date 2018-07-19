package io.office360.auth.web.controller.data.mapping;

import io.office360.auth.persistence.entity.Account;
import io.office360.auth.web.controller.data.response.AccountDto;
import io.office360.common.web.controller.data.mapping.IMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper extends IMapper<AccountDto, Account> {

    AccountDto entityToDto(Account entity);

    Account dtoToEntity(AccountDto dto);

}
