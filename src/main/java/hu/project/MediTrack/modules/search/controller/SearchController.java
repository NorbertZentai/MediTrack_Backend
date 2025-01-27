package hu.project.MediTrack.modules.search.controller;

import hu.project.MediTrack.modules.search.entity.Search;
import hu.project.MediTrack.modules.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller a felhasználói keresések kezelésére.
 */
@RestController
@RequestMapping("/api/searches")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * GET /api/searches
     * Az összes keresés lekérése.
     */
    @GetMapping
    public List<Search> getAllSearches() {
        return searchService.findAll();
    }

    /**
     * GET /api/searches/{id}
     * Egy konkrét keresés ID alapján.
     */
    @GetMapping("/{id}")
    public Search getSearchById(@PathVariable Integer id) {
        Optional<Search> s = searchService.findById(id);
        return s.orElse(null);
    }

    /**
     * POST /api/searches
     * Új keresés rögzítése.
     */
    @PostMapping
    public Search createSearch(@RequestBody Search search) {
        return searchService.saveSearch(search);
    }

    /**
     * PUT /api/searches/{id}
     * Létező keresés frissítése. (tipikusan nem sok értelme van, de példa kedvéért)
     */
    @PutMapping("/{id}")
    public Search updateSearch(@PathVariable Integer id, @RequestBody Search updated) {
        return searchService.findById(id).map(s -> {
            s.setKeyword(updated.getKeyword());
            s.setSearchDate(updated.getSearchDate());
            s.setUser(updated.getUser());
            return searchService.saveSearch(s);
        }).orElse(null);
    }

    /**
     * DELETE /api/searches/{id}
     * Keresés törlése ID alapján.
     */
    @DeleteMapping("/{id}")
    public void deleteSearch(@PathVariable Integer id) {
        searchService.deleteById(id);
    }
}
