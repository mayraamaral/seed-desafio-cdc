package dev.mayra.seeddesafiocdc.utils.validators;

import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.purchase.PurchaseRequestDTO;
import dev.mayra.seeddesafiocdc.repositories.CountryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class ValidStatesIfCountryHasStatesValidator implements ConstraintValidator<ValidStateIfCountryHasStates, PurchaseRequestDTO> {

    private final CountryRepository countryRepository;

    public ValidStatesIfCountryHasStatesValidator(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean isValid(PurchaseRequestDTO purchaseRequestDTO, ConstraintValidatorContext context) {
        Optional<Country> possibleCountry = countryRepository.findById(purchaseRequestDTO.getCountryId());

        if (possibleCountry.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Country not found")
                .addPropertyNode("countryId")
                .addConstraintViolation();
            return false;
        }

        Country country = possibleCountry.get();

        if (country.hasStates() && purchaseRequestDTO.getStateId() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("State must be selected if the country has states")
                .addPropertyNode("stateId")
                .addConstraintViolation();
            return false;
        }

        return true;
    }
}