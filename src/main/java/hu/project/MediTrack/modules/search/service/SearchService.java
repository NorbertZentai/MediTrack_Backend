package hu.project.MediTrack.modules.search.service;

import hu.project.MediTrack.modules.search.entity.Search;
import hu.project.MediTrack.modules.search.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;

    public List<Search> findAll() {
        return searchRepository.findAll();
    }

    public Optional<Search> findById(Integer id) {
        return searchRepository.findById(id);
    }

    public Search saveSearch(Search search) {
        // Itt végezhetsz pl. validálást, logolást, stb.
        return searchRepository.save(search);
    }

    public void deleteById(Integer id) {
        searchRepository.deleteById(id);
    }

    /**
     * Példa: Egy user összes keresése.
     */
    public List<Search> findByUserId(Integer userId) {
        return searchRepository.findByUserId(userId);
    }

    /**
     * Példa: Kulcsszó részlet alapján keresés.
     */
    public List<Search> findByKeyword(String keywordPart) {
        return searchRepository.findByKeywordContainingIgnoreCase(keywordPart);
    }
}
