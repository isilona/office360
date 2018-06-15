package io.office360.restapi.persistence.dao;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import io.office360.common.interfaces.IByNameApi;
import io.office360.restapi.persistence.model.Patient;
import io.office360.restapi.persistence.model.QPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public interface IPatientJpaDao extends
        JpaRepository<Patient, Long>,
        QuerydslPredicateExecutor<Patient>,
        QuerydslBinderCustomizer<QPatient>,
        IByNameApi<Patient> {
    @Override
    default void customize(final QuerydslBindings bindings, final QPatient root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::eq);
    }
    //
}