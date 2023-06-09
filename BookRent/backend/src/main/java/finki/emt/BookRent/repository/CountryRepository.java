package finki.emt.BookRent.repository;

import finki.emt.BookRent.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository
        extends JpaRepository<Country, Long> {

    void deleteByName(String name);

}
