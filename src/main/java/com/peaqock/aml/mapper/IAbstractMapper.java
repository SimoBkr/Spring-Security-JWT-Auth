package com.peaqock.aml.mapper;

import java.util.List;

/**
 * The Interface IAbstractMapper.
 *
 * @param <E> the element type
 * @param <D> the generic type
 */
public interface IAbstractMapper<E, D> {

    /**
     * To entity.
     *
     * @param dto the dto
     * @return the e
     */
    E toEntity(D dto);

    /**
     * To dto.
     *
     * @param entity the entity
     * @return the d
     */
    D toDto(E entity);

    /**
     * To entity list.
     *
     * @param dtos the dtos
     * @return the list
     */
    List<E> toEntityList(List<D> dtos);

    /**
     * To DTO list.
     *
     * @param entities the entities
     * @return the list
     */
    List<D> toDTOList(List<E> entities);

    /**
     * To entity iterable.
     *
     * @param dtos the dtos
     * @return the iterable
     */
    Iterable<E> toEntityIterable(Iterable<D> dtos);

    /**
     * To DTO iterable.
     *
     * @param entities the entities
     * @return the iterable
     */
    Iterable<D> toDTOIterable(Iterable<E> entities);
}
