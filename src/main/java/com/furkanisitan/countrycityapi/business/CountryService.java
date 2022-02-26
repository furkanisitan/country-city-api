package com.furkanisitan.countrycityapi.business;

import com.furkanisitan.countrycityapi.model.entities.Country;
import org.springframework.lang.Nullable;

public interface CountryService {

    /**
     * Returns a proxy object of {@link Country} by {@literal id}.
     *
     * @param code the unique code of the {@link Country}.
     * @return a proxy object of {@link Country} by {@literal id}.
     * @implSpec return {@code null} if entity not exists by {@literal id}.
     */
    @Nullable
    Country getByCode(String code);

}
