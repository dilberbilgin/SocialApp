package com.project.SocialApp.general;

import com.project.SocialApp.dto.request.UserUpdateRequest;
import com.project.SocialApp.dto.response.UserResponseDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
public abstract class BaseService <E extends BaseEntity, R extends JpaRepository<E, Long>>{

    protected final R repository;
    protected BaseService(R repository) {
        this.repository = repository;
    }

    public E save(E entity) {
        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
        }

        LocalDateTime now = LocalDateTime.now();

        if (entity.getId() == null) {
            baseAdditionalFields.setCreatedDate(now);
        }

        baseAdditionalFields.setUpdatedDate(now);
        entity.setBaseAdditionalFields(baseAdditionalFields);

        return repository.save(entity);
    }

    public List<E> findAll() {
        return repository.findAll();
    }

//    public E findByIdWithControl(Long id) {
//        Optional<E> isObjectExist = repository.findById(id);
//
//        if (isObjectExist.isPresent()) {
//            return isObjectExist.get();
//        } else {
//            throw new BusinessException(GeneralErrorMessage.ITEM_NOT_FOUND.getMessage());
//        }
//    }

// daha spesifik bir hata firlatma

    public E findByIdWithControl(Long id) {
        Optional<E> optionalE = repository.findById(id);
        E entity;
        if (optionalE.isPresent()) {
            entity = optionalE.get();
        } else {
            // throw new ItemNotFoundException(GeneralErrorMessage.ITEM_NOT_FOUND);
            throw new RuntimeException("Item not found");
        }
        return entity;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }





//    T update(T entity);
//
//    void delete(T entity);
//
//    T findById(ID id);
//
//    List<T> findAll();
}
