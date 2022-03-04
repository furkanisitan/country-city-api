package com.furkanisitan.countrycityapi.business;

import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.core.exceptions.UniqueConstraintException;
import com.furkanisitan.countrycityapi.model.requests.LanguageCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.LanguageUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.LanguageResponse;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface LanguageService {

    /**
     * Returns all languages by mapping them to {@link LanguageResponse}.
     *
     * @return all languages by mapping them to {@link LanguageResponse}.
     */
    List<LanguageResponse> findAll();

    /**
     * Returns a {@link LanguageResponse} by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @return a {@link LanguageResponse} by {@literal id}.
     * @implSpec return {@code null} if entity not exists by {@literal id}.
     */
    @Nullable
    LanguageResponse findById(Long id);

    /**
     * Creates a new language.
     *
     * @param request the dto object required to create a new language.
     * @return the added language by mapping it to {@link LanguageResponse}.
     * @throws UniqueConstraintException if language code is not unique.
     */
    LanguageResponse create(@Valid LanguageCreateRequest request);

    /**
     * Updates the language.
     *
     * @param request the dto object required to update the language.
     * @throws RecordNotFoundException   if language is not exists.
     * @throws UniqueConstraintException if language code is not unique.
     */
    void update(@Valid LanguageUpdateRequest request);

    /**
     * Deletes language by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @throws RecordNotFoundException if language is not exists by {@literal id}.
     */
    void deleteById(Long id);

}
