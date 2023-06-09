package finki.emt.BookRent.service.impl;

import finki.emt.BookRent.model.Author;
import finki.emt.BookRent.model.Country;
import finki.emt.BookRent.model.dto.AuthorDto;
import finki.emt.BookRent.model.exceptions.AuthorNotFoundException;
import finki.emt.BookRent.model.exceptions.CountryNotFoundException;
import finki.emt.BookRent.repository.AuthorRepository;
import finki.emt.BookRent.repository.CountryRepository;
import finki.emt.BookRent.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {

        Country country = this.countryRepository.findById(authorDto.getCountryId())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountryId()));

        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);
        this.authorRepository.save(author);
        return Optional.of(author);

    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {

        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());

        Country country = this.countryRepository.findById(authorDto.getCountryId())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountryId()));
        author.setCountry(country);

        this.authorRepository.save(author);
        return Optional.of(author);

    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
