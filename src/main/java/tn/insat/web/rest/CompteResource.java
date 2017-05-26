package tn.insat.web.rest;

import com.codahale.metrics.annotation.Timed;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import tn.insat.domain.Compte;

import tn.insat.domain.CompteMin;
import tn.insat.repository.CompteRepository;
import tn.insat.web.rest.util.HeaderUtil;
import tn.insat.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZoneId;
import java.util.*;
import java.sql.Date;

/**
 * REST controller for managing Compte.
 */
@RestController
@RequestMapping("/api")
public class CompteResource {

    private final Logger log = LoggerFactory.getLogger(CompteResource.class);

    private static final String ENTITY_NAME = "compte";

    private final CompteRepository compteRepository;

    public CompteResource(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    /**
     * POST  /comptes : Create a new compte.
     *
     * @param compte the compte to create
     * @return the ResponseEntity with status 201 (Created) and with body the new compte, or with status 400 (Bad Request) if the compte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/comptes")
    @Timed
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) throws URISyntaxException {
        log.debug("REST request to save Compte : {}", compte);
        if (compte.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new compte cannot already have an ID")).body(null);
        }
        Compte result = compteRepository.save(compte);
        return ResponseEntity.created(new URI("/api/comptes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /comptes : Updates an existing compte.
     *
     * @param compte the compte to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated compte,
     * or with status 400 (Bad Request) if the compte is not valid,
     * or with status 500 (Internal Server Error) if the compte couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/comptes")
    @Timed
    public ResponseEntity<Compte> updateCompte(@RequestBody Compte compte) throws URISyntaxException {
        log.debug("REST request to update Compte : {}", compte);
        if (compte.getId() == null) {
            return createCompte(compte);
        }
        Compte result = compteRepository.save(compte);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, compte.getId().toString()))
            .body(result);
    }

    /**
     * GET  /comptes : get all the comptes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of comptes in body
     */


    @GetMapping("/comptes")
    @Timed
    public ResponseEntity<List<Compte>> getAllComptes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Comptes");
        Page<Compte> page = compteRepository.findAll(pageable);
        HashMap params = new HashMap();
        List<Compte> c=compteRepository.findAll();
        List<CompteMin> cm = new ArrayList<>();
        CompteMin compteMin;
        for (Compte o: c){
            compteMin = new CompteMin();
            compteMin.setId(o.getId());
            Date date = Date.valueOf(o.getCreationDate());
            compteMin.setCreationDate(date);
            compteMin.setLogin(o.getUser_account().getLogin());
            compteMin.setEmail(o.getUser_account().getEmail());
            compteMin.setSolde(o.getSolde());
            cm.add(compteMin);
        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("account2.jrxml");
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(cm);
            JasperPrint jprint = JasperFillManager.fillReport(jasperReport, params,ds) ;
            JasperExportManager.exportReportToPdfFile(jprint,"reports.pdf");

        } catch (JRException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comptes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /comptes/:id : get the "id" compte.
     *
     * @param id the id of the compte to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the compte, or with status 404 (Not Found)
     */
    @GetMapping("/comptes/{id}")
    @Timed
    public ResponseEntity<Compte> getCompte(@PathVariable Long id) {
        log.debug("REST request to get Compte : {}", id);
        Compte compte = compteRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(compte));
    }

    /**
     * DELETE  /comptes/:id : delete the "id" compte.
     *
     * @param id the id of the compte to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/comptes/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        log.debug("REST request to delete Compte : {}", id);
        compteRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
