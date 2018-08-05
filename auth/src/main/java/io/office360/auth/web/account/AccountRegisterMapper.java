package io.office360.auth.web.account;

import io.office360.auth.persistence.entity.Account;
import io.office360.common.web.controller.data.mapping.IMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountRegisterMapper extends IMapper<AccountRegisterDto, Account> {

    AccountRegisterDto entityToDto(Account entity);

    Account dtoToEntity(AccountRegisterDto dto);

}
